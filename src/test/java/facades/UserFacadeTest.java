package facades;

import dtos.GuestDTO;
import dtos.Show1DTO;
import entities.Guest;
import entities.Role;
import entities.Show1;
import entities.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserFacadeTest {
    private static EntityManagerFactory emf = EMF_Creator.createEntityManagerFactoryForTest();
    private static UserFacade quizFacade = UserFacade.getUserFacade(emf);

    User user;

    LocalDate date = LocalDate.of(2012,12, 7);
    LocalTime time = LocalTime.of(12,45);
    LocalDate date2 = LocalDate.of(2014,1, 14);
    LocalTime time2 = LocalTime.of(15,25);
    LocalDate date3 = LocalDate.of(2023,3, 20);
    LocalTime time3 = LocalTime.of(21,00);


    private void resetDB(EntityManager em){
        em.getTransaction().begin();

        em.createQuery("DELETE FROM User ").executeUpdate();
        em.createQuery("DELETE FROM Guest ").executeUpdate();
        em.createQuery("DELETE FROM Role ").executeUpdate();
        em.createQuery("DELETE FROM Show1 ").executeUpdate();

        em.createNativeQuery("ALTER TABLE shows AUTO_INCREMENT = 1").executeUpdate();
        em.createNativeQuery("ALTER TABLE guest AUTO_INCREMENT = 1").executeUpdate();
        em.createNativeQuery("ALTER TABLE users AUTO_INCREMENT = 1").executeUpdate();
        em.createNativeQuery("ALTER TABLE roles AUTO_INCREMENT = 1").executeUpdate();



        em.getTransaction().commit();
    }

    @BeforeEach
    void setUp() {
        EntityManager em = emf.createEntityManager();

        Role role = new Role("user");
        Guest guest = new Guest("Testian", "999888", "tester@mail.dk", "Optaget");
        user = new User("Fjolle", "Gem", role, guest);

        Show1 show1 = new Show1("Scary", 2, "FestivalsVej",date, time );
        Show1 show2 = new Show1("Funny", 4, "FunnyBunnyVej", date2,time2);
        Show1 show3 = new Show1("Dumb", 1, "HoldDaHeltOpVej", date3,time3);

        user.getGuest().addShow(show1);
        user.getGuest().addShow(show2);
        user.getGuest().addShow(show3);

        try {
            em.getTransaction().begin();
            em.persist(role);
            em.persist(show1);
            em.persist(show2);
            em.persist(show3);
            em.persist(guest);
            em.persist(user);
            em.getTransaction().commit();

        }finally {
           em.close();
        }

    }

    @AfterEach
    void tearDown() {
        EntityManager em = emf.createEntityManager();
        resetDB(em);

    }

    @Test
    public void testAvaiableShows(){ // Testing all available shows for a user (US1)
       UserFacade userFacade = UserFacade.getUserFacade(emf);

        List<Show1DTO> availableShows = userFacade.getAllAvailableShows();

        assertEquals(1, availableShows.size());


    }

    @Test
    public void testAssignedShows(){ // Testing all assigned shows for a user (US2)

        UserFacade userFacade = UserFacade.getUserFacade(emf);

        List<Show1DTO> assignedShows = userFacade.getAllAssignedShows(user);

        assertEquals(3, assignedShows.size());

    }

    @Test
    public void testAddShows(){
        EntityManager em = emf.createEntityManager();
        User userFromDB = em.find(User.class, "Fjolle");
        userFromDB.getGuest().setShow1s(new ArrayList<>());
        em.merge(userFromDB);
        userFromDB = em.find(User.class, "Fjolle");
        UserFacade userFacade = UserFacade.getUserFacade(emf);
        List<Show1DTO> availableShows = userFacade.getAllAvailableShows();
        userFacade.addShow(1,"Fjolle" );


        assertEquals(1, userFromDB.getGuest().getShow1s().size());
    }


}