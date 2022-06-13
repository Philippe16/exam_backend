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

   

}

