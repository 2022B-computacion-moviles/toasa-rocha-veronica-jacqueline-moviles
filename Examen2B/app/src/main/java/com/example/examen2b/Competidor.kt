package com.example.examen2b

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import com.example.examen2b.dto.FirebaseCompetidorDTO
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class Competidor : AppCompatActivity() {
    var CompetidorSeleccioanda: FirebaseCompetidorDTO? = null
    val CODIGO_RESPUESTA_INTENT_EXPLICITO = 400
    var adapter: ArrayAdapter<FirebaseCompetidorDTO>? = null
    var arregloCompetidores = arrayListOf<FirebaseCompetidorDTO>()
    var  posiconElementoSeleccionado: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_competidor)

        uploadCompetidor()
        adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            arregloCompetidores
        )

        val listPersonas = findViewById<ListView>(R.id.list_view_personas)
        listPersonas.adapter = adapter
        listPersonas.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long)
            {
                CompetidorSeleccioanda = arregloCompetidores[position]
            }

            override fun onNothingSelected(
                p0: AdapterView<*>?)
            {
                Log.i("firestore-persona", "No ha seleccionado ningun item")
            }
        }

        val btnCrearPersona = findViewById<Button>(R.id.btn_crear)
        btnCrearPersona.setOnClickListener {
            abrirActiviad(CrearCompetidor::class.java)
        }

        registerForContextMenu(listPersonas)
    }

    fun uploadCompetidor(){
        val db = Firebase.firestore
        val referencia = db.collection("competidor")

        referencia
            .get()
            .addOnSuccessListener {
                for (document in it){
                    var persona = document.toObject(FirebaseCompetidorDTO::class.java)
                    persona!!.id = document.id
                    persona!!.nombre = document.get("Nombre").toString()
                    persona!!.apellido = document.get("Apellido").toString()
                    persona!!.edad = document.getLong("Edad")!!.toInt()
                    persona!!.email = document.get("Correo Electrónico").toString()
                    persona!!.telefono = document.get("Teléfono").toString()

                    arregloCompetidores.add(persona)
                    adapter?.notifyDataSetChanged()
                }
            }
            .addOnFailureListener {
                Log.i("firebase-firestore", "no se cargaron los datos al listView")
            }
    }

    fun abrirActiviad(
        clase: Class<*>
    ){
        val intentExplicito = Intent(
            this,
            clase
        )
        startActivity(intentExplicito)
    }


    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)

        val inflater = menuInflater
        inflater.inflate(R.menu.menu,menu)

        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position

        posiconElementoSeleccionado = id


    }


    override fun onContextItemSelected(item: MenuItem): Boolean {
        var idElemento = arregloCompetidores[posiconElementoSeleccionado]
        return when(item?.itemId){
            R.id.men_editar -> {
                abrirActividadporId(EditarCompetidor::class.java, idElemento)
                return true
            }

            R.id.men_eliminar -> {
                Log.i("list-view", "Eliminar ${idElemento.id}")
                val db = FirebaseFirestore.getInstance()
                db.collection("personas")
                    .document(idElemento.id!!)
                    .delete()
                    .addOnSuccessListener {
                        abrirActiviad(Competidor::class.java)
                    }
                return true
            }



            R.id.men_ver_productos -> {
                abrirActividadporId(Competencia::class.java, idElemento)
                return true
            }

            else -> super.onContextItemSelected(item)
        }
    }



    fun abrirActividadporId(
        clase: Class<*>,
        persona: FirebaseCompetidorDTO
    ){
        val intentExplicito = Intent(
            this,
            clase
        )
        intentExplicito.putExtra("competidor", persona)
        startActivityForResult(intentExplicito,CODIGO_RESPUESTA_INTENT_EXPLICITO)
    }
}