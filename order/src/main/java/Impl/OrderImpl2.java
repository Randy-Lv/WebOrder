package Impl;

import java.util.List;
import servlet.Order;
import util.MyBatisUtil;

import org.apache.ibatis.session.SqlSession;

public class OrderImpl2 implements IOrder2 {
	public boolean addOrder(Order order)
	{
		SqlSession session =MyBatisUtil.getSqlSession(true);
		IOrder2 orderDao =session.getMapper(IOrder2.class);
		boolean isAdd =orderDao.addOrder(order);
		session.close();
		return isAdd;
	}
	
	public List<Order> getListOrders(int page)
	{
		int start = (page - 1) * 18;
		SqlSession session =MyBatisUtil.getSqlSession(true);
		IOrder2 orderDao =session.getMapper(IOrder2.class);
		List<Order> orders =orderDao.getListOrders(start);
		session.close();
		return orders;
		
	}
	
	public long getOrdersCount()
	{
		SqlSession session =MyBatisUtil.getSqlSession(true);
		IOrder2 orderDao =session.getMapper(IOrder2.class);
		long count = orderDao.getOrdersCount();
		session.close();
		return count;
	}
}
