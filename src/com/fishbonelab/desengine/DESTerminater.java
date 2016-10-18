/**
 *
 */
package com.fishbonelab.desengine;

import com.fishbonelab.desengine.utils.Log;

/**
 * @author otuboyas
 *
 */
public class DESTerminater extends DESBaseActivity {

	private long totalWaitingTime = 0;

	/**
	 *
	 */
	public DESTerminater() {
		super();
		//
		totalWaitingTime = 0;
	}

	/**
	 *
	 * @param past
	 */
	@Override
	public void action(long past) {
		//
		// 時計を進める
		// long now = past + this.workingTime;
		// this.setTime(now);
		//
		for (DESEvent event : this.queue) {
			//
			// 処理開始時間と終了時間を設定する
			// event.setProcessStartTime(past);
			event.setProcessEndTime(past);
			//
			totalWaitingTime += event.getWaittingTime();
			//
			// 送るイベントを待ち行列から削除する
			this.queue.pollFirst();
			//
			//
			Log.finish(event, this);
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
	 * @return totalWaitingTime
	 */
	public double getTotalWaitingTime() {
		return totalWaitingTime;
	}

	/**
	 * @param totalWaitingTime セットする totalWaitingTime
	 */
	// private void setTotalWaitingTime(long totalWaitingTime) {
	// this.totalWaitingTime = totalWaitingTime;
	// }
}
