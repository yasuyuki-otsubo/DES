/**
 *
 */
package com.fishbonelab.desengine.utils;

import com.fishbonelab.desengine.DESActivity;
import com.fishbonelab.desengine.DESEvent;

/**
 * @author otuboyas
 *
 */
public class Log {

	public enum ENUM_EVENTS {
		EVENT_ARRIVAL, EVENT_START, EVENT_END, EVENT_DEPARTURE
	};

	/**
	 *
	 */
	public Log() {
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
			case EVENT_START:
				buf.append(",s,");
				buf.append(event.getProcessStartTime());
				break;
			case EVENT_END:
				buf.append(",e,");
				buf.append(event.getProcessEndTime());
				break;
			case EVENT_DEPARTURE:
				buf.append(",d,");
				buf.append(event.getDepartureTime());
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

	public static void arrival(DESEvent event, DESActivity activity) {
		out(Log.ENUM_EVENTS.EVENT_ARRIVAL, event, activity);
	};

	public static void processStart(DESEvent event, DESActivity activity) {
		out(Log.ENUM_EVENTS.EVENT_ARRIVAL, event, activity);
	};

	public static void processEnd(DESEvent event, DESActivity activity) {
		out(Log.ENUM_EVENTS.EVENT_ARRIVAL, event, activity);
	};

	public static void departure(DESEvent event, DESActivity activity) {
		out(Log.ENUM_EVENTS.EVENT_ARRIVAL, event, activity);
	};

}
