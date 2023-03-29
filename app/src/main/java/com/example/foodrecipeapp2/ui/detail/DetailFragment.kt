package com.example.foodrecipeapp2.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodrecipeapp2.R
import com.example.foodrecipeapp2.databinding.FragmentDetailBinding
import com.example.foodrecipeapp2.util.NetworkResult
import com.example.foodrecipeapp2.util.snackBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
public class DetailFragment : Fragment() {
    private var _binding : FragmentDetailBinding? = null
    private val binding : FragmentDetailBinding
    get() = _binding!!

    private lateinit var adapter: DetailAdapter
    private val viewModel : DetailViewModel by viewModels()
    private val args by navArgs<DetailFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDetailBinding.inflate(inflater,container,false)
        setUpUi()
        setUpObserver()

        return binding.root
    }

    private fun setUpUi(){
        adapter = DetailAdapter()
        binding.recycleIndi.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,
            false
        )
        binding.imgViewBack.setOnClickListener {
            findNavController().navigateUp()
        }
        binding.recycleIndi.adapter = adapter
        args.id.let {
            viewModel.getRecipe2(it)
        }
    }

    private fun setUpObserver(){
        viewModel.myResponceData.observe(viewLifecycleOwner, Observer { response->
            when(response){
                is NetworkResult.Error->{
                    response.message?.snackBar(requireView(), requireContext())
                }
                is NetworkResult.Loading -> {

                }
                is NetworkResult.Success -> {

                    response.data.let { detail->

                        binding.detailViewModel = detail
                        binding.vm = viewModel

                        detail?.extendedIngredients?.let {
                            adapter.setData(it)

                        }
                        detail?.id?.let { id->
                            binding.tvSeeDetailsIng.setOnClickListener {
                                findNavController().navigate(
                                    DetailFragmentDirections.actionDetailFragmentToListIngredientsFragment(id)
                                )
                            }
                        }
                    }
                }
            }

        })
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}