package Impl;

import java.util.List;
import servlet.Order;
import util.MyBatisUtil;

import org.apache.ibatis.session.SqlSession;

public class OrderImpl implements IOrder {
	public boolean addOrder(Order order)
	{
		SqlSession session =MyBatisUtil.getSqlSession(true);
		IOrder orderDao =session.getMapper(IOrder.class);
		boolean isAdd =orderDao.addOrder(order);
		session.close();
		return isAdd;
	}
	
	public List<Order> getListOrders(int page)
	{
		int start = (page - 1) * 18;
		SqlSession session =MyBatisUtil.getSqlSession(true);
		IOrder orderDao =session.getMapper(IOrder.class);
		List<Order> orders =orderDao.getListOrders(start);
		session.close();
		return orders;
		
	}
	
	public long getOrdersCount()
	{
		SqlSession session =MyBatisUtil.getSqlSession(true);
		IOrder orderDao =session.getMapper(IOrder.class);
		long count = orderDao.getOrdersCount();
		session.close();
		return count;
	}
}
