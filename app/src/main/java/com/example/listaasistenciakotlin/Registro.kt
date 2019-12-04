package com.example.listaasistenciakotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_registro.*
import okhttp3.*
import java.io.IOException

class Registro : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        var nombre:String
        var apellidos:String
        var numControl:String

        val irAlogin = txtLogin

        btnRegistro.setOnClickListener {

            nombre = txtNombre.text.toString()
            apellidos = txtApellidos.text.toString()
            numControl = txtNumControl.text.toString()

            registro(nombre,apellidos,numControl)
        }

        irAlogin.setOnClickListener {

            val intentLogin: Intent = Intent(this, MainActivity::class.java)
            startActivity(intentLogin)
            finish()
        }
    }

    fun registro(nombre:String,apellidos:String,numControl:String){

        val url = "https://proyectomovilapi.000webhostapp.com/controllers/createUser.php"

        //solicitud Http
        val client = OkHttpClient()

        val formBody = MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart("nombre",nombre)
            .addFormDataPart("apellidos",apellidos)
            .addFormDataPart("matricula",numControl)
            .build()

        val request =  Request.Builder()
            .url(url)
            .post(formBody)
            .build()

        //respuesta de la solicitud
        client.newCall(request).enqueue(object : Callback{

            override fun onResponse(call: Call, response: Response) {
                val body = response.body!!.string()
                println(body)
            }

            override fun onFailure(call: Call, e: IOException) {
                println("Error")
            }
        })
    }
}
