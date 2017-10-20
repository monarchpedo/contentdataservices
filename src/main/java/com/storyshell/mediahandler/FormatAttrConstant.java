package com.storyshell.mediahandler;

import java.util.Map;

public class FormatAttrConstant {

	public static class Mp3ToOgg {
		public static final int CHANNELS = 2;
		public static final int SAMPLE_RATE = 4500;
		public static final int BITRATE = 21000;
	};

	public static class OggToMp3 {

	};

	public static Map<String, Object> getAttributesList(String conversionType) {
		Class[] a = FormatAttrConstant.class.getDeclaredClasses();
		return null;
	}

}
