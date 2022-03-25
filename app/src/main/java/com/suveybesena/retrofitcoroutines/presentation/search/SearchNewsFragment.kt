package com.suveybesena.retrofitcoroutines.presentation.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.suveybesena.retrofitcoroutines.R
import com.suveybesena.retrofitcoroutines.adapters.NewsAdapter
import com.suveybesena.retrofitcoroutines.common.Resources
import com.suveybesena.retrofitcoroutines.databinding.FragmentSearchNewsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
@AndroidEntryPoint
class SearchNewsFragment : Fragment() {
    private lateinit var binding: FragmentSearchNewsBinding
    private lateinit var searchAdapter: NewsAdapter
    private val viewModel: SearchNewsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchNewsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        observeData()
    }

    private fun observeData() {

        var job: Job? = null
        binding.edtSearch.addTextChangedListener { editable ->
            job?.cancel()
            job = MainScope().launch {
                delay(500L)
                editable?.let {
                    if (editable.toString().isNotEmpty()) {
                        viewModel.searchNews(editable.toString())
                    }
                }
            }
        }


        viewModel.searchNews.observe(viewLifecycleOwner, Observer { response ->
            when (response) {
                is Resources.success -> {
                    response.data?.let { newsResponse ->
                        searchAdapter.differ.submitList(newsResponse.articles)


                    }
                }
            }
        })
    }


    private fun setupRecyclerView() {
        searchAdapter = NewsAdapter()
        binding.searchRecyclerView.apply {
            adapter = searchAdapter
            layoutManager = LinearLayoutManager(activity)
        }
        searchAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("article", it)
            }
            findNavController().navigate(R.id.action_searchNewsFragment_to_articleFragment, bundle)
        }
    }


}