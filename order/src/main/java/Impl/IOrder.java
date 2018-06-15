package Impl;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import servlet.Order;

public interface IOrder {
	boolean addOrder(Order order);
	List<Order> getListOrders(@Param("start")int start);
	long getOrdersCount();
}
