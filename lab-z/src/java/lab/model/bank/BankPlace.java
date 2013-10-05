/**
 * 
 */
package lab.model.bank;

import java.util.ArrayList;
import java.util.List;

import lab.model.NamedEntity;
import lab.model.animal.Animal;

import org.hibernate.Session;

/**
 * @author juanromero
 *
 */
public class BankPlace extends NamedEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int LEVELFORGRID = 4;
	public static final int LEVELFORLEVEL = 1;

	private String longDescription;
	private BankPlace placedIn;
	private int level;
	private List<BankPlace> places;
	private Animal animal;

	public BankPlace(){
		this.setPlaces(new ArrayList<BankPlace>());
	}

	public BankPlace(int id, BankPlace parent){
		this.setName("" + id);
		this.setPlacedIn(parent);
		this.setPlaces(new ArrayList<BankPlace>());
	}

	public BankPlace obtainFirstUnused() {
		if (level == LEVELFORLEVEL && places.size() == 0){
			return this;
		}
		if (level == LEVELFORGRID){
			for(BankPlace bp : places){
				if (bp.getAnimal() == null){
					return bp;
				}
			}
			return null;
		}
		for (BankPlace bp : places){
			BankPlace placeToReturn = bp.obtainFirstUnused();
			if (placeToReturn != null){
				return placeToReturn;
			}
		}
		return null;
	}

	public void createLevel(Session session, int[] dimmensions) {
		if (level == LEVELFORLEVEL){
			createPlaces(session, dimmensions, 0);
		}
	}

	public void createPlaces(Session session, int[] dimmensions, int number) {
		if (number < dimmensions.length){
			for (int i = 0; i < dimmensions[number]; i++){
				BankPlace bp = new BankPlace(i, this);
				session.save(bp);
				this.getPlaces().add(bp);
				bp.createPlaces(session, dimmensions, number + 1);
			}
		}
	}

	public List<BankPlace> putSamplesInBank(List<Animal> animals){
		List<BankPlace> bankPlaces = new ArrayList<BankPlace>();
		for(Animal a : animals){
			BankPlace bp = obtainFirstUnused();
			if (bp.getLevel() > LEVELFORGRID){
				bp.setAnimal(a);
				bankPlaces.add(bp);
			} else {
				dropAnimalsFrom(bankPlaces);
				return null;
			}
		}
		return bankPlaces;
	}

	public void dropAnimalsFrom(List<BankPlace> bankPlaces){
		for(BankPlace bp : bankPlaces){
			bp.setAnimal(null);
		}
	}

	/**
	 * @return the longDescription
	 */
	public String getLongDescription() {
		return longDescription;
	}

	/**
	 * @param longDescription the longDescription to set
	 */
	public void setLongDescription(String longDescription) {
		this.longDescription = longDescription;
	}

	/**
	 * @return the placedIn
	 */
	public BankPlace getPlacedIn() {
		return placedIn;
	}

	/**
	 * @param placedIn the placedIn to set
	 */
	public void setPlacedIn(BankPlace placedIn) {
		if (placedIn != null){
			this.placedIn = placedIn;
			this.setLevel(placedIn.getLevel() + 1);
		}
	}

	/**
	 * @param level the level to set
	 */
	public void setLevel(int level) {
		this.level = level;
	}

	/**
	 * @return the level
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * @param places the places to set
	 */
	public void setPlaces(List<BankPlace> places) {
		this.places = places;
	}

	/**
	 * @return the places
	 */
	public List<BankPlace> getPlaces() {
		return places;
	}

	/**
	 * @param animal the animal to set
	 */
	public void setAnimal(Animal animal) {
		this.animal = animal;
	}

	/**
	 * @return the animal
	 */
	public Animal getAnimal() {
		return animal;
	}

	public String toString(){
		return placedIn.getName() + getName();
	}

}
