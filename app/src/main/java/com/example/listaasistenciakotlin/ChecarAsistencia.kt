package com.example.listaasistenciakotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_checar_asistencia.*
import okhttp3.*
import java.io.IOException

class ChecarAsistencia : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checar_asistencia)

        //recibir datos del intent
        val objectIntent: Intent = intent

        val idListaAsistencia = objectIntent.getIntExtra("idListaAsistencia",-1)



        /*btnCancelar.setOnClickListener {
            checarAsistencia(idListaAsistencia)
        }*/

        btnCheckAsistencia.setOnClickListener {
            checarAsistencia(idListaAsistencia)

        }


    }

    fun checarAsistencia(idListaAsistencia:Int){

        val url = "https://proyectomovilapi.000webhostapp.com/controllers/checarAsistencia.php"

        //solicitud Http
        val client = OkHttpClient()

        val formBody = MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart("idListaAsistencia",idListaAsistencia.toString())
            .build()

        val request =  Request.Builder()
            .url(url)

            .post(formBody)
            .build()

        client.newCall(request).enqueue(object : Callback {

            override fun onResponse(call: Call, response: Response) {
                val body = response.body!!.string()
                println(body)

                //mostrar alerta de check correcto
                if(body == "{\"message\":\"Check\"}"){

                    val builder = AlertDialog.Builder(this@ChecarAsistencia)
                    // titulo del emnsaje
                    builder.setTitle("Asistencia")

                    // mensaje
                    builder.setMessage("La asistencia se ha registrado exitosamente")

                    // Set a positive button and its click listener on alert dialog
                    builder.setPositiveButton("Aceptar"){dialog, which ->
                        // Do something when user press the positive button
                        Toast.makeText(applicationContext,"Asistencia registrada.",Toast.LENGTH_SHORT).show()
                    }

                    //mostrar el mesaje
                    runOnUiThread {
                        val dialog: AlertDialog = builder.create()
                        dialog.show()
                    }



                }

            }

            override fun onFailure(call: Call, e: IOException) {
                println("Error")
            }
        })
    }
}
