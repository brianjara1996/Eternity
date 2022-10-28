document.querySelector('#btnpago').addEventListener('click', function(){
    const saldo = document.getElementById('txtdinero').value;
    Cargarsaldo(saldo);
    this.disabled = true;
});





async function Cargarsaldo(saldo){

    const request = await fetch('mp/cobrar/'+ saldo,{
        method:'GET',
        header: {
        'accept' : 'application/json',
        'Content-Type' : 'application/json'
        }
        });
        const resp = await request.json();
    
        if(resp !=  null){
            
               window.location.href = resp.json;
        }
        else{
            alert("no se pudo realiazar carga")
        }

}