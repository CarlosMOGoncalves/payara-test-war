package pt.test.payaramicro.jsonb;

import java.lang.reflect.Type;
import javax.json.bind.serializer.JsonbSerializer;
import javax.json.bind.serializer.SerializationContext;
import javax.json.stream.JsonGenerator;
import org.apache.commons.lang3.tuple.Pair;
import org.eclipse.yasson.internal.Marshaller;
import org.eclipse.yasson.internal.model.customization.ClassCustomizationBuilder;
import org.eclipse.yasson.internal.model.customization.ContainerCustomization;
import org.eclipse.yasson.internal.serializer.SerializerBuilder;

public class PairSerializer implements JsonbSerializer<Pair> {

    /*
     * NOTE: It serializes the non-parameterized Pair (instead of Pair<T,X>) because there is likely also a problem
     * when the runtime registers one such Serializer. It does so as a ParameterizedType serializer but then looks for
     * the ImmutablePair class registered as a Type, which eventually fails on ComponentMatcher.matches(). We should check
     * on later versions of Yasson whether this is still a thing and maybe open a ticket.
     */
    @Override
    public void serialize(Pair obj, JsonGenerator generator, SerializationContext ctx) {

        if (obj != null) {
            generator.writeStartObject();
            generator.writeKey(obj.getLeft().toString());

            // TODO: uncomment line below when Payara updates to Yasson 1.0.7+.
            // This is related to (https://github.com/eclipse-ee4j/yasson/issues/497) and this (https://github.com/payara/Payara/issues/5290)
            //            ctx.serialize(obj.getRight(), generator);

            // TODO: remove this when Payara updates to Yasson 1.0.7+
            serializeItem(obj.getRight(), generator, ctx);

            generator.writeEnd();
        } else {
            generator.writeNull();
        }

    }

    /**
     * This function creates a SerializerBuilder so that Yasson can properly serialize the value object of the Pair.<br>
     * This had to be done because of a bug in the current verion of Yasson on Payara (1.0.6) which wouldn't figure any custom
     * JsonbSerializer that might have been registered in the current context, as is our case.
     * That means if the value object was, say a LocalDateTime, it wouldn't find any custom serializer that we would have registered,
     * instead opting to use a default one, which is the case.<br>
     *
     * This code was mostly taken from other serializers in the library.
     * TODO: delete this as soon as this is fixed
     *
     */
    private void serializeItem(Object item, JsonGenerator generator, SerializationContext ctx) {

        if (item == null) {
            generator.writeNull();
            return;
        }
        Class<?> itemClass = item.getClass();

        Type instanceValueType = item.getClass().getGenericSuperclass();
        instanceValueType = instanceValueType.equals(Object.class) ? itemClass : instanceValueType;

        SerializerBuilder builder = new SerializerBuilder(((Marshaller) ctx).getJsonbContext());
        builder.withObjectClass(itemClass);
        builder.withType(instanceValueType);

        // This step is here as a workaround. It force the Builder to check for UserSerializers
        builder.withCustomization(new ContainerCustomization(new ClassCustomizationBuilder()));

        JsonbSerializer<Object> rootSerializer = (JsonbSerializer<Object>) builder.build();

        rootSerializer.serialize(item, generator, ctx);
    }

}
