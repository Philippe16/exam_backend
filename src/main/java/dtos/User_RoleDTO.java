package dtos;

import entities.Role;
import entities.User;

import java.util.ArrayList;
import java.util.List;

public class User_RoleDTO {
   String username;
   List<String> roles = new ArrayList<>();

   public User_RoleDTO(User user) {
      this.username = user.getUserName();

      for(Role role : user.getRoleList() ){
         roles.add(role.getRoleName());
      }
   }

   public String getUsername() {
      return username;
   }

   public void setUsername(String username) {
      this.username = username;
   }

   public List<String> getRoles() {
      return roles;
   }

   public void setRoles(List<String> roles) {
      this.roles = roles;
   }
}
