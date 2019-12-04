package com.example.listaasistenciakotlin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.clases_asistidas_row.view.*

class TotalAsistenciasAdapter (val listaClasesAsistidas: ListaTotalAsistencias): RecyclerView.Adapter<CostumViewHolderListaClasesAsistidas>(){

    override fun getItemCount(): Int {
        return listaClasesAsistidas.clases.count()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CostumViewHolderListaClasesAsistidas {
        val layoutInfater = LayoutInflater.from(parent.context)
        val cellForRow = layoutInfater.inflate(R.layout.clases_asistidas_row,parent,false)

        return CostumViewHolderListaClasesAsistidas(cellForRow)
    }

    override fun onBindViewHolder(holder: CostumViewHolderListaClasesAsistidas, position: Int) {
        val clase = listaClasesAsistidas.clases.get(position)

        holder.view.txtNombreMateria.text = clase.nombreMateria
        holder.view.txtDia.text = clase.dia
        holder.view.txtHora.text = clase.hora
    }
}

class CostumViewHolderListaClasesAsistidas(val view: View): RecyclerView.ViewHolder(view){


}