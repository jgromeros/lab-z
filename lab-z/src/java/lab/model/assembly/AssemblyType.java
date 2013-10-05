/**
 * 
 */
package lab.model.assembly;

import java.util.Set;

import lab.model.NamedEntity;

/**
 * @author juanromero
 *
 */
public class AssemblyType extends NamedEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Set<AssemblyDescriptor> assemblyDescriptors;

	/**
	 * @param assemblyDescriptors the assemblyDescriptors to set
	 */
	public void setAssemblyDescriptors(Set<AssemblyDescriptor> assemblyDescriptors) {
		this.assemblyDescriptors = assemblyDescriptors;
	}

	/**
	 * @return the assemblyDescriptors
	 */
	public Set<AssemblyDescriptor> getAssemblyDescriptors() {
		return assemblyDescriptors;
	}

}
