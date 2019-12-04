package com.example.listaasistenciakotlin

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.clase_alumno_row.view.*

class TotalListasGrupoAdapter (val listasGrupo:AllListasGrupo): RecyclerView.Adapter<CostumViewHolderTotalListasGrupo>(){

    override fun getItemCount(): Int {
        return listasGrupo.listas.count()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CostumViewHolderTotalListasGrupo {
        val layoutInfater = LayoutInflater.from(parent.context)
        val cellForRow = layoutInfater.inflate(R.layout.clase_alumno_row,parent,false)

        return CostumViewHolderTotalListasGrupo(cellForRow)
    }

    override fun onBindViewHolder(holder: CostumViewHolderTotalListasGrupo, position: Int) {
        val lista = listasGrupo.listas.get(position)

        holder.view.txtNombreMateria.text = lista.nombreMateria
        holder.view.txtDia.text = lista.dia
        holder.view.txtHora.text = lista.hora

        holder.view.btnChecarAlumno.text = "Ver"

        holder.view.btnChecarAlumno.setOnClickListener {

            //enviar a otro activity
            val intentListaAdmin: Intent = Intent(holder.view.context, ListaAdmin::class.java)
            intentListaAdmin.putExtra("fecha",lista.dia)
            intentListaAdmin.putExtra("nombreGrupo",lista.nombreGrupo)
            holder.view.context.startActivity(intentListaAdmin)

        }
    }
}

class CostumViewHolderTotalListasGrupo(val view: View): RecyclerView.ViewHolder(view){


}