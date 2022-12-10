package ufrn.anacamilly.pokeapi.repository

import ufrn.anacamilly.pokeapi.data.model.PokeItemListModel
import ufrn.anacamilly.pokeapi.data.model.PokeModel

interface PokeRepository {
    suspend fun getPokeList(): PokeItemListModel
    suspend fun getPokemon(url:String): PokeModel
}