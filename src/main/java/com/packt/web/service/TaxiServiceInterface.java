package com.packt.web.service;

import java.util.List;

import com.packt.web.bean.Taxi;

public interface TaxiServiceInterface {
	
	public void save(Taxi taxi);
	public List<Taxi> getAll();
	public Taxi getById(int id);
	public void remove(int id);
	public void update(Taxi taxi);

}
