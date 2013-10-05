/**
 * 
 */
package lab.model.person;

import lab.model.NamedEntity;

/**
 * @author juanromero
 *
 */
public class LabProfessional extends NamedEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String nationalIdentification;
	private String lastName;
	private String status;
	private int lab;
	private boolean technicalDirector;

	/**
	 * @param nationalIdentification the nationalIdentification to set
	 */
	public void setNationalIdentification(String nationalIdentification) {
		this.nationalIdentification = nationalIdentification;
	}

	/**
	 * @return the nationalIdentification
	 */
	public String getNationalIdentification() {
		return nationalIdentification;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param lab the lab to set
	 */
	public void setLab(int lab) {
		this.lab = lab;
	}

	/**
	 * @return the lab
	 */
	public int getLab() {
		return lab;
	}

	public String toString() {
		return getName() + " " + getLastName();
	}

	/**
	 * @param technicalDirector the technicalDirector to set
	 */
	public void setTechnicalDirector(boolean technicalDirector) {
		this.technicalDirector = technicalDirector;
	}

	/**
	 * @return the technicalDirector
	 */
	public boolean isTechnicalDirector() {
		return technicalDirector;
	}

}
