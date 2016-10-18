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
public class TestSimple3 {

	/**
	 *
	 */
	public TestSimple3() {
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//
		/// < (必須)タスクマネージャを作成する
		DESTaskManager manager = new DESTaskManager();
		manager.setName("順次一定間隔イベントを２つの窓口にて対応する");

		/// < ノードを作成する
		DESGenerator generator = new DESGenerator();
		DESActivity activity1 = new DESActivity();
		DESTerminater terminater = new DESTerminater();

		/// < ノードを定義する
		generator.setId(1);
		generator.setName("開始");
		// generator.setTime(0);
		generator.setStartTime(0); // 開始時間： ０
		generator.setEndTime(20); // 終了時間：２０
		generator.setDuration(1); // イベント発生間隔：１０
		generator.setAlgorithmName("一定間隔");
		//
		activity1.setId(2);
		activity1.setRedundantNumber(2);
		activity1.setName("処理1をする");
		activity1.setWorkingTime(5); // 処理時間：５；ここをDurationより大きい数値にすると待ち行列が発生する
		//
		terminater.setId(5);
		terminater.setName("完了");

		/// < ノード間を接続する ( set network among nodes.)
		/// generator => activity1 => activity2 => terminator
		generator.setOutNode(activity1);
		activity1.setOutNode(terminater);

		/// タスクマネージャにノードを登録する
		manager.setGenerator(generator);
		manager.addActivity(activity1);
		manager.addActivity(terminater);
		//
		/// 実行する
		System.out.println(manager.getName());
		Log.header(); /// < ログの見出しを出力する
		manager.simulate();
		//
		// 統計情報を出力する
		manager.showStatistics();
	}
}
