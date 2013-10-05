/**
 * 
 */
package lab.model.assembly;

/**
 * @author juanromero
 *
 */
public class SimpleAssemblyDelegator extends AbstractAssemblyDelegator {

	protected Position firstCellFree(Assembly assembly, AssemblyTest assemblyTest) {
		int k = 0;
		while (true){
			for (int i = 0; i < assembly.getTotalCols(); i++){
				for (int j = 0; j < assembly.getTotalRows(); j++){
					Position position = new Position(k, j, i);
					if (!foundAssemblyDescriptor(assembly, position) && !foundAssemblyTest(assembly, position)){
						return position;
					}
				}
			}
			k++;
		}
	}

}
