document.querySelector('#btnjugar').addEventListener('click', function(){
    const saldo = sessionStorage.getItem("plata");
    
    if(saldo == 0){
        alert("Se necesita Cargar dinero para empezar el juego rey!")
    }
    else{
    barajar();
    this.disabled = true;
    }
});

window.addEventListener('load', function() {

    const dinero = sessionStorage.getItem("plata");

    if(dinero == null || !(dinero => 0)){
        sessionStorage.setItem("plata", 0);       
    }
    
    saldo();
});


document.querySelector('#cargarsaldo').addEventListener('click', function(){

    window.location.href = "cargasaldo.html";

});


document.querySelector('#btnactualizo').addEventListener('click', function(){
   // location.reload();

   var image_x = document.getElementById('imgbanca');

   var image_2 = document.getElementById('your-cards');

   image_x.parentNode.removeChild(image_x);
   image_2.parentNode.removeChild(image_2);


   let cardImg = document.createElement("div");
   cardImg.id = "your-cards";
   document.getElementById("contenedor").append(cardImg);

   document.getElementById("results").innerText = "";

 numero = 0;

 cartabanca = 0;

 conta = 0;

 palo = "";

 verifico = "false";

 conta2 = 0;

 numero_banca = 0;

 numeromayor = 0;

 palo_banca = "";

 palo_j1 = "";

 document.querySelector('#btnjugar').disabled = false;

});

var plata = 0;

var numero = 0;

var numero_banca = 0;

var cartabanca = 0;

var numeromayor = 0;

var conta = 0;

var conta2 = 0;

var palo = "";

var verifico = "";

var palo_banca = "";

var palo_j1 = "";


async  function barajar(){

    const request = await fetch('api/jugar',{
    method:'GET',
    header: {
    'accept' : 'application/json',
    'Content-Type' : 'application/json',
    'Authorization':  localStorage.token
    }
    });
    const cards = await request.json();

    for(let carta of cards){

       

         numero = carta.numero;

         palo = carta.palo;

         if(palo == "ESPADA"){
            palo = "E"
         }
         else if(palo == "ORO"){
            palo = "O"
         }
         else if (palo == "BASTO"){
            palo = "B"
         }
         else if(palo == "COPA"){
            palo = "C"
         }

         if(conta == 0){

            cartabanca = numero;

            let cardImg = document.createElement("img");
            cardImg.src = "./cards/" + numero + "-" + palo + ".png";
            cardImg.id = "imgbanca";
            document.getElementById("dealer-cards").append(cardImg);
            
        }
        else{
        
        let cardImg = document.createElement("img");
            cardImg.src = "./cards/" + numero + "-" + palo + ".png";
            document.getElementById("your-cards").append(cardImg);
        
        }




        conta = 1;
    }


    for(let carta of cards){


        if(conta2 == 0){

            numero_banca = carta.numero

            palo_banca = carta.palo

        }
        else{
            if(carta.palo == palo_banca){
                if(carta.numero > numeromayor){
                    numeromayor = carta.numero;
    
                    palo_j1 = carta.palo;
                }
            }
           
        }



        conta2 = 1;

    }

    if(numero_banca < numeromayor && palo_banca == palo_j1){
        verifico = "true"
    }
    else if(numero_banca == numeromayor && palo_banca == palo_j1){
        verifico = "empate"
    }
    else{
        verifico = "false"
    }

    plata = sessionStorage.getItem("plata");
    let total_partida  = parseInt(plata);
    
    if(verifico == "true"){
    document.getElementById("results").innerText = "Ganaste Rey!";
    total_partida = total_partida + 100;
    document.getElementById("varPlata").innerText =  total_partida;   
    sessionStorage.setItem("plata", total_partida);  
    }
    else if(verifico == "empate"){
    document.getElementById("results").innerText = "Empate Papu";
    }
    else{
    document.getElementById("results").innerText = "Perdiste Pa!";
    if(total_partida > 0){
        total_partida = total_partida - 100;
    }
    document.getElementById("varPlata").innerText =  total_partida;  
    sessionStorage.setItem("plata", total_partida);  
    }

} 


async function saldo(){

    const total =  sessionStorage.getItem("plata");

    const request = await fetch('mp/saldo',{
        method:'GET',
        header: {
        'accept' : 'application/json',
        'Content-Type' : 'application/json',
        'Authorization':  localStorage.token
        }
        });
        const saldo = await request.json();


        if(saldo.json != null){

             let kaka  = parseInt(saldo.json);
            
            document.getElementById("varPlata").innerText = kaka;

            sessionStorage.setItem("plata", kaka);
        }
        else{
            sessionStorage.setItem("plata", total);

            document.getElementById("varPlata").innerText = total;
        }


}
