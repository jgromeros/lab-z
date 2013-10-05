/**
 * 
 */
package lab.model.assembly;

import java.util.List;
import java.util.Set;

import lab.model.labcase.Labcase;
import lab.model.test.Test;
import lab.model.test.TestDescription;

/**
 * @author juango
 *
 */
public interface AssemblyCreator {

	public Assembly doMount(Set<Test> tests, int initRow, int initCol);
	public Assembly doMount(List<Labcase> cases, TestDescription testDescription);

}
