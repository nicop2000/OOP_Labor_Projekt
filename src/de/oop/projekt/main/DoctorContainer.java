package de.oop.projekt.main;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DoctorContainer implements Serializable {

    List<Doctor> doctorList = new ArrayList<>();


    public boolean addDoctorToList(Doctor doctor) {
        doctorList.add(doctor);
        Study.getInstance().setChangesNotSaved(true);
        return true;
    }
//immer wenn einträge geschrieben, modifiziert oder gelöscht werden muss die setChangesNotSaved
// - Methode auf true gesetzt werden

    public boolean removeDoctorFromList(Doctor doctor){
        doctorList.removeIf(n->(n == doctor));
        Study.getInstance().setChangesNotSaved(true);
        return true;
    }







}
