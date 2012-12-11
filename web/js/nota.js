
window.onload = cargaFunciones;


function cargaFunciones(){
    
    document.getElementById("abrirDatosPersonales").onclick=abrir;
    document.getElementById("cerrarDatosPersonales").onclick=cerrar;   
    nota();

}

function nota(){
    var aux = document.getElementById("nota").firstChild.nodeValue;
    var nota = parseFloat(aux.replace(",", "."));
  
    elemento1 = document.createElement('p');
    if(nota<5){
        var imagen = new Image();
        imagen.src = ("img/wrong.png");
        imagen.alt = "Imagen";
        elemento2 = document.getElementById('recuadroNota');
        elemento2.appendChild(imagen);
    }else{
        var imagen = new Image();
        imagen.src = ("img/check.png");
        imagen.alt = "Imagen";
        elemento2 = document.getElementById('recuadroNota');
        elemento2.appendChild(imagen);
       
        
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

	  