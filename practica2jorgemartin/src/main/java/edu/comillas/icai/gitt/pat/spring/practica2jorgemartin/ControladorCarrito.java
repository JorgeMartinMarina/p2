package edu.comillas.icai.gitt.pat.spring.practica2jorgemartin;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
public class ControladorCarrito {

    private final Map<Long, ModeloCarrito> carritos = new ConcurrentHashMap<>();

    // CREATE
    @PostMapping("/api/carrito")
    @ResponseStatus(HttpStatus.CREATED)
    public ModeloCarrito crea(@RequestBody ModeloCarrito carritoNuevo) {
        if (carritos.containsKey(carritoNuevo.idCarrito())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
        carritos.put(carritoNuevo.idCarrito(), carritoNuevo);
        return carritoNuevo;
    }

    // READ
    @GetMapping("/api/carrito/{idCarrito}")
    public ModeloCarrito buscar(@PathVariable long idCarrito) {
        ModeloCarrito carrito = carritos.get(idCarrito);
        if (carrito == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return carrito;
    }

    // READ
    @GetMapping("/api/carrito")
    public Collection<ModeloCarrito> listar() {
        return carritos.values();
    }

    // UPDATE
    @PutMapping("/api/carrito/{idCarrito}")
    public ModeloCarrito actualiza(@PathVariable long idCarrito, @RequestBody ModeloCarrito carritoActualizado) {
        if (!carritos.containsKey(idCarrito)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        if (carritoActualizado.idCarrito() != idCarrito) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        carritos.put(idCarrito, carritoActualizado);
        return carritoActualizado;
    }

    // DELETE
    @DeleteMapping("/api/carrito/{idCarrito}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void borra(@PathVariable long idCarrito) {
        if (carritos.remove(idCarrito) == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
