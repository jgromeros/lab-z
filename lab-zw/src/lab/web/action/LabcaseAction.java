/**
 * 
 */
package lab.web.action;

import java.security.Provider;
import java.security.Security;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.URLName;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lab.exceptions.LabcaseException;
import lab.model.animal.Animal;
import lab.model.animal.Race;
import lab.model.animal.Specie;
import lab.model.enterprise.Enterprise;
import lab.model.labcase.IllegalLabcaseStatusException;
import lab.model.labcase.Labcase;
import lab.model.place.Place;
import lab.model.place.PlaceType;
import lab.model.sample.SampleType;
import lab.model.test.Test;
import lab.model.test.TestDescription;
import lab.web.helper.LabcaseHelper;
import lab.web.util.OAuth2SaslClientFactory;

import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.sun.mail.smtp.SMTPTransport;

/**
 * @author juanromero
 */
public class LabcaseAction extends Action {

    private static final Logger LOGGER = Logger.getLogger(LabcaseAction.class);

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
    private static final String FROM = "jgromero@gmail.com";
    private LabcaseHelper labcaseHelper;

    public static void initialize() {
        Security.addProvider(new OAuth2Provider());
      }

    public LabcaseAction(String actionPath, String action) {
        super(actionPath, action);
        labcaseHelper = new LabcaseHelper();
    }

    /*
     * (non-Javadoc)
     * @see lab.web.action.Action#perform(javax.servlet.http.HttpServletRequest,
     * org.hibernate.Session, org.hibernate.Transaction)
     */
    @Override
    public Map<String, Object> perform(HttpServletRequest request, HttpServletResponse response,
            Session session, Transaction tx) {
        if (getAction().equals(FORM1)) {
            showFirstForm(request, session);
        } else if (getAction().equals(FORM2)) {
            showSecondForm(request, session);
        } else if (getAction().equals(FORM3) || getAction().equals(LOAD)) {
            String actionButton = request.getParameter("action");
            if (request.getRequestURI().contains(DONE)) {
                saveLabcase(request, session);
            } else if (actionButton.equals(CARGAR)) {
                request.getSession().setAttribute("testdescriptions",
                        request.getParameterValues("testdesc"));
                setAction(LOAD);
            } else {
                showThirdForm(request, session);
                setAction(FORM3);
            }
        } else if (getAction().equals(DONE)) {
            saveLabcase(request, session);
        } else if (getAction().equals(SEARCH)) {
            loadEnterprises(session);
        } else if (getAction().equals(FOUND)) {
            getModel().put("labcases", showLabcases(request, session));
        } else if (getAction().equals(CLOSURE)) {
            labcasesToClose(request, session);
        } else if (getAction().equals(CLOSE)) {
            closeLabcases(request, session);
        }
        return getModel();
    }

    public void showFirstForm(HttpServletRequest request, Session session) {
        LOGGER.debug("showFirstForm with the following params:");
        for (String paramName : request.getParameterMap().keySet()) {
            LOGGER.debug(paramName + ": " + request.getParameterMap().get(paramName));
        }
        request.getSession().removeAttribute(LABCASE);
        if (request.getParameter("code") != null && !request.getParameter("code").isEmpty()) {
            Labcase labcase = searchLabcase(session, request.getParameter("code"));
            if (labcase.getStatus().equals(Labcase.FINISHED)) {
                throw new IllegalLabcaseStatusException(Labcase.FINISHED);
            }
            Hibernate.initialize(labcase.getAnimals().get(0).getRace().getSpecie());
            if (labcase != null) {
                request.getSession().setAttribute(LABCASE, labcase);
            }
            Query hql = session.createQuery("from Place p where p.placeType = :placeType and "
                    + "p.placedIn.id = :placedIn order by p.name");
            hql.setParameter("placeType", session.get(PlaceType.class, CITY));
            hql.setParameter("placedIn", labcase.getCity().getPlacedIn().getId());
            getModel().put("cities", hql.list());
        }
        getModel().put("enterprises",
                session.createQuery("from Enterprise e order by e.lastName, e.name").list());
        Query hql = session
                .createQuery("from Place p where p.placeType = :placeType order by p.name");
        hql.setParameter("placeType", session.get(PlaceType.class, REGION));
        getModel().put("regions", hql.list());
        getModel().put("sampleTypes",
                session.createQuery("from SampleType st order by st.description").list());
        getModel().put("species", session.createQuery("from Specie s order by s.name").list());
        LOGGER.debug("showFirstForm finished successfully");
    }

