package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AccountDao;
import entities.Account;
import entities.Cart;
import utils.Constants;

/**
 * Class handle request login and display login page.
 * 
 * @author DuyTQFX11834
 */
@WebServlet({"/login"})
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    private static AccountDao accountDao = new AccountDao();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	boolean check = true;
    	
        String userMail = request.getParameter("userMail");
        String password = request.getParameter("password");
        String remember = request.getParameter("remember");
        
        //Validate form
        if("".equals(userMail)) {
        	check = false;
        	request.setAttribute("userMailFeedback", Constants.EMPTY_FEEDBACK_MESSAGE);
        }
        
        if("".equals(password)) {
        	check = false;
        	request.setAttribute("passwordFeedback", Constants.EMPTY_FEEDBACK_MESSAGE);
        }
        
        Account acc = new Account();
        acc.setUserMail(userMail);
        acc.setPassword(password);
        
        if(!check) {
        	request.setAttribute("loginFail", Constants.EMPTY_LOGIN_MESSAGE);
            request.setAttribute("userLogin", acc);
            request.setAttribute("remember", remember);
            request.getRequestDispatcher("views/login.jsp").forward(request, response);
            return;
        }
        
        try {
        	if(accountDao.login(acc)) {
            	// if login successfully, and remember me, create cookie
            	if(remember != null) {
            		Cookie userMailCookie = new Cookie("userMailCookie", userMail);
            		Cookie passwordCookie = new Cookie("passwordCookie", password);
            		userMailCookie.setMaxAge(60 * 60 * 24 * 15);
            		passwordCookie.setMaxAge(60 * 60 * 24 * 15);
            		response.addCookie(userMailCookie);
            		response.addCookie(passwordCookie);
            	} else { //delete cookie
            		Cookie[] cookies = request.getCookies();
                	if(cookies != null) {
                		for(Cookie  cookie: cookies) {
                			if(cookie.getName().equals("userMailCookie") || 
                					cookie.getName().equals("passwordCookie")) {
                				cookie.setMaxAge(0);
                				response.addCookie(cookie);
                			}
                		}
                	}
            	}
            	
            	// if login successfully, save session user, who have just logined
            	Account userSuccess = accountDao.findUserByMail(userMail);
            	request.getSession().setAttribute("userLogin", userSuccess);
            	
            	//Create a cart
            	request.getSession().setAttribute("cart", new Cart());
            	
            	//if user is admin
            	if(userSuccess.getRole() == 1) {
            		response.sendRedirect(request.getContextPath() + "/admin/home");
            	} else {
            		response.sendRedirect(request.getContextPath() + "/home/1");
            	}
            } else {
                request.setAttribute("loginFail", Constants.LOGIN_FAIL_MESSAGE);
                request.setAttribute("userLogin", acc);
                request.setAttribute("remember", remember);
                request.getRequestDispatcher("views/login.jsp").forward(request, response);
            }
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} 
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	//Get Cookies
    	String userMail = "", password = "", remember = null;
    	Cookie[] cookies = request.getCookies();
    	if(cookies != null) {
    		for(Cookie  cookie: cookies) {
    			if(cookie.getName().equals("userMailCookie")) {
    				userMail = cookie.getValue();
    				remember = "checked";
    			}
    			if(cookie.getName().equals("passwordCookie")) {
    				password = cookie.getValue();
    			}
    		}
    	}
    	
    	request.setAttribute("userMail", userMail);
    	request.setAttribute("password", password);
    	request.setAttribute("remember", remember);
		request.getRequestDispatcher("views/login.jsp").forward(request, response);    
	}
}
