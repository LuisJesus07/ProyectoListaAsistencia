package com.example.listaasistenciakotlin

import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.listaasistencia_row.view.*

class ListaAsistenciaAdapter(val listaAsistencia: ListaAsistencia): RecyclerView.Adapter<CostumViewHolderLista>(){

    override fun getItemCount(): Int {
        return listaAsistencia.alumnos.count()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CostumViewHolderLista {
        val layoutInfater = LayoutInflater.from(parent.context)
        val cellForRow = layoutInfater.inflate(R.layout.listaasistencia_row,parent,false)

        return CostumViewHolderLista(cellForRow)

    }

    override fun onBindViewHolder(holder: CostumViewHolderLista, position: Int) {
        //val clase = listaClases.clases.get(position)

        val alumno = listaAsistencia.alumnos.get(position)

        holder.view.txtNombre.text =  alumno.nombre
        holder.view.txtApellido.text = alumno.apellidos
        holder.view.txtNumControl.text = alumno.matricula

        if(alumno.asistencia.toString() == "2"){
            //actualmente tiene falta

        }else{
            //actualmente tiene asistencia
            holder.view.btnChecarAsistencia.visibility = View.INVISIBLE
            holder.view.imgCheckLista.visibility = View.VISIBLE
            holder.view.btnCancelarAsistencia.visibility = View.VISIBLE
        }

        //btn para poner la asistencia
        holder.view.btnChecarAsistencia.setOnClickListener {

            //enviar a otro activity con el id de la clase seleccionada
            val intentChecarAsistencia: Intent = Intent(holder.view.context, ChecarAsistencia::class.java)
            intentChecarAsistencia.putExtra("idListaAsistencia",alumno.idListaAsistencia)
            holder.view.context.startActivity(intentChecarAsistencia)
        }

        //btn para cancelar la asistencia
        holder.view.btnCancelarAsistencia.setOnClickListener {

            //enviar a otro activity con el id de la clase seleccionada
            val intentCancelarAsistencia: Intent = Intent(holder.view.context, CancelarAsistencia::class.java)
            intentCancelarAsistencia.putExtra("idListaAsistencia",alumno.idListaAsistencia)
            holder.view.context.startActivity(intentCancelarAsistencia)
        }



    }

}

class CostumViewHolderLista(val view: View): RecyclerView.ViewHolder(view){


}