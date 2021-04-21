package de.oop.projekt.main;

import java.io.*;

public class Serializer implements Serializable {


    private static String filename = "Serialization.study";

    @Serial
    private static final long serialVersionUID = 1L;

    private Serializer() {}


    public static void writeToFile(Study study) throws IOException {
        ObjectOutputStream objOutStream = new ObjectOutputStream(new FileOutputStream(filename));
        objOutStream.writeObject(study);
        objOutStream.close();

    }

    public static void readFromFile() throws IOException, ClassNotFoundException {
        ObjectInputStream objInStream = new ObjectInputStream(new FileInputStream(filename));
        Study.setInstance((Study) objInStream.readObject());
        objInStream.close();
    }
}
