package br.com.sysorkole.pokedex

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.sysorkole.pokedex.application.PokedexApplication
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    private val rvList: RecyclerView by lazy{
        findViewById<RecyclerView>(R.id.rv_Pokedexlist)
    }
    private val adapter = PokedexAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        bindView()
        orderList()
    }

    private fun bindView(){
        rvList.adapter = adapter
        rvList.layoutManager = LinearLayoutManager(this)
    }

    // Cria uma lista Mockada
    /*private fun updateList(){
        adapter.updateList(arrayListOf(Pokemon(
                "001",
                "Bulbasaur",
                "https://assets.pokemon.com/assets/cms2/img/pokedex/full/001.png",
                "https://pixelmonmod.com/w/images/d/d6/GrassType.png",
                "https://pixelmonmod.com/w/images/3/3b/PoisonType.png",
                "Qualquer coisa"),Pokemon(
            "002",
            "Ivysaur",
            "https://assets.pokemon.com/assets/cms2/img/pokedex/full/002.png",
            "https://pixelmonmod.com/w/images/d/d6/GrassType.png",
            "https://pixelmonmod.com/w/images/3/3b/PoisonType.png",
            "Qualquer coisa")
        ))
    }*/


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    fun showList(filter: String){
        var listaFiltrada: List<Pokemon> = mutableListOf()
        try{
            listaFiltrada = PokedexApplication.instance.helperDB?.searchPokemon(filter) ?: mutableListOf()
        }catch(ex: Exception){
            ex.printStackTrace()
        }
        adapter.updateList(listaFiltrada)
    }

    fun orderList(){
        var listaFiltrada: List<Pokemon> = mutableListOf()
        try{
            listaFiltrada = PokedexApplication.instance.helperDB?.orderPokemon() ?: mutableListOf()
        }catch(ex: Exception){
            ex.printStackTrace()
        }
        adapter.updateList(listaFiltrada)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.search -> {
            showList(findViewById<EditText>(R.id.edt_toolbar).text.toString())
            true
        }
        R.id.add_pokemon -> {
            val intent = Intent(this, AddPokemon::class.java).apply{}
            startActivity(intent)
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }
    }
}