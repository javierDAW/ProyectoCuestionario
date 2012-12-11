<%-- 
    Document   : muestraNota
    Created on : 11-nov-2012, 20:12:14
    Author     : Cirus
--%>

<%@page import="java.text.DecimalFormat"%>
<%@page import="net.ausiasmarch.pojos.PojoNota"%>
<%@page import="net.ausiasmarch.pojos.PojoUsuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    PojoUsuario user = null;
    user = (PojoUsuario) request.getSession().getAttribute("User");
    if (user == null) {
        response.sendRedirect("../../ProyectoCuestionario");
    } else {
        PojoNota pojoNota = (PojoNota) request.getAttribute("Nota");

        String mensaje = "Gracias por realizar el cuestionario!";
        if (request.getAttribute("Mensaje") != null) {
            mensaje = (String) request.getAttribute("Mensaje");
        }
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Resultado del Cuestionario</title>
        <link rel=StyleSheet href="css/bootstrap.min.css" type="text/css" media=screen>
        <link rel=StyleSheet href="css/plantilla.css" type="text/css" media=screen>
        <script src="js/nota.js"></script>
        
    </head>
    <body>
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
                    <div id="recuadroNota">
                        <h3><%=pojoNota.getNombre()%></h3>
                        <p><%=mensaje%></p>
                        <%
                            float nota = pojoNota.getNota();
                            DecimalFormat formateador = new DecimalFormat("####.##");
                        %>
                        <p>Tu nota es:<span id="nota"><%=formateador.format(nota)%></span></p>
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
<%}%>
