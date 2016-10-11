/**
 *
 */
package com.fishbonelab.desengine;

import java.util.LinkedList;

import com.fishbonelab.desengine.utils.Log;

/**
 * @author otuboyas
 *
 */
public class DESActivity extends DESObject {

	private DESActivity outNode;
	private LinkedList<DESEvent> queue;

	private long workingTime;
	private long redundantType;
	private long redundantNumber;

	/**
	 *
	 */
	public DESActivity() {
		setOutNode(null);
		queue = new LinkedList<DESEvent>();
		//
		workingTime = 1; /// < 処理時間は、通常1以上の値を設定する。単位は、ミリ秒
		setRedundantType(0); /// < 現状では、均等分散のみ
		setRedundantNumber(1); /// < 並列計数は1以上の値を設定する
	}

	/**
	 * @return outNode
	 */
	public DESActivity getOutNode() {
		return outNode;
	}

	/**
	 * @param outNode セットする outNode
	 */
	public void setOutNode(DESActivity outNode) {
		this.outNode = outNode;
	}

	/**
	 *
	 * @param past
	 */
	public void action(long past) {
		//
		// 時計を進める
		long now = past + this.workingTime;
		this.setTime(now);
		//
		// redundant している個数分イベント処理を行う
		for (int ii = 0; ii < this.redundantNumber; ii++) {
			//
			DESEvent event = this.queue.peekFirst();
			if (event == null) {
				break;
			}
			//
			if (event.getArrivalTime() <= now) {
				//
				// 処理開始時間と終了時間を設定する
				event.setProcessStartTime(past);
				Log.processStart(event, this);
				event.setProcessEndTime(now);
				Log.processEnd(event, this);
				//
				// 送るイベントを待ち行列から削除する
				this.queue.pollFirst();
				//
				// イベントを次のアクティビティへ送る
				if (this.outNode != null) {
					//
					// 送達時間を設定する
					event.setDepartureTime(now);
					Log.departure(event, this);
					//
					// イベントを次のアクティビティへ送る
					this.outNode.setEvent(event);
				}
			}
		}
	}

	public void setEvent(DESEvent event) {
		long now = this.getTime();
		//
		// イベントを待ち行列に入れる
		if (event != null) {
			event.setArrivalTime(now);
			Log.arrival(event, this);
			this.queue.add(event);
		}
		// FIXME: このようにすると、処理が一向に終わらなくなる。
		// FIXME: 代替として、マネージャにて実行処理を制御する
		// イベント処理を行う
		// this.doAction(now);
	}

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
