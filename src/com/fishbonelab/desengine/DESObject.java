/**
 *
 */
package com.fishbonelab.desengine;

/**
 * @author otuboyas
 *
 */
public class DESObject {

	private long id;
	private String name;
	private long time;

	/**
	 *
	 */
	public DESObject() {
		id = 0;
		name = "";
		setTime(0);
	}

	/**
	 *
	 * @return
	 */
	public long getId() {
		return id;
	}

	/**
	 *
	 * @param id
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name セットする name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return time
	 */
	public long getTime() {
		return time;
	}

	/**
	 * @param time セットする time
	 */
	public void setTime(long time) {
		this.time = time;
	}

}
