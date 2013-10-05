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
import lab.model.bank.BankPlace;
import lab.model.labcase.Labcase;

import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * @author juanromero
 * TODO: Cuando tiene que crear los espacios de un nivel debería validar que el caso a almacenar no sea de mayor tamaño
 * que el nivel a crear (o recién creado).
 * TODO: Se propone eliminar muestras solo cuando el nivel completo cumpla la característica de tener más de un año.
 * TODO: Al ingresar un caso al banco de sueros, quizás debería ingresar los tamaños de las gradillas para seleccionar
 * el nivel en el cual se va a almacenar
 */
public class BankAction extends Action {

	public static final String LABCASES = "labcases";
	public static final String SAVE = "save";
	public static final String LIST = "banklist";
	public static final String BANKFORM = "bankform";
	public static final String SAVEBANK = "savebank";
	public static final String VIEWBANK = "viewbank";
	public static final String LEVELFORM = "dimmensions";
	public static final String SAVEACTION = "Guardar";
	public static final String DELETEACTION = "Borrar";
	public static final String ACTIONPARAMETER = "accion";
	public static final int BANCO = 0;

	public BankAction(String actionPath, String action) {
		super(actionPath, action);
	}

	/* (non-Javadoc)
	 * @see lab.web.action.Action#perform(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, org.hibernate.Session, org.hibernate.Transaction)
	 */
	@Override
	public Map<String, Object> perform(HttpServletRequest request,
			HttpServletResponse response, Session session, Transaction tx) {
		cleanModel();
		if (getAction().equals(LEVELFORM)){
			this.setAction(SAVE);
			BankPlace place = obtainBank(session, Long.parseLong(request.getParameter("bank")));
			place = place.obtainFirstUnused();
			place.createLevel(session, obtainDimmensions(request));
		}
		if (getAction().equals(LABCASES)){
			obtainLabcases(session);
			getModel().put("banks", session.createQuery("from BankPlace bp where bp.level = 0").list());
		} else if (getAction().equals(SAVE)){
			BankPlace bank = obtainBank(session, Long.parseLong(request.getParameter("bank")));
			BankPlace place = bank.obtainFirstUnused();
			if (place.getLevel() == BankPlace.LEVELFORLEVEL){
				this.setAction(LEVELFORM);
				getModel().put("level", place);
				request.getSession().setAttribute("cases", request.getParameterValues("add"));
			} else {
				List<Animal> animals = obtainAnimals(request, session);
				bank.putSamplesInBank(animals);
			}
		} else if (getAction().equals(LIST)){
			listBanks(session);
		} else if (getAction().equals(SAVEBANK)){
			saveBank(request, session);
		} else if (getAction().equals(VIEWBANK)){
			if (request.getParameter("id") != null){
				viewBank(session, Long.parseLong(request.getParameter("id")));
			}
		} else if (getAction().equals(BANKFORM)){
			if (request.getParameter("id") != null){
				long idBank = Long.parseLong(request.getParameter("id"));
				getModel().put("bank", obtainBank(session, idBank));
			}
			if (request.getParameter("parent") != null){
				getModel().put("parent", session.get(BankPlace.class, Long.parseLong(request.getParameter("parent"))));
			}
		}
		return getModel();
	}

	private void obtainLabcases(Session session) {
		List<Integer> labcasesIds = session.createSQLQuery("SELECT DISTINCT(l.id) FROM labcase l " +
				"JOIN (animal a JOIN (test t JOIN test_description td ON t.test_description = td.id) " +
				"ON a.id = t.animal) ON a.labcase = l.id WHERE l.state = 'F' AND td.save_in_bank = true " +
				"AND a.id NOT IN (SELECT bp.animal FROM bank_place bp WHERE bp.animal IS NOT NULL)").list();
		getModel().put("cases", obtainLabcases(session, labcasesIds));
	}

	private List<Labcase> obtainLabcases(Session session, List<Integer> labcasesIds) {
		List<Labcase> labcases = new ArrayList<Labcase>();
		for (long idLabcase : labcasesIds){
			labcases.add((Labcase) session.get(Labcase.class, idLabcase));
		}
		return labcases;
	}

	private List<Animal> obtainAnimals(HttpServletRequest request, Session session) {
		String[] cases = (String[]) (request.getParameterValues("add") == null?
				request.getSession().getAttribute("cases"):request.getParameterValues("add"));
		return obtainAnimals(session, cases);
	}

	private List<Animal> obtainAnimals(Session session, String[] labcasesToAdd) {
		List<Integer> labcasesIds = new ArrayList<Integer>();
		for(String s : labcasesToAdd){
			labcasesIds.add(Integer.parseInt(s));
		}
		List<Labcase> labcases = obtainLabcases(session, labcasesIds);
		return obtainAnimals(labcases);
	}

	private List<Animal> obtainAnimals(List<Labcase> labcases) {
		List<Animal> animals = new ArrayList<Animal>();
		if (labcases != null){
			for (Labcase l : labcases){
				animals.addAll(l.getAnimals());
			}
		}
		return animals;
	}

	private void listBanks(Session session) {
		getModel().put("banks", session.createQuery("from BankPlace bp where bp.placedIn IS NULL").list());
	}

	private void saveBank(HttpServletRequest request, Session session) {
		BankPlace bankPlace = bind(request, session);
		if (request.getParameter(ACTIONPARAMETER).equals(SAVEACTION)){
			session.saveOrUpdate(bankPlace);
		} else if (request.getParameter(ACTIONPARAMETER).equals(DELETEACTION)){
			bankPlace = (BankPlace) session.get(BankPlace.class, bankPlace.getId());
			if (bankPlace.getPlaces().size() == 0){
				session.delete(bankPlace);
			}
		}
	}

	private void viewBank(Session session, long id) {
		getModel().put("bankPlace", session.get(BankPlace.class, id));
	}

	private BankPlace bind(HttpServletRequest request, Session session) {
		BankPlace bankPlace = null;
		if (request.getParameter("id") != null && !request.getParameter("id").isEmpty()){
			bankPlace = (BankPlace) session.get(BankPlace.class, Long.parseLong(request.getParameter("id")));
		} else {
			bankPlace = new BankPlace();
		}
		if (request.getParameter("parent") != null && !request.getParameter("parent").isEmpty()
				&& bankPlace.getPlacedIn() == null){
			BankPlace parent = (BankPlace) session.get(BankPlace.class,
					Long.parseLong(request.getParameter("parent")));
			bankPlace.setPlacedIn(parent);
			parent.getPlaces().add(bankPlace);
		}
		bankPlace.setName(request.getParameter("name"));
		bankPlace.setLongDescription(request.getParameter("description"));
		if (bankPlace.getPlacedIn() == null){
			bankPlace.setLevel(BANCO);
		}/* else {
			bankPlace.setLevel(bankPlace.getPlacedIn().getLevel() + 1);
		}*/
		return bankPlace;
	}

	private BankPlace obtainBank(Session session, long idBank) {
		return (BankPlace) session.get(BankPlace.class, idBank);
	}

	private int[] obtainDimmensions(HttpServletRequest request) {
		int[] dimmensions = new int[4];
		dimmensions[0] = Integer.parseInt(request.getParameter("level-parts"));
		dimmensions[1] = Integer.parseInt(request.getParameter("spaces"));
		dimmensions[2] = Integer.parseInt(request.getParameter("grids"));
		dimmensions[3] = Integer.parseInt(request.getParameter("grid-places"));
		return dimmensions;
	}

}
