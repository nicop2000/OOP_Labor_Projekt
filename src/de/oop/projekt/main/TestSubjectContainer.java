package de.oop.projekt.main;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TestSubjectContainer implements Serializable {

	List<TestSubject> testSubjectsList = new ArrayList<>();

	private static TestSubjectContainer instance;

	public List<TestSubject> getTestSubjectList() {
		return testSubjectsList;
	}


	public boolean addTestSubjectToList(TestSubject t) {
		testSubjectsList.add(t);
		Study.getInstance().setChangesNotSaved(true);
		return true;
		
	}
	
	public void removeTestSubjectFromList(TestSubject p) {
		testSubjectsList.removeIf(n -> (n == p)); //PredicateFilter
		Study.getInstance().setChangesNotSaved(true);
	}
	//return referenz auf Person, also testpersonList.get(i)
	
	public List<TestSubject> searchForTestSubjectIndexInList(TestSubject p) {
		List<TestSubject> matchingSubjects = new ArrayList<>();
		for (TestSubject testSubject : testSubjectsList) {
			if (testSubject.equals(p)) {
				matchingSubjects.add(testSubject);
			}
		}
		return matchingSubjects;
	}

	public List<TestSubject> searchTestSubjectByLastName(String lastName) {
		List<TestSubject> matchingSubjects = new ArrayList<>();
		if (testSubjectsList.size() > 1) {
			for (TestSubject testSubject : testSubjectsList) {
				if (testSubject.getLastName().equalsIgnoreCase(lastName)) {
					matchingSubjects.add(testSubject);
				}
			}
		} else if (testSubjectsList.size() == 1 ){
			if (testSubjectsList.get(0).getLastName().equalsIgnoreCase(lastName)) {
				matchingSubjects.add(testSubjectsList.get(0));
			}
		}
		return matchingSubjects;
	}

	public List<TestSubject> searchTestSubjectByID(String id) {
		List<TestSubject> matchingSubjects = new ArrayList<>();
		return matchingSubjects;
	}

	public List<TestSubject> searchTestSubjectByDateOfBirth(Date d) {
		return null;
	}

	public int searchTestpersonByAddress(Address a) {
		for (int i = 0; i < testSubjectsList.size(); i++) {
//			if(testpersonList.get(i).address.equals(a)) {
//				return i;
//			}
		}
		return -1;
		
	}
	


	@Override
	public String toString() {
		return "TestpersonContainer [testpersonList=" + testSubjectsList + "]";
	}



}
