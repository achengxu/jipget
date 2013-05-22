package com.achengxu.ipseeker.util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * ��־�������½ϵͳ�û����ڵĸ�Ŀ¼������������־���ļ����£� ���Ҹ���ϵͳ����������־�ļ�
 * 
 * @author wang
 * 
 */
public class IpLoggerUtil {

	/** ��ŵ��ļ��� **/
	private static String file_name = "log.ip";

	/**
	 * �õ�Ҫ��¼����־��·�����ļ�����
	 * 
	 * @return
	 */
	private static String getLogName() {
		StringBuffer logPath = new StringBuffer();
		logPath.append(System.getProperty("user.home"));
		logPath.append("\\" + file_name);
		File file = new File(logPath.toString());
		if (!file.exists())
			file.mkdir();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		logPath.append("\\" + sdf.format(new Date()) + ".log");

		return logPath.toString();
	}

	/**
	 * ����Logger���������־�ļ�·��
	 * 
	 * @param logger
	 * @throws SecurityException
	 * @throws IOException
	 */
	public static void setLogingProperties(Logger logger) throws SecurityException, IOException {
		setLogingProperties(logger, Level.ALL);
	}

	/**
	 * ����Logger���������־�ļ�·��
	 * 
	 * @param logger
	 * @param level
	 *            ����־�ļ������level�������ϵ���Ϣ
	 * @throws SecurityException
	 * @throws IOException
	 */
	public static void setLogingProperties(Logger logger, Level level) {
		FileHandler fh;
		try {
			fh = new FileHandler(getLogName(), true);
			logger.addHandler(fh);// ��־����ļ�
			// logger.setLevel(level);
			fh.setFormatter(new SimpleFormatter());// �����ʽ
			// logger.addHandler(new ConsoleHandler());//���������̨
		} catch (SecurityException e) {
			logger.log(Level.SEVERE, "��ȫ�Դ���", e);
		} catch (IOException e) {
			logger.log(Level.SEVERE, "��ȡ�ļ���־����", e);
		}
	}

	public static void main(String[] args) {
		Logger logger = Logger.getLogger("sgg");
		try {
			setLogingProperties(logger);
			logger.log(Level.INFO, "ddddd");
			logger.log(Level.INFO, "eeeeee");
			logger.log(Level.INFO, "ffffff");
			logger.log(Level.INFO, "gggggg");
			logger.log(Level.OFF, "hhhhhh");
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
