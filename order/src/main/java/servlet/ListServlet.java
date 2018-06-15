package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ListServlet
 */
@WebServlet("/List")
public class ListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ListServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		String pageIndex = request.getParameter("pageIndex");
		int page = pageIndex == null ? 1 : Integer.parseInt(pageIndex);

		long ordersCount = JedisHelper.GetOrdersCount();
		List<String> orders = JedisHelper.GetListOrders(page);

		StringBuilder orderJson = new StringBuilder(String.format("{\"count\":\"%s\",", ordersCount));
		orderJson.append("\"data\":[");
		for (String value : orders) {
			orderJson.append(value).append(",");
		}
		String orderStr = new String(orderJson.toString());
		orderStr = String.format("%s]}", orderStr.substring(0, orderStr.length() - 1));

		response.getWriter().append(orderStr);
		response.getWriter().flush();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
