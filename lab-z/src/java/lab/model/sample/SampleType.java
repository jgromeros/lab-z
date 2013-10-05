/*
 * Created on 4/04/2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package lab.model.sample;

import java.util.HashSet;
import java.util.Set;

import lab.model.DescribedEntity;
import lab.model.test.TestDescription;

/**
 * @author JuanGa
 *
 * TODO
 */
public class SampleType extends DescribedEntity {

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 1L;
    
    private Set<TestDescription> appliableTests;

    /**
     * @param applicableTests The applicableTests to set.
     */
    public void setAppliableTests(Set<TestDescription> appliableTests) {
        this.appliableTests = appliableTests;
    }

    /**
     * @return Returns the applicableTests.
     */
    public Set<TestDescription> getAppliableTests() {
    	return appliableTests;
    }

}
