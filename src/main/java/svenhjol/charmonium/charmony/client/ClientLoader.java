package svenhjol.charmonium.charmony.client;

import svenhjol.charmonium.charmony.Feature;
import svenhjol.charmonium.charmony.Loader;
import svenhjol.charmonium.charmony.Log;
import svenhjol.charmonium.charmony.Resolve;
import svenhjol.charmonium.charmony.enums.Side;

import java.util.Comparator;

public class ClientLoader extends Loader<ClientFeature> {
    private final ClientConfig config;
    private final ClientRegistry registry;
    private final ClientEvents events;

    protected ClientLoader(String id) {
        super(id);
        this.log = new Log(id, this);
        this.config = new ClientConfig(this);
        this.registry = new ClientRegistry(this);
        this.events = new ClientEvents(this);
    }

    @Override
    public Side side() {
        return Side.CLIENT;
    }

    public static ClientLoader create(String id) {
        return Resolve.register(new ClientLoader(id));
    }

    @Override
    protected Class<? extends Loader<ClientFeature>> type() {
        return ClientLoader.class;
    }

    @Override
    protected void configure() {
        // Sort features alphabetically for configuration.
        features.sort(Comparator.comparing(Feature::name));

        config.readConfig(features);
        config.writeConfig(features);
    }

    public ClientRegistry registry() {
        return registry;
    }
}
