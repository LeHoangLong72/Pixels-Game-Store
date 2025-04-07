package controller;

import dao.GameDAO;
import dao.UserDAO;
import dto.GameDTO;
import dto.UserDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "MainController", urlPatterns = {"/MainController"})
public class MainController extends HttpServlet {

    public static final String LOGIN = "login.jsp";
    
    public static final UserDAO udao = new UserDAO();
    
    public static final GameDAO gameDAO = new GameDAO();
    
    public UserDTO getUserDTO(String strUserID){
        UserDTO user = udao.readById(strUserID);
        return user;
    }
    
    public boolean isValidLogin(String strUserID, String strPassword){
       UserDTO user = getUserDTO(strUserID);
       return user != null && user.getPassword().equals(strPassword);
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = LOGIN;
        try {
            String action = request.getParameter("action");
            if (action == null) {
                url = LOGIN;
            }else{
                if (action.equals("login")) {
                    String strUserID = request.getParameter("txtUserID");
                    String strPassword = request.getParameter("txtPassword");
                    if (isValidLogin(strUserID, strPassword)) {
                        url = "search.jsp";
                        UserDTO user = getUserDTO(strUserID);
                        request.getSession().setAttribute("user", user);
                    }else{
                        url = LOGIN;
                        request.setAttribute("message", "Incorrect UserID or Password!");
                    }
                }else if (action.equals("logout")) {
                    url = LOGIN;
                    request.getSession().invalidate();
                }else if (action.equals("search")) {
                    String searchTerm = request.getParameter("searchTerm");
                    List<GameDTO> listGame = gameDAO.search(searchTerm);
                    request.setAttribute("listGame", listGame);
                    url = "search.jsp";
                }
            }
        } catch (Exception e) {
            log("Error at MainController: " + e.toString());
        } finally{
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
