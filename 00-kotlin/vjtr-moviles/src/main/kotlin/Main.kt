import java.util.*;
import javax.swing.ListModel

fun main(args: Array<String>) {
    println("Hola Mundo!")

    // Tipos de variables
    // inmutables (no re asignar)
    val inmutable: String = "Veronica"
    //inmutable = "Jacqueline"

    //Mutables (re asignar) =
    var mutable:String="Veronica"
    mutable="Jacqueline"

    // val > vars

    // duck typing

    val ejemploVariable= "Ejemplo"
    ejemploVariable.trim()
    val edadEjemplo: Int = 12

    // varibles primitivas
    val nombreEstudiante:String ="Veronica Toasa"
    val sueldo: Double =1.2
    val estadoCivil: Char ='S'
    val mayorEdad:Boolean = true

    //clases
    val fechaNacimiento: Date =Date()
    if(true){

    }else{

    }

    //switch no existe

    val estadoCivilWhen='S'
    when(estadoCivilWhen){
        ('S')->{
            println("soltero")
        }
        'C'-> println("Casado")
        else -> println("Desconocido")

    }

    val coqueto =if(estadoCivilWhen=='S')"Si" else "No"

    val sumaUno = Suma(1,2)
    val sumaDos = Suma(1,null)
    val sumaTres = Suma(null,2)
    val sumaCuatro = Suma(null,null)

    sumaUno.sumar()
    sumaDos.sumar()
    sumaTres.sumar()
    sumaCuatro.sumar()

    println(Suma.historialSumas)

    //////////////////////////////////////////////////////////////////////////////////////////////////////////
    //ARREGLOS
    //Tipos de Arreglos

    //Arreglo estatico

    val arregloEstatico: Array<Int> = arrayOf(1, 2, 3)
    println(arregloEstatico)

    //Arreglo Dinamico
    val arregloDinamico: ArrayList<Int> = arrayListOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    println(arregloDinamico)
    arregloDinamico.add(11)
    arregloDinamico.add(12)
    println(arregloDinamico)

    //OPERADORES -> Sirven Para los arreglos estaticos y dinamicos
    //FOR EACH -> Unit
    // Iterar un arreglo

    val respuestaForEach: Unit = arregloDinamico
        .forEach{
            valorActual: Int ->
            println("Valor actual : ${valorActual}")
        }
    arregloDinamico
        .forEachIndexed { indice: Int, ValorActual: Int ->
            println("Valor ${ValorActual} Indice: ${indice}")
        }
    println(respuestaForEach)

    //MAP -> Muta el arreglo (Cambia el arreglo)
    //1) Enviemos el nuevo valor de la iteracion
    // Nos devuelve es un NUEVO ARREGLO con los valores mof¿dificados

    val respuestaMap: List<Double> = arregloDinamico
        .map { valorActual: Int ->
            return@map valorActual.toDouble() + 100.00
        }
    println(respuestaMap)

    val respuestaMapDos = arregloDinamico.map { it + 15 }
    // .map { valorActual : Int ->
    //        return@map valorActual + 15
    //  }


    //Filter -> FILTRAR EL ARREGLO
    //1)Devolver una expresion (TRUE o FALSE)
    //2)Nuevo arreglo filtrado

    val respuestaFilter: List<Int> = arregloDinamico
        .filter{ valorActual: Int ->
            val mayoresACinco: Boolean = valorActual > 5 // Expresion Condicion
        return@filter mayoresACinco
    }

    val respuestaFilterDos = arregloDinamico.filter { it <= 5 }
    println(respuestaFilter)
    println(respuestaFilterDos)

    //OR AND
    //OR -> ANY (Alguno cumple?)
    //AND -> ALL (Todos cumplen?)

    val respuestaAny: Boolean = arregloDinamico
        .any { valorActual: Int ->
            return@any (valorActual > 5)
        }
    println(respuestaAny) //True


    val respuestaAll: Boolean = arregloDinamico
        .all { valorActual: Int ->
            return@all (valorActual > 5)
        }
    println(respuestaAll) //False

    // REDUCE -> Valor acunulado
    // Valor acumulado = 0 ( Siempre 0 en lenguaje Kotlin)
    // [1, 2, 3, 4, 5] -> Sumeme tofod los valores del arreglo
    // valorIteracion1 = valorEmpieza + 1 = 0 + 1 = 1 -> Iteracion 1
    // valorIteracion2 = valorIteracion1 + 2 = 1 + 2 = 3 -> Iteracion 2
    // valorIteracion3 = valorIteracion2 + 3 = 3 + 3 = 6 -> Iteracion 3
    // valorIteracion4 = valorIteracion3 + 4 = 6 + 4 = 10 -> Iteracion 4
    // valorIteracion5 = valorIteracion4 +5 = 10 + 5 = 15 -> Iteracion 5

    val respuestaReduce: Int = arregloDinamico
        .reduce { //acumulado = 0 -> SIEMPRE EMPIEZA EN 0
            acumulado: Int, valorActual: Int ->
            return@reduce (acumulado + valorActual) //logica negocio
        }
    println(respuestaReduce)

    ////////////////////////////////////////////////////////////////////////////////////////
    fun imprimirNombre(nombre:String): Unit{
        println("Nombre :${nombre}")

    }

    fun calcularSueldo(
        sueldo:Double,
        tasa:Double=12.00,
        bonoEspecial:Double?=null
    ): Double{
        //String -> String?
        //Int->Int
        // Date ->Date
        if(bonoEspecial!=null){
            return sueldo*tasa*bonoEspecial
        }


        return sueldo*tasa
    }
}// fin clase main

    abstract class NumerosJava{
        protected val numeroUno: Int
        private val numeroDos: Int
        constructor(
            uno: Int, // parametro
            dos: Int // parametro
        ){
            this.numeroUno = uno;
            this.numeroDos = dos;
        }
    }
    abstract class Numeros ( //Constructor primario)
    //uno: Int, //parametro
    // public var uno: Int, //propiedad de la clase publica
    protected val numeroUno: Int, // Propiedad
    protected val numeroDos: Int, // Propiedad
    ){
        init{  // Bloque Codigo constructor primario
            //this.numeroDos // "This" opcional
            //this.numeroUno
            numeroUno
            numeroDos
            println("Iniciando")

        }
    }
    //Tipos de constructores en Kotlin
    // como heredar
    class Suma ( // Constructor Primario Suma
        uno: Int, // Parametro
        dos: Int, // Parametro
    ): Numeros ( //Heredamos de la clase Numeros
        // Suoer constructor numeros
            uno,
            dos,
        ){
        init {
            this.numeroUno
            this.numeroDos
        }

        constructor( // Segundo constructor
            uno: Int?, // Parametros
            dos: Int // Parametros
        ): this(
            if(uno == null) 0 else uno,
            dos
        ){}

        constructor( // Tercer constructor
            uno: Int, // Parametros
            dos: Int? // Parametros
        ): this(
            uno,
            if(dos == null) 0 else dos,
        ){}

        constructor( // Cuarto constructor
            uno: Int?, // Parametros
            dos: Int? // Parametros
        ): this(
            if(uno == null) 0 else uno,
            if(dos == null) 0 else dos,
        ){}

        fun sumar() : Int{
            val total = numeroUno + numeroDos
            agregarHistorial(total)
            return total
        }

        companion object{
            val pi = 3.14 //Suma.pi -> 3.14
            val historialSumas = arrayListOf<Int>() //Suma.
            fun agregarHistorial(valorNuevaSuma: Int){
                historialSumas.add(valorNuevaSuma)
            }
        }
    }












