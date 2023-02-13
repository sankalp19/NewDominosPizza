package com.ndp.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import com.ndp.bean.Dish;
import com.ndp.bean.Location;
import com.ndp.bean.Order;

public class NewDominozPizzaImpl implements NewDominozPizza {

	@Override
	public void populateData(String dishFile, String locationFile, List<Dish> dishs, Set<Location> locations) {
		Scanner scan = null;
		try {
			scan = new Scanner(new File(dishFile));
			while (scan.hasNext()) {
				String t[] = scan.nextLine().split(",");
				int dishId = Integer.parseInt(t[0].trim());// search parse
				String dishName = t[1].trim();
				double cost = Double.parseDouble(t[2].trim());
				double timeToCook = Double.parseDouble(t[3].trim());
				dishs.add(new Dish(dishId, dishName, cost, timeToCook, new HashSet<Location>()));// to avoid gc of
																									// object
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			scan.close();
		}

		try {
			scan = new Scanner(new File(locationFile));
			while (scan.hasNext()) {
				String t[] = scan.nextLine().split(",");
				int locationCode = Integer.parseInt(t[0].trim());
				int locationDistance = Integer.parseInt(t[1].trim());
				double locationTime = Double.parseDouble(t[2].trim());
				locations.add(new Location(locationCode, locationDistance, locationTime));

			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			scan.close();
		}

	}
	@Override
	public void calculateLocationForDistance(List<Dish> dishes, Set<Location> locations) {
		// TODO Auto-generated method stub
		for (Dish d : dishes) {
			for (Location l : locations) {
				if (d.getTimeToCook() + l.getLocationTime() <= 30) {
					d.getSet().add(l);
				}
			}
		}

	}

	@Override
	public List<Order> calculateOrder(String orderFile, List<Dish> dishes, Set<Location> locations) {
		// TODO Auto-generated method stub
		Scanner scan = null;
		List<Order> list = new ArrayList<Order>();
		try {
			scan = new Scanner(new File(orderFile));
			while (scan.hasNext()) {
				String t[] = scan.nextLine().split(",");
				int dishId = Integer.parseInt(t[0].trim());
				int locationCode = Integer.parseInt(t[1].trim());
				for (Dish d : dishes) {
					for (Location l : locations) {
						if (d.getDishId() == dishId && l.getLocationCode() == locationCode) {
							if (d.getTimeToCook() + l.getLocationTime() <= 30) {
								double cost = d.getCost() + l.getLocationDistance() * 1;
								list.add(new Order(dishId, locationCode, cost));
							}
						}
					}
				}

			}
		} catch (FileNotFoundException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			scan.close();
		}
		return list;
	}

	@Override
	public void freeDelivery(List<Order> orders, List<Dish> dishes, Set<Location> locations) {
		// TODO Auto-generated method stub
	//Dish dish = dishes.get(dishes.indexOf(new Dish(ord.getDishId()))); //add a constructor in Dish class file by DishId only and equals method also needed to be add in the  
		//Location loc = locations.getClass()
		for (Order o : orders) {
			for (Dish d : dishes) {
				for (Location l : locations) {
					if (o.getDishId() == d.getDishId() && o.getLocationCode() == l.getLocationCode()) {
						if (d.getCost() > 200 && l.getLocationDistance() <= 10) {
							o.setTotalCost(d.getCost());
							System.out.println(o);
						}
					}
				}
			}
		}
	}

	@Override
	public void getOrderDetails() {
		// TODO Auto-generated method stub
		try {

			Scanner sc = new Scanner(System.in);
			System.out.println("Enter  dishId");
			int dishId = sc.nextInt();

			System.out.println("Enter  loationCode");
			int locationCode = sc.nextInt();

			FileWriter fw = new FileWriter("C:\\Users\\sankalp\\eclipse-workspace\\NewDominozPizza\\newOrderList.txt",
					true);
			fw.write(+dishId + "," + locationCode);
			fw.write("\r\n");
			System.out.println("checking order details for given dish and location");

			System.out.println(dishId + " ," + locationCode);

			fw.close();

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
	}

}
