package co.penguinLab.rapidapi.ui.filter

import co.penguinLab.rapidapi.di.scope.FragmentScope
import dagger.Module
import dagger.Provides

@Module
class FilterFragmentModule {

    @Provides
    @FragmentScope
    fun provideFilterResultFeedAdapter(): FilterResultFeedAdapter {
        return FilterResultFeedAdapter()
    }
}