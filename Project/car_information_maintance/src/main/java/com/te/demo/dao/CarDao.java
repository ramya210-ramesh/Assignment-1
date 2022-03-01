package com.te.demo.dao;

import org.springframework.data.repository.CrudRepository;

import com.te.demo.bean.CarDetails;

public interface CarDao extends CrudRepository<CarDetails,Integer>{
	public CarDetails findByCarId(int carId);
}