    public void showSecondForm(HttpServletRequest request, Session session) {
        LOGGER.debug("showSecondForm with the following params:");
        for (String paramName : request.getParameterMap().keySet()) {
            LOGGER.debug(paramName + ": " + request.getParameterMap().get(paramName)[0]);
        }
        Labcase labcase = (Labcase) request.getSession().getAttribute(LABCASE);
        if (labcase == null)
            labcase = new Labcase();
        loadSenderInformation(request, session, labcase);
        loadPlaceInformation(request, session, labcase);
        labcase.setAnalysisPurpose(request.getParameter("purpose"));
        labcase.setReproductiveProblem(request.getParameter("problem"));
        int number = Integer.parseInt(request.getParameter("number"));
        manageAnimals(session, labcase, number);
        request.getSession().setAttribute(LABCASE, labcase);
        Specie selectedSpecie = (Specie) session.get(Specie.class,
                Long.parseLong(request.getParameter("specie")));
        Hibernate.initialize(selectedSpecie.getRaces());
        Collections.sort(selectedSpecie.getRaces());
        request.getSession().setAttribute("selectedSpecie", selectedSpecie);
        String[] samplesString = request.getParameterValues("sampletype");
        if (samplesString == null || samplesString.length < 1) {
            throw new LabcaseException("Debe seleccionar al menos una muestra");
        }
        List<TestDescription> testDescriptions = new ArrayList<TestDescription>();
        for (int i = 0; i < samplesString.length; i++) {
            SampleType sampleType = (SampleType) session.get(SampleType.class,
                    Long.parseLong(samplesString[i]));
            Set<TestDescription> doableTests = new HashSet<TestDescription>();
            for (TestDescription td : sampleType.getAppliableTests()) {
                if (td.getDoWeDoIt()) {
                    doableTests.add(td);
                }
            }
            testDescriptions.addAll(doableTests);
            Collections.sort(testDescriptions);
        }
        getModel().put("testDescriptions", testDescriptions);
        getModel().put("profiles",
                session.createQuery("from Profile p where p.enabled = true order by p.description").list());
        LOGGER.debug("showSecondForm finished successfully");
    }

    public void showThirdForm(HttpServletRequest request, Session session) {
        LOGGER.debug("showThirdForm with the following params:");
        for (String paramName : request.getParameterMap().keySet()) {
            LOGGER.debug(paramName + ": " + request.getParameterMap().get(paramName));
        }
        Labcase labcase = (Labcase) request.getSession().getAttribute(LABCASE);
        String[] testStrings = request.getParameterValues("testdesc");
        String[] profileStrings = request.getParameterValues("profile");
        if ((testStrings == null || testStrings.length < 1)
                && (profileStrings == null || profileStrings.length < 1)) {
            throw new LabcaseException("Debe seleccionar al menos un test");
        }
        String[] discountStrings = request.getParameterValues("discount");
        if (nextAnimalIndex(request.getParameter("nextAnimalIndex")) == 0) {
            labcaseHelper.initializeTests(session, labcase, testStrings, profileStrings,
                    discountStrings);
        }
        getModel().put("nextAnimalIndex", nextAnimalIndex(request.getParameter("nextAnimalIndex")));
        getModel()
                .put("endAnimalIndex",
                        endAnimalIndex(request.getParameter("nextAnimalIndex"), labcase
                                .getAnimals().size()));
        LOGGER.debug("showThirdForm finished successfully");
    }

    public void saveLabcase(HttpServletRequest request, Session session) {
        LOGGER.debug("saveLabcase with the following params:");
        for (String paramName : request.getParameterMap().keySet()) {
            LOGGER.debug(paramName + ": " + request.getParameterMap().get(paramName));
        }
        Labcase labcase = (Labcase) request.getSession().getAttribute(LABCASE);
        String[] counterSampleStrings = request.getParameterValues("countersample");
        updateCounterSamples(labcase, counterSampleStrings);
        saveLabcase(request, session, labcase);
        if (nextAnimalIndex(request.getParameter("nextAnimalIndex")) < labcase.getAnimals().size()) {
            getModel().put("nextAnimalIndex",
                    nextAnimalIndex(request.getParameter("nextAnimalIndex")));
            getModel().put(
                    "endAnimalIndex",
                    endAnimalIndex(request.getParameter("nextAnimalIndex"), labcase.getAnimals()
                            .size()));
            this.setAction(FORM3);
        } else {
            getModel().put("caseNumber", labcase.getCode());
            this.setAction(DONE);
            labcase.setStatus(Labcase.SAVED);
            request.getSession().removeAttribute(LABCASE);
        }
        LOGGER.debug("saveLabcase finished successfully");
    }

