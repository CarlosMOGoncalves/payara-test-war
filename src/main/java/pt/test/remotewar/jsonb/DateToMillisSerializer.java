package pt.test.remotewar.jsonb;

import java.util.Date;
import javax.json.bind.serializer.JsonbSerializer;
import javax.json.bind.serializer.SerializationContext;
import javax.json.stream.JsonGenerator;

public class DateToMillisSerializer implements JsonbSerializer<Date> {

    @Override
    public void serialize(Date dateToSerialize, JsonGenerator generator, SerializationContext serializationContext) {

        if (dateToSerialize != null) {
            generator.write(dateToSerialize.getTime());
        } else {
            generator.write((String) null);
        }
    }

}
