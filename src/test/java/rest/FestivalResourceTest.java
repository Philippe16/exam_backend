package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nimbusds.jose.shaded.json.JSONObject;
import entities.Guest;
import entities.Role;
import entities.User;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.response.ValidatableResponse;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.junit.jupiter.api.*;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;
import java.util.ArrayList;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.jupiter.api.Assertions.*;

@Disabled
class FestivalResourceTest {

    private static final int SERVER_PORT = 7777;
    private static final String SERVER_URL = "http://localhost/api";

    static final URI BASE_URI = UriBuilder.fromUri(SERVER_URL).port(SERVER_PORT).build();
    private static HttpServer httpServer;
    private static EntityManagerFactory emf;

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    private static User user;
    private static Role userRole;

    static HttpServer startServer() {
        ResourceConfig rc = ResourceConfig.forApplication(new ApplicationConfig());
        return GrizzlyHttpServerFactory.createHttpServer(BASE_URI, rc);
    }

    private void resetDB(EntityManager em) {
        em.getTransaction().begin();

        user = em.find(User.class, user.getUserName());

        user.getGuest().setShow1s(new ArrayList<>());

        em.merge(user);

        em.createQuery("DELETE FROM User").executeUpdate();
        em.createQuery("DELETE FROM Guest ").executeUpdate();
        em.createQuery("DELETE FROM Show1 ").executeUpdate();
        em.createQuery("DELETE FROM Role").executeUpdate();

        em.createNativeQuery("ALTER TABLE users AUTO_INCREMENT = 1").executeUpdate();
        em.createNativeQuery("ALTER TABLE guest AUTO_INCREMENT = 1").executeUpdate();
        em.createNativeQuery("ALTER TABLE shows AUTO_INCREMENT = 1").executeUpdate();
        em.createNativeQuery("ALTER TABLE roles AUTO_INCREMENT = 1").executeUpdate();

        em.getTransaction().commit();
    }

    @BeforeAll
    public static void setUpClass() {
        //This method must be called before you request the EntityManagerFactory
        EMF_Creator.startREST_TestWithDB();
        emf = EMF_Creator.createEntityManagerFactoryForTest();

        httpServer = startServer();
        //Setup RestAssured
        RestAssured.baseURI = SERVER_URL;
        RestAssured.port = SERVER_PORT;
        RestAssured.defaultParser = Parser.JSON;
    }

    @AfterAll
    public static void closeTestServer() {
        //System.in.read();

        //Don't forget this, if you called its counterpart in @BeforeAll
        EMF_Creator.endREST_TestWithDB();
        httpServer.shutdownNow();
    }

    @BeforeEach
    void setUp() {
        EntityManager em = emf.createEntityManager();

        try {
            System.out.println("HEEERE");
            userRole = new Role("user");
            Guest guest = new Guest("test", "12341234", "test@gmail.com", "Optaget");
            user = new User("User123", "user@gmail.com", userRole, guest);

            em.getTransaction().begin();
            em.persist(userRole);
            em.persist(user);
            em.persist(guest);
            System.out.println("LERLLE");
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @AfterEach
    void tearDown() {
        EntityManager em = emf.createEntityManager();

        try {
            resetDB(em);
        } finally {
            em.close();
        }
    }

    @Test
    public void testServerIsUp() {
        given().when().get("/moviefestival/hello").then().statusCode(200);
    }

    @Test
    public void testLogin() {
        JSONObject requestParams = new JSONObject();
        requestParams.put("email", "user@gmail.com");
        requestParams.put("password", "123");

        ValidatableResponse securityToken = given()
                .contentType("application/json")
                .body(requestParams)
                .when().post("/login")
                .then()
                .body("token", notNullValue());

        System.out.println("TOKEN ---> " + securityToken);
    }

    @Test
    public void testWrongLogin() {
        JSONObject requestParams = new JSONObject();
        requestParams.put("email", "user@mail.com");
        requestParams.put("password", "1234");

        ValidatableResponse securityToken = given()
                .contentType("application/json")
                .body(requestParams)
                .when().post("/login")
                .then()
                .body("token", nullValue());

        System.out.println("TOKEN ---> " + securityToken);
    }
}