const url= "";
async function contactar(event)
{
    event.preventDefault();
    try{

        const body={
            nombre:document.querySelector(".nombre").value,
            email:document.querySelector(".email").value
        };

        const response = await fetch(url, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(body)
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