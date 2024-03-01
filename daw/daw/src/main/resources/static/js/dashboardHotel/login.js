const url = "http://localhost:8080";


// Validaciones tanto de la contraseña como del usuario
function validarsuario(usuario) {
    const regex = /^[a-zA-Z0-9]{1,10}$/;
    return regex.test(usuario);
  }
  
  function validarContrasena(contrasena) {
    const regex = /^[a-zA-Z0-9]{1,10}$/;
    return regex.test(contrasena);
  }
  
  function validarFormulario(usuario,contrasena) {
   
    if (! validarsuario(usuario) || !validarContrasena(contrasena)) {
      document.querySelector(".aviso").textContent='El nombre de usuario solo puede contener letras y números.';
      return false;
    }
    return true;
  
  }

  //to do cambiar class por id en los input
async function validarUsuario() {
    
    const usuarioInput = document.querySelector('.usuario').value;
    const contrasenaInput = document.querySelector('.contrasena').value;
    
    if(!validarFormulario(usuarioInput,contrasenaInput)){
        return;
    }
    try{
    const response = await fetch(`${url}/login`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({ username, password,nombre_hotel:"Hotel AC Balagares" }),
    });
  
    if (!response.ok) {
        throw new Error(`Error HTTP ${response.status}`);
    } 
    }
    catch(error)
    {
        
        console.log(error)
    }
  };
  

