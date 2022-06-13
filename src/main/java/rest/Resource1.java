package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.Show1DTO;
import facades.UserFacade;
import utils.EMF_Creator;

import javax.persistence.EntityManagerFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/hello-world")
public class Resource1 {
    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    // Facades
    private static final UserFacade userFacade = UserFacade.getUserFacade(EMF);

    @GET
    @Produces("text/plain")
    public String hello() {
        return "Hello, World!";
    }

    @GET
    @Path("/availableshows")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response avaiableShows(String jsonContext) {
        UserFacade userFacade = UserFacade.getUserFacade(EMF);
        List<Show1DTO> shows = userFacade.getAllAvailableShows();
        Response response = null;
        if (shows != null) {
            response = Response
                    .status(200)
                    .entity(GSON.toJson(shows))
                    .build();

        }
        return response;
    }

}

