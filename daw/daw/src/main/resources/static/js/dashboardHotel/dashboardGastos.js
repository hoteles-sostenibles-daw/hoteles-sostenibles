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

        if(!response.ok)
        {
            throw new Error(`Error HTTP ${response.status}`)
        }
    } 
    catch(error)
    {
        console.log(error)
    }
}