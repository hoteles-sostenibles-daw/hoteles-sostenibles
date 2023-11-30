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
      console.log(fechaActual.getDate())

      for (let i = 2; i <= lastDayOfMonth.getDate(); i++) {
        const currentDate = new Date(fecha.getFullYear(), fecha.getMonth(), i);
          if(currentDate.toDateString() === fechaActual.toDateString()){
              
                if(fechaActual.getDate() === 1){
                  calendarHTML += `<button class="diaCelda diaActual" type="button" style="grid-column: ${firstDayOfWeek}">1</button>`;
                }
                else{
                  calendarHTML += `<button class="diaCelda diaActual" type="button">${i}</button>`;
                }
          }
          else if(currentDate.toDateString() < fechaActual.toDateString()){
           
            if(fechaActual.getDate()=== 1){
              calendarHTML += `<button class="diaCelda diaPasado" type="button" style="grid-column: ${firstDayOfWeek}">1</button>`;
            }
            else{
              calendarHTML += `<button class="diaCelda diaPasado" type="button">${i}</button>`;
            }
          }
          else{
            if(fechaActual.getDate()=== 1){
              calendarHTML += `<button class="diaCelda" type="button" style="grid-column: ${firstDayOfWeek}">1</button>`;
            }
            else{
              calendarHTML += `<button class="diaCelda" type="button">${i}</button>`;
            }
          }
        }
      calendarDays.innerHTML = calendarHTML;
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

 