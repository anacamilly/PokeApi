package ufrn.anacamilly.pokeapi.ui.list


import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import ufrn.anacamilly.pokeapi.core.Resource
import ufrn.anacamilly.pokeapi.data.remote.PokeDataSource
import ufrn.anacamilly.pokeapi.databinding.FragmentPokeListBinding
import ufrn.anacamilly.pokeapi.presentation.PokeViewModel
import ufrn.anacamilly.pokeapi.presentation.PokeViewModelFactory
import ufrn.anacamilly.pokeapi.repository.PokeRepositoryImpl
import ufrn.anacamilly.pokeapi.repository.RetrofitClient
import ufrn.anacamilly.pokeapi.ui.adapter.PokeListAdapter
import ufrn.anacamilly.pokeapi.ui.listeners.OnClickListener


class PokeListFragment : Fragment(), OnClickListener {

    private lateinit var binding:FragmentPokeListBinding
    private val viewModel by viewModels<PokeViewModel> { PokeViewModelFactory(
        PokeRepositoryImpl(
        PokeDataSource(RetrofitClient.webservice)
    )
    ) }
    private var pokeNames = mutableListOf<String>()
    private lateinit var adapter:PokeListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentPokeListBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getAlllist().observe(viewLifecycleOwner,{ result->
            when(result){
                is Resource.Loading -> {
                    Log.d("LiveData","Loading...")
                }
                is Resource.Success -> {
                    Log.d("LiveData","${result.data}")
                    result.data.results.forEach {
                        pokeNames.add(it.name)
                    }
                    initRecyclerView()
                }
                is Resource.Failure -> {
                    Log.d("LiveData","${result.exception}")
                }
            }

        })
    }

    private fun initRecyclerView() {
        binding.RVPokeList.layoutManager = LinearLayoutManager(context)
        adapter = PokeListAdapter(pokeNames,this)
        binding.RVPokeList.adapter = adapter
    }

    override fun onClick(name: String) {
        val action = PokeListFragmentDirections.actionPokeListFragmentToPokeDetailFragment(name)
        findNavController().navigate(action)
    }

}