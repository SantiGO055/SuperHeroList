package com.cursokotlin.superherolist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class RecyclerAdapter:RecyclerView.Adapter<RecyclerAdapter.HeroHolder>() {

    var superheros: MutableList<Superhero> = ArrayList()
    lateinit var context: Context


    fun RecyclerAdapter(superheros: MutableList<Superhero>,context: Context){
        this.superheros = superheros
        this.context = context
    }
    /*
    * devuelve un objeto ViewHolder al cual le pasamos la celda que hemos creado
    * */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.HeroHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return HeroHolder(layoutInflater.inflate(R.layout.item_superhero_list, parent,false))
    }

    /*
    * se encarga de coger cada una de las posiciones de la lista de superhéroes
    * y pasarlas a la clase ViewHolder para que esta pinte todos los valores.
    * */
    override fun onBindViewHolder(holder: HeroHolder, position: Int) {
        val item = superheros[position]

        holder.bind(item,context)
    }



    /**
     * nos devuelve el tamaño de la lista, que lo necesita el RecyclerView
     */
    override fun getItemCount(): Int {
        return superheros.size
    }

    class HeroHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val superheroName = view.findViewById(R.id.tvSuperhero) as TextView
        private val realName = view.findViewById(R.id.tvRealName) as TextView
        private val publisher = view.findViewById(R.id.tvPublisher) as TextView
        private val avatar = view.findViewById(R.id.ivAvatar) as ImageView

        fun bind(superhero:Superhero, context: Context){
            superheroName.text = superhero.superhero
            realName.text = superhero.realName
            publisher.text = superhero.publisher
            itemView.setOnClickListener(View.OnClickListener {

                Toast.makeText(context, superhero.superhero, Toast.LENGTH_SHORT).show() }
            )
            avatar.loadUrl(superhero.photo)

        }
        private fun ImageView.loadUrl(url: String) {
            Picasso.get().load(url).into(this)
        }
    }
}
