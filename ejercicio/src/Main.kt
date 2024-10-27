import java.util.*

data class Pupusa(val tipo: String, val cantidad: Int)
data class Orden(val cliente: String, val pupusas: List<Pupusa>)

class Pupuseria {
    private val ordenesPendientes: Queue<Orden> = LinkedList()
    private val ordenesDespachadas: Stack<Orden> = Stack()

    fun registrarOrden() {
        println("Ingrese el nombre del cliente:")
        val cliente = readLine() ?: ""

        println("Cuántos tipos de pupusas desea ordenar?")
        val numTipos = readLine()?.toIntOrNull() ?: return
        val pupusas = mutableListOf<Pupusa>()

        for (i in 1..numTipos) {
            println("Ingrese el tipo de pupusa #$i:")
            val tipo = readLine() ?: ""
            println("Ingrese la cantidad de pupusas de $tipo:")
            val cantidad = readLine()?.toIntOrNull() ?: continue
            pupusas.add(Pupusa(tipo, cantidad))
        }

        val orden = Orden(cliente, pupusas)
        ordenesPendientes.add(orden)
        println("Orden registrada para $cliente: ${orden.pupusas.joinToString { "${it.cantidad} pupusas de ${it.tipo}" }}")
    }

    fun verOrdenesPendientes() {
        if (ordenesPendientes.isEmpty()) {
            println("No hay órdenes pendientes.")
        } else {
            println("Órdenes pendientes:")
            ordenesPendientes.forEachIndexed { index, orden ->
                println("${index + 1}. ${orden.cliente}: ${orden.pupusas.joinToString { "${it.cantidad} pupusas de ${it.tipo}" }}")
            }
        }
    }

    fun despacharOrden() {
        if (ordenesPendientes.isEmpty()) {
            println("No hay órdenes pendientes para despachar.")
        } else {
            val ordenDespachada = ordenesPendientes.poll()
            ordenesDespachadas.push(ordenDespachada)
            println("Despachando la orden de: ${ordenDespachada.cliente}")
        }
    }

    fun verOrdenesDespachadas() {
        if (ordenesDespachadas.isEmpty()) {
            println("No hay órdenes despachadas.")
        } else {
            println("Órdenes despachadas:")
            ordenesDespachadas.forEachIndexed { index, orden ->
                println("${index + 1}. ${orden.cliente}: ${orden.pupusas.joinToString { "${it.cantidad} pupusas de ${it.tipo}" }}")
            }
        }
    }
}

fun main() {
    val pupuseria = Pupuseria()

    while (true) {
        println("""
            Bienvenido a la Pupuseria "El Comalito"
            Seleccione una opción:
            1. Registrar una nueva orden
            2. Ver órdenes pendientes
            3. Despachar orden
            4. Ver órdenes despachadas
            5. Salir
        """.trimIndent())

        when (readLine()?.toIntOrNull()) {
            1 -> pupuseria.registrarOrden()
            2 -> pupuseria.verOrdenesPendientes()
            3 -> pupuseria.despacharOrden()
            4 -> pupuseria.verOrdenesDespachadas()
            5 -> break
            else -> println("Opción no válida, intente de nuevo.")
        }
    }
}
