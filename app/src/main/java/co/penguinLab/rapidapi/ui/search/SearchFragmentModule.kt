package co.penguinLab.rapidapi.ui.search

import co.penguinLab.rapidapi.di.scope.FragmentScope
import dagger.Module
import dagger.Provides

@Module
class SearchFragmentModule {

    @Provides
    @FragmentScope
    fun provideFavouriteTVShowsFeedAdapter(): SearchResultFeedAdapter {
        return SearchResultFeedAdapter()
    }
}