package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.User_RoleDTO;
import entities.User;
import facades.FacadeExample;
import facades.RoleFacade;
import facades.UserFacade;
import utils.EMF_Creator;

import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

@Path("/protected")
public class ProtectedPagesResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private static final UserFacade FACADE = UserFacade.getUserFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @Context
    private UriInfo context;

    @Context
    SecurityContext securityContext;


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("userPage")
    @RolesAllowed("user")
    public Response getFromUser() {
        String username = securityContext.getUserPrincipal().getName();

        User user = FACADE.getUserByUsername(username);

        User_RoleDTO user_roleDTO = new User_RoleDTO(user);

        return Response.ok()
               .entity(GSON.toJson(user_roleDTO))
               .build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("adminPage")
    @RolesAllowed("admin")
    public Response getFromAdmin() {
        String username = securityContext.getUserPrincipal().getName();

        User user = FACADE.getUserByUsername(username);

        User_RoleDTO user_roleDTO = new User_RoleDTO(user);

        return Response.ok()
               .entity(GSON.toJson(user_roleDTO))
               .build();
    }


}