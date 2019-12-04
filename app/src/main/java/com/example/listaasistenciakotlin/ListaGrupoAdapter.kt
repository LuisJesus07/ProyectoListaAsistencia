package com.example.listaasistenciakotlin

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.info_grupo_row.view.*

class ListaGrupoAdapter(val listaInfoGrupo:ListaInfoGrupo): RecyclerView.Adapter<CostumViewHolderListaGrupo>(){

    override fun getItemCount(): Int {
        return listaInfoGrupo.alumnos.count()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CostumViewHolderListaGrupo {
        val layoutInfater = LayoutInflater.from(parent.context)
        val cellForRow = layoutInfater.inflate(R.layout.info_grupo_row,parent,false)

        return CostumViewHolderListaGrupo(cellForRow)
    }

    override fun onBindViewHolder(holder: CostumViewHolderListaGrupo, position: Int) {

        val alumno = listaInfoGrupo.alumnos.get(position)

        holder.view.txtNombre.text = alumno.nombre
        holder.view.txtApellido.text = alumno.apellidos
        holder.view.txtNumControl.text = alumno.matricula

        holder.view.btnVerAlumno.setOnClickListener {

            //enviar a activity con info del alumno
            val intentInfoAlumno: Intent = Intent(holder.view.context, InfoAlumno::class.java)
            intentInfoAlumno.putExtra("idUsuario",alumno.idUsuario)
            intentInfoAlumno.putExtra("nombre",alumno.nombre)
            intentInfoAlumno.putExtra("apellidos",alumno.apellidos)
            intentInfoAlumno.putExtra("numControl",alumno.matricula)
            holder.view.context.startActivity(intentInfoAlumno)
        }
    }


}

class CostumViewHolderListaGrupo(val view: View): RecyclerView.ViewHolder(view){


}