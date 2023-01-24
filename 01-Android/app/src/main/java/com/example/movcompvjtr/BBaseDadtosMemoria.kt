package com.example.movcompvjtr

class BBaseDadtosMemoria {
    companion object{
        val arregloBEntrenador = arrayListOf<BEntrenador>()

        init{
            arregloBEntrenador
                .add(
                    BEntrenador("Veronica", "a@a.com")
                )
            arregloBEntrenador
                .add(
                    BEntrenador("Jacqueline", "a@a.com")
                )
            arregloBEntrenador
                .add(
                    BEntrenador("Marco", "a@a.com")
                )
        }
    }
}