package ufrn.anacamilly.pokeapi.data.remote

import ufrn.anacamilly.pokeapi.data.model.PokeItemListModel
import ufrn.anacamilly.pokeapi.data.model.PokeModel
import ufrn.anacamilly.pokeapi.repository.WebService

class PokeDataSource(private val webservice: WebService) {

    //função que pode ser pausada e retomada depois

    //função que retorna a lista de pokemons
    suspend fun getPokelist(): PokeItemListModel {
        return webservice.getPokeList()
    }

    //função que retorna apenas um pokemon
    suspend fun getPokemon(url:String): PokeModel {
        return webservice.getPokemon(url)
    }

}