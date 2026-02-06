# Práctica 2 - API REST Carrito (Spring)

## Objetivo
Diseñar e implementar un servicio web REST siguiendo el estilo request/response sobre HTTP.  
Se construye una API mínima que permite operaciones **CRUD** (Create, Read, Update, Delete) 
sobre el recurso **Carrito** para un caso simplificado de e-commerce.

## Alcance funcional
El recurso **Carrito** representa una compra en curso con las propiedades:

- **idCarrito**: identificador único del carrito.
- **idArticulo**: identificador del artículo asociado.
- **descripcion**: texto descriptivo del artículo.
- **unidades**: número de unidades del artículo.
- **precioFinal**: importe final del carrito (se recibe y se devuelve en las respuestas).

**Simplificación:** cada carrito contiene un único producto.

## Implementación
Se ha implementado un controlador REST en Spring que expone endpoints para:

- **Create**: crear un carrito
- **Read**: consultar un carrito por id y listar todos
- **Update**: actualizar un carrito existente
- **Delete**: eliminar un carrito

Los carritos se almacenan en memoria mediante un `ConcurrentHashMap<Long, ModeloCarrito>`.

## Endpoints (CRUD)

### CREATE
- `POST /api/carrito`
  - **201 CREATED** si se crea correctamente
  - **409 CONFLICT** si ya existe un carrito con ese `idCarrito`

### READ
- `GET /api/carrito/{idCarrito}`
  - **200 OK** si existe
  - **404 NOT FOUND** si no existe

- `GET /api/carrito`
  - **200 OK** devuelve la lista de carritos

### UPDATE
- `PUT /api/carrito/{idCarrito}`
  - **200 OK** si se actualiza correctamente
  - **404 NOT FOUND** si no existe
  - **400 BAD REQUEST** si `idCarrito` del cuerpo no coincide con el de la URL

### DELETE
- `DELETE /api/carrito/{idCarrito}`
  - **204 NO CONTENT** si se elimina
  - **404 NOT FOUND** si no existe

## Modelo de datos
`ModeloCarrito` se define como `record` con los siguientes campos:

- `idCarrito` (long)
- `idArticulo` (long)
- `descripcion` (String)
- `unidades` (long)
- `precioFinal` (long)

