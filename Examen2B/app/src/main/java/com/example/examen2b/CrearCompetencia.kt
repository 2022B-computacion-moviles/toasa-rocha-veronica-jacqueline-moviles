package com.example.examen2b

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.example.examen2b.dto.FirebaseCompetidorDTO
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class CrearCompetencia : AppCompatActivity() {
    var posicionItemSelecionado = 0
    var idCompetidor: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_competencia)

        val competidor = intent.getParcelableExtra<FirebaseCompetidorDTO>("competidor")

        idCompetidor = competidor!!.id

        val idCompetidor = findViewById<TextView>(R.id.txt_id_persona_cp)
        idCompetidor.text = competidor.id.toString()


        val bt_ingresar_competencia = findViewById<Button>(R.id.btn_guardarp)

        bt_ingresar_competencia.setOnClickListener {
            crearCompetencia()
            abrirActividad(Competidor::class.java)
        }

        val btn_cancelar = findViewById<Button>(R.id.btn_cancelarp)
        btn_cancelar.setOnClickListener {
            abrirActividad(Competidor::class.java)
        }
    }

    fun crearCompetencia(){
        val nombre_producto = findViewById<TextView>(R.id.txt_nombre_producto)
        val precio = findViewById<TextView>(R.id.txt_precio)
        val disp = findViewById<TextView>(R.id.txt_disponibilidad)
        val fechaIng = findViewById<TextView>(R.id.txt_fec_ing)
        val participantes = findViewById<TextView>(R.id.txt_cantidad)
        val idCompetidor = findViewById<TextView>(R.id.txt_id_persona_cp)
        val latCcompetencia = findViewById<TextView>(R.id.txt_latitud)
        val longCompetencia = findViewById<TextView>(R.id.txt_longitud)

        val ingresoNombre = nombre_producto.text.toString()
        val ingresoPrecio = precio.text.toString().toDouble()
        val ingresoDis = disp.text.toString()
        val ingresoFI = fechaIng.text.toString()
        val ingresoCantidad = Integer.parseInt(participantes.text.toString())
        val latCompetenciaIngreso = latCcompetencia.text.toString().toDouble()
        val longCompetenciaIngreso = longCompetencia.text.toString().toDouble()
        val idCompetidorIngreso = this.idCompetidor.toString()

        val nuevaCompetencia = hashMapOf<String, Any>(
            "Nombre" to ingresoNombre,
            "Precio" to ingresoPrecio,
            "Disponibilidad" to ingresoDis,
            "Fecha de Ingreso" to ingresoFI,
            "Participantes" to ingresoCantidad,
            "latitud" to latCompetenciaIngreso,
            "longitud" to longCompetenciaIngreso,
            "idPersona" to idCompetidorIngreso,
        )
        val db = Firebase.firestore
        val referencia = db.collection("competencias")

        referencia
            .add(nuevaCompetencia)
            .addOnSuccessListener {
                nombre_producto.text = ""
                precio.text = ""
                disp.text = ""
                fechaIng.text = ""
                participantes.text = ""
                idCompetidor.text = ""
                latCcompetencia.text = ""
                longCompetencia.text = ""
            }
            .addOnFailureListener {
                Log.i("firestore-competencia", "no se pudo cargar los datos al firestore ")
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