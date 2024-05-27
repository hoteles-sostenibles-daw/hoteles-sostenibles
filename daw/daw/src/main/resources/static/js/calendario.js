      /*console.log(Math.round((nuevaFecha.getTime() - fechaGuardada.getTime())/(1000*3600*24)));*/

  const intervaloFechas= [];

  function generarCalendario(fecha) {
      const calendarioDias = document.getElementById('calendarioDias');
      const mesAnno = document.getElementById('mesAnno');

      const primerDiaMes = new Date(fecha.getFullYear(), fecha.getMonth(), 1);
      const ultimoDiaMes = new Date(fecha.getFullYear(), fecha.getMonth() + 1, 0);
  
      mesAnno.textContent = `${getMonthName(fecha.getMonth())} ${fecha.getFullYear()}`;
      
      let calendarHTML = '';
      
      // Fill in the days of the week
      for (let i = 1; i <= 7; i++) {
      
        calendarHTML += `<p class="diaCelda dias">${obtenerDiaSemana(i)}</p>`;
      }
  
      // Determine the day of the week for the first day of the mes
      let primerDiaSemana = primerDiaMes.getDay();
  
      // Adjust the position of the first day in the grid
      if (primerDiaSemana === 0) {
        primerDiaSemana = 7; // If Sunday, set it to 7
      }
      
      // Fill in the rest of the days of the mes
      const fechaActual = new Date();
      const fechaCalendario = new Date(fecha.getFullYear(), fecha.getMonth());
     
     
      for (let i = 1; i <= ultimoDiaMes.getDate(); i++) {
        
    
          if(i === 1)
          {
            if((fechaActual.getFullYear() === fechaCalendario.getFullYear()) && (fechaCalendario.getMonth() === fechaActual.getMonth()) &&  (fechaActual.getDate() === 1))  calendarHTML += `<button class="diaCelda diaActual" type="button" onclick = "fechaSeleccionada(this)" style="grid-column: ${primerDiaSemana}">1</button>`;
            else if(fechaCalendario < fechaActual) calendarHTML += `<button class="diaCelda diaPasado" type="button" style="grid-column: ${primerDiaSemana}">1</button>`; 
            else calendarHTML += `<button class="diaCelda" type="button" onclick = "fechaSeleccionada(this)" style="grid-column: ${primerDiaSemana}">1</button>`;
          }
          else if(fechaCalendario.getFullYear() === fechaActual.getFullYear() && fechaCalendario.getMonth() === fechaActual.getMonth() && i === fechaActual.getDate()){
            calendarHTML += `<button class="diaCelda diaActual" type="button" onclick = "fechaSeleccionada(this)">${fechaActual.getDate()}</button>`;
          } 
          else if(fechaCalendario < fechaActual){ 
            if(fechaCalendario.getFullYear() !== fechaActual.getFullYear() || fechaCalendario.getMonth() !== fechaActual.getMonth()) calendarHTML += `<button class="diaCelda diaPasado" type="button">${i}</button>`
            
            else {
              if(i < fechaActual.getDate()) calendarHTML += `<button class="diaCelda diaPasado" type="button">${i}</button>`;
              else calendarHTML += `<button class="diaCelda" type="button" onclick = "fechaSeleccionada(this)">${i}</button>`;
          }
          }
          else calendarHTML += `<button class="diaCelda" type="button" onclick = "fechaSeleccionada(this)">${i}</button>`;
      }
      calendarioDias.innerHTML = calendarHTML;
    }
    
    function fechaSeleccionada(boton){
      const formularioReserva = document.querySelector(".formularioReserva");
      const dia = boton.textContent;
      const mesYear = document.getElementById("mesAnno").textContent;
      const year = mesYear.split(" ")[1];
      const mes = convertirMes(mesYear.split(" ")[0]);
      const fecha = `${dia}-${mes + 1}-${year}`;
      const fechaEntrada = document.querySelector(".fechaEntrada");
      const fechaSalida = document.querySelector(".fechaSalida");
      formularioReserva.classList.remove("hidden");
      if(intervaloFechas.length === 0){
        intervaloFechas.push(fecha);
        boton.classList.add("fechaSeleccionada");
        fechaEntrada.value = fecha;
        document.querySelector(".divFechaEntrada").classList.remove("hidden");
      }
      else if(intervaloFechas.length === 1){
        const fechaGuardada = new Date (intervaloFechas[0].split("-")[2], intervaloFechas[0].split("-")[1]-1, intervaloFechas[0].split("-")[0] ) ;
        const nuevaFecha = new Date (fecha.split("-")[2],fecha.split("-")[1]-1,fecha.split("-")[0]);
        if(fechaGuardada != nuevaFecha && fechaGuardada < nuevaFecha){
          intervaloFechas.push(fecha); 
          boton.classList.add("fechaSeleccionada");
          fechaSalida.value = fecha;
          document.querySelector(".divFechaSalida").classList.remove("hidden");
        }
      }
      else if( intervaloFechas.length === 2 ) {
        const elementoSeleccionado = document.querySelectorAll(".fechaSeleccionada");
        for (const elemento of elementoSeleccionado) {
            elemento.classList.remove("fechaSeleccionada");
           }
           intervaloFechas.length = 0;
           fechaEntrada.value = "" ;
           fechaSalida.value = "" ;
           document.querySelector(".divFechaEntrada").classList.add("hidden");
           document.querySelector(".divFechaSalida").classList.add("hidden");
       }
    }

    function getMonthName(mes) {
      const nombreMes = ['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio', 'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre', 'Diciembre'];
      return nombreMes[mes];
    }
  
    function obtenerDiaSemana(diaIndex) {
      const diaNombres = ['D', 'L', 'M', 'X', 'J', 'V', 'S'];
      return diaNombres[diaIndex % 7];
    }
  

  function actualizarMes(direccion) {
    const mesYear = document.getElementById("mesAnno").textContent;
    const year = mesYear.split(" ")[1];
    const mes = convertirMes(mesYear.split(" ")[0]);
      if (direccion === 'next') {
      const fecha = new Date (year, mes +1);
        generarCalendario(fecha);
      } else {
          const fecha = new Date (year, mes -1);
        generarCalendario(fecha);
      } 
    }

  function convertirMes(mes){
    const mesNumero = {Enero:0, Febrero:1, Marzo:2, Abril:3, Mayo:4, Junio:5, Julio:6, Agosto:7, Septiembre:8, Octubre:9, Noviembre:10, Diciembre:11};
    return mesNumero[mes];
  }