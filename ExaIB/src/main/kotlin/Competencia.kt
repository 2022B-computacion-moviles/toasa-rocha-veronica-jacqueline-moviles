import java.util.*

//TODO: en esta clase definiremos los atributos para la entidad Competencia
class Competencia(
    val codigo: Int,
    val nombre: String,
    val fechaCompetencia: Date,
    val direccion: String,
    val participantes: Int,
    val costoInscripcion: Double,
) {
    //TODO: este constructor nos ayuda a desplegar una lista de strings
    // en donde se ubicaran los datos ingresados en cada atributo

    constructor(data: List<String>) : this(
        data[0].toInt(),
        data[1],
        DATE_FORMAT.parse(data[2]),
        data[3],
        data[4].toInt(),
        data[5].toDouble(),
    )

    //TODO: desplegar una lista de strings

    override fun toString(): String {
        return "$codigo,$nombre,$fechaCompetencia,$direccion,$participantes,$costoInscripcion";
    }
}
