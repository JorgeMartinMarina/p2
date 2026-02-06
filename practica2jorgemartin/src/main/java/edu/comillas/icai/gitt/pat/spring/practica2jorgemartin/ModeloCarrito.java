package edu.comillas.icai.gitt.pat.spring.practica2jorgemartin;

public record ModeloCarrito(
    long idCarrito,
    long idArticulo,
    String descripcion,
    long unidades,
    long precioFinal
) {
}
