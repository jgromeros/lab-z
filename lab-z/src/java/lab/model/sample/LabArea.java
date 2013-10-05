/**
 * 
 */
package lab.model.sample;

import java.util.Set;

import lab.model.DescribedEntity;
import lab.model.test.TestDescription;

/**
 * @author JuanGa
 *
 * TODO
 */
public class LabArea extends DescribedEntity {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private Set<TestDescription> testDescriptions;

    /**
     * @param tests The tests to set.
     */
    public void setTestDescriptions(Set<TestDescription> testDescriptions) {
        this.testDescriptions = testDescriptions;
    }

    /**
     * @return Returns the tests.
     */
    public Set<TestDescription> getTestDescriptions() {
        return testDescriptions;
    }
}
