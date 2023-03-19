package com.example.examen2b

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.examen2b.dto.FirebaseCompetidorDTO
import com.google.firebase.firestore.FirebaseFirestore

class EditarCompetidor : AppCompatActivity() {
    var idCompetidor: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_competidor)

        val competidor = intent.getParcelableExtra<FirebaseCompetidorDTO>("competidor")
        idCompetidor = competidor!!.id

        val txtID = findViewById<TextView>(R.id.txt_id_persona_editar)
        val txtNombre = findViewById<TextView>(R.id.txt_disp_editar)
        val txtApellido = findViewById<TextView>(R.id.txt_apellido_editar)
        val txtEdad = findViewById<TextView>(R.id.txt_edad_editar)
        val txtEmail = findViewById<TextView>(R.id.txt_email_editar)
        val txtTelefono = findViewById<TextView>(R.id.txt_telefono_editar)

        txtID.text = competidor!!.id
        txtNombre.text = competidor!!.nombre
        txtApellido.text = competidor!!.apellido
        txtEdad.text = competidor!!.edad.toString()
        txtEmail.text = competidor!!.email
        txtTelefono.text = competidor!!.telefono

        val btn_editar_empresa = findViewById<Button>(R.id.btn_actualizar_persona)
        btn_editar_empresa.setOnClickListener {

            val actualizarUsuario = hashMapOf<String, Any>(
                "Nombre" to txtNombre.text.toString(),
                "Apellido" to txtApellido.text.toString(),
                "Edad" to txtEdad.text.toString().toInt(),
                "Correo Electrónico" to txtEmail.text.toString(),
                "Teléfono" to txtTelefono.text.toString()
            )

            val db = FirebaseFirestore.getInstance()
            db.collection("personas")
                .document(idCompetidor!!)
                .set(
                    actualizarUsuario
                )
                .addOnSuccessListener {
                    txtNombre.text = ""
                    txtApellido.text = ""
                    txtEdad.text = ""
                    txtEmail.text = ""
                    txtTelefono.text = ""
                }

            abrirActividad(Competidor::class.java)
        }


        val btn_cancelar = findViewById<Button>(R.id.btn_cancelare)
        btn_cancelar.setOnClickListener {
            abrirActividad(Competidor::class.java)
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