
const url = "http://localhost:8080/";

// obtener fecha actual


function funcionesInicioGestionReservas()
{

    fechaActual();
    reservasFechaEntrada()
    comprobarEstadoCheckin()
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
       console.log(data);
       
    } 

    catch(error)
    {
        
        console.log(error)
    }
}
// componentes
function cargarGestionReserva()
{
    const numReserva = document.querySelector('.inputNumReserva').value;
    window.location.href =`http://localhost:8080/gestionreservas/reserva-${(numReserva)}`;
}

function cargarGestionGastos()
{
    const numHabitacion = document.querySelector('.inputNumHabitacion').value;
    window.location.href =`http://localhost:8080/gestionreservas/habitacion-${(numHabitacion)}`;
}
function cargarInicio()
{
    window.location.href =`http://localhost:8080/gestionreservas`;
}

// verificar fecha aActual
function fechaActual()
{
    const spanFecha = document.querySelectorAll('.spanFecha');
    for(const span of spanFecha){
        span.textContent = `${new Date().getDate()}-${new Date().getMonth()+1}-${new Date().getFullYear()}`;
    }
}

function comprobarEstadoCheckin()
{
    const check = true;//llamada a la api para verifiar checkin online

    const boton = document.querySelector('.r123456') //numreserva

    if(check) crearOkCheckin(boton);
}

//checkin hecho
function checkinRealizado(boton)
{
    if(!boton.hasChildNodes()) crearOkCheckin(boton);
}
//checkout hecho
function checkoutRealizado(boton)
{
    if(!boton.hasChildNodes()) crearOkCheckin(boton);
}
//crear ok checkin/out
function crearOkCheckin(boton)
{
    const iconoOk = document.createElement('img');
    iconoOk.setAttribute('class','okIcon');
    iconoOk.setAttribute('src','../../../img/ok.png');
    iconoOk.setAttribute('alt','icono ok');
    boton.appendChild(iconoOk)
}


