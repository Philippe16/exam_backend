/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entities.User;
import javax.persistence.EntityManagerFactory;
import utils.EMF_Creator;

public class Populator {
    public static void populate(){
        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
        UserFacade uf = UserFacade.getUserFacade(emf);
        RoleFacade rf = RoleFacade.getRoleFacade(emf);

        // Throws an error if you run more than once without changing the userName
        // This is due the fact that we have a unique constraint (as we should) on the userName
        User user = new User("Henning", "terces");

        user.addRole(rf.getRole("admin"));
        uf.create(user);
    }


    public static void main(String[] args) {
        populate();
    }
}
