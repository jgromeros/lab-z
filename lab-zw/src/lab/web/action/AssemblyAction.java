/**
 * 
 */
package lab.web.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lab.model.animal.Animal;
import lab.model.assembly.Assembly;
import lab.model.assembly.AssemblyCreator;
import lab.model.assembly.AssemblyCreatorFactory;
import lab.model.assembly.AssemblyTest;
import lab.model.labcase.Labcase;
import lab.model.test.Test;
import lab.model.test.TestDescription;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * @author juanromero
 *
 */
public class AssemblyAction extends Action {

	public static final String TESTDESCRIPTIONS = "testdescriptions";
	public static final String SELECTCASES = "selectcases";
	public static final String NEWASSEMBLY = "newassembly";

	public AssemblyAction(String actionPath, String action) {
		super(actionPath, action);
	}

	/* (non-Javadoc)
	 * @see lab.web.action.Action#perform(javax.servlet.http.HttpServletRequest, org.hibernate.Session, org.hibernate.Transaction)
	 */
	@Override
	public Map<String, Object> perform(HttpServletRequest request, HttpServletResponse response,
			Session session, Transaction tx) {
		if (getAction().equals(TESTDESCRIPTIONS)){
			loadTestDescriptions(session);
		} else if (getAction().equals(SELECTCASES)){
			searchLabcases(request, session);
		} else if (getAction().equals(NEWASSEMBLY)){
			doAssembly(request, session);
		}
		return getModel();
	}

	private void loadTestDescriptions(Session session) {
    	List<TestDescription> testDescriptions = new ArrayList<TestDescription>();
    	for (TestDescription td : (List<TestDescription>)session.createQuery("from TestDescription td").list()){
    		if (td.getAssemblyType() != null){
    			testDescriptions.add(td);
    		}
    	}
    	getModel().put("testDescriptions", testDescriptions);
	}

	private void searchLabcases(HttpServletRequest request, Session session) {
		TestDescription testDescription = (TestDescription) session.get(TestDescription.class,
				Long.parseLong(request.getParameter("testdescription")));
		getModel().put("testDescription", testDescription);
		getModel().put("cases", selectCasesToAssembly(session,
				selectCasesWithStatusToAssembly(session), testDescription));
	}

	private void doAssembly(HttpServletRequest request, Session session) {
		TestDescription testDescription = (TestDescription) session.get(TestDescription.class,
				Long.parseLong(request.getParameter("testdescription")));
		String[] casesArray = request.getParameterValues("case");
		List<Labcase> labcases = new ArrayList<Labcase>();
		for (String s : casesArray) {
			labcases.add((Labcase) session.get(Labcase.class, Long.parseLong(s)));
		}
		AssemblyCreator ac = new AssemblyCreatorFactory().createAssemblyCreator(testDescription.getAssemblyType());
		Assembly assembly = ac.doMount(labcases, testDescription);
		for (AssemblyTest at : assembly.getTests()){
			if (at.getControl() != null){
				session.save(at.getControl());
			}
			session.save(at);
		}
		session.save(assembly);
		getModel().put("cases", labcases);
		getModel().put("assembly", assembly);
		getModel().put("assemblyArray", assembly.toStringArray());
	}

	private List<Labcase> selectCasesWithStatusToAssembly(Session session){
		Query hql = session.createQuery("from Labcase l where state IN (:status1, :status2)");
		hql.setParameter("status1", Labcase.SAVED);
		hql.setParameter("status2", Labcase.REGISTERING);
		List<Labcase> labcases = (List<Labcase>) hql.list();
		return labcases;
	}

	private List<Labcase> selectCasesToAssembly(Session session, List<Labcase> labcases, TestDescription testDescription){
		List<Labcase> casesToAssembly = new ArrayList<Labcase>();
		for (Labcase labcase : labcases) {
			Animal a = labcase.getAnimals().get(0);
			boolean add = false;
			for (Test t : a.getTests()) {
				if (t.getTestDescription().getId().equals(testDescription.getId())){
					Query hql = session.createQuery("from AssemblyTest where test = :test");
					hql.setParameter("test", t);
					if (hql.list().size() == 0){
						add = true;
					} else{
						break;
					}
				}
			}
			if (add){
				casesToAssembly.add(labcase);
			}
		}
		return casesToAssembly;
	}

}
