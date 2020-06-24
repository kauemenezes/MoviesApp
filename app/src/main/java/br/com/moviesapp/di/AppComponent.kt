package br.com.moviesapp.di

import android.content.Context
import br.com.moviesapp.di.main.MainComponent
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(
        modules = [
            AppModule::class,
            SubComponentsModule::class
        ]
)
interface AppComponent  {

    @Component.Builder
    interface Builder{

        @BindsInstance
        fun application(context: Context): Builder

        fun build(): AppComponent
    }

    fun mainComponent(): MainComponent.Factory
}