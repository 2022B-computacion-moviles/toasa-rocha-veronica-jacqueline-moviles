package com.example.Tarea01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText

class ActualizarCompetencia : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_competencia)

        BaseDatos.base= SqliteHelperUsuario(this)

        val prov = intent.getParcelableExtra<CompetenciaEntrenador>("proveedor")

        Log.i("bdd","Editar ${prov}")
        val IdCompetencia = findViewById<EditText>(R.id.etxtRUCProv)
        val nombreCompetencia= findViewById<EditText>(R.id.etxtNombreProv)
        val ciudadCompetencia = findViewById<EditText>(R.id.etxtCiudadProv)
        val correoCompetencia = findViewById<EditText>(R.id.etxtCorreoProv)
        val telefonoCompetencia = findViewById<EditText>(R.id.etxtTelefonoProv)
        val botonAceptar = findViewById<Button>(R.id.btn_actualizar_editar_Prov)


        IdCompetencia.setText(prov!!.id_competen.toString())
        nombreCompetencia.setText(prov?.nombre_competen)
        ciudadCompetencia.setText(prov?.ciudad_competen)
        correoCompetencia.setText(prov?.correo_competen)
        telefonoCompetencia.setText(prov?.telefono_competen)


        botonAceptar.setOnClickListener{
            if (BaseDatos.base != null){
                if((BaseDatos.base?.actualizarCompetencia (
                        nombreCompetencia.text.toString(),
                        ciudadCompetencia.text.toString(),
                        correoCompetencia.text.toString(),
                        telefonoCompetencia.text.toString(),
                        prov.id_competen!!.toLong()
                    )) !=null){
                    Log.i("bd","proveedoringresado,  ${prov.toString()} ${nombreCompetencia.text.toString()}")
                }

            }

            IdCompetencia.setText("")
            nombreCompetencia.setText("")
            ciudadCompetencia.setText("")
            correoCompetencia.setText("")
            telefonoCompetencia.setText("")

            Log.i("bd",BaseDatos.base.toString())
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




