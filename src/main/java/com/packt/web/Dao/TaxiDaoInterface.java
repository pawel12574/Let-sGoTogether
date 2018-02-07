package com.packt.web.Dao;

import java.util.List;

import com.packt.web.bean.Taxi;

public interface TaxiDaoInterface {
	
	public Taxi getById(int id);
	public List<Taxi> getAll();
	public void persist(Taxi taxi);
	public void remove(int id);
	public void update(Taxi taxi); 

}
