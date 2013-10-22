/**
 * 
 */
package lab.model.animal;

import lab.model.NamedEntity;

/**
 * @author JuanGa
 *
 * TODO
 */
public class Race extends NamedEntity implements Comparable<Race> {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private Specie specie;

    /**
     * @constructors
     */
    public Race(){}

	/**
	 * @param specie the specie to set
	 */
	public void setSpecie(Specie specie) {
		this.specie = specie;
	}

	/**
	 * @return the specie
	 */
	public Specie getSpecie() {
		return specie;
	}

	@Override
	public int compareTo(Race o) {
		return this.getName().compareTo(o.getName());
	}

}
