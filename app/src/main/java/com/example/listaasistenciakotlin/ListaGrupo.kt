package com.example.listaasistenciakotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_lista_grupo.*
import okhttp3.*
import java.io.IOException

class ListaGrupo : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_grupo)

        //recibir datos del intent
        val objectIntent: Intent = intent

        val nombreGrupo = objectIntent.getStringExtra("nombreGrupo")

        recyclerView_listaGrupo.layoutManager = LinearLayoutManager(this)

        listaGrupoJson(nombreGrupo)


    }

    fun listaGrupoJson(nombreGrupo:String){

        val url = "https://proyectomovilapi.000webhostapp.com/controllers/getAlumnosClase.php"



        val client = OkHttpClient()

        val formBody = MultipartBody.Builder()
            .setType(MultipartBody.FORM)
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

                val ListaInfoGrupo = gson.fromJson(body, ListaInfoGrupo::class.java)


                runOnUiThread {
                    recyclerView_listaGrupo.adapter = ListaGrupoAdapter(ListaInfoGrupo)
                }

            }

            override fun onFailure(call: Call, e: IOException) {
                println("Fallo al obtener lista")
            }
        })
    }
}

class ListaInfoGrupo(val alumnos:List<AlumnoGrupo>)

class AlumnoGrupo(val idUsuario:Int,val matricula:String, val nombre:String, val apellidos:String)