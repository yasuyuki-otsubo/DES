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

	protected DESActivity outNode;
	protected LinkedList<DESEvent> queue;
	private int maxQueueCount;

	/**
	 * @return maxQueueCount
	 */
	public int getMaxQueueCount() {
		return maxQueueCount;
	}

	private long workingTime;
	private long workStartTime;
	private long redundantType;
	private long redundantNumber;

	/**
	 *
	 */
	public DESActivity() {
		setOutNode(null);
		queue = new LinkedList<DESEvent>();
		maxQueueCount = 0;
		//
		workingTime = 1; /// < 処理時間は、通常1以上の値を設定する。単位は、ミリ秒
		workStartTime = 0;
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
	 * 実行エンジンによりタイムスケール間隔で呼び出される。
	 * @param past 実行エンジン内の現在時間を渡される。
	 */
	public void action(long past) {
		//
		// 作業開始時間を判定する
		// タイムスケール間隔で実行されるが、アクティビティは処理完了直後に次のイベント処理を行う
		// そのため、処理開始時間を修正する
		if (workStartTime < past) {
			workStartTime = past;
		}
		//
		// 時計を進める
		long now = workStartTime + this.workingTime;
		// long now = past;
		this.setTime(now);
		//
		// redundant している個数分イベント処理を行う
		for (int ii = 0; ii < this.redundantNumber; ii++) {
			// 安全のためにキューを先読みする
			DESEvent event = this.queue.peekFirst();
			if ((event!=null)&&(event.getArrivalTime() <= now)) {
				//
				// 処理開始時間と終了時間を設定する
				event.setProcessStartTime(workStartTime);
				Log.start(event, this);
				event.setProcessEndTime(now);
				event.setTime(now);
				Log.end(event, this);
				//
				// 送るイベントを待ち行列から削除する
				this.queue.pollFirst();
				//
				// イベントを次のアクティビティへ送る
				if (this.outNode != null) {
					//
					// 送達時間を設定する
					event.setDepartureTime(now);
					this.workStartTime = now;
					Log.departure(event, this);
					//
					// イベントを次のアクティビティへ送る
					this.outNode.setEvent(event);
				}
			}
		}
	}

	/**
	 * イベントをActivityに設定する。
	 * Activityが作業中の場合は、Activityの待ち行列に入れる。
	 * @param event
	 */
	public void setEvent(DESEvent event) {
		long now = this.getTime();
		//
		// イベントを待ち行列に入れる
		if (event != null) {
			/// < イベント生成時に誕生時刻を設定しているため、その時刻以降を設定する
			if (now < event.getTime()) {
				now = event.getTime();
			}
			event.setArrivalTime(now);
			Log.arrival(event, this);
			this.queue.add(event);
			// System.out.println(this.queue.size());
			//
			// FIXME: これだと正しい待ち行列数を示さない
			// イベントは必ずキューを通して伝えるため、1を超えたときに記録する
			int qsize = this.queue.size();
			if ((qsize>1)&&(maxQueueCount < qsize)) {
				maxQueueCount = qsize;
			}
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
