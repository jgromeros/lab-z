/**
 * 
 */
package lab.web.helper;

import java.util.ArrayList;
import java.util.Date;

import lab.model.animal.Animal;
import lab.model.labcase.Labcase;
import lab.model.test.Test;
import lab.model.test.TestDescription;
import lab.model.test.TestProfile;

import org.hibernate.Query;
import org.hibernate.Session;

/**
 * @author juanromero
 *
 */
public class LabcaseHelper {

	public void saveLabcase(Session session, Labcase labcase){
	    saveAnimals(session, labcase);
	    if (labcase.getId() == null){
	        labcase.setReceptionDate(new Date());
	        Query hql = session.createQuery("from Labcase l");
	        labcase.setCode("" + (hql.list().size() + 1));
	    	labcase.setStatus(Labcase.REGISTERING);
	    }
	    session.saveOrUpdate(labcase);
	}

    private void saveAnimals(Session session, Labcase labcase) {
    	for (Animal animal : labcase.getAnimals()){
    		persistAnimal(session, animal);
    	}
    }

    private void persistAnimal(Session session, Animal animal) {
    	for (Test test : animal.getTests()){
    		test.setAnimal(animal);
    		session.saveOrUpdate(test);
    	}
    	session.saveOrUpdate(animal);
    }

    /**
     * M&eacute;todo para crear los tests durante la creaci&oacute;n de un nuevo caso
     * @param session
     * @param labcase
     * @param testStrings El listado de tests a crear
     * @param discountStrings El listado de tests que se marcar&aacute;n para aplicar descuento
     */
    public void initializeTests(Session session, Labcase labcase, String[] testStrings,
            String[] profileStrings, String[] discountStrings) {
        for (Animal animal : labcase.getAnimals()){
        	if (animal.getTests() == null){
        		animal.setTests(new ArrayList<Test>());
        	}
        	mergeTestsForAnimal(session, animal, testStrings, profileStrings, discountStrings);
        }
    }

    /**
     * Adds a collection of tests created from testStrings and profileStrings to the animal.
     * It also applies the discounts that has been passed on the corresponding parameter.
     * It is important to note that the first array that is walked is the profileStrings array.
     * This is because if the user selects later one of the tests from the profile, the test
     * will be added only as part of the profile. 
     * @param session
     * @param animal
     * @param testStrings
     * @param discountStrings
     */
    private void mergeTestsForAnimal(Session session, Animal animal, String[] testStrings,
            String[] profileStrings, String[] discountStrings) {
        if (profileStrings != null && profileStrings.length > 0){
            for (String profileString : profileStrings){
                TestProfile testProfile = (TestProfile) session.get(
                        TestProfile.class, Long.valueOf(profileString));
                for (TestDescription testDescription : testProfile.getTestDescriptions()){
                    boolean added = false;
                    for(Test t : animal.getTests()){
                        if (t.getTestDescription().getId().equals(testDescription.getId())){
                            added = true;
                        }
                    }
                    if (!added){
                        animal.getTests().add(createTest(session,
                                testDescription.getId().toString(), animal, discountStrings,
                                testProfile));
                    }
                }
            }
        }
        if (testStrings != null && testStrings.length > 0){
            for (String testString : testStrings){
                boolean added = false;
                for(Test t : animal.getTests()){
                    if (t.getTestDescription().getId().equals(Long.parseLong(testString))){
                        added = true;
                    }
                }
                if (!added){
                    animal.getTests().add(createTest(session, testString, animal,
                            discountStrings, null));
                }
            }
        }
    }

    /**
     * Creates and returns a new Test with testDescription selected by parameter testString
     * and with discounts applying if needed.
     * @param session
     * @param testString
     * @param discountStrings
     * @return
     */
    private Test createTest(Session session, String testString, Animal animal,
            String[] discountStrings, TestProfile testProfile) {
        Test test = new Test();
        test.setTestDescription((TestDescription)session.get(TestDescription.class,
                Long.parseLong(testString)));
        test.setAnimal(animal);
        test.setStatus(Test.REGISTERED);
        applyDiscounts(test, testString, discountStrings);
        test.setCounterSample(Boolean.FALSE);
        test.setTestProfile(testProfile);
        return test;
    }

    /**
     * Apply discounts to Test created for testString
     * TODO: Verify how can testString be eliminated from the signature
     * @param test
     * @param testString
     * @param discountStrings
     */
    private void applyDiscounts(Test test, String testString, String[] discountStrings) {
        if (discountStrings != null){
            for (String discount : discountStrings){
                test.setApplyDiscount(Boolean.FALSE);
                if (discount.equals(testString)){
                    test.setApplyDiscount(Boolean.TRUE);
                    break;
                }
            }
        } else {
            test.setApplyDiscount(Boolean.FALSE);            
        }
    }

}
