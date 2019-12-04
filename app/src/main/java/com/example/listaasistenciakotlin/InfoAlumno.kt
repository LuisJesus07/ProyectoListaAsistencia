package com.example.listaasistenciakotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_info_alumno.*
import okhttp3.*
import java.io.IOException

class InfoAlumno : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info_alumno)

        //recibir datos del intent
        val objectIntent: Intent = intent

        val idUsuario = objectIntent.getIntExtra("idUsuario",-1)
        val nombre = objectIntent.getStringExtra("nombre")
        val apellidos = objectIntent.getStringExtra("apellidos")
        val numControl = objectIntent.getStringExtra("numControl")


        txtNombreInfoAlumno.text = nombre
        txtApellidosInfoAlumno.text = apellidos
        txtMatriculaInfoAlumno.text = numControl

        btnVerAsistencias.setOnClickListener {

            val intentTotalAsistenciaAlumno: Intent = Intent(this, TotalAsistenciasAlumno::class.java)
            intentTotalAsistenciaAlumno.putExtra("idUsuario", idUsuario)
            startActivity(intentTotalAsistenciaAlumno)

        }


    }


}

