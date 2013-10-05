/**
 * 
 */
package lab.model.animal;

import java.util.Set;

import lab.model.NamedEntity;

/**
 * @author JuanGa
 *
 * TODO
 */
public class Specie extends NamedEntity {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private Set<Race> races;

    /**
     * @constructors
     */
    public Specie(){}
    
    /**
     * @param races The races to set.
     */
    public void setRaces(Set<Race> races) {
        this.races = races;
    }

    /**
     * @return Returns the races.
     */
    public Set<Race> getRaces() {
        return races;
    }

}
