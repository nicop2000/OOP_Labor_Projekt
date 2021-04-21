package de.oop.projekt.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import de.oop.projekt.main.*;


public class GUI implements ActionListener {

    private TestSubject tS;
    private Doctor doc;
    private boolean editing = false;
    JMenuItem exit = new JMenuItem("Exit");
    private List<JButton> buttonListMultipleSubjectsEdit = new ArrayList<>();
    private List<JButton> buttonListMultipleSubjectsDelete = new ArrayList<>();
    private List<JButton> buttonListMultipleDoctorsEdit = new ArrayList<>();
    private List<JButton> buttonListMultipleDoctorsDelete = new ArrayList<>();
    private List<TestSubject> resultsTestSubjects;
    private List<Doctor> resultsDoctor;
    private JButton newTestSubjectButton = new JButton("Neue Person erstellen");
    private JButton newDoctorButton = new JButton("Neuen Arzt erstellen");
    private JButton testSubjectSaveButton = new JButton("Testperson speichern");
    private JButton doctorSaveButton = new JButton("Arzt speichern");
    private JButton exportButton = new JButton("Exportieren");
    private JFrame mainFrame = new JFrame();
    private JPanel startpagePanel = new JPanel();
    private JPanel newTestSubjectPanel = new JPanel();
    private JPanel newDoctorPanel = new JPanel();
    private JPanel rootPanel = new JPanel(new CardLayout());
    private JPanel personView = new JPanel();
    private JButton backButton = new JButton("Zurück");
    private JButton switchToSearch = new JButton("Suchen");
    private JButton searchButton = new JButton("Suchen");
    private JButton editButtonTestSubject = new JButton("Testperson bearbeiten");
    private JButton editButtonDoctor = new JButton("Arzt bearbeiten");
    private JButton deleteButtonTestSubject = new JButton("Testperson löschen");
    private JButton deleteButtonDoctor = new JButton("Arzt löschen");

    private JButton importStudy = new JButton("Importieren");
    private ButtonGroup searchButtonsGroup = new ButtonGroup();
    private ButtonGroup searchValueButtonsGroup = new ButtonGroup();
    private ButtonGroup genderButtonGroup = new ButtonGroup();
    private JRadioButton male = new JRadioButton("männlich");
    private JRadioButton female = new JRadioButton("weiblich");
    private JRadioButton divers = new JRadioButton("divers");
    private JRadioButton selectDoctor = new JRadioButton("Ärzte durchsuchen");
    private JRadioButton selectTestSubject = new JRadioButton("Testpersonen durchsuchen");
    private JRadioButton searchForLastname = new JRadioButton("Nachname");
    private JRadioButton searchForDate = new JRadioButton("Geburtsdatum (Format: dd.mm.jjjj)");
    private JRadioButton searchForID = new JRadioButton("ID");
    private JMenuBar menubar = new JMenuBar();
    private JLabel genderLbl = new JLabel("Geschlecht");
    private JLabel nameTitleLbl = new JLabel("Titel:");
    private JTextField preTitle = new JTextField();
    private JLabel firstNameLbl = new JLabel("Vorname:");
    private JTextField firstName = new JTextField();
    private JLabel lastNameLbl = new JLabel("Nachname:");
    private JTextField lastName = new JTextField();
    private JLabel birthdateDescription = new JLabel("Geburtstag (dd.mm.yyyy):");
    private JLabel birthdateDayLbl = new JLabel("Tag:");
    private JTextField birthdateDay = new JTextField();
    private JLabel birthdateMonthLbl = new JLabel("Monat:");
    private JTextField birthdateMonth = new JTextField();
    private JLabel birthdateYearLbl = new JLabel("Jahr:");
    private JTextField birthdateYear = new JTextField();
    private JLabel postTitleLbl = new JLabel("Doktorgrad etc.");
    private JTextField postTitle = new JTextField();
    private JLabel specialtyLbl = new JLabel("Fachgebiet");
    private JTextField specialty = new JTextField();
    private JLabel errorLbl = new JLabel();
    private JPanel searchPanel = new JPanel();
    private JLabel searchInformationLbl = new JLabel("Hier Suchkriterium eingeben:");
    private JTextField searchCriteria = new JTextField();
    private JLabel personString = new JLabel();
    private JLabel doctorString = new JLabel();
    private JLabel errorLblEdit = new JLabel();
    private JLabel infoLblImport = new JLabel();

