package br.com.sysorkole.pokedex

import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import br.com.sysorkole.pokedex.Pokemon
import br.com.sysorkole.pokedex.R
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import java.net.URI

// Responsavel pelo gerenciamento da lista em si(RecyclerView).
class PokedexAdapter : RecyclerView.Adapter<PokedexAdapterViewHolder>() {

    private val list: MutableList<Pokemon> = mutableListOf()

    // Cria cada item visual na tela.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokedexAdapterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.pokemon_layout,parent,false)
        return PokedexAdapterViewHolder(view)
    }

    // Tratamento dado a cada elemento do RecyclerView.
    override fun onBindViewHolder(holder: PokedexAdapterViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size  // Retorna o número de itens da lista

    fun updateList(list: List<Pokemon>){
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }
}

// É responsavel pelo gerenciamento de cada item.
class PokedexAdapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

    private val pokemonNumber: TextView = itemView.findViewById(R.id.numeroPokemon)
    private val pokemonName: TextView = itemView.findViewById(R.id.nomePokemon)
    private val pokemonPhoto: ImageView = itemView.findViewById(R.id.fotoPokemon)
    private val pokemonTypeOne: ImageView = itemView.findViewById(R.id.tipoUmPokemon)
    private val pokemonTypeTwo: ImageView = itemView.findViewById(R.id.tipoDoisPokemon)


    fun bind(pokemon: Pokemon){
        val uriPhoto: Uri = pokemon.foto.toUri()
        val uriTypeOne: Uri = pokemon.primeiroTipo.toUri()
        pokemonName.text = pokemon.nome
        pokemonNumber.text = "#" + pokemon.numero
        Picasso.get().load(uriPhoto).into(pokemonPhoto)
        Picasso.get().load(uriTypeOne).into(pokemonTypeOne)
        if(pokemon.segundoTipo.isNullOrEmpty()){

        }else {
            val uriTypeTwo: Uri = pokemon.segundoTipo.toUri()
            Picasso.get().load(uriTypeTwo).into(pokemonTypeTwo)
        }
    }
}