import java.text.SimpleDateFormat
import java.util.*

val DATE_FORMAT = SimpleDateFormat("dd/mm/yyyy");

class Vista {
    fun header(message: String): Unit {
        println(message.center(50));
    }
    fun menu(opc: Map<String, () -> Unit>): Unit {
        while (true) {
            for (key in opc.keys) {
                println(key);
            }
            print("Ingrese aqui su opción: ");
            val option = readLine()!!.toInt();
            if (option == 0) {
                header("Entidad Competencia - Competidor");
                break;
            } else if (option in 1..opc.keys.size) {
                opc[opc.keys.elementAt(option - 1)]!!();
            } else {
                println("Ingrese una opcion Válida");
            }
        }
    }

    /*en esta parte se mostrara el menu para elegir una opcion*/
    fun mainMenu(): Unit {
        header("Entidad Competencia - Competidor");
        header("Elija una entidad para realizar operaciones CRUD");
        this.menu(
            mapOf(
                "1. Competidor" to ::MenuCompetidor,
                "2. Competencia" to ::MenuCompetencia,
            )
        );
    }

    // TODO: menu de opciones para elegir operacioneS CRUD para la entidad Competidor
    fun MenuCompetidor(): Unit {
        header("Competidor");
        this.menu(
            mapOf(
                "1. Crear Competidor" to ::competidorCreate,
                "2. Ver Competidores" to ::competidorList,
                "3. Actualizar Competidor" to ::competidorUpdate,
                "4. Eliminar Competidor" to ::competidorDelete,
            )
        );
    }

    fun competidorCreate(): Unit {
        header("Creando un nuevo competidor");
        print("Ingrese el nombre del competidor: ");
        val nombre = readLine()!!;
        print("Ingrese el apellido del competidor: ");
        val apellido = readLine()!!;
        print("Ingrese la fecha de nacimiento del competidor {dd/mm/aaaa}: ");
        val fechaNacimiento = readLine()!!;
        print("Ingrese el lugar de nacimiento del competidor: ");
        val lugarNac = readLine()!!;
        print("Ingrese la altura del competidor: ");
        val altura = readLine()!!;
        print("El competidor tiene un entrenador personalizado? (s/n): ");
        val entrenador = readLine()!!;

        val codigo = CRUD.readCompetidor().size;
        val competidor = Competidor(
            codigo = codigo,
            nombre = nombre,
            apellido = apellido,
            fechaNacimiento = DATE_FORMAT.parse(fechaNacimiento),
            lugarNac = lugarNac,
            altura = altura.toDouble(),
            entrenador = entrenador == "s",
            competencia = ArrayList(),
        );

        if (CRUD.createCompetidor(competidor))
            println("Se ha creado competidor correctamente");
        else
            println("Error al crear competidor");
    }

    fun competidorList(): Unit {
        header("Ver Competidores");
        val conpetidor = CRUD.readCompetidor();
        println("CODIGO\tNOMBRE\tAPELLIDO\tFECHA NACIMIENTO\tLUGAR NACIMIENTO\tALTURA\tCOMPETENCIA\tENTRENADOR");
        conpetidor.forEach { competidor ->
            println(competidor.customToString());
        }
    }

    fun competidorUpdate(): Unit {
        header("Actualizar Competidor");
        print("Ingrese el codigo del competidor que desea actualizar: ");
        val codigo = readLine()!!;

        val es = CRUD.readCompetidor(codigo.toInt());
        if (es.codigo == -1) {
            println("No se encontro el codigo de competidor");
            return;
        }

        print("Ingrese el nombre del competidor: ");
        val nombre = readLine()!!;
        print("Ingrese el apellido del competidor: ");
        val apellido = readLine()!!;
        print("Ingrese la fecha de nacimiento del competidor {dia/mes/anio}: ");
        val fechaNacimiento = readLine()!!;
        print("Ingrese el lugar de nacimiento del competidor: ");
        val lugarNac = readLine()!!;
        print("Ingrese la altura del competidor: ");
        val altura = readLine()!!;
        print("El competidor tiene un entrenador personalizado? (s/n): ");
        val entrenador = readLine()!!;
        val competidor = Competidor(
            codigo = codigo.toInt(),
            nombre = nombre,
            apellido = apellido,
            fechaNacimiento = DATE_FORMAT.parse(fechaNacimiento),
            lugarNac = lugarNac,
            altura = altura.toDouble(),
            entrenador = entrenador == "s",
            competencia = ArrayList(),
        );
        if (CRUD.updateCompetidor(competidor))
            println("Se ha actualizado el competidor correctamente");
        else
            println("Error al actualizar competidor");
    }
    fun competidorDelete(): Unit {
        header("Eliminar Competidor");
        print("Ingrese el codigo del competidor que desea eliminar: ");
        val codigo = readLine()!!;
        val es = CRUD.readCompetidor(codigo.toInt());
        if (es.codigo == -1) {
            println("No se encontro el codigo de competidor");
            return;
        }

        if (CRUD.deleteCompetidor(codigo.toInt()))
            println("Se ha eliminado el competidor correctamente");
        else
            println("Error al eliminar competidor");
    }

