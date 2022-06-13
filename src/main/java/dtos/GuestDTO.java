package dtos;

import entities.Festival;
import entities.Guest;
import entities.Show1;
import entities.User;

import java.util.ArrayList;
import java.util.List;

public class GuestDTO {

    private int guestID;
     private String name;
     private String phone;
     private String email;
     private String status;
    private List<Show1DTO> show1s = new ArrayList<>();
    private FestivalDTO festival;
    private UserDTO user;

    public GuestDTO() {
    }

    public GuestDTO(Guest guest) {
        this.guestID = guest.getGuestID();
        this.name = guest.getName();
        this.phone = guest.getPhone();
        this.email = guest.getEmail();
        this.status = guest.getStatus();
        for (Show1 show1: guest.getShow1s()) {
            show1s.add(new Show1DTO(show1));

        }
        this.festival = new FestivalDTO(guest.getFestival());
        this.user = new UserDTO(guest.getUser());

    }

    public List<Show1DTO> getShow1s() {
        return show1s;
    }

    public void setShow1s(List<Show1DTO> show1s) {
        this.show1s = show1s;
    }

    public int getGuestID() {
        return guestID;
    }

    public void setGuestID(int guestID) {
        this.guestID = guestID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
