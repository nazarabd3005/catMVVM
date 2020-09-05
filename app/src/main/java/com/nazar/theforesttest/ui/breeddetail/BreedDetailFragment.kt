package com.nazar.theforesttest.ui.breeddetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.nazar.theforesttest.core.utils.Status
import com.nazar.theforesttest.databinding.FragmentBreedDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class BreedDetailFragment : Fragment() {

    private lateinit var binding: FragmentBreedDetailBinding

    private val breedDetailViewModel: BreedDetailViewModel by viewModels()

    private val adapter: BreedImageAdapter = BreedImageAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBreedDetailBinding.inflate(layoutInflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
            viewmodel = breedDetailViewModel
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerBreed.adapter = adapter
        arguments?.let {
            val safeArgs = BreedDetailFragmentArgs.fromBundle(it)
            breedDetailViewModel.load(safeArgs.id, safeArgs.name)
        }

        breedDetailViewModel.getbreedImages().observe(viewLifecycleOwner, Observer {
            if (it.status == Status.SUCCESS) {
                if (it.data!!.size > 1)
                    adapter.submitList(it.data!!.subList(1, it.data!!.size - 1))
            }
        })

        requireActivity().onBackPressedDispatcher.addCallback {
            binding.holderLoader.reveal()
            Observable.timer(500, TimeUnit.MILLISECONDS).observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    findNavController().popBackStack()
                }
        }

    }


}