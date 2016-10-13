/**
 *
 */
package com.fishbonelab.desengine;

import com.fishbonelab.desengine.utils.Log;

/**
 * @author otuboyas
 *
 */
public class DESGenerator extends DESActivity {

	private long eventCount;
	private String algorithmName;

	// private DESActivity outNode;
	// private LinkedList<DESEvent> queue;

	// private long workingTime;
	// private long redundantType;
	// private long redundantNumber;

	private long startTime;
	private long endTime;
	private long duration;

	/**
	 *
	 */
	public DESGenerator() {
		super();
		//
		eventCount = 0;
		algorithmName = "";
		startTime = 0;
		endTime = 0;
		duration = 0;
	}

	public void generate() throws Exception {
		/// < 設定値のチェック
		if (startTime < 0) {
			throw new Exception();
		}
		if (endTime < 0) {
			throw new Exception();
		}
		if (duration < 0) {
			throw new Exception();
		}
		if (endTime <= startTime) {
			throw new Exception();
		}
		if (duration == 0) {
			throw new Exception();
		}
		if (outNode == null) {
			throw new Exception();
		}
		if (!(outNode instanceof DESActivity)) {
			throw new Exception();
		}
		//
		int id = 1;
		for (long now = startTime; now < endTime; now += duration) {
			DESEvent event = new DESEvent();
			event.setId(id);
			event.setName("customer " + String.valueOf(id));
			//
			event.setTime(now);
			Log.create(event, this);
			this.outNode.setEvent(event);
			//
			id++;
		}
		//
		eventCount = --id;
	}

	/*
	 * @Override public void setEvent(DESEvent event) { long now =
	 * this.getTime(); // // イベントを待ち行列に入れる if (event != null) {
	 * event.setArrivalTime(now); this.queue.add(event); } // // イベント処理を行う
	 * this.doAction(now); }
	 *
	 *//**
		* @return workingTime
		*/
	/*
	 * @Override public long getWorkingTime() { return workingTime; }
	 *
	 *//**
		* @param workingTime セットする workingTime
		*/
	/*
	 * @Override public void setWorkingTime(long workingTime) { this.workingTime
	 * = workingTime; }
	 *
	 *//**
		* @return redundantType
		*/
	/*
	 * @Override public long getRedundantType() { return redundantType; }
	 *
	 *//**
		* @param redundantType セットする redundantType
		*/
	/*
	 * @Override public void setRedundantType(long redundantType) {
	 * this.redundantType = redundantType; }
	 *
	 *//**
		* @return redundantNumber
		*/
	/*
	 * @Override public long getRedundantNumber() { return redundantNumber; }
	 *
	 *//**
		* @param redundantNumber セットする redundantNumber
		*//*
		 * @Override public void setRedundantNumber(long redundantNumber) {
		 * this.redundantNumber = redundantNumber; }
		 */

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
	 * @return endTime
	 */
	public long getEndTime() {
		return endTime;
	}

	/**
	 * @param endTime セットする endTime
	 */
	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}

	/**
	 * @return duration
	 */
	public long getDuration() {
		return duration;
	}

	/**
	 * @param duration セットする duration
	 */
	public void setDuration(long duration) {
		this.duration = duration;
	}

	/**
	 * @return outNode
	 */
	@Override
	public DESActivity getOutNode() {
		return outNode;
	}

	/**
	 * @param outNode セットする outNode
	 */
	@Override
	public void setOutNode(DESActivity outNode) {
		this.outNode = outNode;
	}

	/**
	 * @return eventCount
	 */
	public long getEventCount() {
		return eventCount;
	}

	/**
	 * @return algorithmName
	 */
	public String getAlgorithmName() {
		return algorithmName;
	}

	/**
	 * @param algorithmName セットする algorithmName
	 */
	public void setAlgorithmName(String algorithmName) {
		this.algorithmName = algorithmName;
	}
}
