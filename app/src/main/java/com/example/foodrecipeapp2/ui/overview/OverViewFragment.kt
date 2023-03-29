package com.example.foodrecipeapp2.ui.overview

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.foodrecipeapp2.R
import com.example.foodrecipeapp2.databinding.FragmentOverViewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class OverViewFragment : Fragment() {

    private var _binding : FragmentOverViewBinding? = null
    private val binding : FragmentOverViewBinding
    get() = _binding!!
    private val viewModel : OverViewModel by viewModels()
    lateinit var adapter: OverViewPagingAdapter
    private var lastQuery = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentOverViewBinding.inflate(inflater,container,false)
        setUpUi()
        setUpObserver()
        return binding.root
    }
    fun setUpUi(){
        adapter = OverViewPagingAdapter(onMainClick = {
            it.id?.let {
                findNavController().navigate(OverViewFragmentDirections.actionOverViewFragmentToDetailFragment(it))
            }
        })
        binding.recyclerView.shimmerLayout = R.layout.item_shimmer_place_holder
        binding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.recyclerView.adapter = adapter.withLoadStateHeaderAndFooter(
            header = LoaderAdapter(),
            footer = LoaderAdapter()
        )

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {
                if (!query.isNullOrEmpty() && query != lastQuery) {
                    lastQuery = query
                    viewModel.searchRecipe(query.toString())
                    adapter.refresh()
                }

                return true
            }


            override fun onQueryTextChange(newText: String?): Boolean {
                if (!newText.isNullOrEmpty() && newText != lastQuery) {
                    lastQuery = newText
                    viewModel.searchRecipe(newText.toString())
                    adapter.refresh()
                }

                return true
            }

        })

    }
    fun setUpObserver(){
        lifecycleScope.launch {
            viewModel.recipe.collectLatest {
                adapter.submitData(it)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}