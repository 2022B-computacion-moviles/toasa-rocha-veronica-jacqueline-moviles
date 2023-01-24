package com.example.movcompvjtr

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.movcompvjtr.databinding.ActivityAcicloVidaBinding

class ACicloVida : AppCompatActivity() {
    var textoGlobal=""

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityAcicloVidaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAcicloVidaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_aciclo_vida)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        mostrarSnakbar("OnCreate")
    }

    override fun onStart(){
        super.onStart()
        mostrarSnakbar("onStart")
    }

    override fun onResume() {
        super.onResume()
        mostrarSnakbar("onResume")
    }

    override fun onRestart() {
        super.onRestart()
        mostrarSnakbar("onRestart")
    }

    override fun onPause() {
        super.onPause()
        mostrarSnakbar("onPause")
    }

    override fun onStop() {
        super.onStop()
        mostrarSnakbar("onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        mostrarSnakbar("onDestroy")
    }

    override fun onSaveInstanceState(outState: Bundle){
        outState.run {
            //GUARDAR LAS VARIABLES
            //PRIMITIVAS
            putString("TextoGuardado", textoGlobal)
            //putint ("numeroGuardado", numero)
        }
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val textoRecuperado:String? = savedInstanceState.getString("textoGuardado")
        //val textoRecuperado:Int? = savedInstanceState.getInt("numeroGuardado")
        if (textoRecuperado!= null){
            mostrarSnakbar(textoRecuperado)
            textoGlobal = textoRecuperado
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_aciclo_vida)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

    fun mostrarSnakbar(texto:String){
        textoGlobal += texto
        Snackbar.make(findViewById(R.id.cl_ciclo_vida),
            textoGlobal, Snackbar.LENGTH_LONG)
            .setAction("Action", null).show()
    }
}