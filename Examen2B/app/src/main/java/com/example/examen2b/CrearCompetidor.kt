package com.example.examen2b

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class CrearCompetidor : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_competidor)

        val btn_guardar = findViewById<Button>(R.id.btn_guardar)

        btn_guardar.setOnClickListener{
            crearPersona()
        }

        val btn_cancelar = findViewById<Button>(R.id.btn_cancelar)
        btn_cancelar.setOnClickListener {
            abrirActividad(Competidor::class.java)
        }

    }

    fun crearPersona(){

        val nombre = findViewById<TextView>(R.id.txt_nombre_persona)
        val apellido = findViewById<TextView>(R.id.txt_apellido)
        val edad = findViewById<TextView>(R.id.txt_edad)
        val email = findViewById<TextView>(R.id.txt_email)
        val telefono = findViewById<TextView>(R.id.txt_telefono)

        val nombreIngreso = nombre.text.toString()
        val apellidoIngreso = apellido.text.toString()
        val edadIngreso = Integer.parseInt(edad.text.toString())
        val emailIngreso = email.text.toString()
        val telefonoIngreso = telefono.text.toString()

        val nuevaPersona = hashMapOf<String, Any>(
            "Nombre" to nombreIngreso,
            "Apellido" to apellidoIngreso,
            "Edad" to edadIngreso,
            "Correo Electrónico" to emailIngreso,
            "Teléfono" to telefonoIngreso
        )
        val db = Firebase.firestore
        val referencia = db.collection("personas")

        referencia
            .add(nuevaPersona)
            .addOnSuccessListener {
                nombre.text = ""
                apellido.text = ""
                edad.text = ""
                email.text = ""
                telefono.text = ""

                abrirActividad(Competidor::class.java)

            }
            .addOnFailureListener {
                Log.i("firestore-persona", "no se pudo cargar los datos al firestore ")
            }


    }

    fun abrirActividad(
        clase: Class<*>,
    ){
        val intentExplicito = Intent(
            this,
            clase
        )
        startActivity(intentExplicito)
    }

}