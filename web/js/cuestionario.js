
window.onload = cargaFunciones;

function cargaFunciones(){
   
    document.getElementById("enviar").onclick = pregunta;
    cuenta();
    document.getElementById("abrirDatosPersonales").onclick=abrir;
    document.getElementById("cerrarDatosPersonales").onclick=cerrar; 
    
}

	
var tiempoCuestionario = 30;
	  
function cuenta(){
	
    if(tiempoCuestionario > 0){
        tiempoCuestionario--;
        document.getElementById("atras").innerHTML = "Tiempo: " + tiempoCuestionario +" segundos";
    //document.getElementById("boton").disabled = false;
				
				
    } else {
        document.getElementById("atras").innerHTML = "Tiempo agotado";
        document.getElementById("enviar").disabled = true;
    }
			
}
setInterval('cuenta()', 1000);

function pregunta(){
    
    if (confirm("¿Estas seguro de enviar el cuestionario?")){
       document.formulario.submit();
    }
} 

function abrir()
{
                
    var a ='block';
    var b= 'modal';
    var c= 'ventana';
                
    document.getElementById(b).style.display=a;
    document.getElementById(c).style.display=a;
}
            
function cerrar()
{
    var a ='none';
    var b= 'modal';
    var c= 'ventana';
                
    document.getElementById(b).style.display=a;
    document.getElementById(c).style.display=a;
} 	  