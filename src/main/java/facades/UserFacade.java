package facades;

import dtos.GuestDTO;
import dtos.RoleDTO;
import dtos.Show1DTO;
import dtos.UserDTO;
import entities.Guest;
import entities.Role;
import entities.Show1;
import entities.User;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import org.mindrot.jbcrypt.BCrypt;
import security.errorhandling.AuthenticationException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * @author lam@cphbusiness.dk
 */
public class UserFacade {

    private static EntityManagerFactory emf;
    private static UserFacade instance;

    private UserFacade() {
    }

    /**
     *
     * @param _emf
     * @return the instance of this facade.
     */
    public static UserFacade getUserFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new UserFacade();
        }
        return instance;
    }

    public User getVeryfiedUser(String username, String password) throws AuthenticationException {
        EntityManager em = emf.createEntityManager();
        User user;
        try {
            user = em.find(User.class, username);
            if (user == null || !user.verifyPassword(password)) {
                throw new AuthenticationException("Invalid username or password");
            }
        } finally {
            em.close();
        }
        return user;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public UserDTO create(User user){
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new UserDTO(user);
    }

    public User getUserByUsername(String username) {
        EntityManager em = emf.createEntityManager();

        User user;

        try {
            user = em.find(User.class, username);
        } finally {
            em.close();
        }

        return user;
    }

    public List<Show1DTO> getAllAvailableShows(){
        EntityManager em = emf.createEntityManager();

        List<Show1> shows = new ArrayList<>();
        List<Show1DTO> availableShows = new ArrayList<>();

        try {
            TypedQuery<Show1> query = em.createQuery(
                    "SELECT show1 FROM Show1 AS show1", Show1.class
            );

            shows = query.getResultList();
            for (Show1 show: shows) {
                boolean isAfter = show.getStartDate().isAfter(LocalDate.now());
                if (isAfter){
                    availableShows.add(new Show1DTO(show));
                }


            }
        }finally {
            em.close();
        }

        return availableShows;
    }

    public List<Show1DTO> getAllAssignedShows(User user){
        EntityManager em = emf.createEntityManager();

        List<Show1> shows = new ArrayList<>();
        List<Show1DTO> assignedShows = new ArrayList<>();
        User user1 = em.find(User.class, user.getUserName());
        shows = user1.getGuest().getShow1s();
            for (Show1 show: shows) {
                    assignedShows.add(new Show1DTO(show));
              }
            return assignedShows;

    }

    public void addShow(int showID, String username){
        EntityManager em = emf.createEntityManager();
        Show1 show1FromDB = em.find(Show1.class, showID);
        System.out.println(show1FromDB.getName());
        User userFromDB = em.find(User.class, username);


        userFromDB.getGuest().getShow1s().add(show1FromDB);
        try {
        em.merge(userFromDB);

        }finally {
            em.close();
        }

    }

}
