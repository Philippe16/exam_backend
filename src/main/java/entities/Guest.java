package entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "guest")
public class Guest {

    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "guest_id", nullable = false, unique = true)
    private int guestID;

    @Basic(optional = false)
    @Column(name = "name", nullable = false)
    private String name;

    @Basic(optional = false)
    @Column(name = "phone", nullable = false)
    private String phone;

    @Basic(optional = false)
    @Column(name = "email", nullable = false)
    private String email;

    @Basic(optional = false)
    @Column(name = "status", nullable = false)
    private String status;

    @ManyToMany
    @JoinTable(name = "guests_shows", joinColumns = {
            @JoinColumn(name = "fk_guest_id", referencedColumnName = "guest_id")}, inverseJoinColumns = {
            @JoinColumn(name = "fk_show_id", referencedColumnName = "show_id")}
    )
    private List<Show1> show1s = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "fk_festival_id", nullable = true)
    private Festival festival;

    @OneToOne(mappedBy = "guest")
    private User user;

    public Guest() {
    }

    public Guest(String name, String phone, String email, String status) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.status = status;

    }

    public void addShow(Show1 show1){
        show1s.add(show1);
    }

    public int getGuestID() {
        return guestID;
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

    public List<Show1> getShow1s() {
        return show1s;
    }

    public void setShow1s(List<Show1> show1s) {
        this.show1s = show1s;
    }

    public Festival getFestival() {
        return festival;
    }

    public void setFestival(Festival festival) {
        this.festival = festival;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}