package de.oop.projekt.main;

import java.util.UUID;

public class Doctor extends Person {

    private String preTitle = "";
    private String postTitle = "";
    private String specialty = "";
    private Integer beschaeftigungDauer; // setter und getter fehlen und Konstrktor und GUI-Implementierung
    private String employeeID = UUID.randomUUID().toString();

   

    public Doctor(String preTitle, String firstname, String lastname, String postTitle, Date dateOfBirth, String speicalty, String gender) {
        super(firstname, lastname, dateOfBirth, gender);
        this.setPreTitle(preTitle).setPostTitle(postTitle).setSpecialty(speicalty);

    }

public String getSpecialty() {
	return specialty;
}
    
    public Doctor setSpecialty(String specialty) {
    	this.specialty = specialty;
    	return this;
    }
    
    public String getEmployeeID() {
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

    @Override
    public String toString() {
        return "Arzt:" + getPreTitle() + " " + getFirstName() + " " + getLastName() + ", " + getPostTitle() + " (Mitarbeiter-ID: " + getEmployeeID() + ")";
    }
}
