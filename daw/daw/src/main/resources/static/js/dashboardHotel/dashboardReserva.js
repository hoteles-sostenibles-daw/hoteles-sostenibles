// obtener fecha actual
function funcionesInicioGestionReservas()
{
    fechaActual();
    comprobarEstadoCheckin()
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
        span.textContent = `${new Date().getDate()}/${new Date().getMonth()+1}/${new Date().getFullYear()}`;
    }
}

//info reserva
// function funcionesInicioInfoReserva()
// {
//     const numReserva = window.location.href.split('-')[1];
//     document.querySelector('.spanNumReserva').textContent = numReserva;
// }

//info reserva
// function funcionesInicioInfoGastos()
// {
//     const numHabitacion = window.location.href.split('-')[1];
//     document.querySelector('.spanNumHabitacion').textContent = numHabitacion;
// }

//revisar checkin
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


