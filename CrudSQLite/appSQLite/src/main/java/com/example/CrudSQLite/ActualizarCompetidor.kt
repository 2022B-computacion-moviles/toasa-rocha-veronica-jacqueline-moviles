package com.example.Tarea01

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class ActualizarCompetidor : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_competidor)

        BaseDatos.base = SqliteHelperUsuario(this)

        val cli = intent.getParcelableExtra<CompetidorEntrenador>("cliente")

        Log.i("bdd", "Editar ${cli}")

        val cedulaCompetidor = findViewById<EditText>(R.id.etxtCedulaCli)
        val nombreCompetidor = findViewById<EditText>(R.id.etxtNombreCli)
        val apellidoCompetidor = findViewById<EditText>(R.id.etxtApellidoCli)
        val correoCompetidor = findViewById<EditText>(R.id.etxtCorreoCli)
        val fechaNacimientoCompetidor = findViewById<EditText>(R.id.etxtFechaNacimientoCli)
        val telefonoCompetidor = findViewById<EditText>(R.id.etxtTelefonoCli)

        val botonAceptar = findViewById<Button>(R.id.btn_actualizar_editar_Cli)

        cedulaCompetidor.setText(cli!!.cedula_comp.toString())
        nombreCompetidor.setText(cli?.nombre_comp)
        apellidoCompetidor.setText(cli?.apellido_comp)
        correoCompetidor.setText(cli?.correo_comp)
        fechaNacimientoCompetidor.setText(cli?.fechaNacimiento_comp)
        telefonoCompetidor.setText(cli?.telefono_comp)

        botonAceptar.setOnClickListener {
            if (BaseDatos.base != null) {
                if ((BaseDatos.base?.actualizarCliente(
                        nombreCompetidor.text.toString(),
                        apellidoCompetidor.text.toString(),
                        correoCompetidor.text.toString(),
                        fechaNacimientoCompetidor.text.toString(),
                        telefonoCompetidor.text.toString(),
                        cli?.cedula_comp!!.toLong()
                    )) != null
                ) {
                    Log.i(
                        "bd",
                        "Cliente ingresado,  ${cli.toString()} ${nombreCompetidor.text} ${telefonoCompetidor.text.toString()}"
                    )
                }

            }

            cedulaCompetidor.setText("")
            nombreCompetidor.setText("")
            apellidoCompetidor.setText("")
            correoCompetidor.setText("")
            fechaNacimientoCompetidor.setText("")
            telefonoCompetidor.setText("")

            Log.i("bd", BaseDatos.base.toString())
            abrirActividad(MainActivity::class.java)
        }


    }

    fun abrirActividad(
        clase: Class<*>
    ) {
        val intentExplicito = Intent(
            this,
            clase
        )
        // this.startActivity(intent)
        startActivity(intentExplicito)
    }
}