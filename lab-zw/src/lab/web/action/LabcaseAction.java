/**
 * 
 */
package lab.web.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lab.model.animal.Animal;
import lab.model.animal.Race;
import lab.model.animal.Specie;
import lab.model.enterprise.Enterprise;
import lab.model.labcase.IllegalLabcaseStatusException;
import lab.model.labcase.Labcase;
import lab.model.place.Place;
import lab.model.place.PlaceType;
import lab.model.sample.SampleType;
import lab.model.test.TestDescription;
import lab.web.helper.LabcaseHelper;

import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * @author juanromero
 *
 */
public class LabcaseAction extends Action {

    private static Logger logger = Logger.getLogger(LabcaseAction.class);

	private static final Long REGION = new Long(1);
	private static final Long CITY = new Long(2);
	public static final String FORM1 = "page1";
	public static final String FORM2 = "page2";
	public static final String FORM3 = "page3";
	public static final String DONE = "done";
	public static final String SEARCH = "search";
	public static final String FOUND = "found";
	public static final String CLOSURE = "closure";
	public static final String CLOSE = "close";
	private static final String LABCASE = "labcase";
	private static final String CARGAR = "Cargar";
	private static final String LOAD = "load";
	private static final int ANIMALSPERPAGE = 20;

	private LabcaseHelper labcaseHelper;

	public LabcaseAction(String actionPath, String action) {
		super(actionPath, action);
		labcaseHelper = new LabcaseHelper();
	}

	/* (non-Javadoc)
	 * @see lab.web.action.Action#perform(javax.servlet.http.HttpServletRequest, org.hibernate.Session,
	 * org.hibernate.Transaction)
	 */
	@Override
	public Map<String, Object> perform(HttpServletRequest request, HttpServletResponse response,
			Session session, Transaction tx) {
		if (getAction().equals(FORM1)){
			showFirstForm(request, session);
		} else if (getAction().equals(FORM2)){
			showSecondForm(request, session);
		} else if (getAction().equals(FORM3) || getAction().equals(LOAD)){
			String actionButton = request.getParameter("action");
			if(request.getRequestURI().contains(DONE)){
				saveLabcase(request, session);
			} else if (actionButton.equals(CARGAR)){
				request.getSession().setAttribute("testdescriptions", request.getParameterValues("testdesc"));
				setAction(LOAD);
			} else {
				showThirdForm(request, session);
				setAction(FORM3);
			}
		} else if (getAction().equals(DONE)){
			saveLabcase(request, session);
		} else if (getAction().equals(SEARCH)){
			loadEnterprises(session);
		} else if (getAction().equals(FOUND)) {
	    	getModel().put("labcases", showLabcases(request, session));
		} else if (getAction().equals(CLOSURE)){
			labcasesToClose(request, session);
		} else if (getAction().equals(CLOSE)){
			closeLabcases(request, session);
		}
		return getModel();
	}

	public void showFirstForm(HttpServletRequest request, Session session){
	    logger.debug("showFirstForm with the following params:");
	    for (String paramName : request.getParameterMap().keySet()){
	        logger.debug(paramName + ": " + request.getParameterMap().get(paramName));
	    }
		request.getSession().removeAttribute(LABCASE);
		if (request.getParameter("code") != null && !request.getParameter("code").isEmpty()){
			Labcase labcase = searchLabcase(session, request.getParameter("code"));
			if (labcase.getStatus().equals(Labcase.FINISHED)){
				throw new IllegalLabcaseStatusException(Labcase.FINISHED);
			}
			Hibernate.initialize(labcase.getAnimals().get(0).getRace().getSpecie());
			if (labcase != null){
				request.getSession().setAttribute(LABCASE, labcase);
			}
		}
		getModel().put("enterprises", session.createQuery("from Enterprise e order by e.lastName, e.name").list());
		Query hql = session.createQuery("from Place p where p.placeType = :placeType order by p.name");
		hql.setParameter("placeType", session.get(PlaceType.class, REGION));
		getModel().put("regions", hql.list());
		getModel().put("sampleTypes", session.createQuery("from SampleType st order by st.description").list());
		getModel().put("species", session.createQuery("from Specie s order by s.name").list());
		logger.debug("showFirstForm finished successfully");
	}

