package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.Show1DTO;
import dtos.UserDTO;
import dtos.Username_movieIDDTO;
import entities.Show1;
import entities.User;
import facades.UserFacade;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/moviefestival")
public class FestivalResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    // Facades
    private static final UserFacade userFacade = UserFacade.getUserFacade(EMF);


    @GET
    @Path("/hello")
    @Produces("text/plain")
    public String hello() {
        return "Hello, World!";
    }




    @GET
    @Path("/availableshows")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response availableShows(String jsonContext) {
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

    @GET
    @Path("/assignedshows/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response assignedShows(String jsonContext, @PathParam("username") String username) {
        EntityManager em = EMF.createEntityManager();
        UserFacade userFacade = UserFacade.getUserFacade(EMF);
        User user = em.find(User.class, username);
        List<Show1DTO> shows = userFacade.getAllAssignedShows(user);
        Response response = null;
        if (shows != null) {
            response = Response
                    .status(200)
                    .entity(GSON.toJson(shows))
                    .build();

        }
        return response;
    }

    @PUT
    @Path("/addshows")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addShows(String jsonContext) {
        Username_movieIDDTO unDTO = GSON.fromJson(jsonContext, Username_movieIDDTO.class);
        EntityManager em = EMF.createEntityManager();
        UserFacade userFacade = UserFacade.getUserFacade(EMF);
//        User user = em.find(User.class, unDTO.getUsername());
//        Show1 show = em.find(Show1.class, unDTO.getMovieID());
        userFacade.addShow(unDTO.getMovieID(), unDTO.getUsername());

        Response response = null;
        response = Response
                .status(200)
                .entity(GSON.toJson("SUCCESS"))
                .build();


        return response;
    }




}

