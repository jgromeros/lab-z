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
public class Result extends Entity {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    private ResultFactor resultFactor;
    private String value;
    private AssemblyTest assemblyTest;
    private Date resultDate;
    private LabProfessional labProfessional;
    private String relativeValue;

    /**
     * @param resultFactor The resultFactor to set.
     */
    public void setResultFactor(ResultFactor resultFactor) {
        this.resultFactor = resultFactor;
    }

    /**
     * @return Returns the resultFactor.
     */
    public ResultFactor getResultFactor() {
        return resultFactor;
    }

    /**
     * @param value The value to set.
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * @return Returns the value.
     */
    public String getValue() {
        return value;
    }

	/**
	 * @param assemblyTest the assemblyTest to set
	 */
	public void setAssemblyTest(AssemblyTest assemblyTest) {
		this.assemblyTest = assemblyTest;
	}

	/**
	 * @return the assemblyTest
	 */
	public AssemblyTest getAssemblyTest() {
		return assemblyTest;
	}

	/**
	 * @param resultDate the resultDate to set
	 */
	public void setResultDate(Date resultDate) {
		this.resultDate = resultDate;
	}

	/**
	 * @return the resultDate
	 */
	public Date getResultDate() {
		return resultDate;
	}

	/**
	 * @param labProfessional the labProfessional to set
	 */
	public void setLabProfessional(LabProfessional labProfessional) {
		this.labProfessional = labProfessional;
	}

	/**
	 * @return the labProfessional
	 */
	public LabProfessional getLabProfessional() {
		return labProfessional;
	}

	/**
	 * @param relativeValue the relativeValue to set
	 */
	public void setRelativeValue(String relativeValue) {
		this.relativeValue = relativeValue;
	}

	/**
	 * @return the relativeValue
	 */
	public String getRelativeValue() {
		return relativeValue;
	}

}
