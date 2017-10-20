package com.storyshell.mediahandler;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import it.sauronsoftware.jave.AudioAttributes;
import it.sauronsoftware.jave.EncodingAttributes;
import it.sauronsoftware.jave.VideoAttributes;

public interface FormatService {

	public boolean formatFile(InputStream source, OutputStream target, String formatType);

	public EncodingAttributes generateEncodingAttributes(String sourceType, String targetType);

	public AudioAttributes generateAudioAttr(String sourceType, String targetType);

	public VideoAttributes genereateVideoAttributes(String sourceType, String targetType);

	public List<String> getFileAttributes(String FileName);
}