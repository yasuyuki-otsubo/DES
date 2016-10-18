/**
 *
 */
package com.fishbonelab.desengine.utils;

import com.fishbonelab.desengine.DESActivity;
import com.fishbonelab.desengine.DESBaseActivity;
import com.fishbonelab.desengine.DESEvent;
import com.fishbonelab.desengine.DESGenerator;
import com.fishbonelab.desengine.DESTerminater;

/**
 * @author otuboyas
 *
 */
public class Log {

	private enum ENUM_EVENTS {
		EVENT_ARRIVAL, EVENT_START, EVENT_END, EVENT_DEPARTURE, EVENT_CREATE, EVENT_FINISH
	};

	/**
	 *
	 */
	public Log() {
	}

	/**
	 * ログヘッダを出力する
	 */
	public static void header() {
		String title = "event_id, action, time, pid, memo";
		System.out.println(title);
	}

	/**
	 * イベントタイプに応じたログを出力する
	 * @param etype イベントタイプ
	 * @param event イベントオブジェクト
	 * @param activity アクティビティ・オブジェクト
	 */
	protected static void out(ENUM_EVENTS etype, DESEvent event, DESBaseActivity activity) {
		StringBuffer buf = new StringBuffer();
		//
		/// < construct message
		buf.append(event.getId());
		switch (etype) {
			case EVENT_ARRIVAL:
				buf.append(",arrived,");
				buf.append(event.getArrivalTime());
				break;
			case EVENT_START:
				buf.append(",started,");
				buf.append(event.getProcessStartTime());
				break;
			case EVENT_END:
				buf.append(",end,");
				buf.append(event.getProcessEndTime());
				break;
			case EVENT_DEPARTURE:
				buf.append(",departured,");
				buf.append(event.getDepartureTime());
				break;
			case EVENT_CREATE:
				buf.append(",created,");
				buf.append(event.getStartTime());
				break;
			case EVENT_FINISH:
				buf.append(",finished,");
				buf.append(event.getTime());
				break;
			default:
				break;
		}
		buf.append(",");
		buf.append(activity.getId());
		buf.append(",");
		if ((activity.getName() == null) || (activity.getName().isEmpty())) {
			buf.append("no name");
		} else {
			buf.append(activity.getName());
		}
		//
		System.out.println(buf.toString());
	}

	/**
	 * イベントオブジェクト用のログを出力する
	 * @param etype イベントタイプ
	 * @param event イベントオブジェクト
	 */
	protected static void out2(ENUM_EVENTS etype, DESEvent event) {
		String msg = "";
		StringBuffer buf = new StringBuffer();
		//
		/// < construct message
		buf.append(event.getId());
		switch (etype) {
			case EVENT_ARRIVAL:
				buf.append(",a,");
				buf.append(event.getArrivalTime());
				msg = "";
				break;
			case EVENT_START:
				buf.append(",s,");
				buf.append(event.getProcessStartTime());
				msg = "";
				break;
			case EVENT_END:
				buf.append(",e,");
				buf.append(event.getProcessEndTime());
				msg = "";
				break;
			case EVENT_DEPARTURE:
				buf.append(",d,");
				buf.append(event.getDepartureTime());
				msg = "";
				break;
			case EVENT_CREATE:
				buf.append(",b,");
				buf.append(event.getStartTime());
				msg = "開始";
				break;
			case EVENT_FINISH:
				buf.append(",f,");
				buf.append(event.getTime());
				msg = "完了";
				break;
			default:
				break;
		}
		buf.append(",");
		buf.append(",");
		if (msg.length() > 0) {
			buf.append(msg);
		}
		//
		System.out.println(buf.toString());
	}

	/**
	 *
	 * @param event
	 * @param generator
	 */
	public static void create(DESEvent event, DESGenerator generator) {
		out(Log.ENUM_EVENTS.EVENT_CREATE, event, generator);
	};

	/**
	 *
	 * @param event
	 * @param terminater
	 */
	public static void finish(DESEvent event, DESTerminater terminater) {
		out(Log.ENUM_EVENTS.EVENT_FINISH, event, terminater);
	};

	/**
	 *
	 * @param event
	 * @param activity
	 */
	public static void arrival(DESEvent event, DESActivity activity) {
		out(Log.ENUM_EVENTS.EVENT_ARRIVAL, event, activity);
	};

	/**
	 *
	 * @param event
	 * @param activity
	 */
	public static void start(DESEvent event, DESActivity activity) {
		out(Log.ENUM_EVENTS.EVENT_START, event, activity);
	};

	/**
	 *
	 * @param event
	 * @param activity
	 */
	public static void end(DESEvent event, DESActivity activity) {
		out(Log.ENUM_EVENTS.EVENT_END, event, activity);
	};

	/**
	 *
	 * @param event
	 * @param activity
	 */
	public static void departure(DESEvent event, DESActivity activity) {
		out(Log.ENUM_EVENTS.EVENT_DEPARTURE, event, activity);
	};

}
