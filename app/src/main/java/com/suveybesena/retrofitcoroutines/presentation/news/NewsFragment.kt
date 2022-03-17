package com.suveybesena.retrofitcoroutines.presentation.news

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.suveybesena.retrofitcoroutines.R
import com.suveybesena.retrofitcoroutines.adapters.NewsAdapter
import com.suveybesena.retrofitcoroutines.databinding.FragmentNewsBinding
import com.suveybesena.retrofitcoroutines.presentation.MainActivity
import com.suveybesena.retrofitcoroutines.common.Resources


class NewsFragment : Fragment() {

    lateinit var viewModel: NewsViewModel
    lateinit var newsAdapter: NewsAdapter

    private lateinit var binding: FragmentNewsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).newsViewModel
        setupRecyclerView()
        observeData()

    }

    private fun observeData() {
        viewModel.news.observe(viewLifecycleOwner, Observer { response ->
            when (response) {
                is Resources.success -> {
                    response.data?.let { newsResponse ->
                        newsAdapter.differ.submitList(newsResponse.articles)


                    }
                }
            }
        })
    }

    private fun setupRecyclerView() {
        newsAdapter = NewsAdapter()
        binding.recyclerView.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)

        }
        newsAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("article", it)
            }
            findNavController().navigate(R.id.action_newsFragment_to_articleFragment, bundle)
        }

    }


}


