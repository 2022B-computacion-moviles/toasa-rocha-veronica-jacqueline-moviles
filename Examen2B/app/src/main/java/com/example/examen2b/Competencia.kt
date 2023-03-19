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
    var CompetidorSeleccionada: FirebaseCompetenciaDTO? = null
    var adpatador: ArrayAdapter<FirebaseCompetenciaDTO>? = null
    var arregloCompetencias = arrayListOf<FirebaseCompetenciaDTO>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_competencia)

        val Competidor = intent.getParcelableExtra<FirebaseCompetidorDTO>("persona")
        idCompetidor = Competidor!!.id
        val idenPersona = findViewById<TextView>(R.id.txt_nombre_competidor_competencia)
        idenPersona.text = Competidor.nombre


        cargarProducto(idCompetidor!!)
        adpatador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            arregloCompetencias

        )

        val listViewProductos = findViewById<ListView>(R.id.list_view_productos)
        listViewProductos.adapter = adpatador
        listViewProductos.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long)
            {
                CompetidorSeleccionada = arregloCompetencias[position]
            }

            override fun onNothingSelected(
                p0: AdapterView<*>?)
            {
                Log.i("firestore-persona", "No ha seleccionado ningun item")
            }
        }


        registerForContextMenu(listViewProductos)

        val btnnuevoProducto = findViewById<Button>(R.id.btn_producto_nuevo)
        btnnuevoProducto.setOnClickListener {
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
        inflater.inflate(R.menu.menuproducto,menu)

        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position

        posiconElementoSeleccionado = id

        Log.i("list-view","onCreate ${id}")

    }


    override fun onContextItemSelected(item: MenuItem): Boolean {
        var selproducto = arregloCompetencias[posiconElementoSeleccionado]
        return when(item?.itemId){
            // Editar
            R.id.editar_Competencia -> {
                Log.i("list-view", "Editar ${selproducto} ")
                abrirActividadProductos(EditarCompetencia::class.java, selproducto)
                return true
            }
            //Eliinar
            R.id.eliminar_Competencia -> {
                Log.i("list-view", "Eliminar ${selproducto} ")
                val db = FirebaseFirestore.getInstance()
                db.collection("competencias")
                    .document(selproducto.id!!)
                    .delete()
                    .addOnSuccessListener {
                        adpatador?.remove(adpatador!!.getItem(posiconElementoSeleccionado))
                        adpatador?.notifyDataSetChanged()
                    }

                return true
            }
            R.id.ver_mapa -> {
                abrirActividadProductos(Geo::class.java, selproducto)
                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    fun abrirActividadProductos(
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


    fun cargarProducto(idCompetidor: String){
        val db = Firebase.firestore
        val referencia = db.collection("competencia")

        referencia
            .whereEqualTo("idPersona", idCompetidor)
            .get()
            .addOnSuccessListener {
                for (document in it){

                    var producto = document.toObject(FirebaseCompetenciaDTO::class.java)
                    producto!!.id = document.id
                    producto!!.nombre_Competencia = document.get("Nombre").toString()
                    producto!!.precio = document.getDouble("Precio")
                    producto!!.disponibilidad = document.get("Disponibilidad").toString()
                    producto!!.fecha = document.get("Fecha de Ingreso").toString()
                    producto!!.cantidad = document.getLong("Cantidad")!!.toInt()
                    producto!!.latitud = document.getDouble("latitud")
                    producto!!.longitud = document.getDouble("longitud")
                    producto!!.idCompetidor = document.get("idCompetidor").toString()

                    arregloCompetencias.add(producto)
                    adpatador?.notifyDataSetChanged()

                }
            }
            .addOnFailureListener {
                Log.i("NO INGRESO AL ADD ON SUCCES FILE LISTENER  ", "${idCompetidor}")
            }

    }
}