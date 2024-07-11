package controladores;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "AccederForo", urlPatterns = {"/Foro"})
public class AccederForo extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int Foro = Integer.parseInt(request.getParameter("Foro"));
        request.setAttribute("Foro", Foro);
        RequestDispatcher dispatcher = request.getRequestDispatcher("Foro.jsp");
        dispatcher.forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int Foro = Integer.parseInt(request.getParameter("Foro"));
        HttpSession session = request.getSession();
        session.setAttribute("Foro", Foro);
        response.sendRedirect("Foro.jsp");
    }
}
