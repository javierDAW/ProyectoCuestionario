<%-- 
    Document   : index
    Created on : 22-oct-2012, 18:14:50
    Author     : al036049
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    
    session.invalidate();
    String nombre = "";
    if (request.getAttribute("Usuario") != null) {
        nombre = (String) request.getAttribute("Usuario");
    }
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login y password</title>
        <link rel=StyleSheet href="css/bootstrap.min.css" type="text/css" media=screen>
        <link rel=StyleSheet href="css/plantilla.css" type="text/css" media=screen>
        <script>
            window.onload = function(){
                if(document.getElementsByName("usuario")[0].value == ""){
            
            document.getElementsByName("usuario")[0].focus();
                }else{
                    document.getElementsByName("password")[0].focus();
                    document.getElementById("loginYPass").innerHTML="Usuario o Contraseña incorrectos.";
                }
            }
        </script>
    </head>
    <body>
        <div class="total"> 
            <div id="envoltorio" class="container">
                <div class="row">
                    <div class="span12" id="cabecera"><h1>CUESTIONARIO</h1></div>
                </div>
                <div id="contenedor" class="row">
                    <div class="span2" id="menu"><h2>MENÚ</h2></div>
                    <div class="span10" id="contenido">
                        <div id="login" class=" offset2 span5">
                            <form action="/ProyectoCuestionario/ServletCuestionario" method="post">
                                <div>
                                    <p>Login: <input title="Introduce tu nombre de usuario" type="text" name="usuario" value="<%=nombre%>" /></input></p>
                                    <p>Password: <input title="Introduce tu contraseña" type="password" name="password" value="" /></p>
                                </div>
                                <p id="loginYPass"></p>
                                <br/>
                                <input type="hidden" name="action" value="login" />
                                <input type="submit" class="btn btn-inverse" value="Entrar" />
                            </form>
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

        </div>
    </body>
</html>
