/**
 *
 */
package com.fishbonelab.desengine.test;

import com.fishbonelab.desengine.DESActivity;
import com.fishbonelab.desengine.DESGenerator;
import com.fishbonelab.desengine.DESTaskManager;
import com.fishbonelab.desengine.DESTerminater;
import com.fishbonelab.desengine.utils.Log;

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
		generator.setName("開始");
		generator.setTime(0);
		generator.setStartTime(0);
		generator.setEndTime(20);
		generator.setDuration(10);
		//
		activity1.setId(2);
		activity1.setName("処理する");
		activity1.setWorkingTime(11);
		//
		terminater.setId(3);
		terminater.setName("完了");

		/// < ノード間を接続する ( set network among nodes.)
		generator.setOutNode(activity1);
		activity1.setOutNode(terminater);

		/// タスクマネージャにノードを登録する
		manager.addActivity(activity1);
		manager.addActivity(terminater);
		manager.setGenerator(generator);
		//
		/// 実行する
		Log.header();
		manager.simulate();
	}

}
