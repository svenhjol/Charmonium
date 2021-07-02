package svenhjol.charmonium.loader;

import svenhjol.charmonium.annotation.ClientModule;
import svenhjol.charmonium.helper.ConfigHelper;

import java.util.List;

@SuppressWarnings("unused")
public class ClientLoader<T extends CharmModule> extends ModuleLoader<T> {
    public ClientLoader(String modId, String basePackage) {
        super(modId, basePackage);
    }

    @Override
    protected String getModuleAnnotation() {
        return "Lsvenhjol/charmonium/annotation/ClientModule;";
    }

    @Override
    protected void setupModuleAnnotations(Class<T> clazz, T module) throws IllegalStateException {
        if (clazz.isAnnotationPresent(ClientModule.class)) {
            ClientModule annotation = clazz.getAnnotation(ClientModule.class);
            module.setModId(getModId());
            module.setPriority(annotation.priority());
            module.setAlwaysEnabled(annotation.alwaysEnabled());
            module.setDescription(annotation.description());
            module.setEnabledByDefault(annotation.enabledByDefault());
            module.setEnabled(module.isEnabledByDefault());
        } else {
            throw new RuntimeException("[ClientLoader] Missing annotation for client module `" + clazz + "`");
        }
    }

    @Override
    protected void setupModuleConfig(List<T> modules) {
        ConfigHelper.applyConfig(getModId(), modules);
        modules.forEach(module -> module.setEnabled(!ConfigHelper.isModuleDisabled(getModId(), module.getName())));
        ConfigHelper.writeConfig(getModId(), modules);
    }
}
