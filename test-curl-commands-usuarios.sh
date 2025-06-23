# Comandos curl para probar usuariosaventura microservicio

# Obtener todos los usuarios
curl http://localhost:8083/api/v1/users

# Obtener todos los roles
curl http://localhost:8083/api/v1/roles

# Crear un usuario
curl -X POST "http://localhost:8083/api/v1/users?username=testuser&password=testpass&roleId=1"

# Modificar un usuario (id=1)
curl -X PUT "http://localhost:8083/api/v1/users/1?username=updateduser&roleId=2"

# Eliminar un usuario (id=1)
curl -X DELETE http://localhost:8083/api/v1/users/1

# Iniciar sesión
curl -X POST "http://localhost:8083/api/v1/login?username=testuser&password=testpass"
