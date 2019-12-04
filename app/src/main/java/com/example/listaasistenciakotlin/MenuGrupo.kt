package com.example.listaasistenciakotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_menu_grupo.*

class MenuGrupo : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_grupo)

        //recibir datos del intent  verGrupos
        val objectIntent: Intent = intent

        val idClase = objectIntent.getIntExtra("idClase",-1)
        val nombreGrupo = objectIntent.getStringExtra("nombreGrupo")

        println(nombreGrupo)


        btnGenerarLista.setOnClickListener {
            val intentGenerarLista: Intent = Intent(this, GenerarLista::class.java)
            intentGenerarLista.putExtra("idClase",idClase)
            intentGenerarLista.putExtra("nombreGrupo",nombreGrupo)
            startActivity(intentGenerarLista)
        }

        btnVerLista.setOnClickListener {

            /*val fechaLista = txtFecha.text.toString()
            val intentVerLista: Intent = Intent(this, ListaAdmin::class.java)
            intentVerLista.putExtra("fecha",fechaLista)
            intentVerLista.putExtra("nombreGrupo",nombreGrupo)

            startActivity(intentVerLista)*/
            val intentTotalListasGrupo: Intent = Intent(this, TotalListasGrupo::class.java)
            intentTotalListasGrupo.putExtra("nombreGrupo",nombreGrupo)

            startActivity(intentTotalListasGrupo)

        }

        btnVerGrupo.setOnClickListener {

            val intentVerGrupo: Intent = Intent(this, ListaGrupo::class.java)
            intentVerGrupo.putExtra("nombreGrupo",nombreGrupo)

            startActivity(intentVerGrupo)
        }

    }
}
