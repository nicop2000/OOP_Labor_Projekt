package de.oop.projekt.main;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TestSubjectContainer implements Serializable {

	List<TestSubject> testSubjectsList = new ArrayList<>();

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


	public List<TestSubject> searchTestSubject(String lastName) {
		List<TestSubject> matchingSubjects = new ArrayList<>();
		if (testSubjectsList != null && testSubjectsList.size() >= 1) {
			for (TestSubject testSubject : testSubjectsList) {
				if (testSubject.getLastName().equalsIgnoreCase(lastName)) {
					matchingSubjects.add(testSubject);
				}
			}
		}
		return matchingSubjects;
	}

	public List<TestSubject> searchTestSubject(UUID id) {

		List<TestSubject> matchingSubjects = new ArrayList<>();
		if (testSubjectsList != null && testSubjectsList.size() >= 1) {
			for (TestSubject testSubject : testSubjectsList) {
				if (testSubject.getTestSubjectID().equals(id)) {
					matchingSubjects.add(testSubject);
				}
			}
		}
		return matchingSubjects;
	}

	public List<TestSubject> searchTestSubject(Date d) {
		List<TestSubject> matchingSubjects = new ArrayList<>();
		if (testSubjectsList != null && testSubjectsList.size() >= 1) {
			for (TestSubject testSubject : testSubjectsList) {
				if (testSubject.getDateOfBirth().equals(d)) {
					matchingSubjects.add(testSubject);
				}
			}
		}
		return matchingSubjects;
	}

	


	@Override
	public String toString() {
		return "TestpersonContainer [testpersonList=" + testSubjectsList + "]";
	}



}
