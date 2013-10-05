/**
 * 
 */
package lab.security;

import java.util.Set;

import lab.model.NamedEntity;

/**
 * @author juanromero
 *
 */
public class Section extends NamedEntity implements Comparable<Section> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String url;
	private Set<Section> subsections;

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @return the subsections
	 */
	public Set<Section> getSubsections() {
		return subsections;
	}

	/**
	 * @param subsections the subsections to set
	 */
	public void setSubsections(Set<Section> subsections) {
		this.subsections = subsections;
	}

	@Override
	public int compareTo(Section o) {
		return this.getName().compareTo(o.getName());
	}

}
