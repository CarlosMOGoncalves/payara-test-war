package pt.test.remotewar.jsonb;

import java.lang.reflect.Type;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbConfig;
import javax.json.bind.annotation.JsonbDateFormat;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

@RequestScoped
public class JsonbConverter {

    private static final Logger LOGGER = Logger.getLogger(JsonbConverter.class.getName());

    private Jsonb jsonBConverter;

    @PostConstruct
    public void init() {
        JsonbConfig configuration = new JsonbConfig()
            .withSerializers(new DateToMillisSerializer(), new LocalDateTimeToMillisSerializer(), new PairSerializer())
            .withNullValues(true)
            .withDateFormat(JsonbDateFormat.TIME_IN_MILLIS, Locale.getDefault());
        jsonBConverter = JsonbBuilder.newBuilder().withConfig(configuration).build();
    }

    @Transactional(TxType.SUPPORTS)
    public String convertToJson(Object object) {
        return jsonBConverter.toJson(object);
    }

    @Transactional(TxType.SUPPORTS)
    public <T> T convertFromJson(String s, Type t) {
        return jsonBConverter.fromJson(s, t);
    }

    @PreDestroy
    private void destroy() {
        try {
            jsonBConverter.close();
        } catch (Exception e) {
            LOGGER.log(Level.WARNING, "Could not destroy JSON-B converter", e);
        }
    }

}
