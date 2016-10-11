/**
 *
 */
package com.fishbonelab.desengine.utils;

import com.fishbonelab.desengine.DESActivity;
import com.fishbonelab.desengine.DESEvent;
import com.fishbonelab.desengine.DESGenerator;
import com.fishbonelab.desengine.DESTerminater;

/**
 * @author otuboyas
 *
 */
public class Log {

	public enum ENUM_EVENTS {
		EVENT_ARRIVAL, EVENT_PROC_START, EVENT_PROC_END, EVENT_DEPARTURE, EVENT_START, EVENT_END
	};

	/**
	 *
	 */
	public Log() {
	}

	public static void header() {
		String title = "event_id, action, time, pid, memo";
		System.out.println(title);
	}

	protected static void out(ENUM_EVENTS etype, DESEvent event, DESActivity activity) {
		StringBuffer buf = new StringBuffer();
		//
		/// < construct message
		buf.append(event.getId());
		switch (etype) {
			case EVENT_ARRIVAL:
				buf.append(",a,");
				buf.append(event.getArrivalTime());
				break;
			case EVENT_PROC_START:
				buf.append(",s,");
				buf.append(event.getProcessStartTime());
				break;
			case EVENT_PROC_END:
				buf.append(",e,");
				buf.append(event.getProcessEndTime());
				break;
			case EVENT_DEPARTURE:
				buf.append(",d,");
				buf.append(event.getDepartureTime());
				break;
			case EVENT_START:
				buf.append(",b,");
				buf.append(event.getStartTime());
				break;
			case EVENT_END:
				buf.append(",f,");
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
			case EVENT_PROC_START:
				buf.append(",s,");
				buf.append(event.getProcessStartTime());
				msg = "";
				break;
			case EVENT_PROC_END:
				buf.append(",e,");
				buf.append(event.getProcessEndTime());
				msg = "";
				break;
			case EVENT_DEPARTURE:
				buf.append(",d,");
				buf.append(event.getDepartureTime());
				msg = "";
				break;
			case EVENT_START:
				buf.append(",b,");
				buf.append(event.getStartTime());
				msg = "開始";
				break;
			case EVENT_END:
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

	public static void start(DESEvent event, DESGenerator generator) {
		out(Log.ENUM_EVENTS.EVENT_START, event, generator);
	};

	public static void end(DESEvent event, DESTerminater terminater) {
		out(Log.ENUM_EVENTS.EVENT_END, event, terminater);
	};

	public static void arrival(DESEvent event, DESActivity activity) {
		out(Log.ENUM_EVENTS.EVENT_ARRIVAL, event, activity);
	};

	public static void processStart(DESEvent event, DESActivity activity) {
		out(Log.ENUM_EVENTS.EVENT_PROC_START, event, activity);
	};

	public static void processEnd(DESEvent event, DESActivity activity) {
		out(Log.ENUM_EVENTS.EVENT_PROC_END, event, activity);
	};

	public static void departure(DESEvent event, DESActivity activity) {
		out(Log.ENUM_EVENTS.EVENT_DEPARTURE, event, activity);
	};

}
