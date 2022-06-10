package security;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import facades.UserFacade;
import errorhandling.API_Exception;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import security.errorhandling.AuthenticationException;
import javax.persistence.EntityManagerFactory;
import utils.EMF_Creator;

@Path("logout")
public class LogoutEndpoint {
   private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
   public static final UserFacade USER_FACADE = UserFacade.getUserFacade(EMF);
   private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

   @POST
   @Consumes(MediaType.APPLICATION_JSON)
   @Produces(MediaType.APPLICATION_JSON)
   public Response logout(String jsonString) throws AuthenticationException, API_Exception {
      return Response.ok()
             .entity(GSON.toJson("You logged out...")) // todo: Change later
             .build();
   }

}
