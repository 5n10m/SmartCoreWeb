package SmartCoreWEB;

/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;

/**
 *
 * @author david.molins.goma
 */
@WebServlet(urlPatterns = {"/login"})
public class login extends HttpServlet {
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws java.lang.ClassNotFoundException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        /* TODO output your page here. You may use following sample code. */
        Class.forName("org.sqlite.JDBC");
        out.println("<h1>TEST</h1>");
        
        //out.println(login_JerseyClient.login);
        
            String encodedQuery = URLEncoder.encode("", "UTF-8");
                String postData = "user="+request.getParameter("user")+"&password="+request.getParameter("password")+encodedQuery;
                
                // Connect to the web
                URL url = new URL("http://fiberparty.upc.es:8080/PTIRestBackend/webresources/login");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setDoOutput(true);
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                conn.setRequestProperty("Content-Length",  String.valueOf(postData.length()));
                
                // Write data
                OutputStream os = conn.getOutputStream();
                os.write(postData.getBytes());
                
                // Read response
                StringBuilder responseSB = new StringBuilder();
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                
                String line;
                while ( (line = br.readLine()) != null){
                    responseSB.append(line);
                }
                out.println(Integer.parseInt(responseSB.toString()));
                
                // Close streams
                br.close();
                os.close();
            
            
            if("1".equals(Integer.parseInt(responseSB.toString()))) {      //Si les credencials introduides coincideixen
                out.println("<p><h3><font color=#347C2C> Hola! El teu login ha sigut un exit. </font></h3><p>");
                RequestDispatcher rd = request.getRequestDispatcher("menu.html");
                rd.include(request, response);
            }
            else {
                out.println("<p><h3><font color=#F70D1A> Usuari i/o password introduit/s incorrecte/s. Torni a intentar-ho. </font></h3><p><br>");
                RequestDispatcher rd = request.getRequestDispatcher("index.html");
                rd.include(request, response);
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
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
        }
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

    static class login_JerseyClient {

        private WebTarget webTarget;
        private Client client;
        private static final String BASE_URI = "http://fiberparty.upc.es:8080/PTIRestBackend/webresources";

        public login_JerseyClient() {
            client = javax.ws.rs.client.ClientBuilder.newClient();
            webTarget = client.target(BASE_URI).path("login");
        }

        public String loginpost() throws ClientErrorException {
            return webTarget.request().post(null, String.class);
        }

        public String login(String password, String user) throws ClientErrorException {
            WebTarget resource = webTarget;
            if (password != null) {
                resource = resource.queryParam("password", password);
            }
            if (user != null) {
                resource = resource.queryParam("user", user);
            }
            //return resource.get(String.class);
            //resource.get
            return "SHIT";
        }

        public void close() {
            client.close();
        }
    }
    
}