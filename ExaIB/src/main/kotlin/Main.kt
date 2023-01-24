    //TODO: aqui tenemos la funcion principal en donde se instancia Vista y se le llama al metodo de menu

    fun main(args: Array<String>) {
    val mainView = Vista()
    mainView.mainMenu();
    }

    fun String.center(i: Int): Any {
    return this.padStart((this.length + i) / 2).padEnd(i)
    }
