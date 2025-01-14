package controller;

import logic.FourSquareCipher;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/encode")
public class EncodeServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String keyword1 = request.getParameter("keyword1");
        String keyword2 = request.getParameter("keyword2");
        String text = request.getParameter("text");

        FourSquareCipher cipher = new FourSquareCipher(keyword1, keyword2, true);
        String encodedText = cipher.encode(text);

        response.setContentType("text/html");
        response.getWriter().println("<h1>Encoded Text: " + encodedText + "</h1>");
    }
}