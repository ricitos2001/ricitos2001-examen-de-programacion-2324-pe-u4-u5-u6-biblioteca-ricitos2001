import EstadoLibro.*
import java.util.UUID

// Clase UtilidadesBiblioteca
class UtilidadesBiblioteca {
    companion object {
        fun generarIdentificadorUnico(): String {
            return UUID.randomUUID().toString()
        }
    }
}

data class Libros(var id: String, var titulo: String, var autor: String, var anioDePublicacion: String, var tematica: String, var estado: EstadoLibro = DISPONIBLE)

enum class EstadoLibro {
    DISPONIBLE, PRESTADO
}

class GestorDeBiblioteca {
    var catalogo = mutableListOf<Libros>()
    fun agregarLibro(libro: Libros):String {
        if (!catalogo.contains(libro)) {
            libro.id = UtilidadesBiblioteca.generarIdentificadorUnico()
            catalogo.add(libro)
            return catalogo.toString()
        } else if (catalogo.toString().isEmpty()) {
            return "catalogo vacio"
        } else {
            return "el libro ya esta en el catalogo"
        }
    }
    fun eliminarLibro(id: String):String {
        val libro = catalogo.find { it.id == id }
        if (libro != null) {
            catalogo.remove(libro)
            return "el libro ha sido eliminado"
        }
        else {
            return "el libro no encontrado por título"
        }
    }
    fun cambiarDisponibilidad(titulo: String, nuevoEstado: EstadoLibro) {
        catalogo.find { it.titulo == titulo }?.let {
            it.estado = nuevoEstado
            println("estado cambiado a $nuevoEstado para el libro: ${it.titulo}")
        }
    }
    fun consultarDisponibilidad(id: String):Boolean {
        return catalogo.any { it.id == id && it.estado == DISPONIBLE }
    }

    fun mostrarEstado(catalogo: String):String {
        return catalogo
    }
}

class RegistrosDePrestamos(private var gestorDeLibros: GestorDeBiblioteca) {
    fun registrarPrestamo(titulo: String): String {
        if (gestorDeLibros.consultarDisponibilidad(titulo)) {
            gestorDeLibros.cambiarDisponibilidad(titulo, PRESTADO)
            return "el libro $titulo ha sido prestado"
        } else {
            return "el libro $titulo no esta disponible para prestamo."
        }
    }
    fun devolverLibro(titulo: String):String {
        if (gestorDeLibros.consultarDisponibilidad(titulo)) {
            gestorDeLibros.cambiarDisponibilidad(titulo, DISPONIBLE)
            return "el libro $titulo ha sido prestado"
        } else {
            return "el libro $titulo aun no ha sido prestado"
        }
    }
}

fun main() {
    val gestorDeBiblioteca = GestorDeBiblioteca()
    val libro1 = Libros("", "El señor de los anillos", "J.R.R. Tolkien", "11/11/2001", "fantasia", DISPONIBLE)
    val libro2 = Libros("", "Harry Potter", "J.K. Rowling", "11/11/2001", "Fantasia", PRESTADO)
    val libro3 = Libros("", "El Libro del buen humor", "cesar gabriel ucha sousa", "11/11/2024", "humor", DISPONIBLE)
    gestorDeBiblioteca.agregarLibro(libro1)
    gestorDeBiblioteca.agregarLibro(libro2)
    gestorDeBiblioteca.agregarLibro(libro3)
    println(gestorDeBiblioteca.catalogo)
    println(gestorDeBiblioteca.eliminarLibro("1234567C"))
    println(gestorDeBiblioteca.consultarDisponibilidad(libro1.estado.toString()))
    val registroDePrestamos = RegistrosDePrestamos(gestorDeBiblioteca)
    println("\nrealizar registros de prestamos:")
    println(registroDePrestamos.registrarPrestamo(libro1.id)) // libro1 prestado
    println(registroDePrestamos.registrarPrestamo(libro2.id)) // libro2 ya prestado
    println("\nrealizar devoluciones:")
    println(registroDePrestamos.devolverLibro(libro1.id)) // libro1 devuelto
    println(registroDePrestamos.devolverLibro(libro2.id)) // libro2 ya devuelto
    println(gestorDeBiblioteca.mostrarEstado("${gestorDeBiblioteca.catalogo}"))
}
