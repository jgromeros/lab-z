/*
 * Created on 4/04/2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package lab.model;

/**
 * @author  JuanGa  TODO
 */
public abstract class DescribedEntity extends Entity {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String description;

    /**
     * @param description  The description to set.
     * @uml.property  name="description"
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return  Returns the description.
     * @uml.property  name="description"
     */
    public String getDescription() {
        return description;
    }
}
