package co.penguinLab.rapidapi.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import co.penguinLab.rapidapi.di.ViewModelFactory
import co.penguinLab.rapidapi.di.key.ViewModelKey
import co.penguinLab.rapidapi.ui.MainActivityViewModel
import co.penguinLab.rapidapi.ui.detail.CardDetailViewModel
import co.penguinLab.rapidapi.ui.filter.FilterViewModel
import co.penguinLab.rapidapi.ui.search.SearchViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @IntoMap
    @Binds
    @ViewModelKey(FilterViewModel::class)
    abstract fun provideMoviesViewModel(moviesViewModel: FilterViewModel): ViewModel

    @IntoMap
    @Binds
    @ViewModelKey(SearchViewModel::class)
    abstract fun provideFavouriteViewModel(searchViewModel: SearchViewModel): ViewModel

    @IntoMap
    @Binds
    @ViewModelKey(CardDetailViewModel::class)
    abstract fun provideCardDetailViewModel(cardDetailViewModel: CardDetailViewModel): ViewModel

    @IntoMap
    @Binds
    @ViewModelKey(MainActivityViewModel::class)
    abstract fun provideMainActivityViewModel(mainActivityViewModel: MainActivityViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory
}