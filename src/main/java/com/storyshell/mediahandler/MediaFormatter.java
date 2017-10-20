package com.storyshell.mediahandler;

import java.io.InputStream;
import java.io.OutputStream;
import java.nio.Buffer;

public interface MediaFormatter {

	public boolean isFormatExists(String format);

	public boolean formatFile(InputStream source, String target, String formatType);

	public boolean deleteFile(String fileName);

	public boolean renameFile(String newName, String oldName);
   
    public Buffer[] streamFile(String fileName,int offset);
    
    public boolean validMediaContent(InputStream source);
    
    
}
