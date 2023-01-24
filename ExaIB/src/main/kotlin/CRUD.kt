import java.io.File
import java.util.*
import kotlin.collections.ArrayList

class CRUD {
    companion object {
        //TODO: aqui se creara el archivo donde se guardara los datos ingresados
        fun createFile(name: String): Unit {
            val file = File("$name.csv");
            if (!file.exists()) {
                file.createNewFile();
            }
        }


        fun parseCSV(data: String): List<List<String>> {
            val lines = data.split("\n");
            val result = mutableListOf<List<String>>();
            for (line in lines) {
                if (line.isNotEmpty()) {
                    result.add(line.split(","));
                }
            }
            return result;
        }

        fun toCSV(E: MutableList<Competidor>? = null, M: MutableList<Competencia>? = null): String {
            val data = E ?: M;
            var result = "";
            for (line in data!!) {
                result += line.toString() + "\n";
            }
            return result;
        }


        fun readFile(name: String): String {
            createFile(name);
            val file = File("$name.csv");
            return file.readText();
        }

        fun writeFile(name: String, data: String): Unit {
            createFile(name);
            val file = File("$name.csv");
            file.writeText(data);
        }

        fun createCompetidor(competidor: Competidor): Boolean {
            val data = readFile("competidores");
            val competidores = parseCSV(data).map { Competidor(it) }.toMutableList();
            competidores.add(competidor);
            writeFile("competidores", toCSV(E = competidores));
            return true;
        }

        fun readCompetidor(id: Int): Competidor {
            val data = readFile("competidores");
            val competidores = parseCSV(data).map { Competidor(it) }.toMutableList();
            val R = competidores.find { competidor -> competidor.codigo == id };
            if (R != null) {
                return R;
            }
            return Competidor(-1, "", "", Date(), "", 0.0, ArrayList(), false);
        }

        fun readCompetidor(): List<Competidor> {
            val data = readFile("competidores");
            val competidores = parseCSV(data).map { Competidor(it) }.toMutableList();
            return competidores;
        }

        fun updateCompetidor(competidores: Competidor): Boolean {
            val data = readFile("competidores");
            val competidor = parseCSV(data).map { Competidor(it) }.toMutableList();
            val index = competidor.indexOfFirst { it.codigo == competidores.codigo };
            competidor[index] = competidores;
            writeFile("competidores", toCSV(E = competidor));
            return true;
        }

        fun deleteCompetidor(id: Int): Boolean {
            val data = readFile("competidores");
            val competidores = parseCSV(data).map { Competidor(it) }.toMutableList();
            val index = competidores.indexOfFirst { it.codigo == id };
            competidores.removeAt(index);
            writeFile("competidores", toCSV(E = competidores));
            return true;
        }

        //TODO: Competencia
        fun createCompetencia(competencia: Competencia): Boolean {
            val data = readFile("competencias");
            val competencias = parseCSV(data).map { Competencia(it) }.toMutableList();
            competencias.add(competencia);
            writeFile("competencias", toCSV(M = competencias));
            return true;
        }

        fun readCompetencia(id: Int): Competencia {
            val data = readFile("competencias");
            val competencias = parseCSV(data).map { Competencia(it) }.toMutableList();
            val R = competencias.find { competencia -> competencia.codigo == id };
            if (R != null) {
                return R;
            }
            return Competencia(-1, "", Date(), "", 0, 0.0);
        }
        fun readCompetencia(): List<Competencia> {
            val data = readFile("competencias");
            val competencias = parseCSV(data).map { Competencia(it) }.toMutableList();
            return competencias;
        }
        fun updateCompetencia(competencia: Competencia): Boolean {
            val data = readFile("competencias");
            val competencias = parseCSV(data).map { Competencia(it) }.toMutableList();
            val index = competencias.indexOfFirst { it.codigo == competencia.codigo };
            competencias[index] = competencia;
            writeFile("competencias", toCSV(M = competencias));
            return true;
        }

        fun deleteCompetencia(id: Int): Boolean {
            val data = readFile("competencias");
            val competencias = parseCSV(data).map { Competencia(it) }.toMutableList();
            val index = competencias.indexOfFirst { it.codigo == id };
            competencias.removeAt(index);
            writeFile("competencias", toCSV(M = competencias));
            return true;
        }
    }
}