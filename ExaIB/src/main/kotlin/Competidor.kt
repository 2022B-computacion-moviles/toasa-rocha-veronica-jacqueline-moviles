import java.util.*

    //TODO: en esta clase definiremos los atributos para la entidad Competidor
class Competidor(
    val codigo: Int,
    val nombre: String,
    val apellido: String,
    val fechaNacimiento: Date,
    var lugarNac: String,
    var altura: Double,
    var competencia: ArrayList<Competencia>,
    val entrenador: Boolean
) {
    //TODO: este constructor nos ayuda a desplegar una lista de strings
    // en donde se ubicaran los datos ingresados en cada atributo
    constructor(data: List<String>) : this(
        data[0].toInt(),
        data[1],
        data[2],
        DATE_FORMAT.parse(data[3]),
        data[4],
        data[5].toDouble(),
        ArrayList(),
        data[7] == "true"
    ) {
        //TODO: aqui nos devolvera una lista de strings para poder añadirlo a un array
        data[6].split("-").forEach { codigoCompetencia ->
            if (codigoCompetencia != "") {
                val comp = CRUD.readCompetencia(codigoCompetencia.toInt());
                if (comp.codigo != -1) {
                    competencia.add(comp);
                }
            }
        }
    }

    //TODO: desplegar una lista de strings
    override fun toString(): String {
        var codigoCompetencia = "";
        competencia.forEach { materia ->
            codigoCompetencia += materia.codigo.toString() + "-"
        }
        return "$codigo,$nombre,$apellido,${DATE_FORMAT.format(this.fechaNacimiento)},$lugarNac,$altura,$codigoCompetencia,$entrenador"
    }
    fun customToString(): String {
        var nombreCompetencia = " ";
        competencia.forEach { competencia ->
            nombreCompetencia += competencia.nombre + ", ";
        }
        return "$codigo\t$nombre\t$apellido\t${DATE_FORMAT.format(this.fechaNacimiento)}\t$lugarNac\t$altura\t$entrenador\n\t$nombreCompetencia"
    }
}