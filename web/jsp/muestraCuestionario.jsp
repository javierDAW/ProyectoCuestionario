<%-- 
    Document   : muestraCuestionario
    Created on : 02-nov-2012, 19:29:04
    Author     : al036049
--%>

<%@page import="net.ausiasmarch.pojos.PojoUsuario"%>
<%@page import="net.ausiasmarch.pojos.PojoOpcion"%>
<%@page import="net.ausiasmarch.pojos.PojoPregunta"%>
<%@page import="java.util.ArrayList"%>
<%@page import="net.ausiasmarch.pojos.PojoCuestionario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    PojoUsuario user = null;
    user = (PojoUsuario) request.getSession().getAttribute("User");
    if (user == null) {
        response.sendRedirect("../../ProyectoCuestionario");
    } else {

        PojoCuestionario pj = (PojoCuestionario) request.getAttribute("Cuestionario");

        int id;
        String descripcion = pj.getDescripcion();

%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%=descripcion%></title>
        <link rel=StyleSheet href="css/bootstrap.min.css" type="text/css" media=screen>
        <link rel=StyleSheet href="css/plantilla.css" type="text/css" media=screen>
        <script src="js/cuestionario.js"></script>


    </head>
    <body>
        <div id="modal" style="display:none">


            <div id="ventana" class="contenedor" style="display:none">
                <h2>Datos personales</h2>
                <p>Nombre: <%=user.getNombre()%></p>
                <p>Apellidos: <%=user.getApe1() + " " + user.getApe2()%></p>
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
                    <span><b>Usuario:</b> <a href="#" id="abrirDatosPersonales"> <%=user.getNombre() + " " + user.getApe1()%></a></span>
                    <span class="cerrarSesion"><a href="../../ProyectoCuestionario">Cerrar sesión</a>
                </div>
            </div>

            <div id="contenedor" class="row">
                <div class="span2" id="menu"><h2>MENÚ</h2>
                     
                </div>
                <div class="span10" id="contenido">
                    <div id="formulario">
                        <h2><%=descripcion%></h2>


                        <form id="form" name="formulario" action="/ProyectoCuestionario/ServletCuestionario" method="POST">
                            <%
                                ArrayList pre = pj.getPreguntas();
                                for (int i = 0; i < pre.size(); i++) {
                                    PojoPregunta pp = (PojoPregunta) pre.get(i);
                                    String tituloPregunta = pp.getDescripcion();
                                    int idPregunta = pp.getId();
                                    ArrayList op = pp.getOpciones();
                            %>
                            <h3><%=tituloPregunta%></h3>
                            <%for (int j = 0; j < op.size(); j++) {
                                    PojoOpcion po = (PojoOpcion) op.get(j);
                                    String tituloOpcion = po.getDescripcion();
                                    int idOpcion = po.getId();
                            %>
                            <div class="opciones">
                                <input name="<%=idPregunta%>" type="radio" value="<%=idOpcion%>" > <%=tituloOpcion%></input> 
                                <br />
                            </div>
                            <%}
                                }%>
                            <input type="hidden" name="action" value="respuesta" />
                            <br />
                            <div class="centrar">
                                <input id="enviar" type="button"  value="Enviar" class="btn btn-inverse" />
                            </div>
                        </form>
                    </div>
                   <div id="atras"></div>
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
<%}%>