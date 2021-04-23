package de.oop.projekt.main;

import java.io.Serializable;
import java.util.Objects;

public abstract class Person implements Serializable {

    String firstName;
    String lastName;
    String gender;
    Date dateOfBirth;

    protected Person(String firstName, String lastName, Date dateOfBirth, String gender) {
        this.setFirstName(firstName).setLastName(lastName).setDateOfBirth(dateOfBirth).setGender(gender);
    }

    public String getFirstName() {
        return firstName;
    }

    public Person setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public Person setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getGender(){return gender;}

    public Person setGender(String gender){
        this.gender = gender;
        return this;
    }



    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public Person setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person person = (Person) o;
        return Objects.equals(firstName, person.firstName) && Objects.equals(lastName, person.lastName) && Objects.equals(dateOfBirth, person.dateOfBirth);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, dateOfBirth);
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + "\n* " + dateOfBirth;
    }


}
