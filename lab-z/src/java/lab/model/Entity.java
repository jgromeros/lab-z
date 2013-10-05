/*
 * Created on 4/04/2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package lab.model;

import java.io.Serializable;

/**
 * @author  JuanGa  TODO
 */
public abstract class Entity implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;

    /**
     * @param id  The id to set.
     * @uml.property  name="id"
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return  Returns the id.
     * @uml.property  name="id"
     */
    public Long getId() {
        return id;
    }
}
