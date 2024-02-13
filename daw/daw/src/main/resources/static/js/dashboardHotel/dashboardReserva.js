
const url = "http://localhost:8080/";

// obtener fecha actual


function funcionesInicioGestionReservas()
{

    fechaActual();
    reservasFechaEntrada()
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
       generarLi(data, "entrada");

       
    } 

    catch(error)
    {
        
        console.log(error)
    }
}

function generarLi(listaReservas,tipo){

    let ul ;

    if(tipo === "entrada"){
        ul= document.querySelector(".ulCheckin")
    }

    else if(tipo === "salida"){
        ul= document.querySelector(".ulCheckout")
    }
    
    for(const reserva of listaReservas){
        const li = document.createElement("li");
        li.setAttribute("class","liReserva");
    
        const parrafoNumeroReserva = document.createElement("p");
        parrafoNumeroReserva.textContent = reserva.codigoReserva;

        const parrafoDni = document.createElement("p");
        parrafoDni.setAttribute("class","parrafoDni");
        parrafoDni.textContent = reserva.dni;

        const boton = document.createElement("button");
        boton.setAttribute("class",`checkinState ${reserva.codigoReserva}`)
        boton.setAttribute("type","button")
        boton.setAttribute("onclick","checkinRealizado(this)")
        crearOkCheckin(boton,reserva.checkIn);

        li.appendChild(parrafoNumeroReserva);
        li.appendChild(parrafoDni);
        li.appendChild(boton);
        ul.appendChild(li);

    }


   /* <li class="liReserva">
    <p>123456</p>
    <p class="parrafoDNI">12345678F</p>
    <button class="checkinState r123456" type="button" onclick="checkinRealizado(this)"></button>
</li>*/
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


//checkin hecho
async function  checkinRealizado(boton)
{
    if(!boton.hasChildNodes()){
        try{
            const numReserva = boton.getAttribute("class").split(" ")[1];
            console.log(numReserva);
            const response = await fetch(`${url}actualizarcheckin/${numReserva}`, {
                method: 'GET',
             /* headers: {
                    'Content-Type': 'text/plain'
                },
                body: numReserva*/
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
function checkoutRealizado(boton)
{
    if(!boton.hasChildNodes()) crearOkCheckin(boton);
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


