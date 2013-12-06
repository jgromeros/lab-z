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
 * This class saves each test that have been applied. It saves also the results for the test.
 * It can only have one of two status: 'R' for registered and 'C' for cancelled
 * @author Juano
 */
public class Test extends Entity {

    private static final long serialVersionUID = 1L;

    public static final String REGISTERED = "R";
    public static final String CANCELLED = "C";

    private TestDescription testDescription;
    private List<Result> results;
    private Animal animal;
    private String observations;
    private String status;
    private Boolean counterSample;
    private Boolean applyDiscount;

    public Test(){
    	results = new ArrayList<Result>();
    }

    /**
     * Metodo para retornar el numero de resultados, pues se evalua esto para permitir
     * impresion de reportes.
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean getCounterSample() {
        return counterSample;
    }

    public void setCounterSample(Boolean counterSample) {
        this.counterSample = counterSample;
    }

    public Boolean getApplyDiscount() {
        return applyDiscount;
    }

    public void setApplyDiscount(Boolean applyDiscount) {
        this.applyDiscount = applyDiscount;
    }

}
