package com.nazar.theforesttest.ui.breedlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.nazar.theforesttest.R
import com.nazar.theforesttest.databinding.FragmentBreedDropdownListBinding
import com.nazar.theforesttest.utils.hiltNavGraphViewModel
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class BreedDropdownListFragment : Fragment() {

    private lateinit var binding: FragmentBreedDropdownListBinding

    private val breedDropdownListViewModel: BreedDropdownListViewModel by hiltNavGraphViewModel(R.id.breed_navigation)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBreedDropdownListBinding.inflate(layoutInflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
            viewmodel = breedDropdownListViewModel
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        breedDropdownListViewModel.getBreed().observe(requireActivity(), { breed ->
            if (breed == null)
                return@observe

            binding.holderLoader.reveal()
            Observable.timer(1000, TimeUnit.MILLISECONDS).observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    val directions =
                        BreedDropdownListFragmentDirections.actionBreedDropdownListFragmentToBreedDetailFragment(
                            breed.id, breed.name
                        )
                    findNavController().navigate(directions)
                    breedDropdownListViewModel.clear()
                }

        })
    }


}