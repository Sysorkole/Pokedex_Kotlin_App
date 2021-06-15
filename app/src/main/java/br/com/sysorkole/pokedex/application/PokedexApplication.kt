package br.com.sysorkole.pokedex.application

import android.app.Application
import br.com.sysorkole.pokedex.helper.HelperDB

class PokedexApplication : Application() {

    var helperDB: HelperDB? = null
    private set

    companion object{
        lateinit var instance: PokedexApplication
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        helperDB = HelperDB(this)
    }
}