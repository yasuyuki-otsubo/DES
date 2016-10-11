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
public class DESTerminater extends DESActivity {

	private LinkedList<DESEvent> queue;

	private long workingTime;
	private long redundantType;
	private long redundantNumber;

	/**
	 *
	 */
	public DESTerminater() {
		setOutNode(null);
		queue = new LinkedList<DESEvent>();
		//
		workingTime = 1; /// < 処理時間は、通常1以上の値を設定する。単位は、ミリ秒
		setRedundantType(0); /// < 現状では、均等分散のみ
		setRedundantNumber(1); /// < 並列計数は1以上の値を設定する
	}

	/**
	 *
	 * @param past
	 */
	@Override
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
				event.setProcessEndTime(now);
				//
				// 送るイベントを待ち行列から削除する
				this.queue.pollFirst();
				//
				//
				Log.end(event, this);
			}
		}
	}

	@Override
	public void setEvent(DESEvent event) {
		long now = this.getTime();
		//
		// イベントを待ち行列に入れる
		if (event != null) {
			event.setArrivalTime(now);
			this.queue.add(event);
		}
		//
		// イベント処理を行う
		// this.doAction(now);
	}

	/**
	 * @return workingTime
	 */
	@Override
	public long getWorkingTime() {
		return workingTime;
	}

	/**
	 * @param workingTime セットする workingTime
	 */
	@Override
	public void setWorkingTime(long workingTime) {
		this.workingTime = workingTime;
	}

	/**
	 * @return redundantType
	 */
	@Override
	public long getRedundantType() {
		return redundantType;
	}

	/**
	 * @param redundantType セットする redundantType
	 */
	@Override
	public void setRedundantType(long redundantType) {
		this.redundantType = redundantType;
	}

	/**
	 * @return redundantNumber
	 */
	@Override
	public long getRedundantNumber() {
		return redundantNumber;
	}

	/**
	 * @param redundantNumber セットする redundantNumber
	 */
	@Override
	public void setRedundantNumber(long redundantNumber) {
		this.redundantNumber = redundantNumber;
	}
}
