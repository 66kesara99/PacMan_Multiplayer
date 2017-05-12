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
import javax.servlet.http.HttpSession;

/**
 *
 * @author Kesara
 */
@WebServlet(urlPatterns = {"/pacman","/update"})
public class PacManController extends HttpServlet {

    private PacManGame game = new PacManGame(45, 45);
    private int playerCount;

    @Override
    public void init() {
//        game.start();
        playerCount = 0;
        Logger.getGlobal().log(Level.INFO, "Game Started");
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/event-stream, charset=utf-8");
        response.flushBuffer();
        Logger.getGlobal().log(Level.INFO, "Beginning update stream.");
        

        try (PrintWriter out = response.getWriter()) {
            
            out.print("data: ");
            out.println(game.getBoardState(playerCount));
            out.println();
            out.flush();
            
            HttpSession sess = request.getSession(true);        // Get the existing session
        
            // Get addressbook inside the session
            Integer playerId = (Integer) sess.getAttribute("playerId");

            // Create new session if the session is new
            if (playerId == null && playerCount < 4){
                playerCount++;
                System.out.println(playerCount);
                synchronized(sess){
                    sess.setAttribute("playerId", playerCount);
                }
            }
            
            while (!Thread.interrupted()){
                synchronized (this) {
                    wait();
                }

                out.print("data: ");
                out.println(game.getBoardState(playerCount));
                out.println();

                out.flush();
//                    System.out.println(game.getBoardJSON());
//                    Logger.getGlobal().log(Level.INFO, game.getBoardJSON());
            }
        } catch (InterruptedException e) {
            Logger.getGlobal().log(Level.INFO, "Terminating updates.");
            response.setStatus(HttpServletResponse.SC_GONE);
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if(request.getServletPath().equals("/update")){
            int key = Integer.parseInt(request.getParameter("keypress"));
            
            //process request parameters and return details of searched name
        
            HttpSession sess = request.getSession(true);        // Get the existing session
        
            // Get player id inside the session
            Integer playerId = (Integer) sess.getAttribute("playerId") - 1;
            
            synchronized(this){
                
                if (playerCount > 3){
                    switch (key) {
                        case 37:
                            game.keyPress(playerId, 'L');
                            break;
                        case 38:
                            game.keyPress(playerId, 'U');
                            break;
                        case 39:
                            game.keyPress(playerId, 'R');
                            break;
                        case 40:
                            game.keyPress(playerId, 'D');
                            break;
                        default:
                            break;
                    }
                }
                notifyAll();
            }
        }
    }

}
