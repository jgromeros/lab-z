/**
 * 
 */
package lab.model.assembly;

/**
 * @author juanromero
 *
 */
public class PRRSTGEVPRCVAssemblyDelegator extends AbstractAssemblyDelegator {

	protected Position firstCellFree(Assembly assembly, AssemblyTest at) {
		int k = 0;
		while (true){
			for (int i = 0; i < assembly.getTotalCols(); i++){
				for (int j = 0; j < assembly.getTotalRows(); j++){
					Position position = new Position(k, j, i);
					if (!foundAssemblyDescriptor(assembly, position)){
						if (sameTest(assembly, position, at)){
							position.setCol(position.getCol() + 1);
						}
						if (!foundAssemblyTest(assembly, position)){
							return position;							
						}
					}
				}
			}
			k++;
		}
	}

	protected boolean sameTest(Assembly assembly, Position position, AssemblyTest assemblyTest) {
		for (AssemblyTest at : assembly.getTests()){
			if (at.getRow() == position.getRow() &&
					at.getCol() == position.getCol() &&
					at.getPlaque() == position.getPlaque() &&
					at.getTest().getId() == assemblyTest.getTest().getId()){
				return true;
			}
		}
		return false;
	}

}
