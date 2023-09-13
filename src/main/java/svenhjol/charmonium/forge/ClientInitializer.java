package svenhjol.charmonium.forge;

import svenhjol.charmonium.CharmoniumClient;

public class ClientInitializer {
    public ClientInitializer() {
        var client = CharmoniumClient.instance();
        var loader = client.loader();

        // Autoload all annotated client features from the feature namespace.
        loader.init(client.featurePrefix(), client.featureAnnotation());
    }
}
