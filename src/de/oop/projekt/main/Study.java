package de.oop.projekt.main;

import java.io.*;

public class Study implements Serializable{

    private static Study instance;
    private boolean changesNotSaved = false;
    private String title = "Medizinische Studie";

    private TestSubjectContainer testSubjectContainer = new TestSubjectContainer();
    private DoctorContainer doctorContainer = new DoctorContainer();

    public boolean isChangesNotSaved() {
        return changesNotSaved;
    }

    public Study setChangesNotSaved(boolean changesNotSaved) {
        this.changesNotSaved = changesNotSaved;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Study setTitle(String title) {
        this.title = title;
        return this;
    }

    private Study() { super(); }

    public synchronized static Study getInstance() {
        if (instance == null) {
            instance = new Study();
        }
        return instance;
    }

    private static String filename = "Serialization.study";

    @Serial
    private static final long serialVersionUID = 1L;


    public static void writeToFile() throws IOException {
        ObjectOutputStream objOutStream = new ObjectOutputStream(new FileOutputStream(filename));
        objOutStream.writeObject(getInstance());
        objOutStream.close();
        Study.getInstance().setChangesNotSaved(false);

    }

    public static void readFromFile() throws IOException, ClassNotFoundException {
        ObjectInputStream objInStream = new ObjectInputStream(new FileInputStream(filename));
        instance = (Study) objInStream.readObject();
        objInStream.close();

    }



    public DoctorContainer getDoctorContainer() { return doctorContainer; }

    public TestSubjectContainer getTestSubjectContainer() {
        return testSubjectContainer;
    }



}
