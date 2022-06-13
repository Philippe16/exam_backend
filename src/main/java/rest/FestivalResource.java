package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.Show1DTO;
import dtos.UserDTO;
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

    @GET
    @Path("/assignedShows/{username}")
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
        EntityManager em = EMF.createEntityManager();
        UserFacade userFacade = UserFacade.getUserFacade(EMF);
        UserDTO userDTOFromClient = GSON.fromJson(jsonContext, UserDTO.class);
        Show1DTO show1DTOFromClient = GSON.fromJson(jsonContext, Show1DTO.class);
        User user = em.find(User.class, userDTOFromClient.getUserName());
        Show1 show = em.find(Show1.class, show1DTOFromClient.getShowID());
        user.getGuest().addShow(show);

        em.merge(user);

        Response response = null;
        response = Response
                .status(200)
                .entity(GSON.toJson("SUCCESS"))
                .build();


        return response;
    }




}

