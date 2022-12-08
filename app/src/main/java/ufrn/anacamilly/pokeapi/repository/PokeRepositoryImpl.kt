package ufrn.anacamilly.pokeapi.repository

import ufrn.anacamilly.pokeapi.data.model.PokeItemListModel
import ufrn.anacamilly.pokeapi.data.model.PokeModel
import ufrn.anacamilly.pokeapi.data.remote.PokeDataSource

class PokeRepositoryImpl(private val dataSource: PokeDataSource):PokeRepository {

    override suspend fun getPokeList(): PokeItemListModel = dataSource.getPokelist()
    override suspend fun getPokemon(url:String): PokeModel = dataSource.getPokemon(url)

}