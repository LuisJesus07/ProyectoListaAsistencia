package com.example.listaasistenciakotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_cancelar_asistencia.*
import okhttp3.*
import java.io.IOException

class CancelarAsistencia : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cancelar_asistencia)

        //recibir datos del intent login
        val objectIntent: Intent = intent

        val idListaAsistencia = objectIntent.getIntExtra("idListaAsistencia",-1)


        btnCancelar.setOnClickListener {
            cancelarAsistencia(idListaAsistencia)
        }
    }

    fun cancelarAsistencia(idListaAsistencia:Int){

        val url = "https://proyectomovilapi.000webhostapp.com/controllers/cancelarAsistencia.php"

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
                if(body == "{\"message\":\"Cancelada\"}"){

                    val builderCancelar = AlertDialog.Builder(this@CancelarAsistencia)
                    // titulo del emnsaje
                    builderCancelar.setTitle("Asistencia")

                    // mensaje
                    builderCancelar.setMessage("La asistencia se ha cancelado exitosamente")

                    // Set a positive button and its click listener on alert dialog
                    builderCancelar.setPositiveButton("Aceptar"){dialog, which ->
                        // Do something when user press the positive button
                        Toast.makeText(applicationContext,"Asistencia cancelada.", Toast.LENGTH_SHORT).show()
                    }

                    //mostrar el mesaje
                    runOnUiThread {
                        val dialogCancelar: AlertDialog = builderCancelar.create()
                        dialogCancelar.show()
                    }



                }

            }

            override fun onFailure(call: Call, e: IOException) {
                println("Error")
            }
        })
    }
}
