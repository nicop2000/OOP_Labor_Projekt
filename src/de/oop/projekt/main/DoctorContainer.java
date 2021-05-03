package de.oop.projekt.main;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DoctorContainer implements Serializable {

    List<Doctor> doctorList = new ArrayList<>();

    public List<Doctor> getDoctorList() {
        return doctorList;
    }

    public DoctorContainer setDoctorList(List<Doctor> doctorList) {
        this.doctorList = doctorList;
        return this;
    }

    public boolean addDoctorToList(Doctor doctor) {
        doctorList.add(doctor);
        Study.getInstance().setChangesNotSaved(true);
        return true;
    }
//immer wenn einträge geschrieben, modifiziert oder gelöscht werden muss die setChangesNotSaved
// - Methode auf true gesetzt werden

    public boolean removeDoctorFromList(Doctor doctor) {
        doctorList.removeIf(n -> (n == doctor));
        Study.getInstance().setChangesNotSaved(true);
        return true;
    }

    public List<Doctor> searchDoctor(String lastName) {
        List<Doctor> matchingSubjects = new ArrayList<>();
        if ((this.getDoctorList() != null) && (this.getDoctorList().size() >= 1)) {
            for (int i = 0; i < this.getDoctorList().size(); i++) {
                if (this.getDoctorList().get(i).getLastName().equalsIgnoreCase(lastName)) {
                    matchingSubjects.add(this.getDoctorList().get(i));

                }
            }
        }

        return matchingSubjects;
    }

    // suchen nach Geburtsdatum

    public List<Doctor> searchDoctor(Date dateOfBirth){
        List<Doctor> matchingSubjects = new ArrayList<>();
        if((this.getDoctorList() != null) && (this.getDoctorList().size() >=1)){
            for(int i = 0; i < this.getDoctorList().size(); i++){
                if(this.getDoctorList().get(i).getDateOfBirth().equals(dateOfBirth)){
                    matchingSubjects.add(this.getDoctorList().get(i));
                }
            }
        }

        return matchingSubjects;

    }


// suchen nach ID -- bei Nico schauen in den Testpersoncontainer

    public List<Doctor> searchDoctor(UUID employeeID){
        List<Doctor> matchingSubjects = new ArrayList<>();

        return matchingSubjects;
    }


}
