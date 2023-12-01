  const intervaloFechas= [];

  function generarCalendario(fecha) {
      const calendarDays = document.getElementById('calendarDays');
      const monthYear = document.getElementById('monthYear');

      const firstDayOfMonth = new Date(fecha.getFullYear(), fecha.getMonth(), 1);
      const lastDayOfMonth = new Date(fecha.getFullYear(), fecha.getMonth() + 1, 0);
  
      monthYear.textContent = `${getMonthName(fecha.getMonth())} ${fecha.getFullYear()}`;
      
      let calendarHTML = '';
      
      // Fill in the days of the week
      for (let i = 1; i <= 7; i++) {
      
        calendarHTML += `<p class="diaCelda dias">${obtenerDiaSemana(i)}</p>`;
      }
  
      // Determine the day of the week for the first day of the month
      let firstDayOfWeek = firstDayOfMonth.getDay();
  
      // Adjust the position of the first day in the grid
      if (firstDayOfWeek === 0) {
        firstDayOfWeek = 7; // If Sunday, set it to 7
      }
      
      // Fill in the rest of the days of the month
      const fechaActual = new Date();
      const fechaCalendario = new Date(fecha.getFullYear(), fecha.getMonth());
      for (let i = 1; i <= lastDayOfMonth.getDate(); i++) {
        
    
          if(i === 1)
          {
            if(fechaActual.getDate() === 1) calendarHTML += `<button class="diaCelda diaActual f${i}-${fechaCalendario.getMonth()}-${fechaCalendario.getFullYear()}" type="button" onclick = "fechaSeleccionada(this)" style="grid-column: ${firstDayOfWeek}">1</button>`;
            else if(fechaCalendario < fechaActual) calendarHTML += `<button class="diaCelda diaPasado" type="button" style="grid-column: ${firstDayOfWeek}">1</button>`; 
            else calendarHTML += `<button class="diaCelda f${i}-${fechaCalendario.getMonth()}-${fechaCalendario.getFullYear()}" type="button" onclick = "fechaSeleccionada(this)" style="grid-column: ${firstDayOfWeek}">1</button>`;
          }
          else if(fechaCalendario.getFullYear() === fechaActual.getFullYear() && fechaCalendario.getMonth() === fechaActual.getMonth() && i === fechaActual.getDate()){
            calendarHTML += `<button class="diaCelda diaActual f${i}-${fechaCalendario.getMonth()}-${fechaCalendario.getFullYear()}" type="button" onclick = "fechaSeleccionada(this)">${fechaActual.getDate()}</button>`;
          } 
          else if(fechaCalendario < fechaActual) calendarHTML += `<button class="diaCelda diaPasado" type="button">${i}</button>`;
          else calendarHTML += `<button class="diaCelda f${i}-${fechaCalendario.getMonth()}-${fechaCalendario.getFullYear()}" type="button" onclick = "fechaSeleccionada(this)">${i}</button>`;
      }
      calendarDays.innerHTML = calendarHTML;

      if(intervaloFechas.length === 1 || intervaloFechas.length === 2 ){
      
        if(intervaloFechas.length === 1){
        
          if(intervaloFechas[0].split("-")[1] == fechaCalendario.getMonth()){
            const fecha1 = document.querySelector(`.f${intervaloFechas[0]}`); /* nos queda encontrar el error del querySelector */
            console.log(intervaloFechas[0]);
            fecha1.classList.add("fechaSeleccionada");
          } 
        }
        else{
          const fecha1 = document.querySelector(`.f${intervaloFechas[0]}`);
          const fecha2 = document.querySelector(`.f${intervaloFechas[1]}`)
          fecha1.classList.add("fechaSeleccionada");
          fecha2.classList.add("fechaSeleccionada");
        }
        

      }
    }
    
    function fechaSeleccionada(boton){
      const formularioReserva = document.querySelector(".formularioReserva");
      const dia = boton.textContent;
      const mesYear = document.getElementById("monthYear").textContent;
      const year = mesYear.split(" ")[1];
      const mes = convertirMes(mesYear.split(" ")[0]);
      const fecha = `f${dia}-${mes}-${year}`;
  
      formularioReserva.classList.remove("hidden");
      if(intervaloFechas.length === 0){
        intervaloFechas.push(fecha);
        boton.classList.add("fechaSeleccionada");
      }
      else if(intervaloFechas.length === 1){
        intervaloFechas.push(fecha); 
        boton.classList.add("fechaSeleccionada");
      }
     
    }

    function getMonthName(month) {
      const monthNames = ['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio', 'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre', 'Diciembre'];
      return monthNames[month];
    }
  
    function obtenerDiaSemana(diaIndex) {
      const diaNombres = ['D', 'L', 'M', 'X', 'J', 'V', 'S'];
      return diaNombres[diaIndex % 7];
    }
  

  function actualizarMes(direccion) {
    const mesYear = document.getElementById("monthYear").textContent;
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

 