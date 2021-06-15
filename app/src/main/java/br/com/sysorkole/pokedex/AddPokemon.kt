package br.com.sysorkole.pokedex

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.widget.Toolbar
import br.com.sysorkole.pokedex.application.PokedexApplication

class AddPokemon : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_pokemon)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        val toolbarEdt = findViewById<EditText>(R.id.edt_toolbar)
        toolbar.title = ""
        toolbarEdt.visibility = View.GONE

        setSupportActionBar(toolbar)
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)

        val campoNumeroPokemon: EditText = findViewById(R.id.edt_numero_pokemon)
        val campoNomePokemon: EditText = findViewById(R.id.edt_nome_pokemon)
        val campoFotoPokemon: EditText = findViewById(R.id.edt_foto_pokemon)
        val campoTipoUmPokemon: EditText = findViewById(R.id.edt_tipoum_pokemon)
        val campoTipoDoisPokemon: EditText = findViewById(R.id.edt_tipodois_pokemon)
        val campoDescricaoPokemon: EditText = findViewById(R.id.edt_descricao_pokemon)
        val addBotao: Button = findViewById(R.id.btn_Criar_Pokemon)

        addBotao.setOnClickListener {
            val pokemon = Pokemon(campoNumeroPokemon.text.toString(), campoNomePokemon.text.toString(),
            campoFotoPokemon.text.toString(), campoTipoUmPokemon.text.toString(),
            campoTipoDoisPokemon.text.toString(), campoDescricaoPokemon.text.toString())
            if(campoTipoDoisPokemon.text.isNullOrEmpty()){
                PokedexApplication.instance.helperDB?.salvarPokemonUmTipo(pokemon)
            }else{
                PokedexApplication.instance.helperDB?.salvarPokemon(pokemon)
            }
            val intent = Intent(this, MainActivity::class.java).apply{}
            startActivity(intent)
        }
    }

    /*override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.search -> {
            val intent = Intent(this, MainActivity::class.java).apply{}
            startActivity(intent)
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
    }*/
}