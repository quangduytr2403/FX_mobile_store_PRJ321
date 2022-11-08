package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Class handle request log out
 * 
 * @author DuyTQFX11834
 *
 */
@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // remove session userLogin
        request.getSession().removeAttribute("userLogin");	
        
        //remove cart
        request.getSession().removeAttribute("cart");
        
        //remove search key
        request.getSession().removeAttribute("searchKey");
        
        // redirect to login
        response.sendRedirect(request.getContextPath() + "/login");
    }
}
