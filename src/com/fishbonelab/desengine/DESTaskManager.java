/**
 *
 */
package com.fishbonelab.desengine;

import java.util.ArrayList;

/**
 * @author otuboyas
 *
 */
public class DESTaskManager {

	private ArrayList<DESBaseActivity> list;
	private DESGenerator generator;
	//
	private long maxQueueId = 0;
	private long maxQueueCount = 0;
	private String name = "";

	/**
	 *
	 */
	public DESTaskManager() {
		list = new ArrayList<DESBaseActivity>();
	}

	public void addActivity(DESBaseActivity activity) {
		//
		list.add(activity);
	}

	private void doOneStep(long now) {
		if (!list.isEmpty()) {
			for (DESBaseActivity act : list) {
				if (act instanceof DESBaseActivity) {
					act.action(now);
				}
			}
		}
	}

	public boolean isFinished() {
		boolean result = true;
		//
		if (!list.isEmpty()) {
			for (DESBaseActivity act : list) {
				if (act instanceof DESBaseActivity) {
					if (!act.isEmpty()) {
						result = false;
						break;
					}
				}
			}
		}
		//
		return result;
	}

	/**
	 * @return generator
	 */
	public DESGenerator getGenerator() {
		return generator;
	}

	/**
	 * @param generator セットする generator
	 */
	public void setGenerator(DESGenerator generator) {
		this.list.add(generator);
		this.generator = generator;
	}

	public void simulate() {
		long now = 0;
		long step = 1;

		if (generator != null) {
			try {
				//
				// イベントを作成する
				// 1) 期間または回数を基に実行するイベントを全て作成する
				now = this.generator.getStartTime();
				step = this.generator.getDuration();
				//
				this.generator.generate();
				//
				// 全てのActivityにてエベントが無くなるまで実行する
				while (!this.isFinished()) {
					this.doOneStep(now);
					now += step;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private void getMaxCountOfQueue() {
		long max = 0;
		long id = 0;

		for (DESBaseActivity activity : this.list) {
			if (max < activity.getMaxQueueCount()) {
				max = activity.getMaxQueueCount();
				id = activity.getId();
				// System.out.println(id + "-" + max);
			}
		}
		//
		maxQueueCount = max;
		maxQueueId = id;
	}

	private DESTerminater getTerminater() {
		DESTerminater term = null;

		for (DESBaseActivity act : this.list) {
			if (act instanceof DESTerminater) {
				term = (DESTerminater) act;
				break;
			}
		}

		return term;
	}

	/**
	 *
	 * 1)基本
	 * ・イベント数
	 * ・イベント発生間隔
	 * ・アルゴリズム(一定、ランダム、式)
	 * 2)最大待ち行列イベント
	 * ・発生個所(Activity)
	 * ・待ち行列数
	 * 3)平均待ち時間
	 * ・イベント毎の平均待ち時間
	 * ・アクティビティ毎の平均待ち時間
	 */
	public void showStatistics() {
		//
		// 1)基本
		// ・アクティビティ数
		// ・イベント数
		// ・イベント発生間隔
		// ・アルゴリズム(一定、ランダム、式)
		// 2)最大待ち行列イベント
		// ・発生個所(Activity)
		// ・待ち行列数
		// 3)平均待ち時間
		// ・イベント毎の平均待ち時間
		// ・アクティビティ毎の平均待ち時間

		System.out.println("==================================================");
		System.out.println("= 　　　　　　　　　統計情報 　　　　　　　　　　=");
		System.out.println("==================================================");
		System.out.println(" 1)アクティビティ数　　　 　　　　: " + (this.list.size() - 2));
		System.out.println(" 2)イベント数　　　 　　　　　　　: " + generator.getEventCount());
		System.out.println(" 3)イベント発生間隔 　　　　　　　: " + generator.getDuration());
		System.out.println(" 4)発生アルゴリズム 　　　　　　　: " + generator.getAlgorithmName());
		//
		this.getMaxCountOfQueue();
		double average_event = this.getTerminater().getTotalWaitingTime() / generator.getEventCount();
		double average_act = average_event / (this.list.size() - 2);
		System.out.println(" 5)最大待ち行列数　　　　　　　　 : " + maxQueueCount);
		String nodeName;
		if (maxQueueId == 0) {
			nodeName = "There is not any queue.";
		} else {
			nodeName = "ID-" + String.valueOf(maxQueueId);
		}
		System.out.println(" 6)発生個所　　　　　　　　　　　 : " + nodeName);
		System.out.println(" 7)イベント毎の平均待ち時間　　　 : " + average_event);
		System.out.println(" 8)アクティビティ毎の平均待ち時間 : " + average_act);
		//
		System.out.println("\n※ シミュレーションログは、time項(時間軸)を昇順に並び替えてください。");
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
}
