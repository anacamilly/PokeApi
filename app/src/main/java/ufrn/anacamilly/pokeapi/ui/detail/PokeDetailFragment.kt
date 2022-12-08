package ufrn.anacamilly.pokeapi.ui.detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.squareup.picasso.Picasso
import ufrn.anacamilly.pokeapi.core.Resource
import ufrn.anacamilly.pokeapi.data.remote.PokeDataSource
import ufrn.anacamilly.pokeapi.databinding.FragmentPokeDetailBinding
import ufrn.anacamilly.pokeapi.presentation.PokeViewModel
import ufrn.anacamilly.pokeapi.presentation.PokeViewModelFactory
import ufrn.anacamilly.pokeapi.repository.PokeRepositoryImpl
import ufrn.anacamilly.pokeapi.repository.RetrofitClient

class PokeDetailFragment : Fragment() {

    private lateinit var binding:FragmentPokeDetailBinding
    private val viewModel by viewModels<PokeViewModel> { PokeViewModelFactory(
        PokeRepositoryImpl(PokeDataSource(RetrofitClient.webservice))
    )
    }
    private var name:String? = null
    private val args:PokeDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentPokeDetailBinding.inflate(inflater,container,false)
        name = args.name
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val url = "pokemon/$name/"
        viewModel.getPokemon(url).observe(viewLifecycleOwner,{ result ->

            when(result){
                is Resource.Loading -> {
                    Log.d("LiveData","Loading...")
                }
                is Resource.Success -> {
                    Log.d("LiveData","${result.data}")
                    initViews(result.data.id,result.data.height,
                        result.data.weight,result.data.sprites.front_default.toString())
                }
                is Resource.Failure ->{
                    Log.d("LiveData","${result.exception}")
                }

            }

        })
    }

    private fun initViews(id:Int,height:Double,weight:Double,sprite:String) {
        binding.PokemonName.text = name
        binding.PokemonId.text = id.toString()
        binding.PokemonHeight.text = height.toString()
        binding.PokemonWeight.text = weight.toString()
        Picasso.get().load(sprite).into(binding.PokemonImage)
    }

}