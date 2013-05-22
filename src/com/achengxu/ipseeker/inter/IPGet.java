package com.achengxu.ipseeker.inter;

import java.util.HashMap;

import com.achengxu.ipseeker.parse.IPLocation;
import com.achengxu.ipseeker.parse.IPSeeker;


/**
 * ��ȡIP�����Ϣ
 */
public class IPGet {

	static IPSeeker ip;

	/**
	 * ��ʼ��ip������·��
	 * 
	 * @param path
	 */
	public static void init(String path) {
		// ָ���������ݿ���ļ����������ļ���
		ip = new IPSeeker("qqwry.dat", path);
	}

	/**
	 * ����ip��ø�ip��ַ������Ӫ�̺ͳ���
	 * 
	 * @param netWorkip
	 * <br/>
	 *            218.58.88.175 => ɽ��ʡ�ൺ�п�����<br/>
	 *            212.1.1.1 => ����� <br/>
	 *            192.245.148.0 => ���������� <br/>
	 *            116.254.254.0 => �㶫ʡ������<br/>
	 */
	public static HashMap<String, String> getInfo(String netWorkip) {
		// ����IP 58.20.43.13
		HashMap<String, String> info = new HashMap<String, String>();
		String parMat = "\\b((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\b";
		IPLocation local = ip.getIPLocation(netWorkip);
		String Country = local.getCountry();
		String Area = local.getArea();
		if (netWorkip.matches(parMat)) {
			info.put("country", Country);
			info.put("area", Area);
		}
		// �Լ�ֵ�Եķ�ʽ�洢��redis����
		// jedis.hmset(netWorkip, ipArea);
		return info;
	}

	/** ��ȡ�������Ĺ�����ַ(�������) */
	public static String getPublicIp(String localGroupIp) {
		IPLocation local = ip.getIPLocation(localGroupIp);
		return local.getPublicIp();
	}
	
	public static final String getCountry(String ip){
		return getInfo(ip).get("country");
	}

	public static void main(String[] args) {
		init("conf/");
		System.out.println("**************����Ϊ���Խ��**************");
		System.out.println(getInfo("121.229.142.218"));
		System.out.println(getInfo("61.172.201.195"));
		System.out.println(getInfo("58.20.43.13"));
		System.out.println(getInfo("61.160.200.237"));
		System.out.println(getInfo("74.125.71.147"));
		System.out.println(getInfo("180.173.166.177"));
		System.out.println(getInfo("9.9.9.9"));
		System.out.println(getInfo("8.8.8.8"));
		System.out.println(getInfo("8.8.4.4"));
		System.out.println(getInfo("192.168.1.112"));
		System.out.println(getInfo("127.0.0.1"));
		System.out.println(getPublicIp("127.0.0.1"));
		System.out.println("*****************************************");
		// System.out.println(jedis.hget("58.20.43.13", "country"));
		// for (int a = 0; a < IPField.ip1.length; ++a)
		// for (int b = 0; b < IPField.ip2.length; ++b)
		// for (int c = 0; c < IPField.ip3.length; ++c)
		// for (int d = 0; d < IPField.ip4.length; ++d) {
		// System.out.println(getInfo(IPField.ip1[a] + "."
		// + IPField.ip2[b] + "." + IPField.ip3[c] + "."
		// + IPField.ip4[d]));
		//
		// }
	}

}