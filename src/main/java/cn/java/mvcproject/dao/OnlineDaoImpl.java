package cn.java.mvcproject.dao;

import java.util.List;

import cn.java.mvcproject.model.Online;

public class OnlineDaoImpl extends BaseDao<Online> implements OnlineDao{

	@Override 
	public List<Online> getAllOnline() {
		String sql = "SELECT `ssid`,`userId`,`username`,`page`,`ip`,`time` FROM online;";
		return super.getList(sql);
	}

	@Override
	public int insertOnline(Online online) {
		String sql = "INSERT online SET `ssid`=?,`userId`=?,`username`=?,`page`=?,`ip`=?,`time`=?;";
		return super.update(sql, online.getSsid(),online.getUserId(),online.getUsername(),online.getPage(),online.getIp(),online.getTime());
	}

	@Override
	public int updateOnlie(Online online) {
		String sql = "UPDATE `online` SET `userId`=?,`username`=?,`page`=?,`ip`=?,`time`=? WHERE `ssid`=? ";
		return super.update(sql, online.getUserId(),online.getUsername(),online.getPage(),online.getIp(),online.getTime(),online.getSsid());
	}
 
	@Override
	public int deleteOnline(String ssid) {
		String sql = "DELETE FROM `online` WHERE ssid = ?";
		return super.update(sql, ssid);
	}

	@Override
	public Online getOnlineBySsid(String ssid) {
		String sql = "SELECT `ssid`,`userId`,`username`,`page`,`ip`,`time` FROM online where ssid = ?";
		return super.get(sql, ssid);
	}

}
