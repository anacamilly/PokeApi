package ufrn.anacamilly.pokeapi.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers
import ufrn.anacamilly.pokeapi.core.Resource
import ufrn.anacamilly.pokeapi.repository.PokeRepository

class PokeViewModel (private val repo: PokeRepository) : ViewModel() {

    fun getAlllist () = liveData(Dispatchers.IO){
        emit(Resource.Loading())

        try {
            emit(Resource.Success(repo.getPokeList()))
        }catch (e:Exception){
            emit(Resource.Failure(e))
        }
    }

    fun getPokemon(url:String)= liveData(Dispatchers.IO){
        emit(Resource.Loading())
        try {
            emit(Resource.Success(repo.getPokemon(url)))
        }catch (e:Exception){
            emit(Resource.Failure(e))
        }
    }
}

class PokeViewModelFactory(private val repo:PokeRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(PokeRepository::class.java).newInstance(repo)
    }
}