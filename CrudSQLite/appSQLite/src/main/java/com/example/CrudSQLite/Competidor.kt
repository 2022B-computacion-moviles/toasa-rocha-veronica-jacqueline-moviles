package com.example.Tarea01

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.*
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog

class Competidor : AppCompatActivity() {
    var adapter: ArrayAdapter<CompetidorEntrenador>? = null
    var posicionCompSeleccionado = 0

    companion object{
        var cedulaCompetidor:Long = 0
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_competidor)

        val competencia= intent.getParcelableExtra<CompetenciaEntrenador>("competencia")

        val ruc = Competencia.id_Competencia
        val nombre = competencia!!.nombre_competen
        var nombre_competencia: TextView = findViewById<TextView>(R.id.txvTituloCli)
        nombre_competencia.setText("competencia: ${nombre}")

        Log.i("bdd","ruc:  ${ruc}")

        BaseDatos.base= SqliteHelperUsuario(this)

        if(BaseDatos.base != null) {
            val competidor = BaseDatos.base!!.consultarCompetidorPorCompetencia(ruc)
            adapter = ArrayAdapter(
                this,
                android.R.layout.simple_list_item_1,
                competidor
            )
            val listViewejemplo = findViewById<ListView>(R.id.ltv_competidor)
            listViewejemplo.adapter = adapter
            registerForContextMenu(listViewejemplo)
        }

        val botoncrearCompetidor = findViewById<Button>(R.id.btn_crearCli)
        botoncrearCompetidor.setOnClickListener{
            abrirActividadParametro(CrearCompetidor::class.java,ruc)
        }
    }

    private fun abrirActividadParametro(clase: Class<*>, ruc: Long) {
        val intentExplicito = Intent(
            this,
            clase
        )
        intentExplicito.putExtra("ruc",ruc)
        startActivity(intentExplicito)
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
        inflater.inflate(R.menu.menu_comp, menu)

        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position
        posicionCompSeleccionado = id
        cedulaCompetidor = adapter!!.getItem(posicionCompSeleccionado)?.cedula_comp!!.toLong()
        Log.i("list-view", "List view ${posicionCompSeleccionado}")
    }
    override fun onContextItemSelected(item: MenuItem): Boolean {
        var competidor = adapter!!.getItem(posicionCompSeleccionado)

        return when(item?.itemId){
            // Editar

            R.id.mi_editar_comp -> {
                if( BaseDatos.base != null){
                    abrirActividadParametros(
                        ActualizarCompetidor::class.java,
                        competidor
                    )
                }
                Log.i("list-view","Editar ")

                return true
            }

            // Eliminar
            R.id.mi_eliminar_comp -> {
                if( BaseDatos.base != null){
                    val builder = AlertDialog.Builder(this)
                    builder.setTitle("Eliminar")
                    builder.setMessage("¿Desea eliminar el competidor seleccionado?")

                    builder.setPositiveButton(
                        "Si",
                        DialogInterface.OnClickListener{
                                dialog, which ->
                            BaseDatos.base!!.eliminarCompetidor(cedulaCompetidor)
                            adapter?.remove(adapter!!.getItem(posicionCompSeleccionado));
                        }
                    )
                    builder.setNegativeButton("No", null)
                    val eliminar = builder.create()
                    //abrirActividad(MainActivity::class.java)
                    eliminar.show()
                    Log.i("list-view","Eliminar ")

                }

                return true
            }

            else -> super.onContextItemSelected(item)

        }

    }
    fun abrirActividadParametros(
        clase: Class<*>,
        consultar: CompetidorEntrenador?
    ){
        val intentExplicito = Intent(
            this,
            clase
        )
        intentExplicito.putExtra("competidor",consultar)
        startActivity(intentExplicito)
    }

}
