/**
 * 
 */
package lab.model.test.result;

import java.util.Date;

import lab.model.Entity;
import lab.model.assembly.AssemblyTest;
import lab.model.person.LabProfessional;
import lab.model.test.result.resultfactor.ResultFactor;

/**
 * @author JuanGa
 * This class keeps the value of each factor of the result of any test
 *
 * TODO
 */
public class Result extends Entity implements Comparable<Result> {

    private static final long serialVersionUID = 1L;
    
    private ResultFactor resultFactor;
    private String value;
    private AssemblyTest assemblyTest;
    private Date resultDate;
    private LabProfessional labProfessional;
    private String relativeValue;

    public void setResultFactor(ResultFactor resultFactor) {
        this.resultFactor = resultFactor;
    }

    public ResultFactor getResultFactor() {
        return resultFactor;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

	public void setAssemblyTest(AssemblyTest assemblyTest) {
		this.assemblyTest = assemblyTest;
	}

	public AssemblyTest getAssemblyTest() {
		return assemblyTest;
	}

	public void setResultDate(Date resultDate) {
		this.resultDate = resultDate;
	}

	public Date getResultDate() {
		return resultDate;
	}

	public void setLabProfessional(LabProfessional labProfessional) {
		this.labProfessional = labProfessional;
	}

	public LabProfessional getLabProfessional() {
		return labProfessional;
	}

	public void setRelativeValue(String relativeValue) {
		this.relativeValue = relativeValue;
	}

	public String getRelativeValue() {
		return relativeValue;
	}

	@Override
	public int compareTo(Result o) {
		return resultFactor.getNumberOrder().compareTo(o.getResultFactor().getNumberOrder());
	}

}
