/**
 *
 */
package com.fishbonelab.desengine.test;

import com.fishbonelab.desengine.DESActivity;
import com.fishbonelab.desengine.DESGenerator;
import com.fishbonelab.desengine.DESTaskManager;
import com.fishbonelab.desengine.DESTerminater;

/**
 * @author otuboyas
 *
 */
public class TestSimple1 {

	/**
	 *
	 */
	public TestSimple1() {
		// TODO 自動生成されたコンストラクター・スタブ
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//
		/// < (必須)タスクマネージャを作成する
		DESTaskManager manager = new DESTaskManager();

		/// < ノードを作成する
		DESGenerator generator = new DESGenerator();
		DESActivity activity1 = new DESActivity();
		DESTerminater terminater = new DESTerminater();

		/// < ノードを定義する
		generator.setId(1);
		generator.setName("イベントを作成する");
		generator.setTime(0);
		generator.setStartTime(0);
		generator.setEndTime(100);
		generator.setDuration(10);

		/// < ノード間を接続する ( set network among nodes.)
		generator.setOutNode(activity1);
		activity1.setOutNode(terminater);

		/// タスクマネージャにノードを登録する
		manager.addActivity(activity1);
		manager.addActivity(terminater);
		manager.setGenerator(generator);
		//
		/// 実行する
		manager.simulate();
	}

}
