package ufrn.anacamilly.pokeapi.repository

import com.google.gson.GsonBuilder
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Url
import ufrn.anacamilly.pokeapi.application.AppConstants
import ufrn.anacamilly.pokeapi.data.model.PokeItemListModel
import ufrn.anacamilly.pokeapi.data.model.PokeModel

interface WebService {

    //pega a lista de pokemons na API
    @GET("pokemon?limit=100000&offset=0")
    suspend fun getPokeList(): PokeItemListModel

    //pega apenas um pokemon por vez na API
    @GET
    suspend fun getPokemon(@Url url:String): PokeModel

}

//
object RetrofitClient {
    val webservice by lazy {

        Retrofit.Builder()
            .baseUrl(AppConstants.baseUrl)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build().create(WebService::class.java)

    }

}