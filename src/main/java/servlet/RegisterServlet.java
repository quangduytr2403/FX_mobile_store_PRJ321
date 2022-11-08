package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AccountDao;
import entities.Account;
import utils.Constants;

/**
 * Class handle request register and display register page.
 * 
 * @author DuyTQFX11834
 */
@WebServlet({"/register"})
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    private static AccountDao accountDao = new AccountDao();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	boolean check = true;
    	
        String userMail = request.getParameter("userMail");
        String password = request.getParameter("password");
        String rePassword = request.getParameter("rePassword");
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        
        //Validate form
        if("".equals(userMail)) {
        	check = false;
        	request.setAttribute("userMailFeedback", Constants.EMPTY_FEEDBACK_MESSAGE);
        } else if (!userMail.matches(Constants.EMAIL_REGEX)) {
        	check = false;
        	request.setAttribute("userMailFeedback", Constants.USERMAIL_FEEDBACK_MESSAGE);
        }
        
        if("".equals(password)) {
        	check = false;
        	request.setAttribute("passwordFeedback", Constants.EMPTY_FEEDBACK_MESSAGE);
        } else if (!password.matches(Constants.PASSWORD_REGEX)) {
        	check = false;
        	request.setAttribute("passwordFeedback", Constants.PASSWORD_FEEDBACK_MESSAGE);
        }
        
        if("".equals(rePassword)) {
        	check = false;
        	request.setAttribute("rePasswordFeedback", Constants.EMPTY_FEEDBACK_MESSAGE);
        } else if (!rePassword.equals(password)) {
        	check = false;
        	request.setAttribute("rePasswordFeedback", Constants.REPASSWORD_FEEDBACK_MESSAGE);
        }
        
        if("".equals(name)) {
        	check = false;
        	request.setAttribute("nameFeedback", Constants.EMPTY_FEEDBACK_MESSAGE);
        } 
        
        if("".equals(address)) {
        	check = false;
        	request.setAttribute("addressFeedback", Constants.EMPTY_FEEDBACK_MESSAGE);
        } 
        
        if("".equals(phone)) {
        	check = false;
        	request.setAttribute("phoneFeedback", Constants.EMPTY_FEEDBACK_MESSAGE);
        } else if (!phone.matches(Constants.PHONE_REGEX)) {
        	check = false;
        	request.setAttribute("phoneFeedback", Constants.PHONE_FEEDBACK_MESSAGE);
        }
        
        Account acc = new Account();
        acc.setUserMail(userMail);
        acc.setPassword(password);
        acc.setName(name);
        acc.setAddress(address);
        acc.setPhone(phone);
        
        //If input is not correct
        if(!check) {
            request.setAttribute("registerFail", Constants.REGISTER_FAIL_MESSAGE);
            request.setAttribute("userRegister", acc);
            request.setAttribute("rePassword", rePassword);
            request.getRequestDispatcher("views/register.jsp").forward(request, response);
            return;
        }
        
        //If email is already exist
        try {
        	if(accountDao.isExistedMail(userMail)) {
        		request.setAttribute("registerFail", Constants.REGISTER_EXIST_EMAIL);
        		request.setAttribute("userRegister", acc);
                request.setAttribute("rePassword", rePassword);
                request.getRequestDispatcher("views/register.jsp").forward(request, response);
                return;
        	}	
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
        
        try {
            if (accountDao.registerUser(acc)) {     
                //navigate to login page
                response.sendRedirect(request.getContextPath() + "/login");
            } else {
            	request.setAttribute("registerFail", Constants.REGISTER_FAIL_MESSAGE);
                request.setAttribute("userRegister", acc);
                request.setAttribute("repassword", rePassword);
                request.getRequestDispatcher("views/register.jsp").forward(request, response);
            }
        } catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		request.getRequestDispatcher("views/register.jsp").forward(request, response);    
	}
}
