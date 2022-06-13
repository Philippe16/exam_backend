/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entities.Show;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
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
        Show show = new Show("fffff", 333, "ffffff", date, time);

        try{
            em.getTransaction().begin();
            em.persist(show);
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
