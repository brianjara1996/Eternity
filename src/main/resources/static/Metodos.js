document.querySelector('#btnjugar').addEventListener('click', function(){
    barajar();
    this.disabled = true;
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

        }
        else{
            if(carta.numero > numeromayor){
                numeromayor = carta.numero;
            }           
        }



        conta2 = 1;

    }

    if(numero_banca < numeromayor){

        verifico = "true"
    }
    else if(numero_banca > numeromayor){
        verifico = "false"
    }
    else{
        verifico = "empate"
    }


    if(verifico == "true"){
    document.getElementById("results").innerText = "Ganaste Rey!";
    plata = plata + 100;
    document.getElementById("plata").innerText = "Saldo: $" + plata;    
    }
    else if(verifico == "empate"){
    document.getElementById("results").innerText = "Empate Papu";
    }
    else{
    document.getElementById("results").innerText = "Perdiste Pa!";
    plata = plata - 100;
    document.getElementById("plata").innerText = "Saldo: $" + plata;  
    }

} 
