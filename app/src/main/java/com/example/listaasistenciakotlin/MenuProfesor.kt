package com.example.listaasistenciakotlin

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_menu_alumnos.*
import kotlinx.android.synthetic.main.activity_menu_profesor.*
import okhttp3.*
import java.io.IOException

class MenuProfesor : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_profesor)

        //recibir datos del intent login
        val objectIntent: Intent = intent

        val idUsuario = objectIntent.getIntExtra("idUsuario", -1)

        //recyclerView_grupos.setBackgroundColor(Color.BLUE)

        recyclerView_grupos.layoutManager = LinearLayoutManager(this)
        //recyclerView_grupos.adapter = GrupoAdapter()

        obtenerClases()
    }

    fun obtenerClases(){
        println("fecth json")

        //val url = "http://192.168.1.254/8000/usuarios"
        val url = "https://proyectomovilapi.000webhostapp.com/controllers/getClases.php"

        val request = Request.Builder().url(url).build()

        val client = OkHttpClient()


        client.newCall(request).enqueue(object: Callback {

            override fun onResponse(call: Call, response: Response) {
                val body = response.body!!.string()
                println(body)


                val gson = GsonBuilder().create()

                val ListaClases = gson.fromJson(body, ListaClases::class.java)


                runOnUiThread {
                    recyclerView_grupos.adapter = GrupoAdapter(ListaClases)
                }

            }

            override fun onFailure(call: Call, e: IOException) {
                println("Fallo al obtener grupos")
            }
        })
    }
}


class ListaClases(val clases:List<Clase>)

class Clase(val idClase:Int, val nombre:String, val apellidos:String, val nombreMateria:String, val nombreGrupo:String)
