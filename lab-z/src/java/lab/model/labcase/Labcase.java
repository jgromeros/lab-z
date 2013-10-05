/*
 * Created on 4/04/2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package lab.model.labcase;

import java.util.Date;
import java.util.List;

import lab.model.Entity;
import lab.model.animal.Animal;
import lab.model.enterprise.Enterprise;
import lab.model.person.LabProfessional;
import lab.model.place.Place;
import lab.model.test.TestDescription;

/**
 * @author  JuanGa  TODO
 */
public class Labcase extends Entity {

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 1L;

    /**
     * Uncomplete registered case: The information of animals aren't complete yet
     */
    public static final String REGISTERING = "R";

    /**
     * Case registered: The information of animals in the case are complete. Doesn't have any result, but can be mounted.
     */
    public static final String SAVED = "S";

    /**
     * Some result information has been registered. It means that mounting information can't be modified.
     */
    public static final String WITHRESULT = "W";

    /**
     * Case complete: Registration and result information of this case is complete.
     */
    public static final String FINISHED = "F";

    /**
     * The case was cancelled by some client or by a user for any reason. System doesn't track the reason why the case was cancelled.
     */
    public static final String CANCELLED = "C";

    private String code;
    private List<Animal> animals;
    private Date receptionDate;
    private String sender;
    private Enterprise enterpriseSender;
    private Place city;
    private String owner;
    private String zone;
    private String farm;
    private String status;
    private String analysisPurpose;
    private int totalOfAnimals;
    private String reproductiveProblem;
    private String observations;
    private LabProfessional technicalDirector;
    private Integer icaNumber;

    /**
	 * @param animals the animals to set
	 */
	public void setAnimals(List<Animal> animals) {
		this.animals = animals;
		totalOfAnimals = animals.size();
	}

	/**
	 * @return the animals
	 */
	public List<Animal> getAnimals() {
		return animals;
	}

    /**
     * @param receptionDate The receptionDate to set.
     */
    public void setReceptionDate(Date receptionDate) {
        this.receptionDate = receptionDate;
    }

    /**
     * @return Returns the receptionDate.
     */
    public Date getReceptionDate() {
        return receptionDate;
    }

    /**
     * @param petitioner The petitioner to set.
     */
    public void setSender(String sender) {
        this.sender = sender;
    }

    /**
     * @return Returns the petitioner.
     */
    public String getSender() {
        return sender;
    }

    /**
     * @param enterpriseSender The enterpriseSender to set.
     */
    public void setEnterpriseSender(Enterprise enterpriseSender) {
        this.enterpriseSender = enterpriseSender;
    }

    /**
     * @return Returns the enterpriseSender.
     */
    public Enterprise getEnterpriseSender() {
        return enterpriseSender;
    }

    /**
     * @param String The code to set.
     */
	public void setCode(String code) {
		this.code = code;
	}
    
    /**
     * @return Returns the code.
     */
	public String getCode() {
		return code;
	}

	/**
     * @param place The place to set.
     */
    public void setCity(Place city) {
        this.city = city;
    }

    /**
     * @return Returns the place.
     */
    public Place getCity() {
        return city;
    }

    /**
     * @param owner The owner to set.
     */
    public void setOwner(String owner) {
        this.owner = owner;
    }

    /**
     * @return Returns the owner.
     */
    public String getOwner() {
        return owner;
    }

    /**
     * @param place The place to set.
     */
    public void setZone(String zone) {
        this.zone = zone;
    }

    /**
     * @return Returns the place.
     */
    public String getZone() {
        return zone;
    }

    /**
     * @param farm The farm to set.
     */
    public void setFarm(String farm) {
        this.farm = farm;
    }

    /**
     * @return Returns the farm.
     */
    public String getFarm() {
        return farm;
    }

    /**
     * @param state The status to set.
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return Returns the status.
     */
    public String getStatus() {
        return status;
    }

	/**
	 * @param analysisPurpose the analysisPurpose to set
	 */
	public void setAnalysisPurpose(String analysisPurpose) {
		this.analysisPurpose = analysisPurpose;
	}

	/**
	 * @return the analysisPurpose
	 */
	public String getAnalysisPurpose() {
		return analysisPurpose;
	}

	/**
	 * @return the totalOfAnimals
	 */
	public int getTotalOfAnimals() {
		return totalOfAnimals;
	}

	/**
	 * @param reproductiveProblem the reproductiveProblem to set
	 */
	public void setReproductiveProblem(String reproductiveProblem) {
		this.reproductiveProblem = reproductiveProblem;
	}

	/**
	 * @return the reproductiveProblem
	 */
	public String getReproductiveProblem() {
		return reproductiveProblem;
	}

	/**
	 * @param observations the observations to set
	 */
	public void setObservations(String observations) {
		this.observations = observations;
	}

	/**
	 * @return the observations
	 */
	public String getObservations() {
		return observations;
	}

	/**
	 * @param labProfessional the labProfessional to set
	 */
	public void setLabProfessional(LabProfessional labProfessional) {
		for (Animal a : getAnimals()){
			a.setLabProfessional(labProfessional);
		}
	}

	/**
	 * @param technicalDirector the technicalDirector to set
	 */
	public void setTechnicalDirector(LabProfessional technicalDirector) {
		this.technicalDirector = technicalDirector;
	}

	/**
	 * @return the technicalDirector
	 */
	public LabProfessional getTechnicalDirector() {
		return technicalDirector;
	}

	/**
	 * @param icaNumber the icaNumber to set
	 */
	public void setIcaNumber(Integer icaNumber) {
		this.icaNumber = icaNumber;
	}

	/**
	 * @return the icaNumber
	 */
	public Integer getIcaNumber() {
		return icaNumber;
	}

	public LabProfessional getLabProfessionalForTestDescription(TestDescription testDescription) {
		Animal animal = getAnimals().get(0);
		return animal.getLabProfessionalForTestDescription(testDescription);
	}

}
