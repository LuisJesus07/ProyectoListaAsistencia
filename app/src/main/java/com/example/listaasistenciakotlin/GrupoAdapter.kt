package com.example.listaasistenciakotlin

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.grupo_row.view.*

class GrupoAdapter(val listaClases: ListaClases): RecyclerView.Adapter<CostumViewHolder>(){

    override fun getItemCount(): Int {
        return listaClases.clases.count()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CostumViewHolder {
        val layoutInfater = LayoutInflater.from(parent.context)
        val cellForRow = layoutInfater.inflate(R.layout.grupo_row,parent,false)

        return CostumViewHolder(cellForRow)
    }

    override fun onBindViewHolder(holder: CostumViewHolder, position: Int) {

        val clase = listaClases.clases.get(position)

        holder.view.txtNombreMateriaGrupos.text = clase.nombreMateria
        holder.view.txtNombreProfesorGrupos.text = clase.nombre
        holder.view.txtApellidosProfesorGrupos.text = clase.apellidos
        holder.view.txtNombreGrupoGrupos.text = clase.nombreGrupo

        holder.view.btnVerGrupo.setOnClickListener {

            //enviar a otro activity con el id de la clase seleccionada
            val intentMenuGrupo: Intent = Intent(holder.view.context, MenuGrupo::class.java)
            intentMenuGrupo.putExtra("idClase",clase.idClase)
            intentMenuGrupo.putExtra("nombreGrupo",clase.nombreGrupo);
            holder.view.context.startActivity(intentMenuGrupo)

        }
    }

}

class CostumViewHolder(val view: View): RecyclerView.ViewHolder(view){


}