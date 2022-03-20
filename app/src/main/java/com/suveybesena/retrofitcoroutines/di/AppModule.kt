package com.suveybesena.retrofitcoroutines.di

import android.content.Context
import androidx.room.Room
import com.suveybesena.retrofitcoroutines.BuildConfig
import com.suveybesena.retrofitcoroutines.data.database.ArticleDao
import com.suveybesena.retrofitcoroutines.data.database.ArticleDatabase
import com.suveybesena.retrofitcoroutines.data.remote.api.ArticleAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideOkHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            if (!BuildConfig.DEBUG) {
                this.level = HttpLoggingInterceptor.Level.NONE
            } else {
                this.level = HttpLoggingInterceptor.Level.BODY
            }
        }
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .writeTimeout(60L, TimeUnit.SECONDS)
            .readTimeout(60L, TimeUnit.SECONDS)
            .connectTimeout(60L, TimeUnit.SECONDS)
            .callTimeout(60L, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        gsonConverterFactory: GsonConverterFactory,
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.API_URL)
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }

    @Provides
    @Singleton
    fun provideGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Provides
    @Singleton
    fun provideMyAPI(retrofit: Retrofit): ArticleAPI {
        return retrofit.create(ArticleAPI::class.java)
    }


    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): ArticleDatabase =
        Room.databaseBuilder(context, ArticleDatabase::class.java, "article_db.db")
            .fallbackToDestructiveMigration().build()

    @Provides
    @Singleton
    fun provideArticleDAO(articleDatabase: ArticleDatabase): ArticleDao {
        return articleDatabase.articleDao()
    }
}