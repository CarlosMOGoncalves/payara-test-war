package pt.test.payaramicro.webservices;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import pt.test.payaramicro.jsonb.ClientApp;
import pt.test.payaramicro.jsonb.JsonbConverter;

@Stateless
@Path("/yasson")
public class YassonResource {

    @Inject
    private JsonbConverter jsonbConverter;

    @GET
    @Path("convertjson")
    public Response testYassonConverter() {

        ClientApp json = jsonbConverter.convertFromJson("{\"appName\":\"Payara\",\"appVersion\":\"5\"}", ClientApp.class);

        return Response.ok(json).build();
    }

}
