package pt.test.remotewar.cdi;

import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import fish.payara.micro.cdi.Inbound;

@ApplicationScoped
public class RemoteCDIObserver {

    private static final Logger LOGGER = Logger.getLogger(RemoteCDIObserver.class.getName());

    public void observeCacheEvent(@Observes @Inbound String message) {
        LOGGER.info("Triggered remotely on RemoteCDIObserver from remotewar");
    }
}
