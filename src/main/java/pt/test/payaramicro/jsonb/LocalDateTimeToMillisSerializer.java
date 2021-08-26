package pt.test.payaramicro.jsonb;

import java.time.LocalDateTime;
import java.time.ZoneId;
import javax.json.bind.serializer.JsonbSerializer;
import javax.json.bind.serializer.SerializationContext;
import javax.json.stream.JsonGenerator;

public class LocalDateTimeToMillisSerializer implements JsonbSerializer<LocalDateTime> {

    @Override
    public void serialize(LocalDateTime dateToSerialize, JsonGenerator generator, SerializationContext ctx) {

        if (dateToSerialize != null) {
            generator.write(dateToSerialize.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
        } else {
            generator.write((String) null);
        }

    }

}
