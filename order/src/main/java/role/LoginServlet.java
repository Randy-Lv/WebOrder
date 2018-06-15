package role;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import servlet.MD5Util;
import servlet.OrderServlet;

@WebServlet(description = "WebSite", urlPatterns = { "/Login" })
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Log log = LogFactory.getLog(OrderServlet.class);

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");

		String username = request.getParameter("username");
		String password = request.getParameter("password");

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		log.info(String.format("%s  用戶名：%s", formatter.format(new Date()), username));
		if (username.equals("test") && MD5Util.GetMD5(password).equals("BC54B01FA595788E9BF1396A80EE3BD3")) {
			HttpSession session = request.getSession();
			session.setAttribute("user", username);
			response.sendRedirect("/List.html");
		} else {
			response.sendRedirect("/Login.html?msg=用户名或密码错误");
		}

	}

}