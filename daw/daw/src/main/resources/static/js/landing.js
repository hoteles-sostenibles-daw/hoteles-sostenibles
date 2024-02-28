const url= "http://localhost:8080/emailLanding";


async function contactar(event)
{
    event.preventDefault();
    try{
        const respuesta=document.getElementById("confirmacion");
        
        const nombre = document.querySelector(".nombre").value
        const email = document.querySelector(".email").value

        if(nombre.trim() === '' || !nombre.match(/^[a-zA-Z]+$/)) {
            respuesta.textContent = 'Introduzca un nombre válido'
            return
        }
        if(email.trim() === '' || !email.match(/^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/)) {
            respuesta.textContent = 'Introduzca un email válido'
            return
        }

        const body={
            nombre: nombre,
            email: email
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
