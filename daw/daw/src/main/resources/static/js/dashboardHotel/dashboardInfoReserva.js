const url = 'http://localhost:8080/'

function funcionesInicio()
{
    cargarInfoReserva()
    cargarHuespedReserva()
}

async function cargarInfoReserva()
{
    try{
        const urlReserva = window.location.href

        const response = await fetch(`${url}cargarinforeserva`, {
            method: 'POST',
            headers: {
                'Content-Type': 'text/plain'
            },
            body: urlReserva

        })

        if(!response.ok)
        {
            throw new Error(`Error HTTP ${response.status}`)
        }
        
        const data= await response.json();
        
        cargarReservaHtml(data)
        cargarImagenDni()
        cargarGastos()

    } 

    catch(error)
    {
        console.log(error)
    }
}

function cargarReservaHtml(reserva)
{
    document.querySelector('.spanNumReserva').textContent = reserva.codigo
    document.querySelector('.infoFechaEntrada').textContent = reserva.fecha_entrada
    document.querySelector('.infoFechaSalida').textContent = reserva.fecha_salida

    if(reserva.habitacion_numero == null)
    {
        document.querySelector('.numHabitacion').textContent = 'Sin asignar'
    }
    else
    {
        document.querySelector('.numHabitacion').textContent = reserva.habitacion_numero
    }
    document.querySelector('.numHuespedes').textContent = reserva.numero_huespedes
    document.querySelector('.checkin').textContent = reserva.check_in
    document.querySelector('.checkout').textContent = reserva.check_out
    document.querySelector('.dniOculto').value = reserva.huesped_dni
}

async function cargarHuespedReserva()
{
    try{
        const urlReserva = window.location.href

        const response = await fetch(`${url}cargarhuespedreserva`, {
            method: 'POST',
            headers: {
                'Content-Type': 'text/plain'
            },
            body: urlReserva

        })

        if(!response.ok)
        {
            throw new Error(`Error HTTP ${response.status}`)
        }
        
        const data= await response.json();
        
        cargarHuespedHtml(data)
       
    } 

    catch(error)
    {
        console.log(error)
    }
}
function cargarHuespedHtml(huesped)
{
    document.querySelector('.telefono').textContent = huesped.telefono
    document.querySelector('.email').textContent = huesped.email
}

async function guardarDni()
{
    try{
        const imagenDni = document.querySelector('.imagenDni').files[0]
        const formData = new FormData()
        formData.append('image', imagenDni)
        formData.append('dni', document.querySelector('.dniOculto').value)

        const response = await fetch(`${url}guardarimagen`, {
            method: 'POST',
            body: formData
        })

        if(!response.ok)
        {
            throw new Error(`Error HTTP ${response.status}`)
        }
        window.location.href =`${url}inforeserva/${document.querySelector('.spanNumReserva').textContent}`
    } 
    catch(error)
    {
        console.log(error)
    }
}

async function cargarImagenDni()
{
    try{
        const response = await fetch(`${url}cargarimagen/${document.querySelector('.dniOculto').value}`, {
            method: 'GET',
        })

        if(!response.ok)
        {
            throw new Error(`Error HTTP ${response.status}`)
        }
        const data = await response.blob()
        const imagen = URL.createObjectURL(data)

        if(data.size > 0){
            document.querySelector('.imgDni').src = imagen
        } 
        else{
            document.querySelector('.imgDni').setAttribute('class', 'hidden')
            document.querySelector('.infoDni').textContent = 'No hay ningún DNI guardado'
            document.querySelector('.divInfoReservaContainer').classList.add('alCentro')
        }
        
    } 
    catch(error)
    {
        console.log(error)
    }
}

async function cargarGastos()
{
    try{
        const numeroReserva = document.querySelector('.spanNumReserva').textContent

        const response = await fetch(`${url}gastosreserva/${numeroReserva}`, {
            method: 'GET',
        })

        if(!response.ok)
        {
            throw new Error(`Error HTTP ${response.status}`)
        }
        
        const data= await response.json();
        
        crearTdPorGasto(data)
    } 

    catch(error)
    {
        console.log(error)
    }
}

function crearTdPorGasto(listaGastos) {
    
    const tableGasto = document.querySelector('.tableGastos')
   
    for(let gasto of listaGastos) {
        const tr = document.createElement('tr')
        const tdConcepto = document.createElement('td')
        tdConcepto.textContent = gasto.concepto
        const tdPrecio = document.createElement('td')
        tdPrecio.textContent =  gasto.precio

        tr.appendChild(tdConcepto)
        tr.appendChild(tdPrecio)
        tableGasto.appendChild(tr)
    }
}

function volverInicio() {
    window.location.href = 'http://localhost:8080/recepcion'
}