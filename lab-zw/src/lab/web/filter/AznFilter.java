package lab.web.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import lab.model.persistence.HibernateUtil;
import lab.security.Role;
import lab.security.Section;
import lab.security.User;

import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Servlet Filter implementation class AznFilter
 */
public class AznFilter implements Filter {

    /**
     * Default constructor. 
     */
    public AznFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		if (((HttpServletRequest)request).getUserPrincipal() != null){
			String userName = ((HttpServletRequest)request).getUserPrincipal().getName();
			((HttpServletRequest)request).getSession().setAttribute("username", userName);
			((HttpServletRequest)request).getSession().setAttribute("sections", getSections(userName));
		}
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

	private List<Section> getSections(String userName) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		User user = (User) session.get(User.class, userName);
		List<Section> sections = new ArrayList<Section>();
		for (Role role : user.getRoles()){
			sections.addAll(role.getSections());
		}
		Collections.sort(sections);
		tx.commit();
		session.close();
		return sections;
	}

}
