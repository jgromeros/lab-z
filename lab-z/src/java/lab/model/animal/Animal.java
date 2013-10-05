/**
 * 
 */
package lab.model.animal;

import java.util.Set;

import lab.model.NamedEntity;
import lab.model.person.LabProfessional;
import lab.model.test.Test;
import lab.model.test.TestDescription;

/**
 * @author JuanGa
 *
 * TODO
 */
public class Animal extends NamedEntity {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    private String gender;
    private Race race;
    private String age;
    private Set<Test> tests;
    private String observations;

    /**
     * @constructors
     * 
     */
    public Animal(){}

    /**
     * Constructor with most information about the animal
     * @param name
     * @param identifier
     * @param gender
     * @param race
     */
    public Animal(String name, String gender, Race race){
        this.setName(name);
        this.setGender(gender);
        this.setRace(race);
    }

    public void rename(int homonimeNumber) {
		String nuevoNombre = getName() + "-" + (char)('A' + homonimeNumber);
		setObservations(getObservations() + "\nNombre cambiado de " + getName() + " a " + nuevoNombre);
		setName(nuevoNombre);
    }

    /**
     * @param gender The gender to set.
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * @return Returns the gender.
     */
    public String getGender() {
        return gender;
    }

    /**
     * @param race The race to set.
     */
    public void setRace(Race race) {
        this.race = race;
    }
  
    /**
     * @return Returns the race.
     */
    public Race getRace() {
        return race;
    }

    /**
     * 
     * @param age
     */
	public void setAge(String age) {
		this.age = age;
	}

	/**
	 * 
	 * @return
	 */
	public String getAge() {
		return age;
	}

	/**
	 * @param tests the tests to set
	 */
	public void setTests(Set<Test> tests) {
		this.tests = tests;
	}

	/**
	 * @return the tests
	 */
	public Set<Test> getTests() {
		return tests;
	}

	/**
	 * @param observations the observations to set
	 */
	public void setObservations(String observations) {
		this.observations = observations;
	}

	/**
	 * @return the observations
	 */
	public String getObservations() {
		return observations;
	}

	public void setLabProfessional(LabProfessional labProfessional) {
		for (Test t : getTests()){
			t.setLabProfessional(labProfessional);
		}
	}

	public LabProfessional getLabProfessionalForTestDescription(TestDescription testDescription) {
		for (Test test : getTests()){
			if (test.getTestDescription().getId().equals(testDescription.getId())){
				return test.getLabProfessional();
			}
		}
		return null;
	}

}
