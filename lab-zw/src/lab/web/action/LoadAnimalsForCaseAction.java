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
import lab.model.animal.Race;
import lab.model.labcase.Labcase;
import lab.web.helper.LabcaseHelper;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * @author juanromero
 *
 */
public class LoadAnimalsForCaseAction extends Action {

	public static final String LOADPAGE = "loadcase";
	private static final String LABCASE = "labcase";

	private LabcaseHelper labcaseHelper;

	public LoadAnimalsForCaseAction(String actionPath, String action) {
		super(actionPath, action);
		labcaseHelper = new LabcaseHelper();
	}

	/* (non-Javadoc)
	 * @see lab.web.action.Action#perform(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, org.hibernate.Session, org.hibernate.Transaction)
	 */
	@Override
	public Map<String, Object> perform(HttpServletRequest request,
			HttpServletResponse response, Session session, Transaction tx) {
		String fileData = null;
		if (ServletFileUpload.isMultipartContent(request)){
			FileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			try {
				List<FileItem> items = upload.parseRequest(request);
				for (FileItem item : items){
					if (!item.isFormField()){
						fileData = item.getString();
					}
				}
			} catch (FileUploadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		List<Animal> animals = obtainAnimals(session, fileData);
		Labcase labcase = (Labcase) request.getSession().getAttribute(LABCASE);
		labcase.setAnimals(animals);
        String[] testStrings = (String[]) request.getSession().getAttribute("testdescriptions");
        labcaseHelper.initializeTests(session, labcase, testStrings);
        labcaseHelper.saveLabcase(session, labcase);
        labcase.setStatus(Labcase.SAVED);
		return getModel();
	}

	private List<Animal> obtainAnimals(Session session, String fileData) {
		List<Animal> animals = new ArrayList<Animal>();
		int initialPosition = fileData.indexOf("\"");
		int line = 0;
		while (initialPosition < fileData.length()){
			int finalPosition = fileData.indexOf("\n", initialPosition);
			agregarAnimal(session, animals, fileData.substring(initialPosition, finalPosition));
			initialPosition = finalPosition + 1;
			line += 1;
		}
		return animals;
	}

	private void agregarAnimal(Session session, List<Animal> animals, String line) {
		Animal animal = loadLine(session, line);
		int homonimeNumber = 0;
		for (Animal a : animals){
			if (a.getName().equals(animal.getName())){
				homonimeNumber++;
			}
		}
		if (homonimeNumber > 0){
			animal.rename(homonimeNumber);
		}
		animals.add(animal);
	}

	private Animal loadLine(Session session, String dataLine) {
		String[] values = new String[5];
		int i = 0;
		String nextLine = dataLine;
		while (i < 5){
			nextLine = nextData(values, nextLine, i);
			i++;
			if (nextLine.startsWith("\",")){
				nextLine = nextLine.substring(2);
			} else if (nextLine.startsWith(",")){
				nextLine = nextLine.substring(1);
			}
		}
		return createAnimal(session, values);
	}

	private String nextData(String[] values, String dataLine, int i) {
		int initialPosition = 0;
		int finalPosition = 0;
		if (dataLine.startsWith("\"")){
			initialPosition = 1;
			finalPosition = dataLine.indexOf("\"", initialPosition);
		} else if (dataLine.substring(initialPosition).startsWith(",")){
			return dataLine.substring(initialPosition + 1);
		} else {
			finalPosition = dataLine.indexOf(",");
			if (finalPosition == -1){
				finalPosition = dataLine.length();
			}
		}
		values[i] = dataLine.substring(initialPosition, finalPosition);
		String toReturn = dataLine.substring(finalPosition);
		return toReturn;
	}

	private Animal createAnimal(Session session, String[] values) {
		Animal animal = new Animal();
		animal.setName(values[0]);
		setGender(animal, values[1]);
		animal.setAge(values[2]);
		animal.setObservations(values[3]);
		Query hql = session.createQuery("from Race r where r.name like :name");
		hql.setParameter("name", values[4]);
		animal.setRace((Race) hql.uniqueResult());
		return animal;
	}

	private void setGender(Animal animal, String gender) {
		if (gender.trim().startsWith("M") || gender.trim().startsWith("m"))
			animal.setGender("M");
		else {
			animal.setGender("H");
		}
	}

}
