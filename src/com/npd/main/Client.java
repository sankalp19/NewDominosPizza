package com.npd.main;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.ndp.bean.Dish;
import com.ndp.bean.Location;
import com.ndp.bean.Order;
import com.ndp.util.NewDominozPizza;
import com.ndp.util.NewDominozPizzaImpl;

public class Client {
	public static void main(String[] args) {
		List<Dish> list = new ArrayList<Dish>();
		Set<Location> set = new HashSet<Location>();
		NewDominozPizza nd = new NewDominozPizzaImpl();
		nd.populateData("C:\\Users\\sankalp\\eclipse-workspace\\NewDominozPizza\\dish.txt",
				"C:\\Users\\sankalp\\eclipse-workspace\\NewDominozPizza\\location.txt", list, set);
		System.out.println("--------Dishes--------");
		for (Dish d : list) {
			System.out.println(d);
		}
		System.out.println(list.size());
		System.out.println("\n");
		System.out.println("-------Locations--------");
		for (Location l : set) {
			System.out.println(l);
		}
		System.out.println(set.size());
		System.out.println("\n");
		nd.calculateLocationForDistance(list, set);
		System.out.println("--------Dishes--------");
		for (Dish d : list) {
			System.out.println(d);
			System.out.println(d.getSet().size());
		}
		nd.getOrderDetails();
		System.out.println(list.size());
		System.out.println("\n");
		List<Order> order = nd
				.calculateOrder("C:\\Users\\sankalp\\eclipse-workspace\\NewDominozPizza\\newOrderList.txt", list, set);
		for (Order o : order) {
			System.out.println(o);
		}

		System.out.println(order.size());
		System.out.println("\n");
		System.out.println("Orders available for free delivery are");
		nd.freeDelivery(order, list, set);

	}

}
