package com.achengxu.ipseeker.inter;

import java.util.HashMap;

import com.achengxu.ipseeker.parse.IPLocation;
import com.achengxu.ipseeker.parse.IPSeeker;


/**
 * 获取IP相关信息
 */
public class IPGet {

	static IPSeeker ip;

	/**
	 * 初始化ip库所在路径
	 * 
	 * @param path
	 */
	public static void init(String path) {
		// 指定纯真数据库的文件名，所在文件夹
		ip = new IPSeeker("qqwry.dat", path);
	}

	/**
	 * 根据ip活得该ip地址所在运营商和城市
	 * 
	 * @param netWorkip
	 * <br/>
	 *            218.58.88.175 => 山东省青岛市开发区<br/>
	 *            212.1.1.1 => 意大利 <br/>
	 *            192.245.148.0 => 阿尔及利亚 <br/>
	 *            116.254.254.0 => 广东省广州市<br/>
	 */
	public static HashMap<String, String> getInfo(String netWorkip) {
		// 测试IP 58.20.43.13
		HashMap<String, String> info = new HashMap<String, String>();
		String parMat = "\\b((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\b";
		IPLocation local = ip.getIPLocation(netWorkip);
		String Country = local.getCountry();
		String Area = local.getArea();
		if (netWorkip.matches(parMat)) {
			info.put("country", Country);
			info.put("area", Area);
		}
		// 以键值对的方式存储到redis数据
		// jedis.hmset(netWorkip, ipArea);
		return info;
	}

	/** 获取局域网的公网地址(如果联网) */
	public static String getPublicIp(String localGroupIp) {
		IPLocation local = ip.getIPLocation(localGroupIp);
		return local.getPublicIp();
	}
	
	public static final String getCountry(String ip){
		return getInfo(ip).get("country");
	}

	public static void main(String[] args) {
		init("conf/");
		System.out.println("**************以下为测试结果**************");
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