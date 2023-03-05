package com.example.Tarea01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText

class CrearCompetidor : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_competidor)

        BaseDatos.base = SqliteHelperUsuario (this)

        val IdCompetencia = intent.getLongExtra("id",0)
        println("id"+IdCompetencia)
        val cedulaCompetidor = findViewById<EditText>(R.id.etxtCedulaCli)
        val nombreCompetidor = findViewById<EditText>(R.id.etxtNombreCli)
        val apellidoCompetidor = findViewById<EditText>(R.id.etxtApellidoCli)
        val correoCompetidor = findViewById<EditText>(R.id.etxtCorreoCli)
        val fechaNacimientoCompetidor = findViewById<EditText>(R.id.etxtFechaNacimientoCli)
        val telefonoCompetidor = findViewById<EditText>(R.id.etxtTelefonoCli)

        val botonAceptar = findViewById<Button>(R.id.btn_actualizar_editar_Cli)

        botonAceptar.setOnClickListener{
            if (BaseDatos.base != null){
                if((BaseDatos.base?.crearCompetidor (
                        cedulaCompetidor.text.toString().toLong(),
                        nombreCompetidor.text.toString(),
                        apellidoCompetidor.text.toString(),
                        correoCompetidor.text.toString(),
                        fechaNacimientoCompetidor.text.toString(),
                        telefonoCompetidor.text.toString(),
                        IdCompetencia
                    )) !=null){
                    Log.i("bd","Cliente ingresado,  ${cedulaCompetidor.text.toString().toInt()} ${nombreCompetidor.text.toString()} ${telefonoCompetidor.text.toString()}")
                }

            }

            cedulaCompetidor.setText("")
            nombreCompetidor.setText("")
            apellidoCompetidor.setText("")
            correoCompetidor.setText("")
            fechaNacimientoCompetidor.setText("")
            telefonoCompetidor.setText("")

            Log.i("bd",
                BaseDatos.base!!.consultarCompetidorPorCompetencia(IdCompetencia.toLong()).toString()
            )
            abrirActividad(MainActivity::class.java)
        }

    }
    fun abrirActividad(
        clase: Class<*>
    ){
        val intentExplicito = Intent(
            this,
            clase
        )
        // this.startActivity(intent)
        startActivity(intentExplicito)
    }
}