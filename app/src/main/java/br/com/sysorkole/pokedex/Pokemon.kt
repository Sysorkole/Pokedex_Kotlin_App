package br.com.sysorkole.pokedex

import java.io.Serializable;

data class Pokemon(
    var numero: String,
    var nome: String,
    var foto: String,
    var primeiroTipo: String,
    var segundoTipo: String,
    var descricao: String)