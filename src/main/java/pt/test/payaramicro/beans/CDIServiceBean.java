package pt.test.payaramicro.beans;

import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CDIServiceBean {

    private static final Logger LOGGER = Logger.getLogger(CDIServiceBean.class.getName());

    public void runService() {
        LOGGER.info("CDI Service bean ran");
    }

}