    // TODO: menu de opciones para elegir operacioneS CRUD para la entidad Competencia

    fun MenuCompetencia(): Unit {
        header("Competencia");
        this.menu(
            mapOf(
                "1. Crear Competencia" to ::competenciaCreate,
                "2. Ver Competencias" to ::competenciaList,
                "3. Actualizar competencia" to ::competenciaUpdate,
                "4. Eliminar competencia" to ::competenciaDelete,
            )
        );
    }

    fun competenciaCreate(): Unit {
        header("Crear Competencia");
        print("Ingrese el nombre de la competencia: ");
        val nombre = readLine()!!;
        print("Ingrese la fecha de la competencia {dia/mes/anio}: ");
        val fechaCompetencia = readLine()!!;
        print("Ingrese el numero de participantes parea la competencia: ");
        val participantes = readLine()!!;
        print("Ingrese el costo de la inscripcion de la competencia: ");
        val costoInscripcion = readLine()!!;
        print("Ingrese la direccion de la competencia: ");
        val direccion = readLine()!!;

        val codigo = CRUD.readCompetencia().size;
        val competencia = Competencia(
            codigo = codigo,
            fechaCompetencia = DATE_FORMAT.parse(fechaCompetencia),
            nombre = nombre,
            participantes = participantes.toInt(),
            direccion = direccion,
            costoInscripcion = costoInscripcion.toDouble(),
        );

        if (CRUD.createCompetencia(competencia))
            println("Se ha creado competencia correctamente");
        else
            println("Error al crear competencia");
    }

    fun competenciaList(): Unit {
        header("Ver Competencias");
        val competencia = CRUD.readCompetencia();
        println("CODIGO\tNOMBRE\tFECHA COMPETENCIA\tPARTICIPANTES\tCOSTO INSCRIPCION\tDIRECCION");
        competencia.forEach { competencia ->
            println(competencia.toString().replace(',', '\t'));
        }
    }

    fun competenciaUpdate(): Unit {
        header("Actualizar competencia");
        print("Ingrese el codigo de la competencia que desea actualizar: ");
        val codigo = readLine()!!;

        val comp = CRUD.readCompetencia(codigo.toInt());
        if (comp.codigo == -1) {
            println("No se encontro el codigo de competencia");
            return;
        }

        print("Ingrese el nombre de la competencia: ");
        val nombre = readLine()!!;
        print("Ingrese la fecha de la competencia {dia/mes/anio}: ");
        val fechaCompetencia = readLine()!!;
        print("Ingrese el numero de participantes parea la competencia: ");
        val participantes = readLine()!!;
        print("Ingrese el costo de la inscripcion de la competencia: ");
        val costoInscripcion = readLine()!!;
        print("Ingrese la direccion de la competencia: ");
        val direccion = readLine()!!;
        val competencia = Competencia(
            codigo = codigo.toInt(),
            nombre = nombre,
            direccion = direccion,
            fechaCompetencia = DATE_FORMAT.parse(fechaCompetencia),
            participantes = participantes.toInt(),
            costoInscripcion = costoInscripcion.toDouble(),
        );

        if (CRUD.updateCompetencia(competencia))
            println("Se ha actualizado competencia correctamente");
        else
            println("Error al actualizar competencia");
    }

    fun competenciaDelete(): Unit {
        header("Eliminar competencia");
        print("Ingrese el codigo de la competencia que desea eliminar: ");
        val codigo = readLine()!!;

        val comp = CRUD.readCompetencia(codigo.toInt());
        if (comp.codigo == -1) {
            println("No se encontro el codigo de competencia");
            return;
        }

        if (CRUD.deleteCompetencia(codigo.toInt()))
            println("Se ha eliminado competencia correctamente");
        else
            println("Error al eliminar competencia");
    }
}