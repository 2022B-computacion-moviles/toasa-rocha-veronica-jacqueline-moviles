package com.example.Tarea01

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import java.util.ArrayList

class SqliteHelperUsuario (
    context: Context
): SQLiteOpenHelper(
    context,"moviles",
    null,
    1
) {

    override fun onCreate(db: SQLiteDatabase?) {
        val scriptCrearTablaCompetencia=
            """
            CREATE TABLE PROV(
                Id_Competencia LONG PRIMARY KEY,
                nombre_Competencia VARCHAR(25),
                ciudad_Competencia VARCHAR(15),
                correo_Competencia VARCHAR(25),
                telefono_Competencia VARCHAR(10)
            );
  
            """.trimIndent()
        Log.i("bdd", "Creacion tabla competen")
        db?.execSQL(scriptCrearTablaCompetencia)



        val scriptCrearTablaCompetidor=
            """
            CREATE TABLE CLI(
                cedula_Competidor LONG PRIMARY KEY,
                id_Competen_Comp LONG,
                nombre_Competidor VARCHAR(50),
                apellido_Competidor varchar(50),
                correo_Competidor varchar(50),
                fechaNacimiento_Competidor varchar(50),
                telefono_Competidor varchar(50),
                foreign key(ruc_prov_cli) references PROV(ruc_prov)
            );
  
            """.trimIndent()
        Log.i("bdd", "Creacion tabla Pelicula")
        db?.execSQL(scriptCrearTablaCompetidor)


    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }

    fun crearProveedor(
        id: Long,
        nombre: String,
        ciudad: String,
        correo: String,
        telefono: String): Boolean {
        val conexionEscritura= writableDatabase
        val valoresAGuardar= ContentValues()

        valoresAGuardar.put("Id_Competen",id)
        valoresAGuardar.put("nombre_Competen", nombre)
        valoresAGuardar.put("ciudad_Competen", ciudad)
        valoresAGuardar.put("correo_Competen", correo)
        valoresAGuardar.put("telefono_Competen", telefono)

        val resultadoEscritura: Long= conexionEscritura.insert(
            "PROV",
            null,
            valoresAGuardar)
        conexionEscritura.close()
        if(resultadoEscritura.toInt()!=-1){
            return true
        }else{
            return false
        }
    }

    fun consultarCompetencia(): ArrayList<CompetenciaEntrenador> {
        val scriptConsultarUsuario = "SELECT * FROM PROV"
        val baseDatosLectura = readableDatabase
        val resultaConsultaLectura = baseDatosLectura.rawQuery(
            scriptConsultarUsuario, null)

        val existeUsuario = resultaConsultaLectura.moveToFirst()
        var competencia = arrayListOf<CompetenciaEntrenador>()

        if(existeUsuario){
            do{
                val id_competen = resultaConsultaLectura.getLong(0)
                if(id_competen!=null){
                    competencia.add(
                        CompetenciaEntrenador(
                            id_competen,
                            resultaConsultaLectura.getString(1),
                            resultaConsultaLectura.getString(2),
                            resultaConsultaLectura.getString(3),
                            resultaConsultaLectura.getString(4)
                        ))
                }
            }while(resultaConsultaLectura.moveToNext())
        }

        resultaConsultaLectura.close()
        baseDatosLectura.close()
        return competencia
    }

    fun actualizarCompetencia(
        nombre: String,
        ciudad: String,
        correo: String,
        telefono: String,
        ruc: Long): Boolean {
        val conexionEscritura = writableDatabase
        var valoresActualizar = ContentValues()
        valoresActualizar.put("nombre_competen", nombre)
        valoresActualizar.put("ciudad_competen",ciudad)
        valoresActualizar.put("correo_competen", correo)
        valoresActualizar.put("telefono_competen",telefono)

        val resultadoActualizacion = conexionEscritura.update(
            "PROV",
            valoresActualizar,
            "ruc_prov=?",
            arrayOf(ruc.toString())
        )
        conexionEscritura.close()
        return if (resultadoActualizacion == -1) false else true

    }


    fun eliminarCompetencia(IdCompetencia: Long): Boolean {
        val conexionEscritura = writableDatabase
        var resultadoEliminacion = conexionEscritura
            .delete(
                "COMPETEN",
                "id_competen=?",
                arrayOf(IdCompetencia.toString())
            )
        conexionEscritura.close()
        return if (resultadoEliminacion.toInt() == -1) false else true
    }

    fun consultarCompetenciaPorId(ruc: Long): CompetenciaEntrenador {
        val ruc_2 = ruc.toString()
        val scriptConsultarCompetencia = "SELECT * FROM PROV WHERE ruc_prov = ${ruc_2}"
//        val baseDatosLectura = this.readableDatabase
        val baseDatosLectura = readableDatabase
        val resultaConsultaLectura = baseDatosLectura.rawQuery(
            scriptConsultarCompetencia,
            null
        )
        Log.i("bd", "query ejecutado proveedor")
        val existeCompetencia = resultaConsultaLectura.moveToFirst()
        // val arregloUsuario = arrayListOf<EUsuarioBDD>()
        val usuarioEncontrado = CompetenciaEntrenador(0, "", "","","")
        if (existeCompetencia) {
            do {
                val ruc = resultaConsultaLectura.getLong(0) // Columna indice 0 -> ID
                val nombre = resultaConsultaLectura.getString(1) // Columna indice 1 -> NOMBRE
                val ciudad = resultaConsultaLectura.getString(2)
                val correo = resultaConsultaLectura.getString(3)
                val telefono = resultaConsultaLectura.getString(4)
                if(!ruc.equals(null)){
                    usuarioEncontrado.id_competen = ruc
                    usuarioEncontrado.nombre_competen = nombre
                    usuarioEncontrado.ciudad_competen = ciudad
                    usuarioEncontrado.correo_competen = correo
                    usuarioEncontrado.telefono_competen = telefono
                }
            } while (resultaConsultaLectura.moveToNext())

        }
        resultaConsultaLectura.close()
        baseDatosLectura.close()
        return usuarioEncontrado

    }

    fun consultarCompetidorPorCompetencia(ruc: Long): ArrayList<CompetidorEntrenador> {
        val scriptConsultarCompetidor = "SELECT * FROM CLI WHERE ruc_prov_cli = ${ruc}"
        val baseDatosLectura = readableDatabase
        val resultaConsultaLectura = baseDatosLectura.rawQuery(
            scriptConsultarCompetidor,
            null
        )
        val existeCompetidor = resultaConsultaLectura.moveToFirst()
        val arregloCompetidor = arrayListOf<CompetidorEntrenador>()
        if (existeCompetidor) {
            do {
                val cedula = resultaConsultaLectura.getLong(0) // Columna indice 0 -> ID
                val nombre = resultaConsultaLectura.getString(2) // Columna indice 1 -> NOMBRE
                val apellido = resultaConsultaLectura.getString(3)
                val correo = resultaConsultaLectura.getString(4)
                val fechaNacimiento = resultaConsultaLectura.getString(5)
                val telefono = resultaConsultaLectura.getString(6)
                if(cedula!=null){
                    arregloCompetidor.add(
                        CompetidorEntrenador(
                            cedula,
                            ruc,
                            nombre,
                            apellido,
                            correo,
                            fechaNacimiento,
                            telefono,
                        )
                    )
                    // arregloUsuario.add(usuarioEncontrado)
                }
            } while (resultaConsultaLectura.moveToNext())

        }
        resultaConsultaLectura.close()
        baseDatosLectura.close()
        return arregloCompetidor
    }

    fun crearCompetidor(
        cedula_cli:Long,
        nombre_cli: String,
        apellido_cli: String,
        correo_cli: String,
        fechaNacimiento_cli: String,
        telefono_cli: String,
        id_competen_comp: Long): Boolean
    {
        val conexionExcritura = writableDatabase
        val valoresAGuardar = ContentValues()
        valoresAGuardar.put("id_competen_comp", id_competen_comp)
        valoresAGuardar.put("cedula_cli", cedula_cli)
        valoresAGuardar.put("nombre_cli", nombre_cli)
        valoresAGuardar.put("apellido_cli", apellido_cli)
        valoresAGuardar.put("correo_cli", correo_cli)
        valoresAGuardar.put("telefono_cli", telefono_cli)
        valoresAGuardar.put("fechaNacimiento_cli", fechaNacimiento_cli)

        val resultadoEscritura: Long = conexionExcritura
            .insert(
                "CLI",
                null,
                valoresAGuardar
            )
        conexionExcritura.close()
        return if (resultadoEscritura.toInt() == -1) false else true
    }

    fun actualizarCliente(nombre :String,
                          apellido:String,
                          correo_cli: String,
                          fechaNacimiento:String,
                          telefono_cli: String,
                          cedula :Long):Boolean {

        val conexionEscritura = writableDatabase
        var valoresActualizar = ContentValues(5)
        valoresActualizar.put("nombre_comp", nombre)
        valoresActualizar.put("apellido_comp",apellido)
        valoresActualizar.put("correo_comp", correo_cli)
        valoresActualizar.put("fechaNacimiento_comp",fechaNacimiento)
        valoresActualizar.put("telefono_comp",telefono_cli)

        Log.i(
            "bd",
            "Competidor ingresado,  ${valoresActualizar}"
        )


        val resultadoActualizacion = conexionEscritura.update(
            "COMP",
            valoresActualizar,
            "cedula_competidor=?",
            arrayOf(cedula.toString())
        )
        conexionEscritura.close()
        return if (resultadoActualizacion == -1) false else true
    }

    fun eliminarCompetidor(cedulaCompetidor: Long): Boolean {
        val conexionEscritura = writableDatabase
        var resultadoEliminacion = conexionEscritura
            .delete(
                "CLI",
                "cedula_cli=?",
                arrayOf(cedulaCompetidor.toString())
            )
        conexionEscritura.close()

        return if (resultadoEliminacion.toInt() == -1) false else true
    }


}
