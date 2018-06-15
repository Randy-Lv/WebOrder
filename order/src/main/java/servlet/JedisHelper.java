package servlet;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import redis.clients.jedis.Jedis;

public class JedisHelper {
	static Jedis jedis;
	static {
		// if(jedis == null) {
		// synchronized (JedisHelper.class) {
		if (jedis == null) {
			jedis = new Jedis("127.0.0.1", 6379);
			jedis.auth("eranju3B");
		}
		if (!jedis.isConnected()) {
			jedis.connect();
			jedis.auth("eranju3B");
		}
		// }
		// }
	}

	public static void AddOrder(String key, String value) {
		// 存储数据到列表中
		jedis.set(key, value);
		// jedis.close();
	}

	public static void AddOrderList(String orderModel) {
		// 存储数据到列表中
		jedis.lpush("order", orderModel);
		// jedis.close();
	}

	public static String GetOrderByKey(String key) {
		// 存储数据到列表中
		String value = jedis.get(key);
		// jedis.close();
		return value;
	}

	public static List<String> GetListOrders(int page) {
		int start = (page - 1) * 18 + 1;

		List<String> value = jedis.lrange("order", start, start + 17);
		// jedis.close();
		return value;
	}

	// 获取总记录数
	public static long GetOrdersCount() {
		long count = jedis.llen("order");
		// jedis.close();
		return count;
	}

	public static List<String> GetOrders() {
		List<String> persons = new ArrayList<String>();
		for (String key : jedis.keys("*")) {
			persons.add(jedis.get(key));
		}
		return persons;
	}
}