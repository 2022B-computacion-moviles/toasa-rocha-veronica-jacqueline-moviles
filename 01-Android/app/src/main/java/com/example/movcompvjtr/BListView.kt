package com.example.movcompvjtr

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView

class BListView : AppCompatActivity() {
    val arreglo: ArrayList<BEntrenador> = BBaseDadtosMemoria.arregloBEntrenador

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blist_view)

        val ListView = findViewById<ListView>(R.id.lv_list_view)
        val adaptador = ArrayAdapter(
            this,//contexto
            android.R.layout.simple_list_item_1 //como se va a ver (XML)
                    arreglo
        )
        ListView.adapter = adaptador

        adaptador.notifyDataSetChanged()
        val botonAnadirListView = findViewById<Button>(
            R.id.btn_anadir_list_view
        )

        botonAnadirListView
            .SetOnClickListener {
                anadirEntrenador(adaptador)
            }
    }
}