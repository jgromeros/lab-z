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
            String[] discountStrings) {
        for (Animal animal : labcase.getAnimals()){
        	if (animal.getTests() == null){
        		animal.setTests(new ArrayList<Test>());
        	}
    		for (String testString : testStrings){
    			boolean added = false;
    			for(Test t : animal.getTests()){
    				if (t.getTestDescription().getId().equals(Long.parseLong(testString))){
    					added = true;
    				}
    			}
    			if (!added){
	    			Test test = new Test();
	    			test.setTestDescription((TestDescription)session.get(TestDescription.class,
	    					Long.parseLong(testString)));
	    			test.setStatus(Test.REGISTERED);
	    			for (String discount : discountStrings){
                        test.setApplyDiscount(Boolean.FALSE);
	    			    if (discount.equals(testString)){
	                        test.setApplyDiscount(Boolean.TRUE);
	                        break;
	    			    }
	    			}
	    			test.setCounterSample(Boolean.FALSE);
	               	animal.getTests().add(test);
    			}
    		}
        }
    }

}
