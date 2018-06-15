package Common;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;

import servlet.JedisHelper;
import servlet.Order;

public class Commons {
	public static List<Order> GetOrders() {
		List<Order> orderList = new ArrayList<Order>();

		List<String> orders = JedisHelper.GetListOrders(1);
		for (String value : orders) {
			orderList.add(JSON.parseObject(value, Order.class));
		}

		return orderList;
	}
}
