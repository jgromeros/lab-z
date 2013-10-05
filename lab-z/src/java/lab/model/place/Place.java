/**
 * 
 */
package lab.model.place;

import lab.model.NamedEntity;

/**
 * @author JuanGa
 *
 * TODO
 */
public class Place extends NamedEntity {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private PlaceType placeType;
    private Place placedIn;

    /**
     * @constructors
     */
    public Place(){}
    
    public Place(String name){
        this.setName(name);
    }
    /**
     * @param placedIn The placedIn to set.
     */
    public void setPlacedIn(Place placedIn) {
        this.placedIn = placedIn;
    }

    /**
     * @return Returns the placedIn.
     */
    public Place getPlacedIn() {
        return placedIn;
    }

    /**
     * @param placeType The placeType to set.
     */
    public void setPlaceType(PlaceType placeType) {
        this.placeType = placeType;
    }

    /**
     * @return Returns the placeType.
     */
    public PlaceType getPlaceType() {
        return placeType;
    }

}
