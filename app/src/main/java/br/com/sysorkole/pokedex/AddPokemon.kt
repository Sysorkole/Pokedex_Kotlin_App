package br.com.sysorkole.pokedex

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.URLUtil
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import br.com.sysorkole.pokedex.application.PokedexApplication
import java.util.*

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
        val campoTipoUmPokemon: EditText = findViewById(R.id.edt_tipoum_pokemon)
        val campoTipoDoisPokemon: EditText = findViewById(R.id.edt_tipodois_pokemon)
        val campoDescricaoPokemon: EditText = findViewById(R.id.edt_descricao_pokemon)
        val addBotao: Button = findViewById(R.id.btn_Criar_Pokemon)

        addBotao.setOnClickListener {
            val pokemon:Pokemon
            val number = fixNumber(campoNumeroPokemon.text.toString().trim())
            val typeTwo = giveUriToType(campoTipoDoisPokemon.text.toString().trim())
            val typeOne = giveUriToType(campoTipoUmPokemon.text.toString().trim())
            val photo = giveUritoPhoto(number)
            if(checkIsUri(typeTwo)) {
                    pokemon = Pokemon(
                    number,
                    campoNomePokemon.text.toString().trim().newCapitalize(),
                    photo,
                    typeOne,
                    typeTwo,
                    campoDescricaoPokemon.text.toString().trim()
                )
            } else{
                    pokemon = Pokemon(
                    number,
                    campoNomePokemon.text.toString().trim(),
                    photo,
                    typeOne,
                    "https://www.pokemon.com/br/",
                    campoDescricaoPokemon.text.toString().trim()
                )
            }

            if (checkIsOkay(pokemon)) {
                PokedexApplication.instance.helperDB?.salvarPokemon(pokemon)
                val intent = Intent(this, MainActivity::class.java).apply {}
                startActivity(intent)
            }
        }
    }
    //Sem Erro: True , Com erro: False
    private fun checkIsOkay(pokemon:Pokemon):Boolean{
            when {
                !checkIsNumeric(pokemon.numero) ->{
                    Toast.makeText(this, "Numero Inválido", Toast.LENGTH_LONG).show()
                    return false}
                pokemon.numero=="000"->{
                    Toast.makeText(this, "Numero Inválido", Toast.LENGTH_LONG).show()
                    return false}
                !checkIsAlphabetical(pokemon.nome)->{
                    Toast.makeText(this, "Nome Inválido", Toast.LENGTH_LONG).show()
                    return false}
                !checkIsUri(pokemon.foto)->{
                    Toast.makeText(this, "URL Inválida da Foto", Toast.LENGTH_LONG).show()
                    return false}
                !checkIsUri(pokemon.primeiroTipo)->{
                    Toast.makeText(this, "URL Inválida do Tipo Um", Toast.LENGTH_LONG).show()
                    return false}
                !checkIsUri(pokemon.segundoTipo)->{
                    Toast.makeText(this, "URL Inválida do Tipo Dois", Toast.LENGTH_LONG).show()
                    return false}
                else -> return true
            }
    }

    //Sem Erro: True , Com erro: False
    private fun checkIsNumeric(numericString: String):Boolean{
        return if(numericString.isNullOrBlank()){
            false
        } else numericString.matches("-?\\d+(\\.\\d+)?".toRegex())
    }

    //Sem Erro: False , Com erro: True
    private fun checkIsAlphabetical(text: String): Boolean {
        return when {
            text == "Farfetch'd" -> true
            text.isNullOrBlank() -> false
            else -> return text.none { it !in 'A'..'Z' && it !in 'a'..'z'}
        }
    }

    //Sem Erro: False , Com erro: True
    private fun checkIsUri(uri: String):Boolean{
        return URLUtil.isValidUrl(uri)
    }
    //Sem Erro: False , Com erro: True
    private fun giveUriToType(type: String):String{
        return when(type.newCapitalize()){
            "Aço" -> "https://pixelmonmod.com/w/images/5/58/SteelType.png"
            "Água" -> "https://pixelmonmod.com/w/images/a/a7/WaterType.png"
            "Agua" -> "https://pixelmonmod.com/w/images/a/a7/WaterType.png"
            "Dragão" -> "https://pixelmonmod.com/w/images/5/5d/DragonType.png"
            "Dragao" -> "https://pixelmonmod.com/w/images/5/5d/DragonType.png"
            "Elétrico" -> "https://pixelmonmod.com/w/images/f/f3/ElectricType.png"
            "Eletrico" -> "https://pixelmonmod.com/w/images/f/f3/ElectricType.png"
            "Fada" -> "https://pixelmonmod.com/w/images/4/47/FairyType.png"
            "Fantasma" -> "https://pixelmonmod.com/w/images/9/98/GhostType.png"
            "Fogo" -> "https://pixelmonmod.com/w/images/7/79/FireType.png"
            "Gelo" -> "https://pixelmonmod.com/w/images/1/10/IceType.png"
            "Inseto" -> "https://pixelmonmod.com/w/images/8/81/BugType.png"
            "Lutador" -> "https://pixelmonmod.com/w/images/9/98/FightingType.png"
            "Normal" -> "https://pixelmonmod.com/w/images/8/83/NormalType.png"
            "Pedra" -> "https://pixelmonmod.com/w/images/0/0b/RockType.png"
            "Planta" -> "https://pixelmonmod.com/w/images/d/d6/GrassType.png"
            "Psíquico" -> "https://pixelmonmod.com/w/images/f/f6/PsychicType.png"
            "Psiquico" -> "https://pixelmonmod.com/w/images/f/f6/PsychicType.png"
            "Sombrio" -> "https://pixelmonmod.com/w/images/f/f8/DarkType.png"
            "Terra" -> "https://pixelmonmod.com/w/images/3/31/GroundType.png"
            "Venenoso" -> "https://pixelmonmod.com/w/images/3/3b/PoisonType.png"
            "Voador" -> "https://pixelmonmod.com/w/images/0/0d/FlyingType.png"
            else -> {
                Toast.makeText(this, "Tipo Inválido", Toast.LENGTH_LONG).show()
                return type
            }
        }
    }

    //recebe o número e retorna a url
    private fun giveUritoPhoto(number: String):String{
        return "https://assets.pokemon.com/assets/cms2/img/pokedex/full/$number.png"


    }

    private fun fixNumber(number: String):String{
        val newNumber: String
        when(number.length){
            1-> newNumber="00"+number
            2-> newNumber="0"+number
            3-> newNumber=number
            else->{
                Toast.makeText(this, "Formato do número é inválido", Toast.LENGTH_LONG).show()
                newNumber="000"
            }
        }
        return newNumber
    }

    public fun String.newCapitalize():String{
        return replaceFirstChar { it.titlecase() }
    }


}