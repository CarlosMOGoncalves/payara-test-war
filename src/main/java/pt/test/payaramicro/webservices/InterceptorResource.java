package pt.test.payaramicro.webservices;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import pt.test.payaramicro.beans.PeriodicService;

@Stateless
@Path("/interceptor")
public class InterceptorResource {

    @EJB
    private PeriodicService periodicService;

    @POST
    @Path("intercept")
    public Response triggerInterceptor() {

        periodicService.executeGenerateRandomNumber();
        return Response.ok().build();
    }

}
