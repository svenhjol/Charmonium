package svenhjol.charmonium.charmony.client;

import svenhjol.charmonium.charmony.Config;

public class ClientConfig extends Config {
    public ClientConfig(ClientLoader loader) {
        super(loader.id() + "-client");
    }
}
