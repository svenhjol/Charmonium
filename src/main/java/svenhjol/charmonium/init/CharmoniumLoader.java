package svenhjol.charmonium.init;

import com.google.common.reflect.ClassPath;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.tree.AnnotationNode;
import org.objectweb.asm.tree.ClassNode;
import svenhjol.charmonium.Charmonium;
import svenhjol.charmonium.annotation.Module;
import svenhjol.charmonium.helper.ConfigHelper;
import svenhjol.charmonium.helper.StringHelper;
import svenhjol.charmonium.module.CharmoniumModule;

import java.util.*;
import java.util.function.Consumer;

/**
 * Mini charm-based loader.
 *
 * At minimum we need:
 * - Config and Module annotations
 * - ConfigHelper, StringHelper
 * - CharmModule
 * - LogHandler
 */
@SuppressWarnings({"UnstableApiUsage", "unchecked"})
public class CharmoniumLoader {
    private final String MOD_ID;
    private final List<Class<? extends CharmoniumModule>> CLASSES;
    private final String MODULE_ANNOTATION = "Lsvenhjol/charmonium/annotation/Module;";

    public static final Map<String, CharmoniumModule> LOADED_MODULES = new LinkedHashMap<>();

    public CharmoniumLoader(String modId) {
        MOD_ID = modId;
        CLASSES = new ArrayList<>(); // populate this with discovered classes

        Iterable<ClassPath.ClassInfo> classes;
        String basePackage = "svenhjol." + MOD_ID + ".module";

        try {
            ClassLoader classLoader = CharmoniumLoader.class.getClassLoader();
            classes = ConfigHelper.getClassesInPackage(classLoader, basePackage);
        } catch (Exception e) {
            throw new IllegalStateException("Could not fetch module classes, giving up: " + e.getMessage());
        }


        int count = 0;

        for (ClassPath.ClassInfo c : classes) {
            String className = c.getName();
            String truncatedName = className.substring(basePackage.length() + 1);
            try {
                ClassReader classReader = new ClassReader(c.asByteSource().read());
                ClassNode node = new ClassNode();
                classReader.accept(node, ClassReader.SKIP_CODE | ClassReader.SKIP_DEBUG | ClassReader.SKIP_FRAMES);

                if (node.visibleAnnotations != null && !node.visibleAnnotations.isEmpty()) {
                    for (AnnotationNode annotation : node.visibleAnnotations) {
                        if (annotation.desc.equals(MODULE_ANNOTATION))
                            CLASSES.add((Class<? extends CharmoniumModule>) Class.forName(c.getName()));
                    }
                }

                count++;

            } catch (Exception e) {
                Charmonium.LOG.error("> Error occurred while processing module " + truncatedName + ": " + e.getMessage());
            }
        }

        if (count == 0) {
            Charmonium.LOG.warn("Seems no module classes were processed... this is probably bad.");
        }

        this.launch();
    }

    public static boolean isEnabled(String moduleName) {
        String name = StringHelper.snakeToUpperCamel(moduleName);
        return LOADED_MODULES.containsKey(name)
            && LOADED_MODULES.get(name).enabled;
    }

    protected void launch() {
        Charmonium.LOG.info("Setting up " + MOD_ID);

        register();
        init();

        Charmonium.LOG.info("Done setting up " + MOD_ID);
    }

    protected void register() {
        Map<String, CharmoniumModule> loaded = new HashMap<>();

        CLASSES.forEach(clazz -> {
            try {
                CharmoniumModule module = clazz.getDeclaredConstructor().newInstance();
                if (clazz.isAnnotationPresent(Module.class)) {
                    Module annotation = clazz.getAnnotation(Module.class);

                    module.mod = MOD_ID;
                    module.priority = annotation.priority();
                    module.alwaysEnabled = annotation.alwaysEnabled();
                    module.enabledByDefault = annotation.enabledByDefault();
                    module.enabled = module.enabledByDefault;
                    module.description = annotation.description();

                    String moduleName = module.getName();
                    loaded.put(moduleName, module);

                } else {
                    throw new RuntimeException("No module annotation for class " + clazz);
                }

            } catch (Exception e) {
                throw new RuntimeException("Could not initialize module class: " + clazz.toString(), e);
            }
        });

        // config for this module set
        ConfigHelper.createConfig(MOD_ID, loaded);

        // sort by module priority
        ArrayList<CharmoniumModule> modList = new ArrayList<>(loaded.values());
        modList.sort((mod1, mod2) -> {
            if (mod1.priority == mod2.priority) {
                // sort by name
                return mod1.getName().compareTo(mod2.getName());
            } else {
                // sort by priority
                return Integer.compare(mod2.priority, mod1.priority);
            }
        });

        for (CharmoniumModule mod : modList) {
            for (Map.Entry<String, CharmoniumModule> entry : loaded.entrySet()) {
                if (entry.getValue().equals(mod)) {
                    LOADED_MODULES.put(entry.getKey(), mod);
                    break;
                }
            }
        }

        // add and run register method for all loaded modules
        LOADED_MODULES.forEach((moduleName, module) -> module.register());
    }

    protected void init() {
        // run dependency check on each module
        eachModule(CharmoniumModule::depends);

        // init, only enabled modules are run
        eachEnabledModule(CharmoniumModule::init);
    }

    public void eachModule(Consumer<CharmoniumModule> consumer) {
        LOADED_MODULES.values().forEach(consumer);
    }

    public void eachEnabledModule(Consumer<CharmoniumModule> consumer) {
        LOADED_MODULES.values()
            .stream()
            .filter(m -> m.enabled)
            .forEach(consumer);
    }
}
