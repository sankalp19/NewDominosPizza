package com.ndp.util;

import java.util.List;
import java.util.Set;

import com.ndp.bean.Dish;
import com.ndp.bean.Location;
import com.ndp.bean.Order;

public interface NewDominozPizza {
	void populateData(String dishFile, String locationFile, List<Dish> dishes, Set<Location> locations);

	void calculateLocationForDistance(List<Dish> dishes, Set<Location> locations);

	List<Order> calculateOrder(String orderFile, List<Dish> dishes, Set<Location> locations);

	void freeDelivery(List<Order> orders, List<Dish> dishes, Set<Location> locations);

	void getOrderDetails();

}
