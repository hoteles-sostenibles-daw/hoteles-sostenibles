
function enviarFormulario(event){

    event.preventDefault()

    const campoFechaEntrada = document.querySelector(".fechaEntrada").value.trim();
    const campoFechaSalida = document.querySelector(".fechaSalida").value.trim();
    const campoNumeroPersona = document.querySelector(".inputNumeroPersonas").value.trim();
    const campoNombre = document.querySelector(".nombre").value.trim();
    const campoEmail = document.querySelector(".email").value.trim();
    const campoTelefono = document.querySelector(".telefono").value.trim();

    const bodyFormulario = {
        fechaEntrada:campoFechaEntrada,
        fechaSalida:campoFechaSalida,
        numPersonas:campoNumeroPersona,
        nombre:campoNombre,
        email:campoEmail,
        telefono:campoTelefono
    }

    for(const campo in bodyFormulario){
        if(bodyFormulario[campo].length === 0){
            document.querySelector(".aviso").classList.remove("hidden")
        }
        console.log(typeof bodyFormulario[campo])
    }
}

