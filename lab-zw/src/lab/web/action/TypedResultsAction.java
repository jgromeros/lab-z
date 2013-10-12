/**
 * 
 */
package lab.web.action;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lab.model.animal.Animal;
import lab.model.labcase.Labcase;
import lab.model.person.LabProfessional;
import lab.model.test.Test;
import lab.model.test.TestDescription;
import lab.model.test.result.Result;
import lab.model.test.result.resultfactor.ReferenceValue;
import lab.model.test.result.resultfactor.ResultFactor;
import lab.web.helper.ResultsHelper;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * @author juanromero
 *
 */
public class TypedResultsAction extends Action {

	public static final String LISTPENDINGCASES = "listcases";
	public static final String CASESELECTED = "caseresult";
	public static final String TESTSELECTED = "testresult";
	public static final String REGISTERED = "registered";

	public TypedResultsAction(String actionPath, String action) {
		super(actionPath, action);
	}

	/* (non-Javadoc)
	 * @see lab.web.action.Action#perform(javax.servlet.http.HttpServletRequest, org.hibernate.Session, org.hibernate.Transaction)
	 */
	@Override
	public Map<String, Object> perform(HttpServletRequest request, HttpServletResponse response,
			Session session, Transaction tx) {
		if (getAction().equals(LISTPENDINGCASES)){
			getModel().put("pendingCases", new ResultsHelper().listPendingCases(session));
		} else if (getAction().equals(CASESELECTED)){
			loadCase(request, session);
		} else if (getAction().equals(TESTSELECTED)){
			loadTest(request, session);
		} else if (getAction().equals(REGISTERED)){
			saveResult(request, session);
		}
		return getModel();
	}

	/**
	 * Method to show the selected labcase. It shows the tests to the Model, so the user can select which
	 * result to fill in this run. 
	 * @param request
	 * @param session
	 */
	private void loadCase(HttpServletRequest request, Session session) {
		Labcase labcase = (Labcase) session.get(Labcase.class, Long.parseLong(request.getParameter("id")));
		request.getSession().setAttribute("labcase", labcase);
		List<Animal> animals = labcase.getAnimals();
		Animal animal = animals.get(0);
		for (Test test : animal.getTests()){
			Hibernate.initialize(test.getTestDescription());
		}
	}

	/**
	 * Once the user have selected the test to register results, this method shows the form to fill
	 * that results.
	 * @param request
	 */
	public void loadTest(HttpServletRequest request, Session session){
		getModel().put("testdesc", Long.parseLong(request.getParameter("testdesc")));
		List<LabProfessional> labpros = session.createQuery("from LabProfessional lp where status = 'A'").list();
		getModel().put("labpros", labpros);
		getModel().put("techdirectors", selectTechDirectors(labpros));
		Test test = null;
		Labcase l = (Labcase) session.get(Labcase.class, ((Labcase)request.getSession().getAttribute("labcase")).getId());
		for (Animal animal : l.getAnimals()){
			for (Test t : animal.getTests()){
				if (t.getTestDescription().getId().equals(getModel().get("testdesc"))){
					test = t;
					Long leucocitos = null;
					if (test.getResults().size() == 0){
						for (ResultFactor rf : test.getTestDescription().getResultFactors()){
							Result result = new Result();
							result.setResultFactor(rf);
							result.setResultDate(new Date());
							session.save(result);
							test.getResults().add(result);
						}
					} else {
						for (Result result : test.getResults()){
							if (result.getResultFactor().getId() == 64){//Leucocitos
								leucocitos = result.getValue() == null ? null : Long.parseLong(result.getValue());
							}
							if (result.getResultFactor().getComputedValue() == true && result.getResultFactor().getCalculated() == true
									&& result.getValue() != null){
								result.setRelativeValue("" + ((Double.parseDouble(result.getValue()) * 100)/ leucocitos));
								result.setValue(result.getValue());
							}
						}
					}
				}
			}
		}
		getModel().put("testDescription", test.getTestDescription().getDescription());
		List<ReferenceValue> referenceValues = new ArrayList<ReferenceValue>();
		Query hql = session.createQuery("from ReferenceValue rv where rv.resultFactor = :resultFactor and specie = :specie");
		hql.setParameter("specie", l.getAnimals().get(0).getRace().getSpecie());
		for (ResultFactor rf : test.getTestDescription().getResultFactors()){
			hql.setParameter("resultFactor", rf);
			referenceValues.addAll(hql.list());
		}
		getModel().put("references", referenceValues);
		request.getSession().setAttribute("labcase", l);
	}

