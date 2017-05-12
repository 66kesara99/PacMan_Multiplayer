/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Nathasha
 */

@WebServlet(
        name = "Address Book Search",
        urlPatterns = {"/search","/newentry"},
        initParams = {@WebInitParam(name="filename", value="/addressBook.txt")}
)
public class AddressBookServlet extends HttpServlet {
       
    String path;
    int count;
    
    //Servlet initialization
    public void init() throws ServletException {
        
        ServletConfig config = getServletConfig();
        
        //get the value of the init-parameter
        String filename = config.getInitParameter("filename");
        ServletContext sc = config.getServletContext();
        path = sc.getRealPath(filename);
        count = 0;
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
            
    
    protected void processRequestGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
  
        //process request parameters and return details of searched name
        
        HttpSession sess = request.getSession(true);        // Get the existing session
        
        // Get addressbook inside the session
        AddressBook addressBook = (AddressBook) sess.getAttribute("addressBook");
        
        // Create new session if the session is new
        if (addressBook == null){
            count++;
            addressBook = new AddressBook(path);
            synchronized(sess){
                sess.setAttribute("addressBook", addressBook);
            }
        }
        
        String name = request.getParameter("name");
        String content = addressBook.search(name);

        PrintWriter out = response.getWriter();
        out.println("Your search details here"); 
        out.println(content);
        out.println(count);

        
    }
    
    protected void processRequestPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
  
        //process request parameters and return details of searched name
        
        HttpSession sess = request.getSession();
        
        // Get addressbook inside the session
        AddressBook addressBook = (AddressBook) sess.getAttribute("addressBook");
        
        // Create if the session is new
        if (addressBook == null){
            count++;
            addressBook = new AddressBook(path);
        }
        
        String name = request.getParameter("name");
        String details = request.getParameter("details");
        String done = addressBook.addNew(name, details);

               
        PrintWriter out = response.getWriter();
        out.println(done);
        out.println("File Add Success!");
        synchronized(sess){
            sess.setAttribute("addressBook", addressBook);
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
        processRequestGet(request, response);
        
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
        processRequestPost(request, response);
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
    
//    @Override
//    public void destroy(){
//        addressBook.writeToFile();
//        System.out.println("Destroyed");
//    }

}
