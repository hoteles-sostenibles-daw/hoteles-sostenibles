
    
  
    function generarCalendario(fecha) {
        
        const calendarDays = document.getElementById('calendarDays');
        const monthYear = document.getElementById('monthYear');

      const firstDayOfMonth = new Date(fecha.getFullYear(), fecha.getMonth(), 1);
      const lastDayOfMonth = new Date(fecha.getFullYear(), fecha.getMonth() + 1, 0);
  
      monthYear.textContent = `${getMonthName(fecha.getMonth())} ${fecha.getFullYear()}`;
      
      let calendarHTML = '';
      
      // Fill in the days of the week
      for (let i = 1; i <= 7; i++) {
        calendarHTML += `<div class="day-cell day">${getDayName(i)}</div>`;
      }
  
      // Determine the day of the week for the first day of the month
      let firstDayOfWeek = firstDayOfMonth.getDay();
  
      // Adjust the position of the first day in the grid
      if (firstDayOfWeek === 0) {
        firstDayOfWeek = 7; // If Sunday, set it to 7
      }
      calendarHTML += `<div class="day-cell day" style="grid-column: ${firstDayOfWeek}">${1}</div>`;
  
      // Fill in the rest of the days of the month
      
      for (let i = 2; i <= lastDayOfMonth.getDate(); i++) {

        if(fecha.getDay() === i){
            console.log("hola")
            calendarHTML += `<div class="day-cell diaActual">${i}</div>`;
        }
        else{
            calendarHTML += `<div class="day-cell">${i}</div>`;
        }
      }
  
      calendarDays.innerHTML = calendarHTML;
    }
  
    function getMonthName(month) {
      const monthNames = ['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio', 'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre', 'Diciembre'];
      return monthNames[month];
    }
  
    function getDayName(dayIndex) {
      const dayNames = ['Domingo', 'Lunes', 'Martes', 'Miércoles', 'Jueves', 'Viernes', 'Sábado'];
      return dayNames[dayIndex % 7];
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
   console.log(mesNumero[mes]);
    return mesNumero[mes];
  }
