/**
 * 
 */
package lab.model.animal;

import java.util.List;

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

    private List<Race> races;

    /**
     * @constructors
     */
    public Specie(){}
    
    /**
     * @param races The races to set.
     */
    public void setRaces(List<Race> races) {
        this.races = races;
    }

    /**
     * @return Returns the races.
     */
    public List<Race> getRaces() {
        return races;
    }

}
