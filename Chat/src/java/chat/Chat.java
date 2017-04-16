package chat;

import java.io.*;
import java.util.logging.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

@WebServlet(urlPatterns = {"/chat"})
public final class Chat extends HttpServlet {
    volatile String message;

    @Override
    protected void doPost(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        synchronized (this) {
            message = request.getReader().readLine();
            notifyAll();
            Logger.getGlobal().log(Level.INFO, "Received " + message);
        }
        response.setStatus(HttpServletResponse.SC_ACCEPTED);
    }

    @Override
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/event-stream");
        response.setCharacterEncoding("UTF-8");

        try (final PrintWriter out = response.getWriter()) {
            out.println("data: Welcome to the chatroom!");

            while (!Thread.interrupted()) {
                out.println();
                out.flush();
                Logger.getGlobal().log(Level.INFO, "Sent " + message);
                synchronized (this) {
                    wait();
                    out.print("data: ");
                    out.println(message);
                }
            }
        } catch (InterruptedException ex) {
            Logger.getGlobal().log(Level.INFO, "Exiting");
        }
    }
}
