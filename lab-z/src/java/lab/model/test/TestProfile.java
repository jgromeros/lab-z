package lab.model.test;

import java.util.List;

import lab.model.DescribedEntity;

/**
 * Test profile handling
 * @author Juano
 */
public class TestProfile extends DescribedEntity{

    private static final long serialVersionUID = 1L;

    private List<TestDescription> testDescriptions;

    public List<TestDescription> getTestDescriptions() {
        return testDescriptions;
    }

    public void setTestDescriptions(List<TestDescription> testDescriptions) {
        this.testDescriptions = testDescriptions;
    }

}
