const url= "http://localhost:8080/reserva";
async function enviarFormulario(event){

    event.preventDefault()
    try{

    const campoFechaEntrada = document.querySelector(".fechaEntrada").value.trim();
    const campoFechaSalida = document.querySelector(".fechaSalida").value.trim();
    const campoNumeroPersona = document.querySelector(".inputNumeroPersonas").value.trim();
    const campoNombre = document.querySelector(".nombre").value.trim();
    const campoDni= document.querySelector(".dni").value.trim().toUpperCase();
    const campoTelefono = document.querySelector(".telefono").value.trim();
    const campoEmail = document.querySelector(".email").value.trim();
    const aviso = document.querySelector(".aviso")
        
    if(campoFechaSalida === undefined || campoFechaSalida === '') {
        aviso.textContent = 'Introduzca una fecha de salida'
        return
    }
    if(campoNombre.trim() === '' || !campoNombre.match(/^[a-zA-Z]+$/)) {
        aviso.textContent = 'Introduzca un nombre válido'
        return
    }
    if(campoDni.trim() === '' || !campoDni.match(/^[0-9]{8}[TRWAGMYFPDXBNJZSQVHLCKE]$/)) {
        aviso.textContent = 'Introduzca un DNI válido'
        return
    }
    if(campoEmail.trim() === '' || !campoEmail.match(/^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/)) {
        aviso.textContent = 'Introduzca un email válido'
        return
    }
    if (campoTelefono.trim() === '' || !campoTelefono.match(/^[0-9]{9}$/)){
        aviso.textContent = 'Introduzca un teléfono válido'
        return 
    }
    

    const bodyFormulario = {
        fechaEntrada:campoFechaEntrada,
        fechaSalida:campoFechaSalida,
        numeroPersonas:campoNumeroPersona,
        nombrePersona:campoNombre,
        dni:campoDni,
        email:campoEmail,
        telefono:campoTelefono
    }

        const response = await fetch(url, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(bodyFormulario)
        })

        if(!response.ok)
        {
    
            aviso.textContent="Ha habido un error en el envío de la reserva";
           
            throw new Error(`Error HTTP ${response.status}`)
        }
        
        aviso.textContent= "Reserva efectuada correctamente. Revisa tu email";
        aviso.style.color = 'green'
        setTimeout(() => {
            campoFechaEntrada.value = '' 
            campoFechaSalida.value = '' 
            campoNumeroPersona.value = '' 
            campoNombre.value = '' 
            campoDni.value = '' 
            campoTelefono.value = '' 
            campoEmail.value = ''
            document.querySelector('.formularioReserva').style.display = 'none'
            const dias = document.querySelectorAll('.diaCelda')
            dias.forEach(dia => {
                dia.classList.remove('fechaSeleccionada')
            })
        }, 5000)
    } 
    catch(error)
    {
        
        console.log(error)
     }
    
    }

