const url= "http://localhost:8080/emailLanding";


async function contactar(event)
{
    event.preventDefault();
    try{
        const respuesta=document.getElementById("confirmacion");
       
        const body={
            nombre:document.querySelector(".nombre").value,
            email:document.querySelector(".email").value
        };

        const response = await fetch(url, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(body)
        })

        if(!response.ok)
        {
            respuesta.setAttribute("class","solicitudIncorrecta");
            respuesta.textContent="Ha habido un error en el envío de la solicitud";
           
            throw new Error(`Error HTTP ${response.status}`)
        }
        respuesta.setAttribute("class","solicitudCorrecta");
        respuesta.textContent= "La solicitud ha sido enviada correctamente";
       
    } 
    catch(error)
    {
        
        console.log(error)
    }
    
}
