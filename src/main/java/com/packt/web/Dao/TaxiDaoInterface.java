package com.packt.web.Dao;

import java.util.List;

import com.packt.web.bean.Taxi;

public interface TaxiDaoInterface {
	
	public Taxi getById(Long id);
	public List<Taxi> getAll();
	public void persist(Taxi taxi);
	public void remove(Long id);
	public void update(Taxi taxi); 

}
