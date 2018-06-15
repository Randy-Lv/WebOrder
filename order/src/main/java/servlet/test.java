package servlet;

import java.util.Date;
import java.util.List;

import Impl.*;
import Impl.OrderImpl;
import role.*;

public class test {

	public static void main(String[] args) {

		// 存储数据到列表中
//		List<String> orders = JedisHelper.GetOrders();
//		List<String> orders1 = orders;
		IOrder2 orderImpl = new OrderImpl2();
		Order order = new Order();
		order.setUserName("test1");
		order.setMobile("13800000000");
		order.setCreateDate(new Date());
		//orderImpl.addOrder(order);
		//long count =orderImpl.getOrdersCount();
		List<Order> orders = orderImpl.getListOrders(1);
		System.out.println(orders.size());
	}
}