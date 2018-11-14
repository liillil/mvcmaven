package cn.java.mvcproject.service;

import java.util.List;

import cn.java.mvcproject.dao.FactoryDao;
import cn.java.mvcproject.dao.OnlineDao;
import cn.java.mvcproject.model.Online;

public class OnlineServiceImpl implements OnlineService{
	OnlineDao onlineDao = FactoryDao.getOnlineImpl();
	@Override
	public List<Online> getAllOnline() {
		
		return onlineDao.getAllOnline();
	}

	@Override
	public int insertOnline(Online online) {
		// TODO Auto-generated method stub
		return onlineDao.insertOnline(online);
	} 

	@Override
	public int updateOnlie(Online online) {
		
		return onlineDao.updateOnlie(online);
	}

	@Override
	public void deleteOnline(List<Online> list) {
		if(list !=null && list.size()>0) {
			for (Online online : list) {
				onlineDao.deleteOnline(online.getSsid());
			}
		}
	}

	@Override
	public Online getOnlineBySsid(String ssid) {
		return onlineDao.getOnlineBySsid(ssid);
	}

}
