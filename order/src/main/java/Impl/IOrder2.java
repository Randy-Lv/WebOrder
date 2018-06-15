package Impl;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import servlet.Order;

public interface IOrder2 {
	
	@Insert("insert into orders.order(Title,Num,Company,UserName,Mobile,Fax,Remark,CreateDate) values (#{Title}, #{Num}, #{Company}, #{UserName}, #{Mobile}, #{Fax}, #{Remark}, #{CreateDate} )")
	boolean addOrder(Order order);
	
	@Select("select * from orders.order order by id desc limit ${start} ,18")
	List<Order> getListOrders(@Param("start")int start);
	
	@Select("select count(1) from orders.order")
	long getOrdersCount();
}
