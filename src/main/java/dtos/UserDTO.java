package dtos;

import entities.Role;
import entities.User;

import java.util.ArrayList;
import java.util.List;

public class UserDTO {
    private String userName;
    private String userPass;
    private List<RoleDTO> roleList = new ArrayList<>();

    public UserDTO(User user) {
        this.userName = user.getUserName();
        this.userPass = user.getUserPass();

        for (Role role : user.getRoleList()) {
            roleList.add(new RoleDTO(role));
        }
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    public List<RoleDTO> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<RoleDTO> roleList) {
        this.roleList = roleList;
    }

}
