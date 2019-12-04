package com.example.listaasistenciakotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_menu_alumnos.*
import okhttp3.*
import java.io.IOException

class MenuAlumnos : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_alumnos)

        //recibir datos del intent login
        val objectIntent: Intent = intent

        val nombreAlumno = objectIntent.getStringExtra("nombre")
        val idGrupo = objectIntent.getIntExtra("idGrupo", -1)
        val idUsuario = objectIntent.getIntExtra("idUsuario", -1)


        recyclerView_clases.layoutManager = LinearLayoutManager(this)

        btnVerMisAsistencias.setOnClickListener {

            val intentTotalAsistenciaAlumno: Intent = Intent(this, TotalAsistenciasAlumno::class.java)
            intentTotalAsistenciaAlumno.putExtra("idUsuario", idUsuario)
            startActivity(intentTotalAsistenciaAlumno)
        }

        obtenerClasesAlumno(idGrupo,idUsuario)


    }

    fun obtenerClasesAlumno(idGrupo:Int,idUsuario:Int){

        val url = "https://proyectomovilapi.000webhostapp.com/controllers/verListasAsistenciaAlumno.php"

        //solicitud Http
        val client = OkHttpClient()

        val formBody = MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart("idGrupo",idGrupo.toString())
            .addFormDataPart("idUsuario",idUsuario.toString())
            .build()

        val request =  Request.Builder()
            .url(url)
            .post(formBody)
            .build()

        client.newCall(request).enqueue(object: Callback {

            override fun onResponse(call: Call, response: Response) {
                val body = response.body!!.string()
                println(body)


                val gson = GsonBuilder().create()

                val ListaClasesAlumno = gson.fromJson(body, ListaClasesAlumno::class.java)


                runOnUiThread {
                    recyclerView_clases.adapter = ClaseAlumnoAdapter(ListaClasesAlumno)
                }

            }

            override fun onFailure(call: Call, e: IOException) {
                println("Fallo al obtener clases pendientes")
            }
        })



    }
}

class ListaClasesAlumno(val clases:List<ClaseAlumno>)

class ClaseAlumno(val idListaAsistencia:Int,val dia:String, val nombreMateria:String, val hora:String)
