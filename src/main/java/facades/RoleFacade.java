package facades;

import dtos.RoleDTO;
import dtos.UserDTO;
import entities.Role;
import entities.User;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;

import security.errorhandling.AuthenticationException;

public class RoleFacade {

   private static EntityManagerFactory emf;
   private static RoleFacade instance;

   private RoleFacade() {
   }

   /**
    *
    * @param _emf
    * @return the instance of this facade.
    */
   public static RoleFacade getRoleFacade(EntityManagerFactory _emf) {
      if (instance == null) {
         emf = _emf;
         instance = new RoleFacade();
      }
      return instance;
   }

   private EntityManager getEntityManager() {
      return emf.createEntityManager();
   }

   public Role getRole(String roleName) throws EntityNotFoundException{
      EntityManager em = emf.createEntityManager();
      Role role;
      try {
         role = em.find(Role.class, roleName);
         if (role == null) {
            throw new EntityNotFoundException("The role was not found in the database...");
         }
      } finally {
         em.close();
      }
      return role;
   }
}

