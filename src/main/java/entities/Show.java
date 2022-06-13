package entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class Show {

    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "show_id", nullable = false, unique = true)
    private int showID;

    @Basic(optional = false)
    @Column(name = "name", nullable = false)
    private String name;


    @Basic(optional = false)
    @Column(name = "duration", nullable = false)
    private int duration;

    @Basic(optional = false)
    @Column(name = "location", nullable = false)
    private String location;

    @Basic(optional = false)
    @Column(name = "startDate", nullable = false)
    private LocalDate startDate;

    @Basic(optional = false)
    @Column(name = "startTime", nullable = false)
    private LocalTime startTime;


    public Show() {
    }

    public Show(String name, int duration, String location, LocalDate startDate, LocalTime startTime) {
        this.name = name;
        this.duration = duration;
        this.location = location;
        this.startDate = startDate;
        this.startTime = startTime;
    }

    public int getShowID() {
        return showID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }
}
