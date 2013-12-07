/*
 * Created on 4/04/2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package lab.model.test;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import lab.exceptions.LabcaseException;
import lab.model.DescribedEntity;
import lab.model.assembly.AssemblyType;
import lab.model.sample.SampleType;
import lab.model.test.result.resultfactor.ResultFactor;
import lab.util.LabcaseUtils;

/**
 * @author  JuanGa
 * This persistent class save the description of each test that can be applied
 *   
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
    private List<TestPrice> prices;

    @Override
    public int compareTo(TestDescription o) {
        return this.getDescription().compareTo(o.getDescription().toString());
    }

    /**
     * Returns the price that is currently valid
     * @return
     * @throws LabcaseException if this TestDescription do not has a price currently
     */
    public BigDecimal currentPrice() throws LabcaseException{
        Date now = new Date();
        for (TestPrice price : prices){
            if (now.after(price.getValidFrom()) && now.before(price.getValidUntil())){
                return price.getPrice();
            }
        }
        throw new LabcaseException(LabcaseUtils.createMessage(
                "No existe precio definido para examen {0}", this.getDescription()));
    }

    public void setResultFactors(List<ResultFactor> resultFactors) {
        this.resultFactors = resultFactors;
    }

    public List<ResultFactor> getResultFactors() {
        return resultFactors;
    }

	public void setAssemblyType(AssemblyType assemblyType) {
		this.assemblyType = assemblyType;
	}

	public AssemblyType getAssemblyType() {
		return assemblyType;
	}

	public void setSampleType(SampleType sampleType) {
		this.sampleType = sampleType;
	}

	public SampleType getSampleType() {
		return sampleType;
	}

	public void setSaveInBank(Boolean saveInBank) {
		this.saveInBank = saveInBank;
	}

	public Boolean getSaveInBank() {
		return saveInBank;
	}

	public void setDoWeDoIt(Boolean doWeDoIt) {
		this.doWeDoIt = doWeDoIt;
	}

	public Boolean getDoWeDoIt() {
		return doWeDoIt;
	}

    public List<TestPrice> getPrices() {
        return prices;
    }

    public void setPrices(List<TestPrice> prices) {
        this.prices = prices;
    }

}
