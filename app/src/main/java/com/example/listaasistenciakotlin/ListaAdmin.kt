package com.example.listaasistenciakotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_lista_admin.*
import okhttp3.*
import java.io.IOException

class ListaAdmin : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_admin)

        //recibir datos del intent  MenuGrupo
        val objectIntent: Intent = intent

        val fecha = objectIntent.getStringExtra("fecha")
        val nombreGrupo = objectIntent.getStringExtra("nombreGrupo")


        recyclerView_listaAsistencia.layoutManager = LinearLayoutManager(this)

        listaJson(fecha,nombreGrupo)
    }


    fun listaJson(fecha:String,nombreGrupo:String){

        val url = "https://proyectomovilapi.000webhostapp.com/controllers/verListaAdmin.php"

        val client = OkHttpClient()

        val formBody = MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart("fecha",fecha)
            .addFormDataPart("nombreGrupo",nombreGrupo)
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

                val ListaAsistencia = gson.fromJson(body, ListaAsistencia::class.java)


                runOnUiThread {
                    recyclerView_listaAsistencia.adapter = ListaAsistenciaAdapter(ListaAsistencia)
                }

            }

            override fun onFailure(call: Call, e: IOException) {
                println("Fallo al obtener lista")
            }
        })
    }
}


class ListaAsistencia(val alumnos:List<Alumno>)

class Alumno(val idListaAsistencia:Int, val nombre:String, val apellidos:String, val matricula:String, val asistencia:Int)