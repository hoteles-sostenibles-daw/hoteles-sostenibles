const url = "http://localhost:8080";


// Validaciones tanto de la contraseña como del usuario
function validarsuario(usuario) {
    const regex = /^[a-zA-Z]{1,10}$/;
    return regex.test(usuario);
  }
  
  function validarContrasena(contrasena) {
    const regex = /^[a-zA-Z0-9]{1,12}$/;
    return regex.test(contrasena);
  }
  
  function validarFormulario(usuario,contrasena) {
   
    if (! validarsuario(usuario) || !validarContrasena(contrasena)) {
      document.querySelector(".aviso").textContent='Usuario o contraseña con formato incorrecto';
      return false;
    }
    return true;
  
  }

  //to do cambiar class por id en los input
async function validarUsuario() {
    
    const usuarioInput = document.getElementById('usuario').value;
    const contrasenaInput = document.getElementById('contrasena').value;
    
    if(!validarFormulario(usuarioInput,contrasenaInput)){
        return;
    }
    try{
    const response = await fetch(`${url}/login`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({ 
        rol: usuarioInput, 
        contrasena: contrasenaInput,
        nombre_hotel:"Hotel AC Balagares" }),
    });
  
    if (!response.ok) {
        document.querySelector(".aviso").textContent='Usuario o contraseña incorrectos';
        throw new Error(`Error HTTP ${response.status}`);
    } 

    window.location.href = `http://localhost:8080/${usuarioInput}`
    }
    catch(error)
    {
        
        console.log(error)
    }
  };
  

