package cn.java.mvcproject.service;

import java.util.List;

import cn.java.mvcproject.model.Online;

public interface OnlineService {
	/**
	 * 查询所用在线用户
	 * @return
	 */
	public List<Online> getAllOnline();
	/**
	 * 新增在线用户
	 * @return
	 */
	public int insertOnline(Online online);
	/**
	 * 更新保存的online信息
	 * @param ssid
	 * @return
	 */
	public int updateOnlie(Online online);
	/**
	 * 删除离线用户
	 * @param ssid
	 * @return
	 */
	public void deleteOnline(List<Online> list);
	/**
	 * 根据ssid获取一条online在线的信息
	 * @param ssid
	 * @return
	 */
	public Online getOnlineBySsid(String ssid);
}
