package com.example.listaasistenciakotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_total_asistencias_alumno.*
import okhttp3.*
import java.io.IOException

class TotalAsistenciasAlumno : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_total_asistencias_alumno)

        val objectInent: Intent = intent

        val idUsuario = objectInent.getIntExtra("idUsuario",-1)

        recyclerView_totalAsistencias.layoutManager = LinearLayoutManager(this)

        obtenerTotalAsistencias(idUsuario)
    }

    fun obtenerTotalAsistencias(idUsuario:Int){

        val url = "https://proyectomovilapi.000webhostapp.com/controllers/verListaAlumnoCheck.php"

        //solicitud Http
        val client = OkHttpClient()

        val formBody = MultipartBody.Builder()
            .setType(MultipartBody.FORM)
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

                val ListaTotalAsistencias = gson.fromJson(body, ListaTotalAsistencias::class.java)


                runOnUiThread {
                    recyclerView_totalAsistencias.adapter = TotalAsistenciasAdapter(ListaTotalAsistencias)
                }

            }

            override fun onFailure(call: Call, e: IOException) {
                println("Fallo al obtener clases asistidas")
            }
        })

    }
}

class ListaTotalAsistencias(val clases:List<ClaseAsistida>)

class ClaseAsistida(val idListaAsistencia:Int,val dia:String, val nombreMateria:String, val hora:String)
