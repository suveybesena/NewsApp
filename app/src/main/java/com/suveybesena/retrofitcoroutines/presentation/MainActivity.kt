package com.suveybesena.retrofitcoroutines.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.suveybesena.retrofitcoroutines.R
import com.suveybesena.retrofitcoroutines.presentation.savednews.SavedNewsModelProviderFactory
import com.suveybesena.retrofitcoroutines.data.database.ArticleDatabase
import com.suveybesena.retrofitcoroutines.data.repository.NewsRepository
import com.suveybesena.retrofitcoroutines.presentation.article.ArticleViewModel
import com.suveybesena.retrofitcoroutines.presentation.article.ArticleViewModelProviderFactory
import com.suveybesena.retrofitcoroutines.presentation.news.NewsViewModel
import com.suveybesena.retrofitcoroutines.presentation.news.NewsViewModelProviderFactory
import com.suveybesena.retrofitcoroutines.presentation.savednews.SavedNewsViewModel


class MainActivity : AppCompatActivity() {

    lateinit var newsViewModel: NewsViewModel
    lateinit var savedNewsViewModel: SavedNewsViewModel
    lateinit var articleViewModel : ArticleViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val newsRepository = NewsRepository(ArticleDatabase(this))

        val newsviewModelProviderFactory = NewsViewModelProviderFactory(newsRepository)
        newsViewModel =
            ViewModelProvider(this, newsviewModelProviderFactory).get(NewsViewModel::class.java)

        val savedNewsModelProviderFactory = SavedNewsModelProviderFactory(newsRepository)
        savedNewsViewModel = ViewModelProvider(
            this,
            savedNewsModelProviderFactory
        ).get(SavedNewsViewModel::class.java)

        val articleViewModelProviderFactory = ArticleViewModelProviderFactory(newsRepository)
        articleViewModel = ViewModelProvider(
            this,
            articleViewModelProviderFactory
        ).get(ArticleViewModel::class.java)



        this.supportActionBar?.hide()

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController: NavController = navHostFragment.navController

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation_view)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.newsFragment, R.id.savedNewsFragment
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        bottomNavigationView.setupWithNavController(navController)


    }
}