package com.packt.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.packt.web.Dao.TaxiDaoInterface;
import com.packt.web.bean.Taxi;

@Service
public class TaxiService implements TaxiServiceInterface{

	@Autowired
	TaxiDaoInterface taxiDao;
	
	public void save(Taxi taxi) {
		taxiDao.persist(taxi);
	}

    public List<Taxi> getAll() {
		return taxiDao.getAll();
	}

	public Taxi getById(Long id) {
		return taxiDao.getById(id);
	}

	public void remove(Long id) {
		taxiDao.remove(id);
	}

	public void update(Taxi taxi) {
		taxiDao.update(taxi);
		
	}

}
