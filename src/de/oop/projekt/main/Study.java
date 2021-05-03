package de.oop.projekt.main;

import java.io.*;

public class Study implements Serializable{

    private static Study instance;
    private boolean changesNotSaved = false;
    private String title = "Medizinische Studie";
    private static String filename = "Serialization.study";

    private TestSubjectContainer testSubjectContainer = new TestSubjectContainer();
    private DoctorContainer doctorContainer = new DoctorContainer();

    public boolean isChangesNotSaved() {
        return changesNotSaved;
    }

    public Study setChangesNotSaved(boolean changesNotSaved) {
        this.changesNotSaved = changesNotSaved;
        System.out.println("CHANGED");
        return this;
    }

    public String getTitle() {
        return title;
    }

    public static String getFilename() {
        return filename;
    }

    public static void setFilename(String filename) {
        Study.filename = filename;
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



    @Serial
    private static final long serialVersionUID = 1L;


    public static void writeToFile() throws IOException {
        ObjectOutputStream objOutStream = new ObjectOutputStream(new FileOutputStream(filename));
        objOutStream.writeObject(getInstance());
        objOutStream.close();
        Study.getInstance().setChangesNotSaved(false);
    }
    public static void writeToFile(String filename) throws IOException {
        ObjectOutputStream objOutStream = new ObjectOutputStream(new FileOutputStream(filename));
        objOutStream.writeObject(getInstance());
        objOutStream.close();
        Study.getInstance().setChangesNotSaved(false);
    }
    //TODO: löschen
    public static void readFromFile() throws IOException, ClassNotFoundException {
        ObjectInputStream objInStream = new ObjectInputStream(new FileInputStream(filename));
        instance = (Study) objInStream.readObject();
        objInStream.close();
    }
    //ENDE TODO
    public static void readFromFile(String filename) throws IOException, ClassNotFoundException {
        ObjectInputStream objInStream = new ObjectInputStream(new FileInputStream(filename));
        instance = (Study) objInStream.readObject();
        objInStream.close();
    }



    public DoctorContainer getDoctorContainer() { return doctorContainer; }

    public TestSubjectContainer getTestSubjectContainer() {
        return testSubjectContainer;
    }



}
