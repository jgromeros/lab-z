/**
 * 
 */
package lab.model.test;

import java.util.ArrayList;
import java.util.List;

import lab.model.Entity;
import lab.model.animal.Animal;
import lab.model.person.LabProfessional;
import lab.model.test.result.Result;

/**
 * @author JuanGa
 * This class saves each test that have been applied
 *
 * TODO
 */
public class Test extends Entity {

    private static final long serialVersionUID = 1L;
    
    private TestDescription testDescription;
    private List<Result> results;
    private Animal animal;
    private String observations;

    public Test(){
    	results = new ArrayList<Result>();
    }

    /**
     * Metodo para retornar el numero de resultados, pues se evalua esto para permitir impresion de reportes.
     * @return
     */
    public int getResultsSize() {
    	if (results != null){
    		return results.size();
    	}
    	return 0;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setTestDescription(TestDescription testDescription) {
        this.testDescription = testDescription;
    }

    public TestDescription getTestDescription() {
        return testDescription;
    }

	public void setAnimal(Animal animal) {
		this.animal = animal;
	}

	public Animal getAnimal() {
		return animal;
	}

	public void setLabProfessional(LabProfessional labProfessional) {
		for (Result r : getResults()){
			r.setLabProfessional(labProfessional);
		}
	}

	public LabProfessional getLabProfessional() {
		for (Result result : getResults()){
			return result.getLabProfessional();
		}
		return null;
	}

	public String getObservations() {
		return observations;
	}

	public void setObservations(String observations) {
		this.observations = observations;
	}

}
