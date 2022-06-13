package dtos;

import entities.Festival;
import entities.Guest;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FestivalDTO {


    private int festivalID;
    private String name;
    private String city;
    private LocalDate startDate;
    private int duration;
    private List<GuestDTO> guests = new ArrayList<>();

    public FestivalDTO() {
    }

    public FestivalDTO(Festival festival) {
        this.festivalID = festival.getFestivalID();
        this.name = festival.getName();
        this.city = festival.getCity();
        this.startDate = festival.getStartDate();
        this.duration = festival.getDuration();
        for (Guest guest: festival.getGuest()) {
            guests.add(new GuestDTO(guest));

        }
    }
}
