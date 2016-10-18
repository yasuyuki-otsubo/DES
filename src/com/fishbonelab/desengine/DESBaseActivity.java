/**
 *
 */
package com.fishbonelab.desengine;

import java.util.LinkedList;

/**
 * @author otuboyas
 *
 */
public abstract class DESBaseActivity extends DESObject {

	protected DESBaseActivity outNode;
	protected LinkedList<DESEvent> queue;
	protected int maxQueueCount;

	/**
	 * @return maxQueueCount
	 */
	public int getMaxQueueCount() {
		return maxQueueCount;
	}

	protected long workingTime;
	protected long workStartTime;
	protected long redundantType;
	protected long redundantNumber;

	/**
	 *
	 */
	public DESBaseActivity() {
		setOutNode(null);
		queue = new LinkedList<DESEvent>();
		maxQueueCount = 0;
		//
		workingTime = 1; /// < 処理時間は、通常1以上の値を設定する。単位は、ミリ秒
		// workStartTime = 0;
		setRedundantType(0); /// < 現状では、均等分散のみ
		setRedundantNumber(1); /// < 並列計数は1以上の値を設定する
	}

	/**
	 * @return outNode
	 */
	public DESBaseActivity getOutNode() {
		return outNode;
	}

	/**
	 * @param outNode セットする outNode
	 */
	public void setOutNode(DESBaseActivity outNode) {
		this.outNode = outNode;
	}

	/**
	 * 実行エンジンによりタイムスケール間隔で呼び出される。
	 * @param past 実行エンジン内の現在時間を渡される。
	 */
	abstract public void action(long past);

	/**
	 * イベントをActivityに設定する。
	 * Activityが作業中の場合は、Activityの待ち行列に入れる。
	 * @param event
	 */
	abstract public void setEvent(DESEvent event);

	public boolean isEmpty() {
		return this.queue.isEmpty();
	}

	/**
	 * @return workingTime
	 */
	public long getWorkingTime() {
		return workingTime;
	}

	/**
	 * @param workingTime セットする workingTime
	 */
	public void setWorkingTime(long workingTime) {
		this.workingTime = workingTime;
	}

	/**
	 * @return redundantType
	 */
	public long getRedundantType() {
		return redundantType;
	}

	/**
	 * @param redundantType セットする redundantType
	 */
	public void setRedundantType(long redundantType) {
		this.redundantType = redundantType;
	}

	/**
	 * @return redundantNumber
	 */
	public long getRedundantNumber() {
		return redundantNumber;
	}

	/**
	 * @param redundantNumber セットする redundantNumber
	 */
	public void setRedundantNumber(long redundantNumber) {
		this.redundantNumber = redundantNumber;
	}
}
