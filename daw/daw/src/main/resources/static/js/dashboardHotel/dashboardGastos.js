const url = 'http://localhost:8080/'

async function insertarGasto()
{
    try{ 
        const gasto = {
            concepto: document.getElementById('concepto').value,
            precio: document.getElementById('precio').value.toString(),
            numHabitacion: document.getElementById('habitacion').value.toString()
        }
        
        const response = await fetch(`${url}addgasto`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(gasto)
        })

        const mensaje =  document.querySelector('.mensajeGasto')
        if(!response.ok)
        {
            mensaje.textContent = 'La habitación no está ocupada'
            throw new Error(`Error HTTP ${response.status}`)
        }
        mensaje.classList.remove('aviso')
        mensaje.style.color = 'green'
        mensaje.textContent = 'Gasto añadido correctamente'
    } 
    catch(error)
    {
        console.log(error)
    }
}