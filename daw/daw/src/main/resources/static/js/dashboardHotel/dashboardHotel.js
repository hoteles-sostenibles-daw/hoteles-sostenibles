// obtener fecha actual
function funcionesInicio(){
    cargarInicio();
    fechaActual();
    verificarCheckin();
    verificarCheckout();
    numReservaCheckin();
    dniReservaCheckin();
    numReservaCheckout();
    dniReservaCheckout();
}
// componentes
function cargarGestionReserva(){
    const show = document.querySelector(".articleComponenteContainer");
    show.innerHTML = '';
    fetch('./dashboardInfoReserva.html')
    .then(response => response.text())
    .then(data => {
        show.innerHTML = data;
    })
}

function cargarGestionGastos(){
    const show = document.querySelector(".articleComponenteContainer");
    show.innerHTML = '';
    fetch('./dashboardInfoGastos.html')
    .then(response => response.text())
    .then(data => {
        show.innerHTML = data;
    })
}
function cargarInicio(){
    const show = document.querySelector(".articleComponenteContainer");
    show.innerHTML = '';
    fetch('./dashboardInfoInicio.html')
    .then(response => response.text())
    .then(data => {
        show.innerHTML = data;
    })
}

// verificar fecha aActual
function fechaActual(){
    const spanFecha = document.querySelectorAll(".spanFecha");
    for(const span of spanFecha){
        span.textContent = `${new Date().getDate()}/${new Date().getMonth()+1}/${new Date().getFullYear()}`;
    }
}

// verificar check in para 
function verificarCheckin(){
    //añadir boton con onclick=validarCheckin si no lo ha hecho online
    //mostrar parrafo con check verde si lo ha hecho online
    
}

// validar checkin manual
function validarCheckin(){

}
// verificar check out 
function verificarCheckout(){
    //añadir boton con onclick=validarCheckout si no lo ha hecho online
    //mostrar parrafo con check verde si lo ha hecho online
    
}

// validar checkin manual
function validarCheckout(){

}

function numReservaCheckin(){

}
function dniReservaCheckin(){

}
function numReservaCheckout(){

}
function dniReservaCheckout(){
    
}