	/**
	 * Method to retrieve the information of the results of a test and persist it
	 * @param request
	 * @param response
	 * @return
	 */
	public void saveResult(HttpServletRequest request, Session session){
		Labcase labcase = (Labcase) request.getSession().getAttribute("labcase");
		List<Animal> animals = labcase.getAnimals();
		for (Animal animal : animals){
			for (Test test : animal.getTests()){
				TestDescription testDescription = test.getTestDescription();
				Set<Result> results = test.getResults();
				if (results.size() == 0){
					for (ResultFactor resultFactor : testDescription.getResultFactors()){
						Result result = new Result();
						if(request.getParameter("action").equals("Calcular")){
							if (resultFactor.getComputedValue() == true && resultFactor.getCalculated() == true){
								result.setRelativeValue(request.getParameter("test" + test.getId() + "relativefactor" + resultFactor.getId()));
								result.setValue("16,66");
							}
							if (resultFactor.getCalculated() == true && resultFactor.getComputedValue() == false){
								result.setValue("1300");
							}
						}
						result.setResultFactor(resultFactor);
						result.setResultDate(new Date());
						result.setValue(request.getParameter("test" + test.getId() + "factor" + resultFactor.getId()));
						session.save(result);
						results.add(result);
					}
				} else {
					double leucocitos = 0;
					double hematies = 0;
					double hematocrito = 0;
					double hemoglobina = 0;
					for (Result result : results){
						if (test.getTestDescription().getId() == 57){//Cuadro hematico
							if (!result.getResultFactor().getComputedValue() && !result.getResultFactor().getCalculated()){
								result.setValue(request.getParameter("test" + test.getId() + "factor" + result.getResultFactor().getId()));
								if (result.getResultFactor().getId() == 64){//Leucocitos
									leucocitos = Double.parseDouble(result.getValue());
								} else if (result.getResultFactor().getId() == 58){//Hematies
									hematies = Double.parseDouble(result.getValue());
								} else if (result.getResultFactor().getId() == 59){//Hematocrito
									hematocrito = Double.parseDouble(result.getValue());
								} else if (result.getResultFactor().getId() == 60){//Hemoglobina
									hemoglobina = Double.parseDouble(result.getValue());
								}
							}
							session.saveOrUpdate(result);
						} else {
							for (ResultFactor resultFactor : testDescription.getResultFactors()){
								result.setValue(request.getParameter("test" + test.getId() + "factor" + resultFactor.getId()));
							}
							session.saveOrUpdate(result);
						}
					}
					//Iteracion para los valores calculados
					if (test.getTestDescription().getId() == 57){//Cuadro hematico
						for (Result result : results){
							if (result.getResultFactor().getCalculated() && !result.getResultFactor().getComputedValue()){
								if (result.getResultFactor().getId() == 61){//VCM
									BigDecimal valor = new BigDecimal((hematocrito * 10)/hematies, new MathContext(4));
									result.setValue(valor.toPlainString());
								} else if (result.getResultFactor().getId() == 62){//HCM
									BigDecimal valor = new BigDecimal((hemoglobina * 10)/hematies, new MathContext(4));
									result.setValue(valor.toPlainString());
								} else if (result.getResultFactor().getId() == 63){//CCMH
									BigDecimal valor = new BigDecimal((hemoglobina * 100)/hematocrito, new MathContext(4));
									result.setValue(valor.toPlainString());
								}
							} else if (result.getResultFactor().getCalculated() && result.getResultFactor().getComputedValue()){
								result.setRelativeValue(request.getParameter("test" + test.getId() + "relativefactor" + result.getResultFactor().getId()));
								BigDecimal valor = new BigDecimal(result.getRelativeValue());
								valor = (valor.multiply(new BigDecimal(leucocitos))).divide(new BigDecimal(100));
								result.setValue(valor.toPlainString());
							}
							session.saveOrUpdate(result);
						}
					}
				}
				if (request.getParameter("obs_test" + test.getId())!= null && !request.getParameter("obs_test" + test.getId()).isEmpty()){
					test.setObservations(request.getParameter("obs_test" + test.getId()));
				}
				session.update(test);
			}
		}
		if (!request.getParameter("action").equals("Calcular")){
			updateLabcaseData(request, session, labcase);
			labcase.setStatus(Labcase.WITHRESULT);
			session.update(labcase);
		}
	}

	private List<LabProfessional> selectTechDirectors(List<LabProfessional> labpros) {
		List<LabProfessional> techdirs = new ArrayList<LabProfessional>();
		for (LabProfessional lp : labpros){
			if (lp.isTechnicalDirector()){
				techdirs.add(lp);
			}
		}
		return techdirs;
	}

	private void updateLabcaseData(HttpServletRequest request, Session session, Labcase labcase) {
		if (request.getParameter("observations")!= null && !request.getParameter("observations").isEmpty()){
			labcase.setObservations(request.getParameter("observations"));
		}
		labcase.setLabProfessional((LabProfessional) session.get(LabProfessional.class,
				Long.parseLong(request.getParameter("lab_professional"))));
		labcase.setTechnicalDirector((LabProfessional) session.get(LabProfessional.class,
				Long.parseLong(request.getParameter("tech_dir"))));
		if (labcase.getIcaNumber() == null){
			int icaNumber = (Integer) session.createSQLQuery("SELECT numero_foliado(1)").uniqueResult();
			labcase.setIcaNumber(icaNumber);
		}

	}
}
