package com.suveybesena.retrofitcoroutines.presentation.savednews


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.suveybesena.retrofitcoroutines.adapters.NewsAdapter
import com.suveybesena.retrofitcoroutines.databinding.FragmentSavedNewsBinding
import com.suveybesena.retrofitcoroutines.presentation.MainActivity
import kotlinx.android.synthetic.main.fragment_news.*


class SavedNewsFragment : Fragment() {
    lateinit var viewModel: SavedNewsViewModel
    lateinit var newsAdapter: NewsAdapter

    private lateinit var binding: FragmentSavedNewsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSavedNewsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = (activity as MainActivity).savedNewsViewModel
        setupRecyclerView()
        observeData()
        initList()
    }

    private fun initList() {
        val itemTouchHelperCallBack = object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val article = newsAdapter.differ.currentList[position]
                viewModel.deleteArticle(article)
                Snackbar.make(requireView(), "Sucessfully deleted.", Snackbar.LENGTH_LONG).apply {
                    setAction("Undo") {
                        viewModel.saveArticle(article)
                    }.show()
                }
            }
        }
        ItemTouchHelper(itemTouchHelperCallBack).apply {
            attachToRecyclerView(recyclerView)
        }
    }

    private fun observeData() {
        //viewModel.getSavedNews()
        //viewModel.getSavedNews.observe(viewLifecycleOwner, Observer { list ->
        //    newsAdapter.differ.submitList(list)
        //})

        viewModel.getSavedNews().observe(viewLifecycleOwner, Observer { article ->
            newsAdapter.differ.submitList(article)

        })
    }

    private fun setupRecyclerView() {

        newsAdapter = NewsAdapter()
        binding.recyclerView.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)

        }

    }
}


