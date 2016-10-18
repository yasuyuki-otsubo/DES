/**
 *
 */
package com.fishbonelab.desengine;

import com.fishbonelab.desengine.utils.Log;

/**
 * @author otuboyas
 *
 */
public class DESGenerator extends DESBaseActivity {

	private long eventCount;
	private String algorithmName;

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
		int id = 0;
		for (long now = startTime; now <= endTime; now += duration) {
			/// idを更新する
			id++;
			/// イベントを作成する
			DESEvent event = new DESEvent();
			event.setId(id);
			event.setName("customer " + String.valueOf(id));
			//
			event.setTime(now);
			//
			// イベントの発生時刻を記録する
			event.setStartTime(now);
			Log.create(event, this);
			//
			// 待ち時間を正しく計算できるように、直接Activityへ送らずに
			// 一旦自信のキューに保存し、タスクマネージャの制御にて
			// Activityへイベントを送るように変更した
			// this.outNode.setEvent(event);
			this.queue.add(event);
		}
		// 最後に作成したイベントIDを総作成個数として保存する
		eventCount = id;
	}

	/*
	 * (非 Javadoc)
	 *
	 * @see com.fishbonelab.desengine.DESActivity#action(long)
	 */
	@Override
	public void action(long past) {
		//
		while (true) {
			//
			// エラー防止のため、キューを先読みする
			DESEvent event = this.queue.peekFirst();
			if ((event != null) && (past >= event.getStartTime())) {
				// 安全にキューからイベントを削除する
				this.queue.pollFirst();
				//
				// イベント送付先の時間を設定する
				this.getOutNode().setTime(past);
				//
				// イベントをアクティビティへ送る
				this.getOutNode().setEvent(event);
			} else {
				break;
			}
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

	@Override
	public void setEvent(DESEvent event) {
		// TODO 自動生成されたメソッド・スタブ
	}
}
