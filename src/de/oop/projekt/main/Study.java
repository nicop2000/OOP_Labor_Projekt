package de.oop.projekt.main;

import java.io.*;

public class Study implements Serializable{

    private static Study instance;
    private boolean changesNotSaved = false;

    public boolean isChangesNotSaved() {
        return changesNotSaved;
    }

    public Study setChangesNotSaved(boolean changesNotSaved) {
        this.changesNotSaved = changesNotSaved;
        return this;
    }



    private Study() { super(); }

    public synchronized static Study getInstance() {
        if (instance == null) {
            instance = new Study();
        }
        return instance;
    }

    public synchronized static void setInstance(Study newInstance) {
        instance = newInstance;
        TestSubjectContainer.setInstance(newInstance.getTestSubjectContainer());
        DoctorContainer.setInstance(newInstance.getDoctorContainer());
    }

    private TestSubjectContainer testSubjectContainer = TestSubjectContainer.getInstance();
    private DoctorContainer doctorContainer = DoctorContainer.getInstance();

    public DoctorContainer getDoctorContainer() { return doctorContainer; }

    public TestSubjectContainer getTestSubjectContainer() {
        return testSubjectContainer;
    }



}
