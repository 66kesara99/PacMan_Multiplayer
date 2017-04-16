/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Kesara
 */
@WebServlet(urlPatterns = {"/PacManController"})
public class PacManController extends HttpServlet {

    private PacManGame game;

    @Override
    public void init() {
        game = new PacManGame(40, 40);
        Logger.getGlobal().log(Level.INFO, "Game Started");
    }


    @Override
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/event-stream");
        response.setCharacterEncoding("UTF-8");

        try (final PrintWriter out = response.getWriter()) {
            
            out.print("data: ");
            out.println(game.getBoardJSON());
                    
        } catch (Exception ex) {
            Logger.getGlobal().log(Level.INFO, "Exiting");
        }
    }

}
