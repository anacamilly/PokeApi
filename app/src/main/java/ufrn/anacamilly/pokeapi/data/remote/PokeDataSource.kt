package ufrn.anacamilly.pokeapi.data.remote

import ufrn.anacamilly.pokeapi.data.model.PokeItemListModel
import ufrn.anacamilly.pokeapi.data.model.PokeModel
import ufrn.anacamilly.pokeapi.repository.WebService

class PokeDataSource(private val webservice: WebService) {

    suspend fun getPokelist(): PokeItemListModel {
        return webservice.getPokeList()
    }

    suspend fun getPokemon(url:String): PokeModel {
        return webservice.getPokemon(url)
    }

}