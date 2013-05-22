package com.achengxu.ipseeker.util;

import java.io.UnsupportedEncodingException;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * �����࣬�ṩһЩ����ķ���
 */
public class IPUtil {

	private static Logger _logger = Logger.getLogger(IpLoggerUtil.class.getName());

	private static StringBuilder sb = new StringBuilder();

	/**
	 * ��ip���ַ�����ʽ�õ��ֽ�������ʽ
	 * 
	 * @param ip
	 *            �ַ�����ʽ��ip
	 * @return �ֽ�������ʽ��ip
	 */
	public static byte[] getIpByteArrayFromString(String ip) {
		byte[] ret = new byte[4];
		StringTokenizer st = new StringTokenizer(ip, ".");
		try {
			ret[0] = (byte) (Integer.parseInt(st.nextToken()) & 0xFF);
			ret[1] = (byte) (Integer.parseInt(st.nextToken()) & 0xFF);
			ret[2] = (byte) (Integer.parseInt(st.nextToken()) & 0xFF);
			ret[3] = (byte) (Integer.parseInt(st.nextToken()) & 0xFF);
		} catch (Exception e) {
			_logger.log(Level.WARNING, "��ip���ַ�����ʽ�õ��ֽ�������ʽ����", e);
		}
		return ret;
	}

	/**
	 * @param ip
	 *            ip���ֽ�������ʽ
	 * @return �ַ�����ʽ��ip
	 */
	public static String getIpStringFromBytes(byte[] ip) {
		sb.delete(0, sb.length());
		sb.append(ip[0] & 0xFF);
		sb.append('.');
		sb.append(ip[1] & 0xFF);
		sb.append('.');
		sb.append(ip[2] & 0xFF);
		sb.append('.');
		sb.append(ip[3] & 0xFF);
		return sb.toString();
	}

	/**
	 * ����ĳ�ֱ��뷽ʽ���ֽ�����ת�����ַ���
	 * 
	 * @param b
	 *            �ֽ�����
	 * @param offset
	 *            Ҫת������ʼλ��
	 * @param len
	 *            Ҫת���ĳ���
	 * @param encoding
	 *            ���뷽ʽ
	 * @return ���encoding��֧�֣�����һ��ȱʡ������ַ���
	 */
	public static String getString(byte[] b, int offset, int len, String encoding) {
		try {
			return new String(b, offset, len, encoding);
		} catch (UnsupportedEncodingException e) {
			return new String(b, offset, len);
		}
	}
}