	public void showSecondForm(HttpServletRequest request, Session session) {
        logger.debug("showSecondForm with the following params:");
        for (String paramName : request.getParameterMap().keySet()){
            logger.debug(paramName + ": " + request.getParameterMap().get(paramName)[0]);
        }
        Labcase labcase = (Labcase) request.getSession().getAttribute(LABCASE);
        if (labcase == null)
        	labcase = new Labcase();
        loadSenderInformation(request, session, labcase);
        loadPlaceInformation(request, session, labcase);
        labcase.setAnalysisPurpose(request.getParameter("purpose"));
        labcase.setReproductiveProblem(request.getParameter("problem"));
        int number = Integer.parseInt(request.getParameter("number"));
        if (labcase.getAnimals() == null){
			List<Animal> animals = new ArrayList<Animal>();
	    	for (int i = 0; i < number; i++){
	    		Animal animal = new Animal();
	    		animal.setName("" + (i + 1));
	    		initializeAnimal(session, animal);
	    		animals.add(animal);
	    	}
	    	labcase.setAnimals(animals);
        }
        request.getSession().setAttribute(LABCASE, labcase);
        Specie selectedSpecie = (Specie) session.get(Specie.class,
        		Long.parseLong(request.getParameter("specie")));
        Hibernate.initialize(selectedSpecie.getRaces());
        Collections.sort(selectedSpecie.getRaces());
        request.getSession().setAttribute("selectedSpecie", selectedSpecie);
        String[] samplesString = request.getParameterValues("sampletype");
        List<TestDescription> testDescriptions = new ArrayList<TestDescription>();
        for (int i = 0; i < samplesString.length; i++){
        	SampleType sampleType = (SampleType) session.get(SampleType.class,
        			Long.parseLong(samplesString[i]));
        	Set<TestDescription> doableTests = new HashSet<TestDescription>();
        	for (TestDescription td : sampleType.getAppliableTests()){
        		if (td.getDoWeDoIt()){
        			doableTests.add(td);
        		}
        	}
        	testDescriptions.addAll(doableTests);
        	Collections.sort(testDescriptions);
        }
        getModel().put("testDescriptions", testDescriptions);
        logger.debug("showSecondForm finished successfully");
	}

	public void showThirdForm(HttpServletRequest request, Session session){
        logger.debug("showThirdForm with the following params:");
        for (String paramName : request.getParameterMap().keySet()){
            logger.debug(paramName + ": " + request.getParameterMap().get(paramName));
        }
        Labcase labcase = (Labcase) request.getSession().getAttribute(LABCASE);
        String[] testStrings = request.getParameterValues("testdesc");
    	if (nextAnimalIndex(request.getParameter("nextAnimalIndex")) == 0){
    		labcaseHelper.initializeTests(session, labcase, testStrings);
    	}
    	getModel().put("nextAnimalIndex", nextAnimalIndex(request.getParameter("nextAnimalIndex")));
    	getModel().put("endAnimalIndex", endAnimalIndex(request.getParameter("nextAnimalIndex"),
    			labcase.getAnimals().size()));
        logger.debug("showThirdForm finished successfully");
	}

	public void saveLabcase(HttpServletRequest request, Session session) {
        logger.debug("saveLabcase with the following params:");
        for (String paramName : request.getParameterMap().keySet()){
            logger.debug(paramName + ": " + request.getParameterMap().get(paramName));
        }
        Labcase labcase = (Labcase) request.getSession().getAttribute(LABCASE);
        saveLabcase(request, session, labcase);
        if (nextAnimalIndex(request.getParameter("nextAnimalIndex")) < labcase.getAnimals().size()){
        	getModel().put("nextAnimalIndex", nextAnimalIndex(request.getParameter("nextAnimalIndex")));
        	getModel().put("endAnimalIndex", endAnimalIndex(request.getParameter("nextAnimalIndex"),
        			labcase.getAnimals().size()));
        	this.setAction(FORM3);
        } else{
        	getModel().put("caseNumber", labcase.getCode());
        	this.setAction(DONE);
        	labcase.setStatus(Labcase.SAVED);
            request.getSession().removeAttribute(LABCASE);
        }
        logger.debug("saveLabcase finished successfully");
	}

	private void loadSenderInformation(HttpServletRequest request, Session session, Labcase labcase) {
        labcase.setOwner(request.getParameter("owner"));
        labcase.setEnterpriseSender((Enterprise) session.get(Enterprise.class,
        		new Long(request.getParameter("enterprise"))));
        labcase.setSender(request.getParameter("sender"));
    }

    private void loadPlaceInformation(HttpServletRequest request, Session session, Labcase labcase) {
        labcase.setCity((Place) session.get(Place.class, Long.parseLong(request.getParameter("city"))));
        if (request.getParameter("zone") != null && request.getParameter("zone") != ""){
        	labcase.setZone(request.getParameter("zone"));
        }
        if(request.getParameter("farm") != null && request.getParameter("farm") != ""){
        	labcase.setFarm(request.getParameter("farm"));
        }
    }

	private void loadAnimals(HttpServletRequest request, Session session, Labcase labcase) {
		String[] names = request.getParameterValues("animalname");
		String[] genders = request.getParameterValues("gender");
		String[] age = request.getParameterValues("age");
		String[] races = request.getParameterValues("race");
		String[] notes = request.getParameterValues("notes");
		List<Animal> animals = labcase.getAnimals();
		int initialIndex = nextAnimalIndex(request.getParameter("nextAnimalIndex")) - ANIMALSPERPAGE;
		int endIndex = nextAnimalIndex(request.getParameter("nextAnimalIndex"));
		for (int i = 0; initialIndex + i < animals.size() && initialIndex + i < endIndex; i++){
			Animal a = (Animal) animals.get(initialIndex + i);
			a.setName(names[i]);
			a.setGender(genders[i]);
			a.setAge(age[i]);
			a.setRace((Race) session.get(Race.class, Long.parseLong(races[i])));
			a.setObservations(notes[i]);
		}
    }