    private void
            loadSenderInformation(HttpServletRequest request, Session session, Labcase labcase) {
        labcase.setOwner(request.getParameter("owner"));
        labcase.setEnterpriseSender((Enterprise) session.get(Enterprise.class,
                LabcaseUtil.dealWithLongParameter(request, "enterprise")));
        labcase.setSender(request.getParameter("sender"));
    }

    private void loadPlaceInformation(HttpServletRequest request, Session session, Labcase labcase) {
        labcase.setCity((Place) session.get(Place.class,
                Long.parseLong(request.getParameter("city"))));
        if (request.getParameter("zone") != null && request.getParameter("zone") != "") {
            labcase.setZone(request.getParameter("zone"));
        }
        if (request.getParameter("farm") != null && request.getParameter("farm") != "") {
            labcase.setFarm(request.getParameter("farm"));
        }
    }

    private void loadAnimals(HttpServletRequest request, Session session, Labcase labcase) {
        String[] names = request.getParameterValues("animalname");
        String[] medicalHistories = request.getParameterValues("medicalhistory");
        String[] genders = request.getParameterValues("gender");
        String[] age = request.getParameterValues("age");
        String[] races = request.getParameterValues("race");
        String[] notes = request.getParameterValues("notes");
        List<Animal> animals = labcase.getAnimals();
        int initialIndex = nextAnimalIndex(request.getParameter("nextAnimalIndex"))
                - ANIMALSPERPAGE;
        int endIndex = nextAnimalIndex(request.getParameter("nextAnimalIndex"));
        for (int i = 0; initialIndex + i < animals.size() && initialIndex + i < endIndex; i++) {
            Animal a = (Animal) animals.get(initialIndex + i);
            a.setName(names[i]);
            a.setMedicalHistory(medicalHistories[i]);
            a.setGender(genders[i]);
            a.setAge(age[i]);
            a.setRace((Race) session.get(Race.class, Long.parseLong(races[i])));
            a.setObservations(notes[i]);
        }
    }

    private int nextAnimalIndex(String indexString) {
        if (indexString != null) {
            return Integer.parseInt(indexString);
        }
        return 0;
    }

