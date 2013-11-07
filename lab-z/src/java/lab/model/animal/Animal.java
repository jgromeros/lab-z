/**
 * 
 */
package lab.model.animal;

import java.util.List;
import java.util.Set;

import lab.model.NamedEntity;
import lab.model.person.LabProfessional;
import lab.model.test.Test;

/**
 * @author JuanGa
 *
 * TODO
 */
public class Animal extends NamedEntity {

    private static final long serialVersionUID = 1L;
    
    private String gender;
    private Race race;
    private String age;
    private List<Test> tests;
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

    /**
     * Changes name automatically. Also adds a comment about this fact
     * @param homonimeNumber
     */
    public void rename(int homonimeNumber) {
		String nuevoNombre = getName() + "-" + (char)('A' + homonimeNumber);
		setObservations(getObservations() + "\nNombre cambiado de " + getName() + " a " + nuevoNombre);
		setName(nuevoNombre);
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getGender() {
        return gender;
    }

    public void setRace(Race race) {
        this.race = race;
    }
  
    public Race getRace() {
        return race;
    }

	public void setAge(String age) {
		this.age = age;
	}

	public String getAge() {
		return age;
	}

	public void setTests(List<Test> tests) {
		this.tests = tests;
	}

	public List<Test> getTests() {
		return tests;
	}

	public void setObservations(String observations) {
		this.observations = observations;
	}

	public String getObservations() {
		return observations;
	}

	public void setLabProfessional(LabProfessional labProfessional) {
		for (Test t : getTests()){
			t.setLabProfessional(labProfessional);
		}
	}

	public LabProfessional getLabProfessionalForTest(Test test) {
		return test.getLabProfessional();
	}

}
