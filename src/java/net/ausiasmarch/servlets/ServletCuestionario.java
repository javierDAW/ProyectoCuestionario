/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.ausiasmarch.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.ausiasmarch.clasesDao.ContestacionDao;
import net.ausiasmarch.clasesDao.CuestionarioDao;
import net.ausiasmarch.clasesDao.NotaDao;
import net.ausiasmarch.clasesDao.UsuarioDao;
import net.ausiasmarch.pojos.PojoContestacion;
import net.ausiasmarch.pojos.PojoCuestionario;
import net.ausiasmarch.pojos.PojoNota;
import net.ausiasmarch.pojos.PojoOpcion;
import net.ausiasmarch.pojos.PojoPregunta;
import net.ausiasmarch.pojos.PojoUsuario;

/**
 *
 * @author al036049
 */
public class ServletCuestionario extends HttpServlet {

    CuestionarioDao cuestionarioDao = null;
    UsuarioDao usuarioDao = null;
    ArrayList arrayCuestionarios = null;
    PojoUsuario pojoUsuario = null;
    PojoCuestionario pojoCuestionario = null;
    private final String url = "jdbc:mysql://localhost:3307/proyectocuest";
    private final String userConexion = "root";
    private final String passwdConexion = "toor";
    private int regPorPag = 4;
    private int numeroPaginas;
    private String ordena = "id";

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession sesion = null;
        RequestDispatcher reqDispatcher = null;
        try {
            String action = request.getParameter("action");

            switch (action) {

                case "login":

                    String user = request.getParameter("usuario");
                    String passwd = request.getParameter("password");
                    cuestionarioDao = new CuestionarioDao(url, userConexion, passwdConexion);
                    usuarioDao = new UsuarioDao(url, userConexion, passwdConexion);
                    pojoUsuario = usuarioDao.isValidUser(user, passwd);

                    if (pojoUsuario != null) {

                        sesion = request.getSession();
                        sesion.setAttribute("User", pojoUsuario);
                        int numeroPaginas = cuestionarioDao.numeroPaginas(regPorPag);
                        request.setAttribute("NumeroPaginas", numeroPaginas);
                        ordena ="id";
                        arrayCuestionarios = cuestionarioDao.rellenaListaCuestionario(1, regPorPag,ordena);
                        request.setAttribute("ListaCuestionarios", arrayCuestionarios);
                        reqDispatcher = getServletConfig().getServletContext().getRequestDispatcher("/jsp/seleccionaCuestionarios.jsp");
                        reqDispatcher.forward(request, response);

                    } else {
                        request.setAttribute("Usuario", user);
                        reqDispatcher = getServletConfig().getServletContext().getRequestDispatcher("/index.jsp");
                        reqDispatcher.forward(request, response);
                    }
                    break;

                case "paginacion":

                    int numPag = Integer.parseInt(request.getParameter("numPag"));
                    numeroPaginas = cuestionarioDao.numeroPaginas(regPorPag);
                    request.setAttribute("NumeroPaginas", numeroPaginas);
                    arrayCuestionarios = cuestionarioDao.rellenaListaCuestionario(numPag, regPorPag,ordena);
                    request.setAttribute("ListaCuestionarios", arrayCuestionarios);
                    request.setAttribute("Numero", numPag);
                    reqDispatcher = getServletConfig().getServletContext().getRequestDispatcher("/jsp/seleccionaCuestionarios.jsp");
                    reqDispatcher.forward(request, response);
                    break;

                case "select":

                    int id = Integer.parseInt(request.getParameter("id"));
                    pojoCuestionario = new PojoCuestionario();
                    cuestionarioDao = new CuestionarioDao(url, userConexion, passwdConexion);
                    pojoCuestionario = cuestionarioDao.rellenaCuestionario(id);

                    request.setAttribute("Cuestionario", pojoCuestionario);
                    ServletContext sc = getServletContext();
                    reqDispatcher = sc.getRequestDispatcher("/jsp/muestraCuestionario.jsp");
                    reqDispatcher.forward(request, response);
                    break;

                case "ordena":

                    ordena = request.getParameter("ordenar");
                    numeroPaginas = cuestionarioDao.numeroPaginas(regPorPag);
                    request.setAttribute("NumeroPaginas", numeroPaginas);
                    arrayCuestionarios = cuestionarioDao.rellenaListaCuestionario(1, regPorPag,ordena);
                    request.setAttribute("ListaCuestionarios", arrayCuestionarios);
                    reqDispatcher = getServletConfig().getServletContext().getRequestDispatcher("/jsp/seleccionaCuestionarios.jsp");
                    reqDispatcher.forward(request, response);
                    break;

                case "respuesta":

                    ContestacionDao cd = new ContestacionDao(url, userConexion, passwdConexion);
                    if (cd.existeRegistro(pojoUsuario, pojoCuestionario)) {
                        String mensaje = "Ya has realizado este Cuestionario con anterioridad!";
                        request.setAttribute("Mensaje", mensaje);
                    } else {

                        Enumeration<String> parametros = request.getParameterNames();

                        do {
                            String parametro = parametros.nextElement();
                            PojoContestacion pc = new PojoContestacion();
                            if (!parametro.equals("action")) {
                                pc.setIdAlumno(pojoUsuario.getId());
                                int idPregunta = Integer.parseInt(parametro);
                                pc.setIdPregunta(idPregunta);
                                pc.setContestacion(request.getParameter(parametro));

                                cd.insertaContestacion(pc);
                            }

                        } while (parametros.hasMoreElements());
                    }
                    NotaDao notaDao = new NotaDao(url, userConexion, passwdConexion);
                    PojoNota pojoNota = notaDao.CalculaNota(pojoUsuario, pojoCuestionario);
                    request.setAttribute("Nota", pojoNota);
                    reqDispatcher = getServletConfig().getServletContext().getRequestDispatcher("/jsp/muestraNota.jsp");
                    reqDispatcher.forward(request, response);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            out.close();
        }
    }
// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">

    /**
     * Handles the HTTP
     * <code>GET</code> method.
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
        } catch (Exception e) {
            e.printStackTrace();

        }

    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
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
        } catch (Exception e) {
            e.printStackTrace();

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
}
