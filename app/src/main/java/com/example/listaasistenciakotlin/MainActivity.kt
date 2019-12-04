package com.example.listaasistenciakotlin

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.isVisible
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var nombre:String
        var numControl:String

        val crearCuenta = txtCrearCuenta

        btnLogin.setOnClickListener {

            nombre = txtNombre.text.toString()
            numControl = txtNumControl.text.toString()

            login(nombre,numControl)
        }

        //ir a la pntalla de registro
        crearCuenta.setOnClickListener {

            val intentRegistro:Intent = Intent(this, Registro::class.java)
            startActivity(intentRegistro)
            finish()

        }


    }

    fun login( nombre:String, numControl:String){

        val url = "https://proyectomovilapi.000webhostapp.com/controllers/loginUser.php"


        //intents
        val intentProfesor:Intent = Intent(this, MenuProfesor::class.java)
        val intentAlumnos:Intent = Intent(this, MenuAlumnos::class.java)

        //texto error
        val txtError = txtError

        //solicitud Http
        val client = OkHttpClient()

        val formBody = MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart("nombre",nombre)
            .addFormDataPart("matricula",numControl)
            .build()

        val request =  Request.Builder()
            .url(url)
            .post(formBody)
            .build()

        client.newCall(request).enqueue(object: Callback{

            override fun onResponse(call: Call, response: okhttp3.Response) {
                val body = response.body!!.string()
                println(body)

                val gson = Gson()

                val usuario : Usuario = gson.fromJson(body, Usuario::class.java)

                println(usuario)


                if(usuario.privilegios == 1){
                    //println("Profesor")
                    intentProfesor.putExtra("Nombre",usuario.nombre)
                    intentProfesor.putExtra("Apellidos",usuario.apellidos)
                    intentProfesor.putExtra("idUsuario",usuario.idUsuario)
                    startActivity(intentProfesor)
                }else{

                    if(usuario.privilegios == 2){
                        //println("Alumno")
                        intentAlumnos.putExtra("Nombre",usuario.nombre)
                        intentAlumnos.putExtra("Apellidos",usuario.apellidos)
                        intentAlumnos.putExtra("idUsuario",usuario.idUsuario)
                        intentAlumnos.putExtra("idGrupo",usuario.idGrupo)

                        println("El id a enviiar es"+usuario.idUsuario)

                        startActivity(intentAlumnos)
                    }else{
                        //println("No existe la cuenta")

                        runOnUiThread {
                            txtError.visibility = View.VISIBLE
                        }

                    }
                }


            }

            override fun onFailure(call: Call, e: IOException) {
                println("Fallo")
            }
        })


    }

}

data class Usuario(val idUsuario:Int,val nombre: String, val matricula: String, val apellidos: String, val privilegios:Int, val idGrupo:Int) {
}
