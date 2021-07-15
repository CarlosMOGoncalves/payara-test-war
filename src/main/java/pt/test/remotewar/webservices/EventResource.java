package pt.test.remotewar.webservices;

import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import fish.payara.micro.cdi.Outbound;
import pt.test.remotewar.jsonb.ClientApp;
import pt.test.remotewar.jsonb.JsonbConverter;

@Stateless
@Path("/events")
public class EventResource {

    private static final Logger LOGGER = Logger.getLogger(EventResource.class.getName());

    @Inject
    @Outbound
    private Event<String> event;

    @Inject
    private JsonbConverter jsonbConverter;

    @POST
    @Path("sendevent")
    public Response runUpdateExchangeRate() {
        LOGGER.info("Sent event from remote war");
        event.fire("Ships ahoy!");
        return Response.ok().build();
    }

    @GET
    public Response testYassonConverter() {

        ClientApp json = jsonbConverter.convertFromJson("{\"appName\":\"Payara\",\"appVersion\":\"5\"}", ClientApp.class);

        return Response.ok(json).build();
    }

}
