package com.storyshell.util;

import java.io.File;
import java.io.InputStream;
import java.nio.Buffer;

import com.storyshell.mediahandler.MediaFormatter;

import it.sauronsoftware.jave.AudioAttributes;
import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.EncoderException;
import it.sauronsoftware.jave.EncodingAttributes;

/**
 * 
 * @author Monarchpedo
 *
 */
public class FileProcessor implements MediaFormatter {

	private String FILE_PATH = "";
	public Encoder encoder = new Encoder();
	public static AudioAttributes audioAttributes = new AudioAttributes();
	public EncodingAttributes encodingAttributes = new EncodingAttributes();

	public static void main(String args[]) throws EncoderException {
		FileProcessor converter = new FileProcessor();
		String[] list = converter.encoder.getSupportedEncodingFormats();
		for (int i = 0; i < list.length; i++) {
			System.out.println(list[i]);
		}
		converter.audioAttributes.setBitRate(256000);
		converter.audioAttributes.setChannels(2);
		converter.audioAttributes.setSamplingRate(44100);
		converter.audioAttributes.setCodec("vorbis");
		converter.encodingAttributes.setFormat("ogg");
		converter.encodingAttributes.setAudioAttributes(audioAttributes);
		File source = new File("D:/confession/Set Fire To The Rain (Live At The Royal Albert Hall).mp3");
		File target = new File("D:/confession/sample.ogg");
		converter.encoder.encode(source, target, converter.encodingAttributes);
	}

	@Override
	public boolean isFormatExists(String format) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean formatFile(InputStream source, String target, String formatType) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteFile(String fileName) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean renameFile(String newName, String oldName) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Buffer[] streamFile(String fileName, int offset) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean validMediaContent(InputStream source) {
		// TODO Auto-generated method stub
		return false;
	}
}
