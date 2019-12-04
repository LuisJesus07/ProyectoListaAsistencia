package com.example.listaasistenciakotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_total_listas_grupo.*
import okhttp3.*
import java.io.IOException

class TotalListasGrupo : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_total_listas_grupo)

        //recibir datos del intent
        val objectIntent: Intent = intent

        val nombreGrupo = objectIntent.getStringExtra("nombreGrupo")

        recyclerView_totalListasGrupo.layoutManager = LinearLayoutManager(this)

        obtenerTotalListasGrupo(nombreGrupo)
    }


    fun obtenerTotalListasGrupo(nombreGrupo:String){

        val url = "https://proyectomovilapi.000webhostapp.com/controllers/getAllListasGrupoAdmin.php"

        //solicitud Http
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

                val AllListasGrupo = gson.fromJson(body, AllListasGrupo::class.java)


                runOnUiThread {
                    recyclerView_totalListasGrupo.adapter = TotalListasGrupoAdapter(AllListasGrupo)
                }

            }

            override fun onFailure(call: Call, e: IOException) {
                println("Fallo al obtener clases pendientes")
            }
        })
    }
}

class AllListasGrupo(val listas:List<Lista>)

class Lista(val nombreGrupo:String,val dia:String, val nombreMateria:String, val hora:String)