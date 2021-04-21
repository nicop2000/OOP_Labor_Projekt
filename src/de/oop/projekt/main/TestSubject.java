package de.oop.projekt.main;
import java.util.ArrayList;
import java.util.List;
import java.util.*;

//@SuppressWarnings("unused")
public class TestSubject extends Person {

	private String testSubjectID = UUID.randomUUID().toString();


	private List<String> intolerances = new ArrayList<>();




	public String getTestSubjectID(){
		return this.testSubjectID;
	}

	public TestSubject addIntolerance(String intolerance){
		this.intolerances.add(intolerance);
		return this;
	}

	public TestSubject removeIntolerance(String intolerance){
		this.intolerances.removeIf(n ->(n == intolerance));
		return this;

	}

public String getIntolerance (){
	String intolString = "";
 for (int i = 0; i < intolerances.size(); i++){
 	intolString += intolerances.get(i);
 	if (!(i < (intolerances.size() - 1))) {
		intolString += ", ";
	}
	}
return intolString;

	}

	public TestSubject(String vorname, String nachname, Date geburtsdatum, String gender) {
		super(vorname, nachname, geburtsdatum, gender);
	}


//	public void export() throws IOException {
//		FileOutputStream fos = new FileOutputStream(filenameExport +  this.getNachname() + this.getVorname());
//		FileWriter myWriter = new FileWriter(filenameExport +  this.getNachname() + this.getVorname());
//		myWriter.write(this.toString());
//		myWriter.close();
//	}
	
	
	
	
	@Override
	public String toString() {
		return "Testperson Nr. " + testSubjectID + " " + super.toString();
	}


}
