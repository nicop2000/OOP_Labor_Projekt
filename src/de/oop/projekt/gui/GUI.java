package de.oop.projekt.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


import de.oop.projekt.main.*;


public class GUI extends JPanel implements ActionListener {

    //startRegion attributes: main frame and its panels
    private final JFrame mainFrame = new JFrame();
    private final JPanel rootPanel = new JPanel(new CardLayout());
    private final JPanel startpagePanel = new JPanel();
    private final JPanel newTestSubjectPanel = new JPanel();
    private final JPanel newDoctorPanel = new JPanel();
    private final JPanel personView = new JPanel();
    private final JPanel searchPanel = new JPanel();
    private final JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, startpagePanel, rootPanel);
    //endRegion attributes: main frame and its panels

    //startRegion attributes: input-fields with matching labels
    private final JLabel genderLbl = new JLabel("Geschlecht");
    private final JLabel nameTitleLbl = new JLabel("Titel");
    private final JTextField preTitle = new JTextField();
    private final JLabel firstNameLbl = new JLabel("Vorname");
    private final JTextField firstName = new JTextField();
    private final JLabel lastNameLbl = new JLabel("Nachname");
    private final JTextField lastName = new JTextField();
    private final JLabel dateOfBirthLbl = new JLabel("Geburtstag (dd.mm.yyyy):");
    private final JTextField dateOfBirthInput = new JTextField();
    private final JLabel postTitleLbl = new JLabel("Doktorgrad etc.");
    private final JTextField postTitle = new JTextField();
    private final JLabel specialtyLbl = new JLabel("Fachgebiet");
    private final JTextField specialty = new JTextField();
    private final JLabel dateOfEntryLbl = new JLabel("Eintrittsdatum");
    private final JTextField dateOfEntryInput = new JTextField();
    private final JLabel searchInformationLbl = new JLabel("Hier Suchkriterium eingeben:");
    private final JTextField searchCriteria = new JTextField();
    //endRegion attributes: input-fields with matching labels

    //startRegion attributes: lables for informations and textual representations of saved objects
    private final JLabel infoLblStartpage = new JLabel();
    private final JLabel errorLbl = new JLabel();
    private final JLabel personString = new JLabel();
    private final JLabel doctorString = new JLabel();
    private final JLabel errorLblEdit = new JLabel();
    //endRegion attributes: lables for informations and textual representations of saved objects

    //startRegion attributes: regular Buttons
    private final JButton switchToSearch = new JButton("Nach Personen suchen");
    private final JButton searchButton = new JButton("Suche starten");
    private final JButton newTestSubjectButton = new JButton("Neue Testperson erstellen");
    private final JButton testSubjectSaveButton = new JButton("Testperson speichern");
    private final JButton editButtonTestSubject = new JButton("Testperson bearbeiten");
    private final JButton deleteButtonTestSubject = new JButton("Testperson löschen");
    private final JButton newDoctorButton = new JButton("Neuen Arzt erstellen");
    private final JButton doctorSaveButton = new JButton("Arzt speichern");
    private final JButton editButtonDoctor = new JButton("Arzt bearbeiten");
    private final JButton deleteButtonDoctor = new JButton("Arzt löschen");
    private final JButton exportButton = new JButton("Exportieren");
    //endRegion attributes: regular Buttons

    //startRegion attributes: RadioButtons and their groups
    private final JRadioButton male = new JRadioButton("männlich");
    private final JRadioButton female = new JRadioButton("weiblich");
    private final JRadioButton divers = new JRadioButton("divers");
    private final JRadioButton selectDoctor = new JRadioButton("Ärzte durchsuchen");
    private final JRadioButton selectTestSubject = new JRadioButton("Testpersonen durchsuchen");
    private final JRadioButton searchForLastname = new JRadioButton("Nachname");
    private final JRadioButton searchForDate = new JRadioButton("Geburtsdatum (Format: dd.mm.jjjj)");
    private final JRadioButton searchForID = new JRadioButton("ID");
    private final ButtonGroup searchButtonsGroup = new ButtonGroup();
    private final ButtonGroup searchValueButtonsGroup = new ButtonGroup();
    private final ButtonGroup genderButtonGroup = new ButtonGroup();
    //endRegion attributes: RadioButtons and their groups

    //startRegion attributes: items for menu
    private final JMenuBar menubar = new JMenuBar();
    private final JMenu file = new JMenu("Datei");
    private final JMenu edit = new JMenu("Bearbeiten");
    private final JMenu help = new JMenu("Hilfe");
    private final JMenuItem newStudy = new JMenuItem("Neu");
    private final JMenuItem save = new JMenuItem("Speichern");
    private final JMenuItem saveAs = new JMenuItem("Speichern unter");
    private final JMenuItem importStudy = new JMenuItem("Importieren");
    private final JMenuItem changeTitle = new JMenuItem("Namen der Studie ändern");
    private final JMenuItem exit = new JMenuItem("Exit");
    //endRegion attributes: items for menu
    private final List<JButton> buttonListMultipleSubjectsEdit = new ArrayList<>();
    private final List<JButton> buttonListMultipleSubjectsDelete = new ArrayList<>();
    private final List<JButton> buttonListMultipleDoctorsEdit = new ArrayList<>();
    private final List<JButton> buttonListMultipleDoctorsDelete = new ArrayList<>();
    //startRegion attributes: multiple variables for temporary savings in runtime
    private TestSubject tS;
    private Doctor doc;
    private boolean editing = false;
    private List<TestSubject> resultsTestSubjects = new ArrayList<>();
    private List<Doctor> resultsDoctor = new ArrayList<>();
    //endRegion attributes: multiple variables for temporary savings in runtime

    public GUI() {
        initialSetup();

        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                exitSession();
            }
        }); //TODO: zu bearbeiten

        //importAtStart();
    }

    public static void main(String[] args) {
        new GUI();
    }

    private void exitSession() {
        Object[] options = {"Nein, abbrechen", "Ja, nicht speichern und schließen"};

        if (Study.getInstance().isChangesNotSaved()) {
            int result = JOptionPane.showOptionDialog(mainFrame,
                    "Es gibt ungespeicherte Änderungen. Möchten Sie das Programm wirklich beenden?", "Achtung:",
                    JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
            if (result == JOptionPane.NO_OPTION) {
                exportData();
                mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            } else if (result == JOptionPane.YES_OPTION) {
                mainFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            }
        } else {
            exportData();
            mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
    }

    private boolean getEditing() {
        return editing;
    }

    private void setEditing(boolean b) {
        editing = b;
    }

    void importAtStart() {
        //TODO: MUSS NOCH GELÖSCHT WERDEN
        TestSubject nico1 = new TestSubject("Nico", "Petersen", new Date(7, 12, 2000), "männlich");
        TestSubject nico2 = new TestSubject("Nicoo", "Petersen", new Date(8, 3, 1936), "männlich");
        TestSubject nico3 = new TestSubject("Nicooo", "Petersen", new Date(28, 8, 1964), "männlich");
        TestSubject andrea = new TestSubject("Andrea", "Robitzsch", new Date(25, 6, 1986), "weiblich");

        Study.getInstance().getTestSubjectContainer().addTestSubjectToList(nico1);
        Study.getInstance().getTestSubjectContainer().addTestSubjectToList(nico2);
        Study.getInstance().getTestSubjectContainer().addTestSubjectToList(nico3);
        Study.getInstance().getTestSubjectContainer().addTestSubjectToList(andrea);
        System.out.println(Study.getInstance().getTitle());

        try {
            Study.writeToFile();
            Study.readFromFile();
            infoLblStartpage.setText("Import erfolgreich");
        } catch (IOException ioException) {
            System.out.println(ioException.getMessage());
            System.out.println(ioException.getCause());
            System.out.println(ioException.getLocalizedMessage());
            infoLblStartpage.setText("<html>" + ioException.getMessage() + "<br/>" + ioException.getCause() + "<br/>" + ioException.getLocalizedMessage() + "</html>");
        } catch (ClassNotFoundException classNotFoundException) {
            infoLblStartpage.setText(classNotFoundException.getMessage());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == importStudy) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.showDialog(null, "Ausgewählte Datei importieren");
            String filenameImport = fileChooser.getSelectedFile().getName();

            try {
                Study.readFromFile(filenameImport);
                infoLblStartpage.setText("Import erfolgreich");
                Study.getInstance().setChangesNotSaved(false);
                mainFrame.setTitle(Study.getInstance().getTitle());
            } catch (IOException ioException) {
                infoLblStartpage.setText(ioException.getMessage());
            } catch (ClassNotFoundException classNotFoundException) {
                infoLblStartpage.setText(classNotFoundException.getMessage());
            }
        }

        if (e.getSource() == newTestSubjectButton) {
            testSubjectFields();
            switchCards("newTestSubject");
        }

        if (e.getSource() == exit) {
            exitSession();
        }

        if (e.getSource() == newDoctorButton) {
            doctorFields();
            switchCards("newDoctor");
        }

        if (e.getSource() == testSubjectSaveButton) {
            saveTestSubject();
        }
        if (e.getSource() == doctorSaveButton) {
            saveDoctor();
        }

        if (e.getSource() == switchToSearch) {
            switchCards("search");
        }

        if (e.getSource() == editButtonTestSubject) {
            personView.removeAll();
            tS = resultsTestSubjects.get(0);
            editViewTestSubject(resultsTestSubjects.get(0));
        }

        if (e.getSource() == editButtonDoctor) {
            personView.removeAll();
            doc = resultsDoctor.get(0);
            editViewDoctor(resultsDoctor.get(0));
        }

        if (e.getSource() == deleteButtonTestSubject) {
            deletePerson(resultsTestSubjects.get(0));
        }

        if (e.getSource() == deleteButtonDoctor) {
            deleteDoctor(resultsDoctor.get(0));
        }

        for (int i = 0; i < buttonListMultipleSubjectsEdit.size(); i++) {
            if (e.getSource() == buttonListMultipleSubjectsEdit.get(i)) {
                tS = resultsTestSubjects.get(i);
                editViewTestSubject(resultsTestSubjects.get(i));
            }
        }

        for (int i = 0; i < buttonListMultipleSubjectsDelete.size(); i++) {
            if (e.getSource() == buttonListMultipleSubjectsDelete.get(i)) {
                deletePerson(resultsTestSubjects.get(i));
                switchCards("search");
            }
        }

        for (int i = 0; i < buttonListMultipleDoctorsEdit.size(); i++) {
            if (e.getSource() == buttonListMultipleDoctorsEdit.get(i)) {
                doc = resultsDoctor.get(i);
                editViewDoctor(resultsDoctor.get(i));
            }
        }

        for (int i = 0; i < buttonListMultipleDoctorsDelete.size(); i++) {
            if (e.getSource() == buttonListMultipleDoctorsDelete.get(i)) {
                deleteDoctor(resultsDoctor.get(i));
            }
        }

        if (e.getSource() == changeTitle) {
            String newTitle = JOptionPane.showInputDialog(mainFrame,
                    "Neuen Titel für die Studie eingeben",
                    null);
            if (newTitle != null && newTitle != "") {
                Study.getInstance().setTitle(newTitle);
                mainFrame.setTitle(Study.getInstance().getTitle());
            }
        }

        if (e.getSource() == searchButton) {

            if (searchForLastname.isSelected()) {
                if (selectTestSubject.isSelected()) {
                    listSearchResultsTestSubjects(Study.getInstance().getTestSubjectContainer().searchTestSubject(searchCriteria.getText()));
                } else if (selectDoctor.isSelected()) {
                    listSearchResultsDoctors(Study.getInstance().getDoctorContainer().searchDoctor(searchCriteria.getText()));
                }
            } else if (searchForDate.isSelected()) {
                Date searchDate = Helper.stringToDate(searchCriteria.getText());
                if (selectTestSubject.isSelected()) {
                    listSearchResultsTestSubjects(Study.getInstance().getTestSubjectContainer().searchTestSubject(searchDate));
                } else if (selectDoctor.isSelected()) {
                    listSearchResultsDoctors(Study.getInstance().getDoctorContainer().searchDoctor(searchDate));
                }
            } else if (searchForID.isSelected()) {
                if (selectTestSubject.isSelected()) {
                    listSearchResultsTestSubjects(Study.getInstance().getTestSubjectContainer().searchTestSubject(UUID.fromString(searchCriteria.getText())));
                } else if (selectDoctor.isSelected()) {
                    listSearchResultsDoctors(Study.getInstance().getDoctorContainer().searchDoctor(UUID.fromString(searchCriteria.getText())));
                }
            }
        }
        if (e.getSource() != importStudy) {
            infoLblStartpage.setText("");
        }
        if (e.getSource() == save) {
            exportData();
        }
        if (e.getSource() == saveAs) {
            Object[] options = {"Abbrechen", "Speichernn"};

            String customFilename = JOptionPane.showInputDialog(this, "Unter welchem Namen soll die Datei gespeichert werden?", Study.getInstance().getTitle());
            if (!customFilename.isEmpty()) {
                Study.setFilename(customFilename);
            } else {
                infoLblStartpage.setText("Dateiname ungültig");
                return;
            }
            exportData();
//            JFileChooser fileChooser = new JFileChooser();
//            fileChooser.setCurrentDirectory(new java.io.File("."));
//            fileChooser.setDialogTitle("Wo soll die Datei gespeichert werden?");
//            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
//            fileChooser.setAcceptAllFileFilterUsed(false);
//            FileNameExtensionFilter filter = new FileNameExtensionFilter("Study files", "study", "Study-File");
//            fileChooser.setFileFilter(filter);
//            if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
//                System.out.println("getCurrentDirectory(): "
//                        + fileChooser.getCurrentDirectory());
//                System.out.println("getSelectedFile() : "
//                        + fileChooser.getSelectedFile());
//            } else {
//                System.out.println("No Selection ");
//            }
        }
        if (e.getSource() == newStudy) {
            //JOPTION PANE MIT ABFRAGE
            clearFields();
            infoLblStartpage.setText("");
            errorLbl.setText("");
            errorLblEdit.setText("");
            Study.setFilename("Serialization.study");
            Study.getInstance().setTitle("Medizinische Studie");
            Study.getInstance().setChangesNotSaved(false);
            Study.getInstance().getTestSubjectContainer().getTestSubjectList().clear();
            Study.getInstance().getDoctorContainer().getDoctorList().clear();
        }
    }

    private void testSubjectSearchListing() {
        if (resultsTestSubjects != null && resultsTestSubjects.size() != 0) {
            listSearchResultsTestSubjects(resultsTestSubjects);
        } else {
            errorLblEdit.setText("Keine Person gefunden!");
        }
    }

    private void doctorSearchListing() {

    }

    private void deletePerson(TestSubject t) {
        Study.getInstance().getTestSubjectContainer().removeTestSubjectFromList(t);
        switchCards("startpage");
        infoLblStartpage.setText("Person erfolgreich gelöscht");
    }

    private void deleteDoctor(Doctor d) {
        Study.getInstance().getDoctorContainer().removeDoctorFromList(d);
        switchCards("startpage");
        infoLblStartpage.setText("Arzt erfolgreich gelöscht");
    }

    private void editViewTestSubject(TestSubject t) {
        testSubjectFields();
        firstName.setText(t.getFirstName());
        lastName.setText(t.getLastName());
        dateOfBirthInput.setText(t.getDateOfBirth().toString());
        setEditing(true);
        switchCards("newTestSubject");
    }

    private void editViewDoctor(Doctor d) {
        doctorFields();
        firstName.setText(d.getFirstName());
        lastName.setText(d.getLastName());
        preTitle.setText(d.getPreTitle());
        dateOfBirthInput.setText(d.getDateOfBirth().toString());
        dateOfEntryInput.setText(d.getDateOfEntry().toString());
        postTitle.setText(d.getPostTitle());
        specialty.setText(d.getSpecialty());
        setEditing(true);
        switchCards("personView");
    }

    private void listSearchResultsTestSubjects(List<TestSubject> list) {
        if (list == null || list.size() == 0) {
            errorLblEdit.setText("Keine Person gefunden!");
            return;
        }
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
        } else if (list.size() == 1) {
            personString.setText(list.get(0).toString());
            personView.add(personString);
            personView.add(editButtonTestSubject);
            personView.add(deleteButtonTestSubject);
        } else {
            return;
        }
        switchCards("personView");
    }

    private void listSearchResultsDoctors(List<Doctor> list) {
        if (list == null || list.size() == 0) {
            errorLblEdit.setText("Kein Arzt gefunden!");
            return;
        }
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
            switchCards("personView");
        } else {
            return;
        }
        switchCards("personView");
    }

    //removes all objects from panel and adds them new -> TestSubject
    private void testSubjectFields() {
        newTestSubjectPanel.removeAll();
        newTestSubjectPanel.add(genderLbl);
        newTestSubjectPanel.add(male);
        newTestSubjectPanel.add(female);
        newTestSubjectPanel.add(divers);
        newTestSubjectPanel.add(firstNameLbl);
        newTestSubjectPanel.add(firstName);
        newTestSubjectPanel.add(lastNameLbl);
        newTestSubjectPanel.add(lastName);
        newTestSubjectPanel.add(dateOfBirthLbl);
        newTestSubjectPanel.add(dateOfBirthInput);
        newTestSubjectPanel.add(errorLbl);
        newTestSubjectPanel.add(testSubjectSaveButton);
        genderButtonGroup.clearSelection();
        //clears all the fields from the panel
        clearFields();
        errorLbl.setText("");
    }

    //removes all objects from panel and adds them new -> Doctor
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
        newDoctorPanel.add(dateOfBirthLbl);
        newDoctorPanel.add(dateOfBirthInput);
        newDoctorPanel.add(specialtyLbl);
        newDoctorPanel.add(specialty);
        newDoctorPanel.add(dateOfEntryLbl);
        newDoctorPanel.add(dateOfEntryInput);
        newDoctorPanel.add(errorLbl);
        newDoctorPanel.add(doctorSaveButton);
        //clears all the fields on the panel
        clearDoctorFields();
        errorLbl.setText("");
    }

    //method for switching through the different panels
    private void switchCards(String newCard) {
        CardLayout cards = (CardLayout) rootPanel.getLayout();
        cards.show(rootPanel, newCard);

    }

    private void saveTestSubject() {
        if (!Helper.dateFieldValid(dateOfBirthInput.getText())) {
            errorLbl.setText("Geburtsdatum ungültig");
            return;
        }
        String gender = "";
        if (male.isSelected()) {
            gender = "male";
        } else if (female.isSelected()) {
            gender = "female";
        } else if (divers.isSelected()) {
            gender = "divers";
        }

        Date dateOfBirth = Helper.stringToDate(dateOfBirthInput.getText());
        if (getEditing()) {
            tS.setDateOfBirth(dateOfBirth);
            tS.setFirstName(firstName.getText());
            tS.setLastName(lastName.getText());
            errorLbl.setText("Testperson erfolgreich geändert");
            tS = null;
            setEditing(false);
            Study.getInstance().setChangesNotSaved(true);

        } else {
            TestSubject newPerson = new TestSubject(firstName.getText(), lastName.getText(), dateOfBirth, gender);
            if (Study.getInstance().getTestSubjectContainer().addTestSubjectToList(newPerson)) {
                errorLbl.setText("Testperson erfolgreich hinzugefügt");
                clearFields();
            }
        }

    }

    private boolean exportData() {
        try {
            Study.writeToFile();
            infoLblStartpage.setText("<html>Export erfolgreich<br/>Dateiname: " + Study.getFilename() + "</html>");
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private void saveDoctor() {
        if (!Helper.dateFieldValid(dateOfBirthInput.getText())) {
            errorLbl.setText("Geburtsdatum ungültig");
            return;
        }
        String gender = "";
        if (male.isSelected()) {
            gender = "male";
        } else if (female.isSelected()) {
            gender = "female";
        } else if (divers.isSelected()) {
            gender = "divers";
        }

        Date dateOfBirth = Helper.stringToDate(dateOfBirthInput.getText());
        Date dateOfEntry = Helper.stringToDate(dateOfEntryLbl.getText());

        if (!getEditing()) {
            Doctor newDoctor = new Doctor(preTitle.getText(), firstName.getText(), lastName.getText(), postTitle.getText(), dateOfBirth, specialty.getText(), gender, dateOfEntry);
            if (Study.getInstance().getDoctorContainer().addDoctorToList(newDoctor)) {
                errorLbl.setText("Arzt erfolgreich hinzugefügt");
                genderButtonGroup.clearSelection();
                setEditing(false);
            }
        } else {
            doc.setDateOfBirth(dateOfBirth);
            doc.setFirstName(firstName.getText());
            doc.setLastName(lastName.getText());
            doc.setPostTitle(postTitle.getText());
            doc.setSpecialty(specialty.getText());
            doc.setPreTitle(preTitle.getText());
            doc.setDateOfEntry(dateOfEntry);
            errorLbl.setText("Arzt erfolgreich geändert");
            doc = null;
            java.util.Date currentDate = new java.util.Date();

        }
        clearDoctorFields();
    }

    private void clearFields() {
        firstName.setText("");
        lastName.setText("");
        dateOfBirthInput.setText("");
        genderButtonGroup.clearSelection();

    }

    private void clearDoctorFields() {
        clearFields();
        preTitle.setText("");
        postTitle.setText("");
        specialty.setText("");
    }

    private void setUpMenu() {
        //add action listeners and specified keystrokes to menu items
        newStudy.addActionListener(this);
        newStudy.setAccelerator(KeyStroke.getKeyStroke('N', Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
        save.addActionListener(this);
        save.setAccelerator(KeyStroke.getKeyStroke('S', Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
        saveAs.addActionListener(this);
        saveAs.setAccelerator(KeyStroke.getKeyStroke('S', Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx() + ActionEvent.SHIFT_MASK));
        changeTitle.addActionListener(this);
        changeTitle.setAccelerator(KeyStroke.getKeyStroke('U', Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
        exit.addActionListener(this);
        exit.setAccelerator(KeyStroke.getKeyStroke('Q', Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
        //add menu items to menu category
        file.add(newStudy);
        file.add(save);
        file.add(saveAs);
        file.add(importStudy);
        file.add(exit);
        edit.add(changeTitle);
        //add menu categories to menu bar
        menubar.add(file);
        menubar.add(edit);
        menubar.add(help);
    }

    private void initialSetup() {
        //called once at start of GUI
        System.setProperty("apple.laf.useScreenMenuBar", "true");
        setUpMenu();
        //startRegion set up radio button groups
        searchButtonsGroup.add(selectDoctor);
        searchButtonsGroup.add(selectTestSubject);
        genderButtonGroup.add(male);
        genderButtonGroup.add(female);
        genderButtonGroup.add(divers);
        searchValueButtonsGroup.add(searchForLastname);
        searchValueButtonsGroup.add(searchForDate);
        searchValueButtonsGroup.add(searchForID);
        //endRegion set up radio button groups

        //startRegion set Layouts for different panels
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
        //endRegion set Layouts for different panels

        //startRegion add ActionListeners to all buttons
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
        //endRegion add ActionListeners to all buttons

        //startRegion prepare panels and the greater frame
        searchPanel.add(searchInformationLbl);
        searchPanel.add(searchCriteria);
        searchPanel.add(selectDoctor);
        searchPanel.add(selectTestSubject);
        searchPanel.add(searchForLastname);
        searchPanel.add(searchForDate);
        searchPanel.add(searchForID);
        searchPanel.add(errorLblEdit);
        searchPanel.add(searchButton);
        startpagePanel.add(newTestSubjectButton);
        startpagePanel.add(newDoctorButton);
        startpagePanel.add(switchToSearch);
        startpagePanel.add(infoLblStartpage);
        rootPanel.add(new JPanel(), "start");
        rootPanel.add(newTestSubjectPanel, "newTestSubject");
        rootPanel.add(newDoctorPanel, "newDoctor");
        rootPanel.add(searchPanel, "search");
        rootPanel.add(personView, "personView");
        mainFrame.add(splitPane, BorderLayout.CENTER);
        mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        mainFrame.setJMenuBar(menubar);
        mainFrame.setTitle(Study.getInstance().getTitle());
        mainFrame.pack();
        mainFrame.setVisible(true);
        //endRegion prepare panels and the greater frame

        switchCards("startpage");

    }

}
