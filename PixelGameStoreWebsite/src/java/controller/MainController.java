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
import javax.servlet.http.HttpSession;
import utils.AuthUtils;

@WebServlet(name = "MainController", urlPatterns = {"/MainController"})
public class MainController extends HttpServlet {

    private static final String LOGIN = "login.jsp";
    private static final UserDAO udao = new UserDAO();
    private static final GameDAO gameDAO = new GameDAO();

    
    

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void processSearch(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String searchTerm = request.getParameter("searchTerm");
        if (searchTerm == null) {
            searchTerm = "";
        }
        List<GameDTO> listGame = gameDAO.search(searchTerm);
        request.setAttribute("searchTerm", searchTerm);
        request.setAttribute("listGame", listGame);
    }

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = LOGIN;
        try {
            String action = request.getParameter("action");
            if (action == null) {
                url = LOGIN;
            } else {
                if (action.equals("login")) {
                    String strUserID = request.getParameter("txtUserID");
                    String strPassword = request.getParameter("txtPassword");
                    if (AuthUtils.isValidLogin(strUserID, strPassword)) {
                        url = "search.jsp";
                        UserDTO user = AuthUtils.getUserDTO(strUserID);
                        request.getSession().setAttribute("user", user);

                        processSearch(request, response);
                    } else {
                        url = LOGIN;
                        request.setAttribute("message", "Incorrect UserID or Password!");
                    }
                } else if (action.equals("logout")) {
                    HttpSession session = request.getSession();
                    if (AuthUtils.isLoggedIn(session)) {
                        url = LOGIN;
                        request.getSession().invalidate();
                    }
                } else if (action.equals("search")) {
                    HttpSession session = request.getSession();
                    if (AuthUtils.isLoggedIn(session)) {
                        processSearch(request, response);
                        url = "search.jsp";
                    }
                } else if (action.equals("delete")) {
                    HttpSession session = request.getSession();
                    if (AuthUtils.isAdmin(session)) {
                        String id = request.getParameter("id");
                        gameDAO.delete(id);
                        processSearch(request, response);
                        url = "search.jsp";
                    }
                } else if (action.equals("add")) {
                    HttpSession session = request.getSession();
                    if (AuthUtils.isAdmin(session)) {
                        try {
                            boolean checkError = false;

                            String gameID = request.getParameter("txtGameID");
                            String gameName = request.getParameter("txtGameName");
                            String developer = request.getParameter("txtDeveloper");
                            String genre = request.getParameter("txtGenre");
                            double price = Double.parseDouble(request.getParameter("txtPrice"));

                            if (gameID == null || gameID.trim().isEmpty()) {
                                checkError = true;
                                request.setAttribute("gameID_error", "Game ID cannot be empty.");
                            }

                            if (gameName == null || gameName.trim().isEmpty()) {
                                checkError = true;
                                request.setAttribute("gameName_error", "Game Name cannot be empty.");
                            }

                            if (developer == null || developer.trim().isEmpty()) {
                                checkError = true;
                                request.setAttribute("developer_error", "Developer cannot be empty.");
                            }

                            if (genre == null || genre.trim().isEmpty()) {
                                checkError = true;
                                request.setAttribute("genre_error", "Genre cannot be empty.");
                            }

                            if (price < 0) {
                                checkError = true;
                                request.setAttribute("price_error", "Price cannot be negative.");
                            }

                            GameDTO game = new GameDTO(gameID, gameName, developer, genre, price, true);
                            if (!checkError) {
                                gameDAO.create(game);
                                processSearch(request, response);
                                url = "search.jsp";
                            } else {
                                request.setAttribute("game", game);
                                url = "gameForm.jsp";
                            }
                        } catch (Exception e) {
                        }
                    }
                }
            }
        } catch (Exception e) {
            log("Error at MainController: " + e.toString());
        } finally {
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
