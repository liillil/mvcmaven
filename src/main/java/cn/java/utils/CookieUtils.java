package cn.java.utils;

import java.security.MessageDigest;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtils {
	private static final String KEY = "cookie@235liuzhen";
	/**
	 * 命令浏览器创建cookie文件用的方法
	 * @param userId :放到cookie信息，用户账号
	 * @param req
	 * @param resp :调用addcookie方法的response对象
	 * @param sec :cookie失效的时间 单位是秒
	 * 
	 */
	public static void createCookie(String userId,HttpServletRequest req,HttpServletResponse resp,int sec) {
		Cookie userCookie = new Cookie("userKey", userId);
		Cookie ssidCookie = new Cookie("ssid", md5Encrypt(userId));
		userCookie.setMaxAge(sec);
		ssidCookie.setMaxAge(sec);
		resp.addCookie(userCookie);
		resp.addCookie(ssidCookie);
	}
	/**
	 * 加密方法，把一个明文字符串加密成密文
	 * @param ss:等待被加密的明文
	 * @return
	 */
	public static String md5Encrypt(String ss) {
		ss = ss == null?"":ss+KEY; //将传过来的userId与ss拼接
		char[] md5Digist = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};//字典
		try{//使用MD5加密算法加密
			MessageDigest md = MessageDigest.getInstance("MD5");//md5  sha1两种加密算法，
			byte[] b = ss.getBytes(); //将新的字符串转换为byte数组
			md.update(b);
			byte[] mssarr = md.digest();//真正加密 用一个byte数组接收加密后的类容
			
			int len = mssarr.length;
			char[] str = new char[len*2];
			int k = 0;//计数
			
			
			for(int i = 0;i<len;i++) {
				byte by = mssarr[i]; //010101010
				str[k++] = md5Digist[(by >> 4) & 0xf];  //再将接受到的数组中的元素 向左移动四位 再与0xf相与 再将所得的数值当做字典的下标存入另一个char数组中
				str[k++] = md5Digist[by & 0xf];// 再将上面得到的继续与0xf相与 将所得的数值当做字典的下标 存入 char数组的下一个位置
			}
			System.out.println(new String(str));
			return new String(str);
		}catch (Exception e) { 
			e.printStackTrace();
		}
		return null;
		
	}
}
