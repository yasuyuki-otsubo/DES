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
public class DESGenerator extends DESActivity {

	private DESActivity outNode;
	private LinkedList<DESEvent> queue;

	private long workingTime;
	private long redundantType;
	private long redundantNumber;

	private long startTime;
	private long endTime;
	private long duration;

	/**
	 *
	 */
	public DESGenerator() {
		outNode = null;
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
	private void doAction(long past) {
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
				// イベントを次のアクティビティへ送る
				if (this.outNode != null) {
					//
					// 送達時間を設定する
					event.setDepartureTime(now);
					//
					// イベントを次のアクティビティへ送る
					this.outNode.setEvent(event);
				}
			}
		}
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
			Log.start(event, this);
			this.outNode.setEvent(event);
			//
			id++;
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
		this.doAction(now);
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
}
