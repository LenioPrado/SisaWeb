/*
* File:	     LogSISA.java
* Creation date:
* Author:        L�nio
*/

package br.edu.pcs.ifsulmg.sisa.util;

import java.io.File;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogSISA {

	public static String WEB = "web";
	
	/** The Constant KEY_MAINCLASS. */
	public static final String KEY_MAINCLASS = "main.class";
	
	/**
	 * Instantiates a new log sisa.
	 */
	private LogSISA() { }
	
	/** The LOGGER. */
	public static Logger log = null;
	
	static{
		File fileDir = new File("log");
		fileDir.mkdir();
		log = LoggerFactory.getLogger(getAppender());
	}
	
	/**
	 * Gets the appender.
	 *
	 * @return the appender
	 */
	private static String getAppender(){		
		String mainC = getMainClass();
		if(mainC == null){
			System.err.println("  ERROR: not found main class !");
		}
		return WEB;
	}
	
	/**
	 * Gets the main class.
	 *
	 * @return the main class
	 */
	private static String getMainClass() {
		String lastMainClass = getMainClassName();
		if(lastMainClass==null){
			Map<Thread,StackTraceElement[]> stackTraceMap = Thread.getAllStackTraces();
			for (Thread t : stackTraceMap.keySet())
			{
				if ("main".equals(t.getName()))
				{
					StackTraceElement[] mainStackTrace = stackTraceMap.get(t);
					for (StackTraceElement element : mainStackTrace)
					{
						lastMainClass = element.getClassName();
					}
				}
			}
		}
		return lastMainClass;
	}
	
	/**
	 * Gets the main class name.
	 *
	 * @return the main class name
	 */
	private static String getMainClassName()
	{
	  return System.getProperty(KEY_MAINCLASS);
	}
}
