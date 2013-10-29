/*
 * Created on 4/04/2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package lab.model.test;

import java.util.List;

import lab.model.DescribedEntity;
import lab.model.assembly.AssemblyType;
import lab.model.sample.SampleType;
import lab.model.test.result.resultfactor.ResultFactor;

/**
 * @author  JuanGa
 * This persistent class save the description of each test that can be applied
 *   
 * TODO
 */
public class TestDescription extends DescribedEntity implements Comparable<TestDescription> {

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 1L;

    /**
     * The list of result parameters that are measured for the test described
     */
    private List<ResultFactor> resultFactors;
    /**
     * There are several types of assembly. Each type describes a different configuration of
     * plaques. Usually a plaque is specific to some equipment. This object describes the
     * configuration used by this test.
     */
    private AssemblyType assemblyType;
    private SampleType sampleType;
    private Boolean saveInBank;
    private Boolean doWeDoIt;

    /**
     * @param resultFactors The resultFactors to set.
     */
    public void setResultFactors(List<ResultFactor> resultFactors) {
        this.resultFactors = resultFactors;
    }

    /**
     * @return Returns the resultFactors.
     */
    public List<ResultFactor> getResultFactors() {
        return resultFactors;
    }

	/**
	 * @param assemblyType the assemblyType to set
	 */
	public void setAssemblyType(AssemblyType assemblyType) {
		this.assemblyType = assemblyType;
	}

	/**
	 * @return the assemblyType
	 */
	public AssemblyType getAssemblyType() {
		return assemblyType;
	}

	/**
	 * @param sampleType the sampleType to set
	 */
	public void setSampleType(SampleType sampleType) {
		this.sampleType = sampleType;
	}

	/**
	 * @return the sampleType
	 */
	public SampleType getSampleType() {
		return sampleType;
	}

	/**
	 * @param saveInBank the saveInBank to set
	 */
	public void setSaveInBank(Boolean saveInBank) {
		this.saveInBank = saveInBank;
	}

	/**
	 * @return the saveInBank
	 */
	public Boolean getSaveInBank() {
		return saveInBank;
	}

	/**
	 * @param doWeDoIt the doWeDoIt to set
	 */
	public void setDoWeDoIt(Boolean doWeDoIt) {
		this.doWeDoIt = doWeDoIt;
	}

	/**
	 * @return the doWeDoIt
	 */
	public Boolean getDoWeDoIt() {
		return doWeDoIt;
	}

	@Override
	public int compareTo(TestDescription o) {
		return this.getDescription().compareTo(o.getDescription().toString());
	}

}