    private int nextAnimalIndex(String indexString) {
    	if (indexString != null){
    		return Integer.parseInt(indexString);
    	}
    	return 0;
    }

    private int endAnimalIndex(String indexCurrentString, int listSize) {
    	if (nextAnimalIndex(indexCurrentString) + 19 < listSize){
    		return nextAnimalIndex(indexCurrentString) + 19;
    	}
    	return listSize - 1;
    }

    private void initializeAnimal(Session session, Animal animal) {
    	animal.setGender("H");
    	animal.setRace((Race) session.get(Race.class, 6L));
    }

    private void saveLabcase(HttpServletRequest request, Session session, Labcase labcase) {
        loadAnimals(request, session, labcase);
        labcaseHelper.saveLabcase(session, labcase);
    }

    private Labcase searchLabcase(Session session, String code) {
    	Query hql = session.createQuery("from Labcase l where l.code = :code");
    	hql.setParameter("code", code);
    	return (Labcase) hql.uniqueResult();
    }

    private void loadEnterprises(Session session) {
    	Query hql = session.createQuery("from Enterprise");
    	getModel().put("enterprises", hql.list());
    }

    private List<Labcase> showLabcases(HttpServletRequest request, Session session) {
    	Query hql = null;
    	Map<String, Object> params = new HashMap<String, Object>();
    	hql = session.createQuery(createQueryForLabcases(request, session, params));
    	for (String param : params.keySet()){
    		hql.setParameter(param, params.get(param));
    	}
    	List<Labcase> labcases = hql.list();
    	initializeLabcases(labcases);
    	return labcases;
    }

    private String createQueryForLabcases(HttpServletRequest request, Session session, Map<String, Object> params) {
    	String query = "from Labcase l";
    	boolean withWhere = false;
    	if (request.getParameter("code") != null && !request.getParameter("code").isEmpty()){
    		query += " where l.code = :code";
    		params.put("code", request.getParameter("code"));
    		withWhere = true;
    	}
    	if (request.getParameter("enterprise") != null && !request.getParameter("enterprise").isEmpty()){
    		if (withWhere == false){
    			query += " where l.enterpriseSender = :enterprise";
    		} else {
    			query += " and l.enterpriseSender = :enterprise";
    		}
        	params.put("enterprise", session.get(Enterprise.class, Long.parseLong(request.getParameter("enterprise"))));
    		withWhere = true;
    	} 
    	if (request.getParameter("owner") != null && !request.getParameter("owner").isEmpty()){
    		if (withWhere == false){
    			query += " where l.owner = :owner";
    		} else {
    			query += " and l.owner = :owner";
    		}
        	params.put("owner", request.getParameter("owner"));
    		withWhere = true;
    	}
    	if (request.getParameter("regdate") != null && !request.getParameter("regdate").isEmpty()){
    		if (withWhere == false){
    			query += " where l.receptionDate = :receptionDate";
    		} else {
    			query += " and l.receptionDate = :receptionDate";
    		}
        	params.put("receptionDate", java.sql.Date.valueOf(request.getParameter("regdate")));
    		withWhere = true;
    	}
    	if (request.getParameter("finished") != null){
    		if (withWhere == false){
    			query += " where l.status = 'R'";
    		} else {
    			query += " and l.status = 'R'";
    		}
    	}
    	return query;
    }

    private List<Labcase> initializeLabcases(List<Labcase> labcases) {
    	for (Labcase l : labcases){
    		Hibernate.initialize(l.getEnterpriseSender());
    	}
    	return labcases;
    }

    private void labcasesToClose(HttpServletRequest request, Session session) {
        logger.debug("labcasesToClose with the following params:");
        for (String paramName : request.getParameterMap().keySet()){
            logger.debug(paramName + ": " + request.getParameterMap().get(paramName));
        }
    	getModel().put("labcasesToFinish", labcasesToClose(session));
        logger.debug("labcasesToClose finished successfully");
    }

    private List<Labcase> labcasesToClose(Session session) {
    	Query hql = session.createQuery("from Labcase l where l.status = 'W'");
    	List<Labcase> labcases = hql.list();
    	labcases = initializeLabcases(labcases);
    	return labcases;
    }

	private void closeLabcases(HttpServletRequest request, Session session) {
        logger.debug("closeLabcases with the following params:");
        for (String paramName : request.getParameterMap().keySet()){
            logger.debug(paramName + ": " + request.getParameterMap().get(paramName));
        }
		for (Labcase labcase : labcasesToClose(session)){
			labcase.setStatus(Labcase.FINISHED);
		    session.saveOrUpdate(labcase);
		}
        logger.debug("closeLabcases finished successfully");
	}

}
