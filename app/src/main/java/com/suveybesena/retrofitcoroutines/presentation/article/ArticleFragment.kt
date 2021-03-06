package com.suveybesena.retrofitcoroutines.presentation.article

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.suveybesena.retrofitcoroutines.databinding.FragmentArticleBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArticleFragment : Fragment() {
    private val viewModel: ArticleViewModel by viewModels()
    private lateinit var binding: FragmentArticleBinding
    val args: ArticleFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentArticleBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeData()
    }

    private fun observeData() {
        val article = args.article
        try {
            binding.webView.apply {
                webViewClient = WebViewClient()
                article.url?.let { loadUrl(it) }
            }
        } catch (e: Exception) {
            println(e.localizedMessage)
        }

        binding.fab.setOnClickListener {
            viewModel.saveArticle(article)
            Snackbar.make(requireView(), "Article Saved Succesfully", Snackbar.LENGTH_LONG).show()
        }
    }

}