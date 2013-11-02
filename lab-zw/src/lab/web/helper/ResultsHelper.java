/**
 * 
 */
package lab.web.helper;

import java.util.List;

import lab.model.labcase.Labcase;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 * @author juanromero
 *
 */
public class ResultsHelper {

	/**
     * Method to load cases in state "Saved" and "With result". These are the cases which
     * hasn't been closed, so the result of this can be changed.
	 * @param session
	 * @return
	 */
	public List<Labcase> listPendingCases(Session session) {
		Query hql = session.createQuery("from Labcase where state in (:status1, :status2) order by code");
		hql.setParameter("status1", Labcase.SAVED);
		hql.setParameter("status2", Labcase.WITHRESULT);
		List<Labcase> pendingCases = hql.list();
		for (Labcase labcase : pendingCases){
			Hibernate.initialize(labcase.getEnterpriseSender());
		}
		return hql.list();
	}

}
