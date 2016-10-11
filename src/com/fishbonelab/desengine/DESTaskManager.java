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

	private ArrayList<DESActivity> list;
	private DESGenerator generator;

	/**
	 *
	 */
	public DESTaskManager() {
		list = new ArrayList<DESActivity>();
	}

	public void addActivity(DESActivity activity) {
		//
		list.add(activity);
	}

	private void doOneStep(long now) {
		if (!list.isEmpty()) {
			for (DESActivity act : list) {
				if (act instanceof DESActivity) {
					act.action(now);
				}
			}
		}
	}

	public boolean isFinished() {
		boolean result = true;
		//
		if (!list.isEmpty()) {
			for (DESActivity act : list) {
				if (act instanceof DESActivity) {
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
				// 設定された期間を実行する
				while (!this.isFinished()) {
					this.doOneStep(now);
					now += step;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
