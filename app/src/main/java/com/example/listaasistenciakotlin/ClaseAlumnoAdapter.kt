package com.example.listaasistenciakotlin

import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.clase_alumno_row.view.*

class ClaseAlumnoAdapter(val listaClasesAlumno: ListaClasesAlumno): RecyclerView.Adapter<CostumViewHolderClases>(){

    override fun getItemCount(): Int {

        return listaClasesAlumno.clases.count()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CostumViewHolderClases {
        val layoutInfater = LayoutInflater.from(parent.context)
        val cellForRow = layoutInfater.inflate(R.layout.clase_alumno_row,parent,false)

        return CostumViewHolderClases(cellForRow)

    }

    override fun onBindViewHolder(holder: CostumViewHolderClases, position: Int) {

        val clase = listaClasesAlumno.clases.get(position)

        println("numero de elementos:" )

        holder.view.txtNombreMateria.text = clase.nombreMateria
        holder.view.txtDia.text = clase.dia
        holder.view.txtHora.text = clase.hora

        holder.view.btnChecarAlumno.setOnClickListener {

            //falta conseguir el id lista para madar al activity checar
            val intentAsistenciaAlumno: Intent = Intent(holder.view.context, ChecarAsistencia::class.java)
            intentAsistenciaAlumno.putExtra("idListaAsistencia",clase.idListaAsistencia)
            holder.view.context.startActivity(intentAsistenciaAlumno)

        }


    }

}

class CostumViewHolderClases(val view: View): RecyclerView.ViewHolder(view){


}