    private void setEditing(boolean b) {
        editing = b;
    }

    private boolean getEditing() {
        return editing;
    }


    public GUI() {
        initialSetup();

        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {

                if (Study.getInstance().isChangesNotSaved()) {
                    int result = JOptionPane.showConfirmDialog(mainFrame,
                            "Es gibt ungespeicherte Änderungen. Möchten Sie das Programm wirklich beenden?", "Achtung:",
                            JOptionPane.YES_NO_OPTION);
                    if (result == JOptionPane.YES_OPTION) {
                        exportData();
                        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    } else if (result == JOptionPane.NO_OPTION) {
                        mainFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                    }
                } else {
                    exportData();
                    mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                }
            }
        }); // zu bearbeiten

        importAtStart();
    }

    void importAtStart() {
        TestSubject nico1 = new TestSubject("Nico", "Petersen", new Date(7, 12, 2000), "männlich");
        TestSubject nico2 = new TestSubject("Nicoo", "Petersen", new Date(8, 3, 1936), "männlich");
        TestSubject nico3 = new TestSubject("Nicooo", "Petersen", new Date(28, 8, 1964), "männlich");
        TestSubject andrea = new TestSubject("Andrea", "Robitzsch", new Date(25, 6, 1986), "weiblich");

        TestSubjectContainer.getInstance().addTestSubjectToList(nico1);
        TestSubjectContainer.getInstance().addTestSubjectToList(nico2);
        TestSubjectContainer.getInstance().addTestSubjectToList(nico3);
        TestSubjectContainer.getInstance().addTestSubjectToList(andrea);


        try {
            Serializer.writeToFile(Study.getInstance());
            Serializer.readFromFile();
            infoLblImport.setText("Import erfolgreich");
        } catch (IOException ioException) {
            infoLblImport.setText(ioException.getMessage());
        } catch (ClassNotFoundException classNotFoundException) {
            infoLblImport.setText(classNotFoundException.getMessage());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == importStudy) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.showDialog(null, "Ausgewählte Datei importieren");
            String filenameImport = fileChooser.getSelectedFile().getName();

            try {
                Serializer.readFromFile();
                infoLblImport.setText("Import erfolgreich");
                Study.getInstance().setChangesNotSaved(false);
            } catch (IOException ioException) {
                infoLblImport.setText(ioException.getMessage());
            } catch (ClassNotFoundException classNotFoundException) {
               infoLblImport.setText(classNotFoundException.getMessage());
            }
        }

        if(e.getSource() == newTestSubjectButton) {
            personFields();
            switchCards("newTestSubject");
        }

        if(e.getSource() == exit) {
            System.exit(0);
        }

        if(e.getSource() == newDoctorButton) {
            doctorFields();
            switchCards("newDoctor");
        }

       if(e.getSource() == testSubjectSaveButton) {
           saveTestSubject();
       }
        if(e.getSource() == doctorSaveButton) {
            saveDoctor();
        }
       if(e.getSource() == backButton) {
           infoLblImport.setText("");
           switchCards("startpage");
       }

        if(e.getSource() == switchToSearch) {
            searchPanel.add(backButton);
            switchCards("search");
        }

        if(e.getSource() == editButtonTestSubject) {
            personView.removeAll();
            tS = resultsTestSubjects.get(0);
            editViewTestSubject(resultsTestSubjects.get(0));
        }

        if(e.getSource() == editButtonDoctor) {
            personView.removeAll();
            doc = resultsDoctor.get(0);
            editViewDoctor(resultsDoctor.get(0));
        }

        if(e.getSource() == deleteButtonTestSubject) {
            deletePerson(resultsTestSubjects.get(0));
        }

        if(e.getSource() == deleteButtonDoctor) {
            deleteDoctor(resultsDoctor.get(0));
        }

        for (int i = 0; i < buttonListMultipleSubjectsEdit.size(); i++) {
            if(e.getSource() == buttonListMultipleSubjectsEdit.get(i)) {
                tS = resultsTestSubjects.get(i);
                editViewTestSubject(resultsTestSubjects.get(i));
            }
        }

        for (int i = 0; i < buttonListMultipleSubjectsDelete.size(); i++) {
            if(e.getSource() == buttonListMultipleSubjectsDelete.get(i)) {
                deletePerson(resultsTestSubjects.get(i));
            }
        }

        for (int i = 0; i < buttonListMultipleDoctorsEdit.size(); i++) {
            if(e.getSource() == buttonListMultipleDoctorsEdit.get(i)) {
                doc = resultsDoctor.get(i);
                editViewDoctor(resultsDoctor.get(i));
            }
        }

        for (int i = 0; i < buttonListMultipleDoctorsDelete.size(); i++) {
            if(e.getSource() == buttonListMultipleDoctorsDelete.get(i)) {
                deleteDoctor(resultsDoctor.get(i));
            }
        }





        if(e.getSource() == searchButton) {

            if(searchForLastname.isSelected()) {
                if(selectTestSubject.isSelected()) {
                   resultsTestSubjects = Study.getInstance().getTestSubjectContainer().searchTestSubjectByLastName(searchCriteria.getText());
                   testSubjectSearchListing();
                } else if(selectDoctor.isSelected()) {
//                    resultsDoctor = Study.getInstance().getDoctorContainer().searchDoctorByLastName(searchCriteria.getText());
                doctorSearchListing();
                }
            } else if(searchForDate.isSelected()) {
                Date searchDate = stringToDate(searchCriteria.getText());
                if(selectTestSubject.isSelected()) {
                    resultsTestSubjects = Study.getInstance().getTestSubjectContainer().searchTestSubjectByDateOfBirth(searchDate);
                    testSubjectSearchListing();

                } else if(selectDoctor.isSelected()) {
//                    resultsDoctor = Study.getInstance().getDoctorContainer().searchDoctorByDateOfBirth(searchDate);
                    doctorSearchListing();
                }
            } else if(searchForID.isSelected()) {
                if(selectTestSubject.isSelected()) {
                    resultsTestSubjects = Study.getInstance().getTestSubjectContainer().searchTestSubjectByID(searchCriteria.getText());
                    testSubjectSearchListing();
                } else if(selectDoctor.isSelected()) {
//                    resultsDoctor = Study.getInstance().getDoctorContainer().searchDoctorByID(searchCriteria.getText());
                    doctorSearchListing();
                }
            }

        }
    }

    private Date stringToDate(String dateAsString) {
        //turns string from user-input to Date
        //If user-input is not a valid format, default Date 1.1.1970 is returned
        if(!FieldValidator.dateFieldValid(dateAsString)) {
            return new Date(1,1,1970);
        }
        String[] dateAsArray = dateAsString.split("\\.");
        System.out.println(Arrays.toString(dateAsArray));
        int day = Integer.parseInt(dateAsArray[0]);
        int month = Integer.parseInt(dateAsArray[1]);
        int year = Integer.parseInt(dateAsArray[2]);
        return new Date(day, month, year);

    }
    private void testSubjectSearchListing() {
        if(resultsTestSubjects != null && resultsTestSubjects.size() != 0) {
            searchResultsTestSubjects(resultsTestSubjects);
        } else {
            errorLblEdit.setText("Keine Person gefunden!");
        }
    }

    private void doctorSearchListing() {

        if (resultsDoctor.size() != 0) {
            searchResultsDoctors(resultsDoctor);
        } else {
            errorLblEdit.setText("Kein Arzt gefunden!");
        }

    }
    


    private void deletePerson(TestSubject t) {
        Study.getInstance().getTestSubjectContainer().removeTestSubjectFromList(t);
        switchCards("startpage");
        infoLblImport.setText("Person erfolgreich gelöscht");
    }

    private void deleteDoctor(Doctor d) {
        Study.getInstance().getDoctorContainer().removeDoctorFromList(d);
        switchCards("startpage");
        infoLblImport.setText("Arzt erfolgreich gelöscht");
    }

    private void editViewTestSubject(TestSubject t) {
        personFields();
        newTestSubjectPanel.add(backButton);
        firstName.setText(t.getFirstName());
        lastName.setText(t.getLastName());
        birthdateDay.setText(String.valueOf(t.getDateOfBirth().getDay()));
        birthdateMonth.setText(String.valueOf(t.getDateOfBirth().getMonth()));
        birthdateYear.setText(String.valueOf(t.getDateOfBirth().getYear()));
        setEditing(true);
        switchCards("newTestSubject");
    }

    private void editViewDoctor(Doctor d) {
        doctorFields();
        newDoctorPanel.add(backButton);
        firstName.setText(d.getFirstName());
        lastName.setText(d.getLastName());
        preTitle.setText(d.getPreTitle());
        birthdateDay.setText(String.valueOf(d.getDateOfBirth().getDay()));
        birthdateMonth.setText(String.valueOf(d.getDateOfBirth().getMonth()));
        birthdateYear.setText(String.valueOf(d.getDateOfBirth().getYear()));
        postTitle.setText(d.getPostTitle());
        specialty.setText(d.getSpecialty());
        setEditing(true);
        switchCards("newDoctor");
    }


    private void searchResultsTestSubjects(List<TestSubject> list) {
        personView.removeAll();
        if (list.size() > 1) {
            for (int i = 0; i < list.size(); i++) {
                JLabel personStringFromList = new JLabel(list.get(i).toString());
                personView.add(personStringFromList);
                buttonListMultipleSubjectsEdit.add(new JButton("Bearbeiten"));
                buttonListMultipleSubjectsEdit.get(i).addActionListener(this);
                buttonListMultipleSubjectsDelete.add(new JButton("Löschen"));
                buttonListMultipleSubjectsDelete.get(i).addActionListener(this);
                personView.add(buttonListMultipleSubjectsEdit.get(i));
                personView.add(buttonListMultipleSubjectsDelete.get(i));
            }
        } else if (list.size() == 1){
            personString.setText(list.get(0).toString());
            personView.add(personString);
            personView.add(editButtonTestSubject);
            personView.add(deleteButtonTestSubject);
            personView.add(backButton);
        } else {
            return;
        }
        personView.add(backButton);
        switchCards("personView");
    }

    private void searchResultsDoctors(List<Doctor> list) {
        personView.removeAll();
        if (list.size() > 1) {
            for (int i = 0; i < list.size(); i++) {
                JLabel doctorStringFromList = new JLabel(list.get(i).toString());
                personView.add(doctorStringFromList);
                buttonListMultipleDoctorsEdit.add(new JButton("Bearbeiten"));
                buttonListMultipleDoctorsEdit.get(i).addActionListener(this);
                buttonListMultipleDoctorsDelete.add(new JButton("Löschen"));
                buttonListMultipleDoctorsDelete.get(i).addActionListener(this);
                personView.add(buttonListMultipleDoctorsEdit.get(i));
                personView.add(buttonListMultipleDoctorsDelete.get(i));


            }
        } else if (list.size() == 1) {
            doctorString.setText(list.get(0).toString());
            personView.add(doctorString);
            personView.add(editButtonDoctor);
            personView.add(deleteButtonDoctor);
            personView.add(backButton);
            switchCards("personView");
        } else {
            return;
        }
        personView.add(backButton);
        switchCards("personView");
    }

    private void personFields(){
        newTestSubjectPanel.removeAll();
        newTestSubjectPanel.add(genderLbl);
        newTestSubjectPanel.add(male);
        newTestSubjectPanel.add(female);
        newTestSubjectPanel.add(divers);
        newTestSubjectPanel.add(firstNameLbl);
        newTestSubjectPanel.add(firstName);
        newTestSubjectPanel.add(lastNameLbl);
        newTestSubjectPanel.add(lastName);
        newTestSubjectPanel.add(birthdateDescription);
        newTestSubjectPanel.add(birthdateDayLbl);
        newTestSubjectPanel.add(birthdateDay);
        newTestSubjectPanel.add(birthdateMonthLbl);
        newTestSubjectPanel.add(birthdateMonth);
        newTestSubjectPanel.add(birthdateYearLbl);
        newTestSubjectPanel.add(birthdateYear);
        newTestSubjectPanel.add(errorLbl);
        newTestSubjectPanel.add(testSubjectSaveButton);
        newTestSubjectPanel.add(backButton);
    }

    private void doctorFields() {
        newDoctorPanel.removeAll();
        newDoctorPanel.add(genderLbl);
        newDoctorPanel.add(male);
        newDoctorPanel.add(female);
        newDoctorPanel.add(divers);
        newDoctorPanel.add(nameTitleLbl);
        newDoctorPanel.add(preTitle);
        newDoctorPanel.add(firstNameLbl);
        newDoctorPanel.add(firstName);
        newDoctorPanel.add(lastNameLbl);
        newDoctorPanel.add(lastName);
        newDoctorPanel.add(postTitleLbl);
        newDoctorPanel.add(postTitle);
        newDoctorPanel.add(birthdateDescription);
        newDoctorPanel.add(birthdateDayLbl);
        newDoctorPanel.add(birthdateDay);
        newDoctorPanel.add(birthdateMonthLbl);
        newDoctorPanel.add(birthdateMonth);
        newDoctorPanel.add(birthdateYearLbl);
        newDoctorPanel.add(birthdateYear);
        newDoctorPanel.add(specialtyLbl);
        newDoctorPanel.add(specialty);
        newDoctorPanel.add(errorLbl);
        newDoctorPanel.add(doctorSaveButton);
        newDoctorPanel.add(backButton);

    }

    private void switchCards(String newCard) {
        CardLayout cards = (CardLayout) rootPanel.getLayout();
        cards.show(rootPanel, newCard);
    }

    private void saveTestSubject() {
        if (!checkDateFields()) {
            return;
        }
        String gender = "";
        if(male.isSelected()) {
            gender = "male";
        } else if(female.isSelected()) {
            gender = "female";
        } else if(divers.isSelected()) {
            gender = "divers";
        }
        int day = Integer.parseInt(birthdateDay.getText());
        int month = Integer.parseInt(birthdateMonth.getText());
        int year = Integer.parseInt(birthdateYear.getText());
        Date birthdate = new Date(day, month, year);
        if (getEditing()) {
            tS.setDateOfBirth(birthdate);
            tS.setFirstName(firstName.getText());
            tS.setLastName(lastName.getText());
            errorLbl.setText("Testperson erfolgreich geändert");
            tS = null;

        } else {
            TestSubject newPerson = new TestSubject(firstName.getText(), lastName.getText(), birthdate, gender);
            if (TestSubjectContainer.getInstance().addTestSubjectToList(newPerson)) {
                errorLbl.setText("Testperson erfolgreich hinzugefügt");

            }

        }
        firstName.setText("");
        lastName.setText("");
        birthdateDay.setText("");
        birthdateMonth.setText("");
        birthdateYear.setText("");
        genderButtonGroup.clearSelection();
    }

    private boolean exportData() {

        try {
            Serializer.writeToFile(Study.getInstance());
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean checkDateFields() {

        if (!FieldValidator.intFieldValid(birthdateDay.getText())) {
            errorLbl.setText("Tag des Geburtsdatums ungültig");
            return false;
        }
        if (!FieldValidator.intFieldValid(birthdateMonth.getText())) {
            errorLbl.setText("Monat des Geburtsdatums ungültig");
            return false;
        }
        if (!FieldValidator.intFieldValid(birthdateYear.getText())) {
            errorLbl.setText("Jahr des Geburtsdatums ungültig");
            return false;
        }

        return true;

    }



    private void saveDoctor() {
        if (!checkDateFields()) {
            return;
        }


        String gender = "";
        if(male.isSelected()) {
            gender = "male";
        } else if(female.isSelected()) {
            gender = "female";
        } else if(divers.isSelected()) {
            gender = "divers";
        }
        int day = Integer.parseInt(birthdateDay.getText());
        int month = Integer.parseInt(birthdateMonth.getText());
        int year = Integer.parseInt(birthdateYear.getText());
        Date birthdate = new Date(day, month, year);
        if(getEditing()) {

            Doctor newDoctor = new Doctor(preTitle.getText(), firstName.getText(), lastName.getText(), postTitle.getText(), birthdate, specialty.getText(), gender);
            if (DoctorContainer.getInstance().addDoctorToList(newDoctor)) {
                errorLbl.setText("Arzt erfolgreich hinzugefügt");

            }
        } else {
            doc.setDateOfBirth(birthdate);
            doc.setFirstName(firstName.getText());
            doc.setLastName(lastName.getText());
            doc.setPostTitle(postTitle.getText());
            doc.setSpecialty(specialty.getText());
            doc.setPreTitle(preTitle.getText());
            errorLbl.setText("Arzt erfolgreich geändert");
            doc = null;

        }
        firstName.setText("");
        lastName.setText("");
        birthdateDay.setText("");
        birthdateMonth.setText("");
        birthdateYear.setText("");
        preTitle.setText("");
        postTitle.setText("");
        specialty.setText("");
    }

    private void setUpMenu() {
        JMenu file = new JMenu("Datei");
        JMenu help = new JMenu("Help");
        menubar.add(file);
        menubar.add(help);
        exit.addActionListener(this);

        file.add(exit);
    }

    private void initialSetup() {
        //called once at start of GUI
        System.setProperty("apple.laf.useScreenMenuBar", "true");
        mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        searchButtonsGroup.add(selectDoctor);
        searchButtonsGroup.add(selectTestSubject);
        genderButtonGroup.add(male);
        genderButtonGroup.add(female);
        genderButtonGroup.add(divers);


        //region set Layouts for different panels
        startpagePanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        startpagePanel.setLayout(new GridLayout(0, 1));
        newTestSubjectPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        newTestSubjectPanel.setLayout(new GridLayout(0, 1));
        newDoctorPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        newDoctorPanel.setLayout(new GridLayout(0, 1));
        searchPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        searchPanel.setLayout(new GridLayout(0, 1));
        personView.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        personView.setLayout(new GridLayout(0, 1));
        //endregion


        //region add ActionListeners to all buttons
        newTestSubjectButton.addActionListener(this);
        newDoctorButton.addActionListener(this);
        switchToSearch.addActionListener(this);
        testSubjectSaveButton.addActionListener(this);
        doctorSaveButton.addActionListener(this);
        searchButton.addActionListener(this);
        exportButton.addActionListener(this);
        editButtonTestSubject.addActionListener(this);
        editButtonDoctor.addActionListener(this);
        importStudy.addActionListener(this);
        deleteButtonTestSubject.addActionListener(this);
        deleteButtonDoctor.addActionListener(this);
        backButton.addActionListener(this);
        //endregion

        searchValueButtonsGroup.add(searchForLastname);
        searchValueButtonsGroup.add(searchForDate);
        searchValueButtonsGroup.add(searchForID);




        //RadioButton for search
        searchPanel.add(selectDoctor); //Group 1
        searchPanel.add(selectTestSubject); //Group 1
        searchPanel.add(searchForLastname); //Group 2
        searchPanel.add(searchForDate); //Group 2
        searchPanel.add(searchForID); //Group 2
        searchPanel.add(searchInformationLbl);
        searchPanel.add(searchCriteria);
        searchPanel.add(errorLblEdit);
        searchPanel.add(searchButton);

        startpagePanel.add(newTestSubjectButton);
        startpagePanel.add(newDoctorButton);
        startpagePanel.add(switchToSearch);
        startpagePanel.add(importStudy);
        startpagePanel.add(infoLblImport);

        rootPanel.add(startpagePanel, "startpage");
        rootPanel.add(newTestSubjectPanel, "newTestSubject");
        rootPanel.add(newDoctorPanel, "newDoctor");
        rootPanel.add(searchPanel, "search");
        rootPanel.add(personView, "personView");

        mainFrame.add(rootPanel, BorderLayout.CENTER);
//        mainFrame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

        mainFrame.setJMenuBar(menubar);
        setUpMenu();

        mainFrame.setTitle("OOP-Projekt");
        mainFrame.pack();

        mainFrame.setVisible(true);

        switchCards("startpage");

    }

    public static void main(String[] args) {
        new GUI();
    }
}
