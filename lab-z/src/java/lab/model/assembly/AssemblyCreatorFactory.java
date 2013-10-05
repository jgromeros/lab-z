/**
 * 
 */
package lab.model.assembly;

import java.util.HashMap;
import java.util.Map;

/**
 * @author juanromero
 *
 */
public class AssemblyCreatorFactory {

	private Map<String, AssemblyCreator> assemblies = new HashMap<String, AssemblyCreator>();

	public AssemblyCreatorFactory() {
		getAssemblies().put(BrucellaAssemblyCreator.TYPE, new BrucellaAssemblyCreator());
		getAssemblies().put(BasicAssemblyCreator.BASE, new BasicAssemblyCreator());
		getAssemblies().put(PRRSTGEVPRCVAssemblyCreator.TYPE, new PRRSTGEVPRCVAssemblyCreator());
		getAssemblies().put(BasicAssemblyCreator.TOXOPLASM, new BasicAssemblyCreator());
		getAssemblies().put(BasicAssemblyCreator.T3T4, new BasicAssemblyCreator());
		getAssemblies().put(BasicAssemblyCreator.TSH, new BasicAssemblyCreator());
	}

	public AssemblyCreator createAssemblyCreator(AssemblyType assemblyType) {
		return getAssemblies().get(assemblyType.getName());
	}

	/**
	 * @param assemblies the assemblies to set
	 */
	public void setAssemblies(Map<String, AssemblyCreator> assemblies) {
		this.assemblies = assemblies;
	}

	/**
	 * @return the assemblies
	 */
	public Map<String, AssemblyCreator> getAssemblies() {
		return assemblies;
	}
}
