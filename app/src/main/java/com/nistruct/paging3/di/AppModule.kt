package com.nistruct.paging3.di

import android.content.Context
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.room.Room
import com.nistruct.paging3.Constants.BASE_URL
import com.nistruct.paging3.data.local.BeerDatabase
import com.nistruct.paging3.data.local.BeerEntity
import com.nistruct.paging3.data.remote.BeerApi
import com.nistruct.paging3.repo.BeerRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@OptIn(ExperimentalPagingApi::class)
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideBeerDatabase ( @ApplicationContext context  :Context) : BeerDatabase{
        return Room.databaseBuilder(context,
        BeerDatabase::class.java,
        "beers.db").build()
    }

    @Provides
    @Singleton
    fun provideBeerApi():BeerApi{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create()
    }

    @Provides
    @Singleton
    fun provideBeerPager(beerDb : BeerDatabase,beerApi: BeerApi) : Pager<Int, BeerEntity> {
        return Pager(config = PagingConfig(pageSize = 20),
            remoteMediator = BeerRepository(beerApi, beerDb),
            pagingSourceFactory = {beerDb.dao.pagingSource()})
    }

}