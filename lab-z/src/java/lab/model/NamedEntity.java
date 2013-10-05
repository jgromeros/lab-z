/*
 * Created on 4/04/2006
 *
 * TODO 
 */
package lab.model;

/**
 * @author  JuanGa  TODO To change the template for this generated type comment go to  Window - Preferences - Java - Code Style - Code Templates
 */
public abstract class NamedEntity extends Entity {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;

    /**
     * @param name  The name to set.
     * @uml.property  name="name"
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return  Returns the name.
     * @uml.property  name="name"
     */
    public String getName() {
        return name;
    }
}
