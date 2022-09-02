package com.picpay.desafio.android.di

import com.picpay.desafio.android.api.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module //dagger
@InstallIn(SingletonComponent::class) //interface convertida p uma classe
object ServiceModule {

    @Singleton
    @Provides
    fun returnRepository(): Repository{
        return Repository()
    } //isso td p injetar no construtor da mainviewmodel

}