/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entities.Guest;
import entities.Role;
import entities.Show1;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import entities.User;
import utils.EMF_Creator;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;


public class Populator {
    public static void populate() throws ParseException {
        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
        EntityManager em = emf.createEntityManager();
        LocalDate date = LocalDate.of(2022,4, 5);
        LocalTime time = LocalTime.of(12,45);
        LocalDate date1 = LocalDate.of(2021,3, 10);
        LocalTime time1 = LocalTime.of(10,00);
        LocalDate date2 = LocalDate.of(2022,4, 5);
        LocalTime time2 = LocalTime.of(14,30);
        LocalDate date3 = LocalDate.of(2023,1, 15);
        LocalTime time3 = LocalTime.of(18,00);
        Show1 show1 = new Show1("Mørkes Frembrud", 3, "Stenbruddet", date, time);
        Show1 show2 = new Show1("Kærlighed ved første hik", 2, "Smilenes Kanal", date1, time1);
        Show1 show3 = new Show1("Terminator", 3, "Ulvedalen", date2, time2);
        Show1 show4 = new Show1("Blinkkende Lygter", 3, "Rød Plads", date3, time3);


        Role roleU = new Role("user");
        Role roleA = new Role("admin");
        Guest guest = new Guest("Mads", "12121212","mads@test.dk", "Ledig");
        Guest guest2 = new Guest("Thomas", "12341234","thomas@spiller.dk", "Optaget");
        Guest guest3 = new Guest("Sille", "43214321", "sillepigen@fnis.dk", "Ledig");


        User user = new User("Seje", "krøllebølle", roleU, guest);
        User user2 = new User("Chef", "chef123", roleA, guest2);
        User user3 = new User("FriskSild", "123", roleU, guest3);

        try{
            em.getTransaction().begin();
            em.persist(guest);
            em.persist(guest2);
            em.persist(guest3);
            em.persist(user);
            em.persist(user2);
            em.persist(user3);
            em.persist(show1);
            em.persist(show2);
            em.persist(show3);
            em.persist(show4);
            em.getTransaction().commit();
        }finally {
            em.close();
        }
    }

    private static Date stringToDateParser(String date) throws ParseException{
        String date_string = date;
        SimpleDateFormat formatter = new SimpleDateFormat("dd-mm-yy");

        return formatter.parse(date);
    }

    private static Date stringToTimeParser(String date) throws ParseException{
        String time_string = date;
        DateFormat formatter = new SimpleDateFormat("hh:mm:ss a");

        return formatter.parse(date);
    }


    public static void main(String[] args) throws ParseException {
        populate();
    }



}
