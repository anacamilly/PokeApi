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

    /*Puede que aquí necesite modificarlo por lo de la query*/
    @GET("pokemon?limit=151&offset=0")
    suspend fun getPokeList(): PokeItemListModel
    @GET
    suspend fun getPokemon(@Url url:String): PokeModel

}


object RetrofitClient {


    val webservice by lazy {

        Retrofit.Builder()
            .baseUrl(AppConstants.baseUrl)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build().create(WebService::class.java)

    }

}