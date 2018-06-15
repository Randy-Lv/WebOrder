package servlet;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.alibaba.fastjson.JSON;

/**
 * Servlet implementation class OrderServlet
 */
@WebServlet(description = "WebSite", urlPatterns = { "/Add" })
public class OrderServlet extends HttpServlet {

	private static Log log = LogFactory.getLog(OrderServlet.class);
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public OrderServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at1: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");

		String code = request.getParameter("code");
		// 验证验证码
		if (code == null) {
			response.getWriter().println("验证失败！");
			return;
		}
		if (request.getSession().getAttribute("code") == null) {
			response.getWriter().println("验证码超时，请刷新验证码！");
		}

		String sessionCode = request.getSession().getAttribute("code").toString();
		if (!code.equalsIgnoreCase(sessionCode)) {
			response.getWriter().println("验证码输入错误！");
			return;
		}

		Order order = new Order();
		order.setTitle(request.getParameter("Title"));
		order.setNum(request.getParameter("Num"));
		order.setCompany(request.getParameter("Company"));
		order.setUserName(request.getParameter("UserName"));
		order.setMobile(request.getParameter("Mobile"));
		order.setFax(request.getParameter("Fax"));
		order.setRemark(request.getParameter("Remark"));
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//order.CreateDate = dateFormat.format(new Date());
		order.setCreateDate(new Date());
		String id = UUID.randomUUID().toString();

		String info = JSON.toJSONString(order);
		log.info(info);

		try {
			Map<String, Order> map = new HashMap<String, Order>();
			JedisHelper.AddOrderList(info);
			// JedisHelper.AddOrder(id, serializeToString(order));
			// JedisHelper.AddOrder(id, info);
			// Object object= deserializeToObject(serializeToString(order));
			// info = JedisHelper.GetOrderByKey(id);
			// order =JSON.parseObject(info,Order.class);
			// List<String> orders =JedisHelper.GetListOrders();
			// for (String value : orders) {
			// response.getWriter().append(value);
			// }
			response.getWriter().append("保存成功！");
		} catch (Exception e) {
			e.printStackTrace();
			log.error(info);
			response.getWriter().append("保存失败，请重新提交！");
		}

	}

	// 序列化
	public static String serializeToString(Object obj) throws Exception {
		ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
		ObjectOutputStream objOut = new ObjectOutputStream(byteOut);
		objOut.writeObject(obj);
		String str = byteOut.toString("ISO-8859-1");// 此处只能是ISO-8859-1,但是不会影响中文使用
		return str;
	}

	// 反序列化
	public static <T> T deserializeToObject(String str) throws Exception {
		ByteArrayInputStream byteIn = new ByteArrayInputStream(str.getBytes("ISO-8859-1"));
		ObjectInputStream objIn = new ObjectInputStream(byteIn);
		Object obj = objIn.readObject();
		return (T) obj;
	}
}
