            
<%-- 
    Document   : seleccionaCuestionarios
    Created on : 23-oct-2012, 18:08:17
    Author     : al036049
--%>

<%@page import="java.util.Date"%>
<%@page import="net.ausiasmarch.pojos.PojoUsuario"%>
<%@page import="java.util.ArrayList"%>
<%@page import="net.ausiasmarch.pojos.PojoCuestionario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    PojoUsuario user = null;
    user = (PojoUsuario) request.getSession().getAttribute("User");
    if (user == null) {
        response.sendRedirect("../../ProyectoCuestionario");
    } else {
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Selecciona cuestionario</title>
        <link rel=StyleSheet href="css/bootstrap.min.css" type="text/css" media=screen>
        <link rel=StyleSheet href="css/plantilla.css" type="text/css" media=screen>
        <script src="js/nota.js"></script>
    </head>
    <body>
        <%

            ArrayList lista = (ArrayList) request.getAttribute("ListaCuestionarios");
            int id;
            String descripcion;
            Date fecha;
            int evaluacion;
            PojoCuestionario pj;%>
            <div id="modal" style="display:none">


            <div id="ventana" class="contenedor" style="display:none">
                <h2>Datos personales</h2>
                <p>Nombre: <%=user.getNombre()%></p>
                <p>Apellidos: <%=user.getApe1()+" "+user.getApe2()%></p>
                <p>Email: <%=user.getEmail()%></p>
                <p>Telefono: <%=user.getTelefono()%></p>
                <p>Login: <%=user.getLogin()%></p>
                <p>Password: <%=user.getPassword()%></p>
                <a href="#close" title="Cerrar" id="cerrarDatosPersonales">Close</a>
            </div>
        </div>
        <div id="envoltorio" class="container">
            <div class="row">
                <div class="span12" id="cabecera"><h1>CUESTIONARIO</h1>
                    <span><b>Usuario:</b><a href="#" id="abrirDatosPersonales"> <%=user.getNombre() + " " + user.getApe1()%></a></span>
                    <span class="cerrarSesion"><a href="../../ProyectoCuestionario">Cerrar sesión</a>
                </div>
            </div>

            <div id="contenedor" class="row">
                <div class="span2" id="menu"><h2>MENÚ</h2></div>
                <div class="span10" id="contenido">

                    <div id="tabla">
                        <table class="table table-striped">
                            <thead>
                                <tr>
                                    <th><a href="/ProyectoCuestionario/ServletCuestionario?ordenar=id&action=ordena">Número</a></th>
                                    <th><a href="/ProyectoCuestionario/ServletCuestionario?ordenar=descripcion&action=ordena">Descripción</a></th>
                                    <th><a href="/ProyectoCuestionario/ServletCuestionario?ordenar=fecha&action=ordena">Fecha</a></th>
                                    <th><a href="/ProyectoCuestionario/ServletCuestionario?ordenar=evaluacion&action=ordena">Evaluación</a></th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    for (int i = 0; i < lista.size(); i++) {
                                        pj = new PojoCuestionario();
                                        pj = (PojoCuestionario) lista.get(i);
                                        id = pj.getId();
                                        descripcion = pj.getDescripcion();
                                        fecha = pj.getFecha();
                                        evaluacion = pj.getEvaluacion();
                                %>
                                <tr>
                                    <td><%=id%></td>
                                    <td><a href="/ProyectoCuestionario/ServletCuestionario?id=<%=id%>&action=select" ><%=descripcion%></a></td> 
                                    <td><%=fecha%></td> 
                                    <td><%=evaluacion%></td> 
                                </tr>
                                <%
                                    }%>
                            </tbody>
                        </table>
                    </div>

                   
                    <%
                        int numeroPaginas = (Integer) request.getAttribute("NumeroPaginas");
                        int numPag=1;
                        if (request.getAttribute("Numero") != null) {
                             numPag = (Integer) request.getAttribute("Numero");
                        }
                        String activo = " class=\"active\"";
                    %>


                    <div class="span10" id="pagContainer">
                        <div class="pagination" id="paginacion">
                            <ul>
                                <li><a href="#">&laquo;</a></li>
                                <%
                                    for (int i = 0; i < numeroPaginas; i++) {
                                %>

                                <li<% if(i+1 == numPag){out.write(activo);}%>><a href="/ProyectoCuestionario/ServletCuestionario?numPag=<%=i + 1%>&action=paginacion" ><%=i + 1%></a></li>

                                <%                             }
                                %>
                                <li><a href="#">&raquo;</a></li>
                            </ul> 
                        </div>
                    </div>
                </div>
                              
            </div>
            <div class="row">            
                <div id="pie" class="span12">

                    <p class="centrar">
                        <a href="http://validator.w3.org/check?uri=referer"><img
                                src="http://www.w3.org/Icons/valid-xhtml10"
                                alt="Valid XHTML 1.0 Transitional" height="31" width="88" /></a>
                        <a href="http://jigsaw.w3.org/css-validator/check/referer">
                            <img 
                                src="http://jigsaw.w3.org/css-validator/images/vcss"
                                alt="Valid CSS!" />
                        </a>
                    </p>
                </div>
            </div>
        </div>
    </body>
</html>

<% }%>
