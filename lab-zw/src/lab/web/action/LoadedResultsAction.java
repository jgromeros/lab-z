/**
 * 
 */
package lab.web.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lab.model.assembly.Assembly;
import lab.model.assembly.AssemblyTest;
import lab.model.labcase.Labcase;
import lab.model.test.Test;
import lab.model.test.result.Result;
import lab.model.test.result.resultfactor.ResultFactor;
import lab.web.helper.ResultsHelper;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * @author juanromero
 *
 */
public class LoadedResultsAction extends Action {

	public static final String LISTASSEMBLIES = "listassemblies";
	public static final String LOADFILE = "assemblyresult";
	public static final String LOADRESULTS = "loadresult";

	public LoadedResultsAction(String actionPath, String action) {
		super(actionPath, action);
	}

	/* (non-Javadoc)
	 * @see lab.web.action.Action#perform(javax.servlet.http.HttpServletRequest, org.hibernate.Session, org.hibernate.Transaction)
	 */
	@Override
	public Map<String, Object> perform(HttpServletRequest request, HttpServletResponse response,
			Session session, Transaction tx) {
		if (getAction().equals(LISTASSEMBLIES)){
			List<Assembly> assemblies = new ArrayList<Assembly>();
			for (Labcase labcase : new ResultsHelper().listPendingCases(session)){
				for (Test test : labcase.getAnimals().get(0).getTests()){
					Assembly assembly = obtainAssembly(session, test);
					if (assembly != null){
						Hibernate.initialize(assembly.getAssemblyDate());
						if (!assemblies.contains(assembly)){
							assemblies.add(assembly);
						}
					}
				}
			}
			getModel().put("pendingAssemblies", assemblies);
		} else if (getAction().equals(LOADFILE)){
			Assembly assembly = (Assembly) session.get(Assembly.class, Long.parseLong(request.getParameter("id")));
			getModel().put("assembly", assembly);
		} else if (getAction().equals(LOADRESULTS)){
			Assembly assembly = null;
			String fileData = null;
			if (ServletFileUpload.isMultipartContent(request)){
				FileItemFactory factory = new DiskFileItemFactory();
				ServletFileUpload upload = new ServletFileUpload(factory);
				try {
					List<FileItem> items = upload.parseRequest(request);
					for (FileItem item : items){
						if (item.isFormField()){
							if (item.getFieldName().equals("assembly")){
								assembly = (Assembly) session.get(Assembly.class, Long.parseLong(item.getString()));
							}
						} else {
							fileData = item.getString();
						}
					}
				} catch (FileUploadException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			double[][][] resultMatrix = obtainResults(fileData);
			truncateResults(session, assembly);
			assignResults(session, assembly, resultMatrix);
		}
		return getModel();
	}

	private Assembly obtainAssembly(Session session, Test test) {
		Query hql = session.createQuery("from AssemblyTest at where at.test = :test");
		hql.setParameter("test", test);
		List<AssemblyTest> assemblyTests = (List<AssemblyTest>) hql.list();
		if (assemblyTests != null && assemblyTests.size() > 0){
			AssemblyTest at = assemblyTests.get(0);
			return at.getAssembly();
		} else {
			return null;
		}
	}

/*	private String loadFile(HttpServletRequest request) {
		String contentType = request.getContentType();
		String data = obtainJustTheFile(inputToString(request), contentType);
		return data;
	}

	private String inputToString(HttpServletRequest request) {
		if (request.getContentType() != null){
			int length = request.getContentLength();
			try {
				DataInputStream dis = new DataInputStream(request.getInputStream());
				byte[] dataBytes = new byte[length];
				int byteRead = 0;
				int totalBytesRead = 0;
				while (byteRead < length){
					byteRead = dis.read(dataBytes, totalBytesRead, length);
					totalBytesRead += byteRead;
				}
				return new String(dataBytes);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			System.out.println("No esta enviando ningun archivo");
		}
		return null;
	}

	private String obtainJustTheFile(String data, String contentType) {
		String boundary = obtainBoundary(contentType);
		int initialPosition = data.indexOf("filename=\"");
		initialPosition = data.indexOf("Content-Type", initialPosition);
		initialPosition = data.indexOf("\n", initialPosition);
		initialPosition = data.indexOf("\n", initialPosition);
		int finalPosition = data.indexOf(boundary, initialPosition);
		return data.substring(initialPosition, finalPosition - 6);//Hay dos guiones parte del boundary que no se ven en el contentType y ademas hay algunos saltos de linea, por eso el -6
	}

	private String obtainBoundary(String contentType) {
		int lastIndex = contentType.lastIndexOf("=");
		return contentType.substring(lastIndex + 1);
	}*/

	/**
	 * Takes the parameter, which contains the file uploaded and split it in lines which are passed one by one
	 * to loadLine().
	 * @param fileData
	 */
	private double[][][] obtainResults(String fileData) {
		double[][][] resultMatrix = new double[8][8][12];
		int initialPosition = fileData.indexOf("1A");
		int line = 0;
		while (initialPosition < fileData.length()){
			int finalPosition = fileData.indexOf("\n", initialPosition);
			loadLine(resultMatrix, fileData.substring(initialPosition, finalPosition), line);
			initialPosition = finalPosition + 1;
			line += 1;
		}
		return resultMatrix;
/*		for (int i = 0; i < 8; i++){
			System.out.println("Placa numero " + i);
			for (int j = 0; j < 8; j++){
				for (int k = 0; k < 12; k++){
					System.out.print(resultMatrix[i][j][k] + " ");
				}
				System.out.println();
			}
		}*/
	}

	/**
	 * Reads one line (dataLine) and parses it to return one "row" of a three dimmensional array.
	 * To parse the line uses 12 as the number of values to load by each line. (5 + (i * 6)) puts the reader on 
	 * the first character of each value, and (10 + (i * 6)) puts the reader on the last character of each value.
	 * @param resultMatrix
	 * @param dataLine
	 * @param lineNumber
	 */
	private void loadLine(double[][][] resultMatrix, String dataLine, int lineNumber) {
		for (int i = 0; i < 12; i++){
			if (dataLine.length() > 11){
				try {
					double value = Double.parseDouble(dataLine.substring((5 + (i * 6)), (10 + (i * 6))));
					resultMatrix[lineNumber / 8][lineNumber % 8][i] = value;
				} catch(NumberFormatException e){
					return;
				}
			}
		}
	}

	private void truncateResults(Session session, Assembly assembly) {
		for (AssemblyTest at : assembly.getTests()){
			if (at.getTest() != null){
				for (Result result : at.getTest().getResults()){
					session.delete(result);
				}
			} else if (at.getControl().getResult() != null){
				session.delete(at.getControl().getResult());
			}
		}
	}

	private void assignResults(Session session, Assembly assembly, double[][][] resultMatrix) {
		for (AssemblyTest at : assembly.getTests()){
			Result result = new Result();
			if (at.getTest() != null){
				for (ResultFactor rf : at.getTest().getTestDescription().getResultFactors()){
					if (rf.getName().equals("Densidad îptica")){
						result.setResultFactor(rf);
						result.setValue("" + resultMatrix[at.getPlaque()][at.getRow()][at.getCol()]);
						result.setAssemblyTest(at);
						result.setResultDate(new Date());
						at.getTest().getResults().add(result);
					}
				}
			} else {
				for (ResultFactor rf : at.getControl().getTestDescription().getResultFactors()){
					if (rf.getName().equals("Densidad îptica")){
						result.setResultFactor(rf);
						result.setValue("" + resultMatrix[at.getPlaque()][at.getRow()][at.getCol()]);
						result.setAssemblyTest(at);
						result.setResultDate(new Date());
						at.getControl().setResult(result);
					}
				}
			}
			session.save(result);
		}
	}

}
