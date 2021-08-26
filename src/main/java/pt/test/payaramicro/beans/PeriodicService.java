package pt.test.payaramicro.beans;

import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import pt.test.payaramicro.interceptors.FunctionLogger;

@Stateless
public class PeriodicService {

    private static final Logger LOGGER = Logger.getLogger(PeriodicService.class.getSimpleName());

    @FunctionLogger
    public void executeGenerateRandomNumber() {

        int randomNumber = ThreadLocalRandom.current().nextInt();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }

        LOGGER.info("Generated number is: " + randomNumber);
    }

}
