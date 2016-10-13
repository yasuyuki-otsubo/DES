/**
 *
 */
package com.fishbonelab.desengine;

import java.util.Date;

/**
 * @author otuboyas
 *
 */
public class DESEvent extends DESObject {

	// Timestamp
	private long startTime = -1;
	private long arrivalTime = -1;
	private long processStartTime = -1;
	private long processEndTime = -1;
	private long departureTime = -1;

	// Calcurated
	private long waittingTime = -1;
	// private long processingTime = -1;

	/**
	 *
	 */
	public DESEvent() {
		//
	}

	/**
	 *
	 */
	public void setTimestamp() {
		long currentTime = new Date().getTime();
		if (this.startTime == -1) {
			this.startTime = currentTime;
		} else if (this.arrivalTime == -1) {
			this.arrivalTime = currentTime;
		} else if (this.processStartTime == -1) {
			this.processStartTime = currentTime;
		} else if (this.processEndTime == -1) {
			this.processEndTime = currentTime;
		} else if (this.departureTime == -1) {
			this.departureTime = currentTime;
		} else {
			this.departureTime = currentTime;
		}
	}

	/**
	 * @return startTime
	 */
	public long getStartTime() {
		return startTime;
	}

	/**
	 * @param startTime セットする startTime
	 */
	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	/**
	 * @return arrivalTime
	 */
	public long getArrivalTime() {
		return arrivalTime;
	}

	/**
	 * @param arrivalTime セットする arrivalTime
	 */
	public void setArrivalTime(long arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	/**
	 * @return processStartTime
	 */
	public long getProcessStartTime() {
		return processStartTime;
	}

	/**
	 * @param processStartTime セットする processStartTime
	 */
	public void setProcessStartTime(long processStartTime) {
		this.processStartTime = processStartTime;
		//
		// 待ち時間の計算
		this.setWaittingTime(this.processStartTime - this.arrivalTime);
	}

	/**
	 * @return processEndTime
	 */
	public long getProcessEndTime() {
		return processEndTime;
	}

	/**
	 * @param processEndTime セットする processEndTime
	 */
	public void setProcessEndTime(long processEndTime) {
		this.processEndTime = processEndTime;
		//
		// 処理時間の計算
		// this.processingTime = this.processEndTime - this.processStartTime;
	}

	/**
	 * @return departureTime
	 */
	public long getDepartureTime() {
		return departureTime;
	}

	/**
	 * @param departureTime セットする departureTime
	 */
	public void setDepartureTime(long departureTime) {
		this.departureTime = departureTime;
	}

	/**
	 * @return waittingTime
	 */
	public long getWaittingTime() {
		if (waittingTime < 0) {
			waittingTime = 0;
		}
		System.out.println(this.getId() + ":" + waittingTime);
		return waittingTime;
	}

	/**
	 * @param waittingTime セットする waittingTime
	 */
	private void setWaittingTime(long waittingTime) {
		this.waittingTime += waittingTime;
		// System.out.println(waittingTime + "-" + waittingTime);
	}

}
