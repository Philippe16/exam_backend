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

    @GET
    @Produces("text/plain")
    public String hello() {
        return "Hello, World!";
    }



}

