package com.example.listaasistenciakotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_generar_lista.*
import okhttp3.*
import java.io.IOException

class GenerarLista : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_generar_lista)

        //recibir datos del intent  menuGrupo
        val objectIntent: Intent = intent

        val idClase = objectIntent.getIntExtra("idClase",-1)
        val nombreGrupo = objectIntent.getStringExtra("nombreGrupo")

        //println(idClase)
        //println(nombreGrupo)

        btnCrearLista.setOnClickListener {

            val dia = txtDia.text.toString()
            val hora = txtHora.text.toString()

            generaraLista(idClase,nombreGrupo,dia,hora)
        }

        btnGenerarAutomaticamente.setOnClickListener {

            generarListaAutomaticamente(idClase, nombreGrupo)
        }


    }


    fun generaraLista(idClase:Int,nombreGrupo:String,dia:String,hora:String){

        val url = "https://proyectomovilapi.000webhostapp.com/controllers/createListaAsistencia.php"

        //solicitud Http
        val client = OkHttpClient()

        val formBody = MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart("nombreGrupo",nombreGrupo)
            .addFormDataPart("dia",dia)
            .addFormDataPart("hora",hora)
            .addFormDataPart("idClase",idClase.toString())
            .build()

        val request =  Request.Builder()
            .url(url)
            .post(formBody)
            .build()


        client.newCall(request).enqueue(object: Callback {

            override fun onResponse(call: Call, response: okhttp3.Response) {
                val body = response.body!!.string()
                println(body)

                //mostrar alerta de lsiat generada correctamenete
                if(body == "{\"message\":\"Lista generada con exito\"}"){

                    val builderListaGenerada = AlertDialog.Builder(this@GenerarLista)
                    // titulo del emnsaje
                    builderListaGenerada.setTitle("Lista Generada")

                    // mensaje
                    builderListaGenerada.setMessage("La lista se ha generado correctamente.")

                    // Set a positive button and its click listener on alert dialog
                    builderListaGenerada.setPositiveButton("Aceptar"){dialog, which ->
                        // Do something when user press the positive button
                        Toast.makeText(applicationContext,"Lista generada.", Toast.LENGTH_SHORT).show()
                    }

                    //mostrar el mesaje
                    runOnUiThread {
                        val dialogListaGenerada: AlertDialog = builderListaGenerada.create()
                        dialogListaGenerada.show()
                    }



                }



            }

            override fun onFailure(call: Call, e: IOException) {
                println("Fallo Generar lista")
            }
        })
    }

    fun generarListaAutomaticamente(idClase:Int,nombreGrupo:String){

        val url = "https://proyectomovilapi.000webhostapp.com/controllers/createListaAsistencia.php"

        //solicitud Http
        val client = OkHttpClient()

        val formBody = MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart("nombreGrupo",nombreGrupo)
            .addFormDataPart("idClase",idClase.toString())
            .build()

        val request =  Request.Builder()
            .url(url)
            .post(formBody)
            .build()


        client.newCall(request).enqueue(object: Callback {

            override fun onResponse(call: Call, response: okhttp3.Response) {
                val body = response.body!!.string()
                println(body)

                //mostrar alerta de lsiat generada correctamenete
                if(body == "{\"message\":\"Lista generada con exito\"}"){

                    val builderListaGeneradaAut = AlertDialog.Builder(this@GenerarLista)
                    // titulo del emnsaje
                    builderListaGeneradaAut.setTitle("Lista Generada")

                    // mensaje
                    builderListaGeneradaAut.setMessage("La lista se ha generado correctamente.")

                    // Set a positive button and its click listener on alert dialog
                    builderListaGeneradaAut.setPositiveButton("Aceptar"){dialog, which ->
                        // Do something when user press the positive button
                        Toast.makeText(applicationContext,"Lista generada.", Toast.LENGTH_SHORT).show()
                    }

                    //mostrar el mesaje
                    runOnUiThread {
                        val dialogListaGenerada: AlertDialog = builderListaGeneradaAut.create()
                        dialogListaGenerada.show()
                    }



                }



            }

            override fun onFailure(call: Call, e: IOException) {
                println("Fallo Generar lista")
            }
        })
    }
}
