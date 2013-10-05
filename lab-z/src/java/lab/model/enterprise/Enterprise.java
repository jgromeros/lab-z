/**
 * 
 */
package lab.model.enterprise;

import lab.model.NamedEntity;

/**
 * @author JuanGa
 *
 * TODO
 */
public class Enterprise extends NamedEntity {

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 1L;
    
    private Long identityNumber;
    private String lastName;
    private String phone;
    private String address;
    private String email;
    private String numeroICA;

    /**
     * @param identityNumber The identityNumber to set.
     */
    public void setIdentityNumber(Long identityNumber) {
        this.identityNumber = identityNumber;
    }

    /**
     * @return Returns the identityNumber.
     */
    public Long getIdentityNumber() {
        return identityNumber;
    }

    /**
     * @param lastName The lastName to set.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return Returns the lastName.
     */
    public String getLastName() {
        return lastName;
    }

	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param numeroICA the numeroICA to set
	 */
	public void setNumeroICA(String numeroICA) {
		this.numeroICA = numeroICA;
	}

	/**
	 * @return the numeroICA
	 */
	public String getNumeroICA() {
		return numeroICA;
	}

}
