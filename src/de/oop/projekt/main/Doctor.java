package de.oop.projekt.main;

//import java.time.LocalDate;
import java.util.*;
import java.time.*;

import static java.time.temporal.ChronoUnit.DAYS;

public class Doctor extends Person {

    private String preTitle = "";
    private String postTitle = "";
    private String specialty = "";
    private Date dateOfEntry; // setter und getter sind in Dateklasse angelegt
    private UUID employeeID = UUID.randomUUID();


    /**
     * Kontruktor für das Objekt Doctor. Stützt sich teils auf den Konstruktor der Superklasse
     * @param preTitle
     * @param firstname
     * @param lastname
     * @param postTitle
     * @param dateOfBirth
     * @param specialty
     * @param gender
     * @param dateOfEntry
     */
    public Doctor(String preTitle, String firstname, String lastname, String postTitle, Date dateOfBirth, String specialty, String gender, Date dateOfEntry) {
        super(firstname, lastname, dateOfBirth, gender);
        this.setPreTitle(preTitle).setPostTitle(postTitle).setSpecialty(specialty).setDateOfEntry(dateOfEntry);

    }

public String getSpecialty() {
	return specialty;
}
    
    public Doctor setSpecialty(String specialty) {
    	this.specialty = specialty;
    	return this;
    }
    
    public UUID getEmployeeID() {
        return employeeID;
    }


    public String getPreTitle() {
        return preTitle;
    }

    public Doctor setPreTitle(String preTitle) {
        this.preTitle = preTitle;
        return this;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public Doctor setPostTitle(String postTitle) {
        this.postTitle = postTitle;
        return this;
    }

    public Doctor setDateOfEntry(Date dateOfEntry){
       this.dateOfEntry = dateOfEntry;
       return this;
    }

    public Date getDateOfEntry(){
        return this.dateOfEntry;
    }

    public long durationOfEmployment(){

        LocalDate startDate = LocalDate.of(this.getDateOfEntry().getYear(), this.getDateOfEntry().getMonth(), this.getDateOfEntry().getDay());
        LocalDate currentDate = LocalDate.now();
        long duration = DAYS.between(startDate, currentDate);

            return duration;
    }



    @Override
    public String toString() {
        return "Arzt:" + getPreTitle() + " " + getFirstName() + " " + getLastName() + ", " + getPostTitle() + " (Mitarbeiter-ID: " + getEmployeeID() + ")";
    }
}
