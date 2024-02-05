const url= "http://localhost:8080/reserva";
async function enviarFormulario(event){

    event.preventDefault()
    try{
    let verificar = false;
    const campoFechaEntrada = document.querySelector(".fechaEntrada").value.trim();
    const campoFechaSalida = document.querySelector(".fechaSalida").value.trim();
    const campoNumeroPersona = document.querySelector(".inputNumeroPersonas").value.trim();
    const campoNombre = document.querySelector(".nombre").value.trim();
    const campoDni= document.querySelector(".dni").value.trim().toUpperCase();
    const campoEmail = document.querySelector(".email").value.trim();
    const campoTelefono = document.querySelector(".telefono").value.trim();

    const bodyFormulario = {
        fechaEntrada:campoFechaEntrada,
        fechaSalida:campoFechaSalida,
        numeroPersonas:campoNumeroPersona,
        nombrePersona:campoNombre,
        dni:campoDni,
        email:campoEmail,
        telefono:campoTelefono
    }

    for(const campo in bodyFormulario){
        if(bodyFormulario[campo].length === 0){
            document.querySelector(".aviso").textContent="Por favor rellene todos los campos y seleccione dos fechas";
            verificar=true;
        }
    }

        if(!verificar){

        
        const response = await fetch(url, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(bodyFormulario)
        })

        if(!response.ok)
        {
    
            document.querySelector(".aviso").textContent="Ha habido un error en el envío de la reserva";
           
            throw new Error(`Error HTTP ${response.status}`)
        }
        
        document.querySelector(".aviso").textContent= "Reserva efectuada correctamente. Revisa tu email";
    }
    } 
    catch(error)
    {
        
        console.log(error)
    }
    
}

