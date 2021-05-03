package de.oop.projekt.test;

import de.oop.projekt.main.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLOutput;

import static org.junit.jupiter.api.Assertions.*;

class StudyTest {

    DoctorContainer doctorContainer = Study.getInstance().getDoctorContainer();
    TestSubjectContainer testSubjectContainer = Study.getInstance().getTestSubjectContainer();
    TestSubject nico = new TestSubject("Nico", "Petersen", new Date(7, 12, 2000), "männlich");
    TestSubject nico2 = new TestSubject("Nico", "Petersen", new Date(7, 12, 2000), "männlich");
    TestSubject andrea = new TestSubject("Andrea", "Robitzsch", new Date(21, 10, 1986), "weiblich");
    TestSubject finn = new TestSubject("Finn", "Gruendel", new Date(16, 2, 1999), "männlich");
    Doctor kielholz = new Doctor("Dr.","Johann", "Kielholz", "M.D.", new Date(12, 7, 1956), "Allgemeinmedizin", "männlich", new Date(01, 10, 1984));
    Doctor thorsen = new Doctor("Dr.", "Nana", "Thorsen", "M.D.", new Date(23, 5, 1970), "Allgemeinmedizin", "weiblich", new Date(15, 07, 2000));
    Doctor karev = new Doctor("Dr.", "Alex", "Karev", "M.D., F.A.C.S", new Date(1, 5, 1980), "Pediatrician", "männlich", new Date(01, 12, 2012));




    @Test
    void personenVornamen() {
        assertEquals("Nico", nico.getFirstName());
        assertEquals("Andrea", andrea.getFirstName());
        assertEquals("Finn", finn.getFirstName());

    }

    @Test
    void equalObjects() {
        assertEquals(nico2, nico);
    }

    @Test
    void personenNachnamen() {
        assertEquals("Petersen", nico.getLastName());
        assertEquals("Robitzsch", andrea.getLastName());
        assertEquals("Gruendel", finn.getLastName());
    }

    @Test
    void addPersonsToContainer() {
        testSubjectContainer.addTestSubjectToList(nico);
        testSubjectContainer.addTestSubjectToList(andrea);
        testSubjectContainer.addTestSubjectToList(finn);
    }

    @Test
    void addDoctorsToContainer() {

    }

    @Test
    void getIndexesFromPersons() {
        addPersonsToContainer();
        //assertEquals(nico, testSubjectContainer.searchTestSubjectByLastName("Petersen"));
       // assertEquals(andrea, TestSubjectContainer.searchForTestSubjectIndexInList(andrea));
    }

    @Test
    void serializeStudy() {

//        addPersonsToContainer();
//        Serializer s = new Serializer("Serialization.study");
//        try {
//            s.writeToFile(Study.getInstance());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    @Test
    void getStudyFromFile() {
//        Serializer s = new Serializer("Serialization.study");
//        try {
//            s.readFromFile();
//            checkInstances();
//            System.out.println(Study.getInstance().getTestSubjectContainer().getTestSubjectList().get(0));
//            System.out.println(Study.getInstance().getTestSubjectContainer().searchTestSubjectByLastName("Petersen"));
//            //assertEquals(0, Study.getInstance().getTestSubjectContainer().searchTestSubjectByLastName("Petersen"));
//		} catch (IOException | ClassNotFoundException e){
//			e.printStackTrace();
//		}
    }
 @Test
 void testDurationOfEmployment()
 {
     System.out.println(kielholz.durationOfEmployment());
     System.out.println(thorsen.durationOfEmployment());
     System.out.println(karev.durationOfEmployment());



 }




}