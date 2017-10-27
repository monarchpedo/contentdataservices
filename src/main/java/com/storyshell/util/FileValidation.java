package com.storyshell.util;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileValidation {

	private static Logger LOG = LoggerFactory.getLogger(FileValidation.class);
	private static List<String> extensionList = Arrays.asList(".gif", ".jpg", ".img", ".tif", ".png");
	private static List<String> videoList = Arrays.asList(".mp4", ".ogg", ".wav", ".flv", ".mov", ".wmv", ".avi");
	private static List<String> audioList = Arrays.asList(".mp3", ".tif", ".wma");
	private static List<String> specialFileName = Arrays.asList("web.config", ".htaccess");
	private static List<String> unwantedCharacters = Arrays.asList("<", ">", "", "?", "*");
	private static int validVideoSize = 40;
	private static int validImageSize = 5;
	private static int validAudioSize = 20;

	private static boolean validFileName(final String fileName) {
		if (fileName.length() >= 255) {
			return true;
		}
		return false;
	}

	private static boolean validFileSize(final String fileName) {
		return false;
	}

	private static boolean validFileMetaData(final String fileName) {
		for (String specialChar : unwantedCharacters) {
			if (fileName.indexOf(specialChar) != 0) {
				return true;
			}
		}
		return false;
	}

	private static boolean validFileExtension(final String fileName) {

		int dotIndex = fileName.indexOf('.');
		int lastDotINdex = fileName.lastIndexOf('.');
		if (dotIndex != lastDotINdex) {
			return true;
		}

		String extension = fileName.substring(dotIndex, fileName.length());
		if (!extensionList.contains(extension)) {
			return true;
		}
		return false;
	}

	public static boolean validFileNameFormat(final String fileName) {
		if (validFileSize(fileName) || validFileMetaData(fileName) || validFileExtension(fileName)
				|| checkNeutralCharacters(fileName)) {
			return true;
		}
		return false;
	}

	private static boolean checkSpecialFile(final String fileName) {
		return false;
	}

	private static boolean checkNeutralCharacters(final String fileName) {

		return false;
	}

	private static boolean checkNullCharacteres(final String fileName) {
		for (int i = 0; i < fileName.length(); i++) {
			if (fileName.charAt(i) <= -1) {
				return true;
			}
		}
		return false;
	}

	public static int checkMediaType(String fileName) {
		int result = 0;
		int dotIndex = fileName.indexOf('.');
		int lastDotINdex = fileName.lastIndexOf('.');
		String extension = fileName.substring(dotIndex, fileName.length());
		if (extensionList.contains(extension)) {
			result = 1;
		} else if (videoList.contains(extension)) {
			result = 2;
		} else if (audioList.contains(extensionList)) {
			result = 3;
		}
		return result;
	}

}
