package ufrn.anacamilly.pokeapi.data.remote

import ufrn.anacamilly.pokeapi.data.model.PokeItemListModel
import ufrn.anacamilly.pokeapi.data.model.PokeModel
import ufrn.anacamilly.pokeapi.repository.WebService

class PokeDataSource(private val webservice: WebService) {

    //função que retorna a lista de pokemons direto da API
    suspend fun getPokelist(): PokeItemListModel {
        return webservice.getPokeList()
    }

    //função que retorna apenas um pokemon eespecifico direto da API
    suspend fun getPokemon(url:String): PokeModel {
        return webservice.getPokemon(url)
    }

}