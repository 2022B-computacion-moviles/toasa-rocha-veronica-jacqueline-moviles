import java.util.*;
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








