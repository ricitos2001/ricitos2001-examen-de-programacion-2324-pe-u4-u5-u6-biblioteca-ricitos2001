import EstadoLibro.*
import java.util.UUID
data class Libros(var id: String, var titulo: String, var autor: String, var anioDePublicacion: Int, var tematica: String, var estado: EstadoLibro = DISPONIBLE)

enum class EstadoLibro {
    DISPONIBLE, PRESTADO
}

// clase UtilidadesBiblioteca
class UtilidadesBiblioteca {
    companion object {
        fun generarIdentificadorUnico(): String {
            return UUID.randomUUID().toString()
        }
    }
}

class GestorDeBiblioteca {
    var catalogo = mutableListOf<Libros>()
    private var registroPrestamos = mutableListOf<Libros>()
    // metodo para agregar libros
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
    // metodo para eliminar libros
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
    // metodo para
    fun registrarPrestamo(id: String):String {
        val libro = catalogo.find { it.id == id }
        if (libro != null && libro.estado == DISPONIBLE) {
            libro.estado = PRESTADO
            registroPrestamos.add(libro)
            return "el libro  ha sido prestado"
        } else {
            return "el libro no esta disponible para prestamo."
        }
    }
    //metodo para devolver un libro
    fun devolverLibro(id: String):String {
        val libro = catalogo.find { it.id == id }
        if (libro != null && libro.estado == PRESTADO) {
            libro.estado = DISPONIBLE
            registroPrestamos.remove(libro)
            return "el libro  ha sido devuelto"
        } else {
            return "el libro aun no ha sido prestado"
        }
    }
    //metodo para consultar la disponibilidad de un libro
    fun consultarDisponibilidad(id: String): Libros? {
        return catalogo.find { it.id == id && it.estado == DISPONIBLE }
    }
    // metodo para mostrar el estado de un libro
    fun mostrarEstado() {
        catalogo.forEach {
            println("${it.titulo}: ${it.estado}")
        }
    }
}
// funcion principal que inicia el programa
fun main() {
    val gestorDeBiblioteca = GestorDeBiblioteca()
    val libro1 = Libros("1234567A", "El señor de los anillos", "J.R.R. Tolkien",1954 , "fantasia", DISPONIBLE)
    val libro2 = Libros("1234567B", "Harry Potter", "J.K. Rowling", 1998, "Fantasia", PRESTADO)
    val libro3 = Libros("1234567C", "El Libro del buen humor", "Ramon Gomez", 2020, "humor", DISPONIBLE)
    gestorDeBiblioteca.agregarLibro(libro1)
    gestorDeBiblioteca.agregarLibro(libro2)
    gestorDeBiblioteca.agregarLibro(libro3)
    println(gestorDeBiblioteca.catalogo)
    println(gestorDeBiblioteca.eliminarLibro("1234567C"))
    println(gestorDeBiblioteca.consultarDisponibilidad(libro1.estado.toString()))
    println("\nrealizar registros de prestamos:")
    println(gestorDeBiblioteca.registrarPrestamo(libro1.id)) // libro1 prestado
    println(gestorDeBiblioteca.registrarPrestamo(libro2.id)) // libro2 ya prestado
    println("\nrealizar devoluciones:")
    println(gestorDeBiblioteca.devolverLibro(libro1.id)) // libro1 devuelto
    println(gestorDeBiblioteca.devolverLibro(libro2.id)) // libro2 ya devuelto
    gestorDeBiblioteca.mostrarEstado()
}
