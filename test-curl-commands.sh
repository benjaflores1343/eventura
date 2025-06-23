# Comandos curl para probar event-service

# Crear un evento
curl -X POST http://localhost:8082/events -H "Content-Type: application/json" -d "{\"name\":\"Evento Test\",\"date\":\"2025-06-21\"}"

# Obtener todos los eventos
curl http://localhost:8082/events

# Obtener eventos filtrados por usuarioId=1
curl http://localhost:8082/events?usuarioId=1

# Obtener evento por id=1
curl http://localhost:8082/events/1

# Eliminar evento por id=1
curl -X DELETE http://localhost:8082/events/1
