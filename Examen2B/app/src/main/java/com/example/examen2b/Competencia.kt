package com.example.examen2b

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.*
import com.example.examen2b.dto.FirebaseCompetidorDTO
import com.example.examen2b.dto.FirebaseCompetenciaDTO
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class Competencia : AppCompatActivity() {
    var posiconElementoSeleccionado = 0
    val CODIGO_RESPUESTA_INTENT_EXPLICITO = 400
    var idCompetidor: String? = ""
    var CompetidorSeleccionado: FirebaseCompetenciaDTO? = null
    var adpatador: ArrayAdapter<FirebaseCompetenciaDTO>? = null
    var arregloCompetencias = arrayListOf<FirebaseCompetenciaDTO>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_competencia)

        val Competidor = intent.getParcelableExtra<FirebaseCompetidorDTO>("competidor")
        idCompetidor = Competidor!!.id
        val idenCompetidor = findViewById<TextView>(R.id.txt_nombre_competidor_competencia)
        idenCompetidor.text = Competidor.nombre


        cargarCompetencia(idCompetidor!!)
        adpatador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            arregloCompetencias

        )

        val listViewCompetencias = findViewById<ListView>(R.id.list_view_productos)
        listViewCompetencias.adapter = adpatador
        listViewCompetencias.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long)
            {
                CompetidorSeleccionado = arregloCompetencias[position]
            }

            override fun onNothingSelected(
                p0: AdapterView<*>?)
            {
                Log.i("firestore-competidor", "No ha seleccionado ningun item")
            }
        }


        registerForContextMenu(listViewCompetencias)

        val btnnuevoCompetencia = findViewById<Button>(R.id.btn_producto_nuevo)
        btnnuevoCompetencia.setOnClickListener {
            abrirActiviad(CrearCompetencia::class.java, Competidor!!)
        }

    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)

        val inflater = menuInflater
        inflater.inflate(R.menu.menucompetencia,menu)

        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position

        posiconElementoSeleccionado = id

        Log.i("list-view","onCreate ${id}")

    }


    override fun onContextItemSelected(item: MenuItem): Boolean {
        var selcompetencia = arregloCompetencias[posiconElementoSeleccionado]
        return when(item?.itemId){
            // Editar
            R.id.editar_Competencia -> {
                Log.i("list-view", "Editar ${selcompetencia} ")
                abrirActividadCompetencia(EditarCompetencia::class.java, selcompetencia)
                return true
            }
            //Eliinar
            R.id.eliminar_Competencia -> {
                Log.i("list-view", "Eliminar ${selcompetencia} ")
                val db = FirebaseFirestore.getInstance()
                db.collection("competencias")
                    .document(selcompetencia.id!!)
                    .delete()
                    .addOnSuccessListener {
                        adpatador?.remove(adpatador!!.getItem(posiconElementoSeleccionado))
                        adpatador?.notifyDataSetChanged()
                    }

                return true
            }
            R.id.ver_mapa -> {
                abrirActividadCompetencia(Geo::class.java, selcompetencia)
                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    fun abrirActividadCompetencia(
        clase: Class<*>,
        producto: FirebaseCompetenciaDTO
    ){
        val intentExplicito = Intent(
            this,
            clase
        )
        intentExplicito.putExtra("competencia", producto)
        startActivityForResult(intentExplicito, CODIGO_RESPUESTA_INTENT_EXPLICITO)
    }

    fun abrirActiviad(
        clase: Class<*>,
        persona: FirebaseCompetidorDTO
    ){
        val intentExplicito = Intent(
            this,
            clase
        )
        intentExplicito.putExtra("competidor", persona)
        startActivityForResult(intentExplicito, CODIGO_RESPUESTA_INTENT_EXPLICITO)
    }


    fun cargarCompetencia(idCompetidor: String){
        val db = Firebase.firestore
        val referencia = db.collection("competencia")

        referencia
            .whereEqualTo("idCompetidor", idCompetidor)
            .get()
            .addOnSuccessListener {
                for (document in it){

                    var Competencia = document.toObject(FirebaseCompetenciaDTO::class.java)
                    Competencia!!.id = document.id
                    Competencia!!.nombre_Competencia = document.get("Nombre").toString()
                    Competencia!!.precio = document.getDouble("Precio")
                    Competencia!!.disponibilidad = document.get("Disponibilidad").toString()
                    Competencia!!.fecha = document.get("Fecha de Ingreso").toString()
                    Competencia!!.participantes = document.getLong("Participantes")!!.toInt()
                    Competencia!!.latitud = document.getDouble("latitud")
                    Competencia!!.longitud = document.getDouble("longitud")
                    Competencia!!.idCompetidor = document.get("idCompetidor").toString()

                    arregloCompetencias.add(Competencia)
                    adpatador?.notifyDataSetChanged()

                }
            }
            .addOnFailureListener {
                Log.i("NO INGRESO AL ADD ON SUCCES FILE LISTENER  ", "${idCompetidor}")
            }

    }
}