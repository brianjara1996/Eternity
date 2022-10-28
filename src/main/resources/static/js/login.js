document.querySelector("#btningreso").addEventListener('click', function(){
    const user = document.getElementById('txtuser').value;
    const pass = document.getElementById('txtpass').value;
    Ingresar(user,pass);
});


async  function Ingresar(user, pass){

    const request = await fetch('/api/login/' + user + '/' + pass,{
    method:'GET',
    header: {
    'accept' : 'application/json',
    'Content-Type' : 'application/json'
    }
    });

    const resp = await request.json();

    if(resp.usuario != null){
            
        window.location.href = "http://localhost:8080/juego.html";
 }
 else{
     alert("User y pass no valida")
 }

}