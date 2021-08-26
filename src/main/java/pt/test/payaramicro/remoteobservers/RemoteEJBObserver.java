package pt.test.payaramicro.remoteobservers;

import java.util.logging.Logger;
import javax.ejb.Singleton;
import javax.enterprise.event.Observes;
import fish.payara.micro.cdi.Inbound;

@Singleton
public class RemoteEJBObserver {

    private static final Logger LOGGER = Logger.getLogger(RemoteEJBObserver.class.getName());

    public void observeRemoteEvent(@Observes @Inbound String message) {
        LOGGER.info("Triggered remotely on RemoteEJBObserver from remotewar");
    }

}
