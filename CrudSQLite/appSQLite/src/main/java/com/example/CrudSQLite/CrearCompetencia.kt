package com.example.Tarea01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText

class CrearCompetencia : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_competencia)

        BaseDatos.base = SqliteHelperUsuario (this)

        val IdCompetencia = findViewById<EditText>(R.id.etxtRUCProv)
        val nombreCompetencia= findViewById<EditText>(R.id.etxtNombreProv)
        val ciudadCompetencia = findViewById<EditText>(R.id.etxtCiudadProv)
        val correoCompetencia = findViewById<EditText>(R.id.etxtCorreoProv)
        val telefonoCompetencia = findViewById<EditText>(R.id.etxtTelefonoProv)

        val botonAceptar = findViewById<Button>(R.id.btn_actualizar_editar_Prov)
        botonAceptar.setOnClickListener{
            if (BaseDatos.base != null){
                val proveedor = BaseDatos.base!!.crearProveedor(
                    IdCompetencia.text.toString().toLong(),
                    nombreCompetencia.text.toString(),
                    ciudadCompetencia.text.toString(),
                    correoCompetencia.text.toString(),
                    telefonoCompetencia.text.toString()
                )
                if(proveedor==true){
                    Log.i("bd","proveedoringresado,  ${IdCompetencia.text.toString().toLong()} ${nombreCompetencia.text.toString()}")
                    abrirActividad(Competencia::class.java)
                }else{
                    Log.i("bd","error ingresar proveedor")
                }

            }

            IdCompetencia.setText("")
            nombreCompetencia.setText("")
            ciudadCompetencia.setText("")
            correoCompetencia.setText("")
            telefonoCompetencia.setText("")
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