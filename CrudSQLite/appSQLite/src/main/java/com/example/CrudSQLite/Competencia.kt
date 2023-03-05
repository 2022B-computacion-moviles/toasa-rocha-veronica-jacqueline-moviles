package com.example.Tarea01

import android.annotation.SuppressLint
import android.content.DialogInterface
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
import androidx.appcompat.app.AlertDialog

class Competencia : AppCompatActivity() {
    companion object{
        var id_Competencia: Long = 0
    }

    var posicionCompetenSeleccionado = 0
    var adapter: ArrayAdapter<CompetenciaEntrenador>? = null
    val CODIGO_RESPUESTA_INTENT_EXPLICITO = 401

    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_competencia)

        BaseDatos.base= SqliteHelperUsuario(this)
        val listViewCompetencia = findViewById<ListView>(R.id.ltv_competencia)
        if(BaseDatos.base != null) {
            val competencia = BaseDatos.base!!.consultarCompetencia()
            adapter = ArrayAdapter(
                this,
                android.R.layout.simple_list_item_1,
                competencia
            )

            listViewCompetencia.adapter = adapter
        }


        val  botonCrearCompetencia = findViewById<Button>(R.id.btn_crearProv)
             botonCrearCompetencia.setOnClickListener{
            abrirActividad(CrearCompetencia::class.java)
        }
        registerForContextMenu(listViewCompetencia)
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
    override fun onCreateContextMenu(

        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_competen, menu)

        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position
        posicionCompetenSeleccionado = id
        id_Competencia = adapter!!.getItem(posicionCompetenSeleccionado)?.id_competen!!.toLong()
        Log.i("list-view", "List view ${posicionCompetenSeleccionado}")
        Log.i("list-view", "Entrenador ${
            BaseDatos.base!!.consultarCompetenciaPorId(id_Competencia).id_competen} " +
                "${BaseDatos.base!!.consultarCompetenciaPorId(id_Competencia).nombre_competen} " )
    }
    override fun onContextItemSelected(item: MenuItem): Boolean {
        var competencia = adapter!!.getItem(posicionCompetenSeleccionado)

        return when(item?.itemId){
            // Editar

            R.id.mi_editar_competen -> {
                if( BaseDatos.base != null){
                    abrirActividadParametros(
                        ActualizarCompetencia::class.java,
                        competencia
                    )
                }
                Log.i("list-view","Editar ")
                Log.i("list-view","Entrenador ${BaseDatos.base!!.consultarCompetenciaPorId(
                    id_Competencia)}")
                return true
            }

            // Eliminar
            R.id.mi_eliminar_competen -> {
                if( BaseDatos.base != null){
                    val builder = AlertDialog.Builder(this)
                    builder.setTitle("Eliminar")
                    builder.setMessage("¿Desea eliminar la competencia seleccionada?")

                    builder.setPositiveButton(
                        "Si",
                        DialogInterface.OnClickListener{
                                dialog, which ->
                            BaseDatos.base!!.eliminarCompetencia(id_Competencia)
                            adapter?.remove(adapter!!.getItem(posicionCompetenSeleccionado));
                        }
                    )
                    builder.setNegativeButton("No", null)
                    val eliminar = builder.create()
                    eliminar.show()
                   Log.i("list-view","Eliminar ")
                    Log.i("list-view","Entrenador ${BaseDatos.base!!.consultarCompetenciaPorId(
                        id_Competencia)}")
                }

                return true
            }
            R.id.mi_ver_competen -> {
                if (competencia != null) {
                    abrirActividadParametros(Competidor::class.java,competencia)
                }
                return true
            }
                else -> super.onContextItemSelected(item)

        }

    }


    fun abrirActividadParametros(
            clase: Class<*>,
            consultar: CompetenciaEntrenador?
        ){
            val intentExplicito = Intent(
                this,
                clase
            )
            intentExplicito.putExtra("competencia",consultar)
            startActivity(intentExplicito)
        }

    }
