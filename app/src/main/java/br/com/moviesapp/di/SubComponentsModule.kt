package br.com.moviesapp.di

import br.com.moviesapp.di.main.MainComponent
import dagger.Module

@Module(
    subcomponents = [
        MainComponent::class
    ]
)
class SubComponentsModule