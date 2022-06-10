package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.Bored_DogFactDTO;
import facades.ExternDataFacade;
import utils.EMF_Creator;

import javax.persistence.EntityManagerFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/externResource")
public class ExternResource {
   private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
   private static final ExternDataFacade FACADE =  ExternDataFacade.getExternDataFacade(EMF);
   private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

   @GET
   @Path("/bored-dogfact")
   @Produces(MediaType.APPLICATION_JSON)
   public Response getCombinedData() {
      Bored_DogFactDTO bored_dogFactDTO = FACADE.getCombinedData();

      return Response.ok()
             .entity(GSON.toJson(bored_dogFactDTO))
             .build();
   }
}