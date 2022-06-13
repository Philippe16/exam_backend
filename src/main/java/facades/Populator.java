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
        LocalDate date = LocalDate.of(2012,12, 7);
        LocalTime time = LocalTime.of(12,45);
        Show1 show1 = new Show1("fffff", 333, "ffffff", date, time);

        Role role = new Role("user");
        Guest guest = new Guest("Mads", "12121212","mads@test.dk", "Ledig");
        User user = new User("Seje", "krøllebølle", role, guest );

        try{
            em.getTransaction().begin();
            em.persist(role);
            em.persist(guest);
            em.persist(user);
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
