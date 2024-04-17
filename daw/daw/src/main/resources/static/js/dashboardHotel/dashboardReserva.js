
const url = "http://localhost:8080/";

// obtener fecha actual


function funcionesInicioGestionReservas()
{

    fechaActual();
    reservasFechaEntrada()
    reservasFechaSalida()
}
// manejar fechas
async function reservasFechaEntrada()
{
    try{
        const fechaEntrada= document.querySelector('.spanFecha').textContent;
        const response = await fetch(`${url}fechaentrada`, {
            method: 'POST',
            headers: {
                'Content-Type': 'text/plain'
            },
            body: fechaEntrada

        })

        if(!response.ok)
        {
          
           
            throw new Error(`Error HTTP ${response.status}`)
        }
       const data= await response.json();
       generarTR(data, 'entrada')
       
    } 

    catch(error)
    {
        
        console.log(error)
    }
}

async function reservasFechaSalida()
{
    try{
        const fechaSalida= document.querySelector('.spanFecha').textContent;
        const response = await fetch(`${url}fechasalida`, {
            method: 'POST',
            headers: {
                'Content-Type': 'text/plain'
            },
            body: fechaSalida

        })

        if(!response.ok)
        {
            throw new Error(`Error HTTP ${response.status}`)
        }
       const data= await response.json();
       generarTR(data, 'salida')
       
    } 

    catch(error)
    {
        
        console.log(error)
    }
}

function generarTR(listaReservas, tipo){
    
    let table
   
    for(const reserva of listaReservas){

        if(tipo === "entrada"){
            table = document.querySelector('.tableEntradas')
        }
        else if(tipo === "salida"){
            table = document.querySelector('.tableSalidas')
        }

        const tr = document.createElement("tr")
        const tdNumReserva = document.createElement("td");
        const tdNDNI = document.createElement("td");
        const tdCheckin = document.createElement("td");

        tdNumReserva.textContent = reserva.codigoReserva;
        tdNDNI.textContent = reserva.dni;

        const boton = document.createElement("button");
        boton.setAttribute("class",`checkinState ${reserva.codigoReserva}`)
        boton.setAttribute("type","button")
        if(tipo === 'entrada') {
            boton.setAttribute("onclick","checkinRealizado(this)")
            crearOkCheckin(boton,reserva.checkIn);
        }
        if(tipo === 'salida') {
            boton.setAttribute("onclick","checkoutRealizado(this)")
            crearOkCheckin(boton,reserva.checkOut);
        }
        
    
        tr.appendChild(tdNumReserva);
        tr.appendChild(tdNDNI);
        tdCheckin.appendChild(boton)
        tr.appendChild(tdCheckin);
        table.appendChild(tr);

    }
}


// verificar fecha aActual
function fechaActual()
{
    const spanFecha = document.querySelectorAll('.spanFecha');
    for(const span of spanFecha){
        span.textContent = `${new Date().getDate()}-${new Date().getMonth()+1}-${new Date().getFullYear()}`;
    }
}

//checkin hecho
async function checkinRealizado(boton)
{
    if(!boton.hasChildNodes()){
        try{
            const numReserva = boton.getAttribute("class").split(" ")[1];
            console.log(numReserva);
            const response = await fetch(`${url}actualizarcheckin/${numReserva}`, {
                method: 'GET'
            })
    
            if(!response.ok)
            {
                throw new Error(`Error HTTP ${response.status}`)
            }
            crearOkCheckin(boton,"S");
    } 
    
    catch(error)
    {
        
        console.log(error)
    }

    }
    
}
//checkout hecho
async function checkoutRealizado(boton)
{
    if(!boton.hasChildNodes()){
        try{
            const numReserva = boton.getAttribute("class").split(" ")[1];
            console.log(numReserva);
            const response = await fetch(`${url}actualizarcheckout/${numReserva}`, {
                method: 'GET'
            })
    
            if(!response.ok)
            {
                throw new Error(`Error HTTP ${response.status}`)
            }
            crearOkCheckin(boton,"S");
    } 
    
    catch(error)
    {
        
        console.log(error)
    }

    }
    
}

//crear ok checkin/out
function crearOkCheckin(boton,estado)
{
    
    if(estado === "S"){
        const iconoOk = document.createElement('img');
     iconoOk.setAttribute('class','okIcon');
        iconoOk.setAttribute('src','../../../img/ok.png');
        iconoOk.setAttribute('alt','icono ok');
        boton.appendChild(iconoOk)
    }
   
    
   
}

async function obtenerInfoReserva()
{
    try{
        const codigoReserva = document.querySelector('.inputNumReserva').value

        const response = await fetch(`${url}obtenerinforeserva`, {
            method: 'POST',
            headers: {
                'Content-Type': 'text/plain'
            },
            body: codigoReserva

        })

        if(!response.ok)
        {
            throw new Error(`Error HTTP ${response.status}`)
        }
       const data= await response.json();
        
       if(data)
       {
        window.location.href =`${url}inforeserva/${codigoReserva}`
       }
       else
       {
        document.querySelector('.aviso1').textContent = 'El código de reserva no existe'
       }
    } 

    catch(error)
    {
        console.log(error)
    }
}

async function obtenerInfoPorHabitacion()
{
    try{
        const numHabitacion = document.querySelector('.inputNumHabitacion').value

        if(isNaN(numHabitacion) || numHabitacion.length > 2) {
            document.querySelector('.aviso2').textContent = 'La habitación no existe'
            return
        }

        const response = await fetch(`${url}obtenerinfohabitacion`, {
            method: 'POST',
            headers: {
                'Content-Type': 'text/plain'
            },
            body: numHabitacion

        })

        if(!response.ok)
        {
            throw new Error(`Error HTTP ${response.status}`)
        }
       const data = await response.text();
       if(data.length > 0)
       {
        window.location.href =`${url}inforeserva/${data}`
       }
       else
       {
        document.querySelector('.aviso2').textContent = 'La habitación no existe'
       }
    } 

    catch(error)
    {
        console.log(error)
    }
}

