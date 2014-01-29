package lab.model.test;

import java.util.Date;
import java.util.List;

import lab.exceptions.LabcaseException;
import lab.model.DescribedEntity;
import lab.util.LabcaseUtils;

/**
 * Test profile handling
 * @author Juano
 */
public class Profile extends DescribedEntity{

    private static final long serialVersionUID = 1L;

    private List<TestDescription> testDescriptions;
    private List<TestPrice> prices;

    /**
     * Returns the price that is currently valid
     * @return
     * @throws LabcaseException if this TestDescription do not has a price currently
     */
    public TestPrice currentPrice() throws LabcaseException{
        Date now = new Date();
        for (TestPrice price : prices){
            if (now.after(price.getValidFrom()) && now.before(price.getValidUntil())){
                return price;
            }
        }
        throw new LabcaseException(LabcaseUtils.createMessage(
                "No existe precio definido para perfil {0}", this.getDescription()));
    }

    public List<TestDescription> getTestDescriptions() {
        return testDescriptions;
    }

    public void setTestDescriptions(List<TestDescription> testDescriptions) {
        this.testDescriptions = testDescriptions;
    }

    public List<TestPrice> getPrices() {
        return prices;
    }

    public void setPrices(List<TestPrice> prices) {
        this.prices = prices;
    }

}
