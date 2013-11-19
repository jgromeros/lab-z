package lab.web.servlet;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lab.model.persistence.HibernateUtil;
import lab.model.place.Place;
import lab.model.place.PlaceType;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.google.gson.Gson;

public class ParamsServlet extends HttpServlet {

    private static final Long CITY = new Long(2);

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String region = request.getParameter("regionname");
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        Query hql = session.createQuery("from Place p where p.placeType = :placeType and " +
        		"p.placedIn.id = :placedIn order by p.name");
        hql.setParameter("placeType", session.get(PlaceType.class, CITY));
        hql.setParameter("placedIn", Long.parseLong(region));
        Map<String, String> cities = new LinkedHashMap<String, String>();
        for (Place place : (List<Place>)hql.list()){
            cities.put(place.getId().toString(), place.getName());
        }
        tx.commit();
        sendJsonResponse(response, cities);
    }

    private void sendJsonResponse(HttpServletResponse response, Object object) throws IOException {
        String json = null ;
        json = new Gson().toJson(object);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);    	
    }

}