    private int endAnimalIndex(String indexCurrentString, int listSize) {
        if (nextAnimalIndex(indexCurrentString) + 19 < listSize) {
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
        for (String param : params.keySet()) {
            hql.setParameter(param, params.get(param));
        }
        List<Labcase> labcases = hql.list();
        initializeLabcases(labcases);
        return labcases;
    }

    private String createQueryForLabcases(HttpServletRequest request, Session session,
            Map<String, Object> params) {
        String query = "select l from Labcase l";
        boolean withWhere = false;
        if (request.getParameter("animalName") != null
                && !request.getParameter("animalName").isEmpty()) {
            query += " join l.animals a where a.name = :animalName";
            params.put("animalName", request.getParameter("animalName"));
            withWhere = true;
        }
        if (request.getParameter("code") != null && !request.getParameter("code").isEmpty()) {
            query += " where l.code = :code";
            params.put("code", request.getParameter("code"));
            withWhere = true;
        }
        if (request.getParameter("enterprise") != null
                && !request.getParameter("enterprise").isEmpty()) {
            if (withWhere == false) {
                query += " where l.enterpriseSender = :enterprise";
            } else {
                query += " and l.enterpriseSender = :enterprise";
            }
            params.put(
                    "enterprise",
                    session.get(Enterprise.class,
                            Long.parseLong(request.getParameter("enterprise"))));
            withWhere = true;
        }
        if (request.getParameter("owner") != null && !request.getParameter("owner").isEmpty()) {
            if (withWhere == false) {
                query += " where l.owner = :owner";
            } else {
                query += " and l.owner = :owner";
            }
            params.put("owner", request.getParameter("owner"));
            withWhere = true;
        }
        if (request.getParameter("regdate") != null && !request.getParameter("regdate").isEmpty()) {
            if (withWhere == false) {
                query += " where l.receptionDate = :receptionDate";
            } else {
                query += " and l.receptionDate = :receptionDate";
            }
            params.put("receptionDate", java.sql.Date.valueOf(request.getParameter("regdate")));
            withWhere = true;
        }
        if (request.getParameter("finished") != null) {
            if (withWhere == false) {
                query += " where l.status = 'R'";
            } else {
                query += " and l.status = 'R'";
            }
        }
        return query;
    }

    private List<Labcase> initializeLabcases(List<Labcase> labcases) {
        for (Labcase l : labcases) {
            Hibernate.initialize(l.getEnterpriseSender());
        }
        return labcases;
    }

    private void labcasesToClose(HttpServletRequest request, Session session) {
        LOGGER.debug("labcasesToClose with the following params:");
        for (String paramName : request.getParameterMap().keySet()) {
            LOGGER.debug(paramName + ": " + request.getParameterMap().get(paramName));
        }
        getModel().put("labcasesToFinish", labcasesToClose(session));
        LOGGER.debug("labcasesToClose finished successfully");
    }

    private List<Labcase> labcasesToClose(Session session) {
        Query hql = session.createQuery("from Labcase l where l.status = 'W'");
        List<Labcase> labcases = hql.list();
        labcases = initializeLabcases(labcases);
        return labcases;
    }

    /**
     * Updates de status of the case to finished and invokes the sending of the email.
     * 
     * @param request
     * @param session
     */
    private void closeLabcases(HttpServletRequest request, Session session) {
        LOGGER.debug("closeLabcases with the following params:");
        for (String paramName : request.getParameterMap().keySet()) {
            LOGGER.debug(paramName + ": " + request.getParameterMap().get(paramName));
        }
        initialize();
        for (Labcase labcase : labcasesToClose(session)) {
            labcase.setStatus(Labcase.FINISHED);
            session.saveOrUpdate(labcase);
//            sendEmail(labcase);
        }
        LOGGER.debug("closeLabcases finished successfully");
    }

    private void sendEmail(Labcase labcase) {
        String to = labcase.getEnterpriseSender().getEmail();
        String subject = "Resultados";
        String body = "Buen dia,\n\nAdjunto reporte de resultados\n\nCordialmente,\n\nLuisa Acero"
                + "\nLaboratorio\nVetest";
        String oauthToken = "ya29.dgCAaigAUeQ4-RoAAABVySSwVT4THjDbWULt1czoAWI4VuCwQblNsahdq18vbg";
        String host = "smtp.gmail.com";
        int port = 587;
        String userEmail = "jgromero@gmail.com";
        Properties props = new Properties();
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.starttls.required", "true");
        props.put("mail.smtp.sasl.enable", "true");
        props.put("mail.smtp.sasl.mechanisms", "XOAUTH2");
        props.put(OAuth2SaslClientFactory.OAUTH_TOKEN_PROP, oauthToken);
        javax.mail.Session session = javax.mail.Session.getInstance(props);
        session.setDebug(Boolean.TRUE);
        final URLName unusedUrlName = null;
        SMTPTransport transport = new SMTPTransport(session, unusedUrlName);
        final String emptyPassword = "";
        try {
            transport.connect(host, port, userEmail, emptyPassword);
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("from@no-spam.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse("to@no-spam.com"));
            message.setSubject(subject);
            message.setText(body);
            Address[] addresses = {new InternetAddress(to)};
            transport.sendMessage(message, addresses);
            transport.close();
        } catch (MessagingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    /**
     * Updates labcases setting the counter samples to true when apply. ie. when the index of the
     * animal in the list, and the id of the test description is in the list.
     * 
     * @param labcase
     * @param counterSampleStrings
     */
    private void updateCounterSamples(Labcase labcase, String[] counterSampleStrings) {
        if (counterSampleStrings != null) {
            for (String counterSampleString : counterSampleStrings) {
                String[] counterSample = counterSampleString.split("-");
                Animal animal = labcase.getAnimals().get(Integer.valueOf(counterSample[0]));
                for (Test test : animal.getTests()) {
                    if (Long.valueOf(counterSample[1]).equals(test.getTestDescription().getId())) {
                        test.setCounterSample(Boolean.TRUE);
                    }
                }
            }
        }
    }

    /**
     * Allow to create the quantity of animals in the number parameter
     * 
     * @param labcase
     * @param session
     * @param number
     */
    private void manageAnimals(Session session, Labcase labcase, int number) {
        List<Animal> animals = labcase.getAnimals();
        if (animals == null) {
            animals = new ArrayList<Animal>();
            addAnimals(session, animals, 0, number);
            labcase.setAnimals(animals);
        } else if (animals.size() < number) {
            addAnimals(session, animals, animals.size(), number);
        }
    }

    /**
     * Create and adds an animal to the list of animals.
     */
    private void addAnimals(Session session, List<Animal> animals, int currentSize, int number) {
        for (int i = currentSize; i < number; i++) {
            Animal animal = new Animal();
            animal.setName("" + (i + 1));
            initializeAnimal(session, animal);
            animals.add(animal);
        }
    }

    public static final class OAuth2Provider extends Provider {
        private static final long serialVersionUID = 1L;

        public OAuth2Provider() {
          super("Google OAuth2 Provider", 1.0,
                "Provides the XOAUTH2 SASL Mechanism");
          put("SaslClientFactory.XOAUTH2",
              "lab.web.util.OAuth2SaslClientFactory");
        }
      }

}
