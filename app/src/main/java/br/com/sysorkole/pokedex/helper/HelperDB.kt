package br.com.sysorkole.pokedex.helper

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import br.com.sysorkole.pokedex.Pokemon

class HelperDB(
    context: Context?
) : SQLiteOpenHelper(context, NOME_DB, null, VERSAO_ATUAL) {

    companion object{
        private val NOME_DB = "pokedex.db"
        private val VERSAO_ATUAL = 5

    }

    val TABLE_NAME = "pokedex"
    val COLUMNS_NUMERO = "numero"
    val COLUMNS_NOME = "nome"
    val COLUMNS_FOTO = "foto_url"
    val COLUMNS_TIPOUM = "tipo_um_url"
    val COLUMNS_TIPODOIS = "tipo_dois_url"
    val COLUMNS_DESCRICAO = "descricao"
    val DROP_TABLE = "DROP TABLE IF EXISTS $TABLE_NAME"
    val CREATE_TABLE = "CREATE TABLE $TABLE_NAME ("+ "$COLUMNS_NUMERO TEXT NOT NULL,"+
            "$COLUMNS_NOME TEXT NOT NULL,"+
            "$COLUMNS_FOTO TEXT NOT NULL,"+
            "$COLUMNS_TIPOUM TEXT NOT NULL,"+
            "$COLUMNS_TIPODOIS TEXT NOT NULL,"+
            "$COLUMNS_DESCRICAO TEXT NOT NULL,"+
            ""+
            "PRIMARY KEY($COLUMNS_NUMERO)"+ //Poderia adicionar AUTO INCREMENT Depois no NOME
            ")"

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        if(oldVersion != newVersion){
            db?.execSQL(DROP_TABLE)
            onCreate(db)
        }
    }

    fun searchPokemon(filter: String) : List<Pokemon>{
        val db = readableDatabase ?: return mutableListOf()
        val list = mutableListOf<Pokemon>()
        val sql = "SELECT * FROM $TABLE_NAME WHERE $COLUMNS_NOME LIKE '%$filter%'"
        var cursor: Cursor = db.rawQuery(sql,null)
        if(cursor == null){
            db.close()
            return mutableListOf()
        }
        while(cursor.moveToNext()){
            var pokemon = Pokemon(
                cursor.getString(cursor.getColumnIndex(COLUMNS_NUMERO)),
                cursor.getString(cursor.getColumnIndex(COLUMNS_NOME)),
                cursor.getString(cursor.getColumnIndex(COLUMNS_FOTO)),
                cursor.getString(cursor.getColumnIndex(COLUMNS_TIPOUM)),
                cursor.getString(cursor.getColumnIndex(COLUMNS_TIPODOIS)),
                cursor.getString(cursor.getColumnIndex(COLUMNS_NUMERO))
            )
            list.add(pokemon)
        }
        db.close()
        return list
    }

    fun orderPokemon(): List<Pokemon>{
        val db = readableDatabase ?: return mutableListOf()
        val list = mutableListOf<Pokemon>()
        val sql = "SELECT * FROM $TABLE_NAME ORDER BY $COLUMNS_NUMERO ASC"
        var cursor: Cursor = db.rawQuery(sql,null)
        if(cursor == null){
            db.close()
            return mutableListOf()
        }
        while(cursor.moveToNext()){
            var pokemon = Pokemon(
                cursor.getString(cursor.getColumnIndex(COLUMNS_NUMERO)),
                cursor.getString(cursor.getColumnIndex(COLUMNS_NOME)),
                cursor.getString(cursor.getColumnIndex(COLUMNS_FOTO)),
                cursor.getString(cursor.getColumnIndex(COLUMNS_TIPOUM)),
                cursor.getString(cursor.getColumnIndex(COLUMNS_TIPODOIS)),
                cursor.getString(cursor.getColumnIndex(COLUMNS_DESCRICAO))
            )
            list.add(pokemon)
        }
        db.close()
        return list
    }

    fun salvarPokemon(pokemon: Pokemon){
        val db = writableDatabase ?: return
        val sql = "INSERT INTO $TABLE_NAME ($COLUMNS_NUMERO, $COLUMNS_NOME, $COLUMNS_FOTO, " +
                "$COLUMNS_TIPOUM, $COLUMNS_TIPODOIS, $COLUMNS_DESCRICAO) VALUES " +
                "('${pokemon.numero}','${pokemon.nome}','${pokemon.foto}'," +
                "'${pokemon.primeiroTipo}','${pokemon.segundoTipo}','${pokemon.descricao}')"
        db.execSQL(sql)
        db.close()
    }
}