package pt.test.payaramicro.beans;

import java.util.logging.Logger;
import javax.ejb.Singleton;

@Singleton
public class DataCacheService {

    private static final Logger LOGGER = Logger.getLogger(DataCacheService.class.getName());

    public void loadCache() {
        LOGGER.info("Data Cache EJB ran");
    }

}
