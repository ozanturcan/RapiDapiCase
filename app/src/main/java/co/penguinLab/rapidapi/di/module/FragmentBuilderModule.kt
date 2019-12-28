package co.penguinLab.rapidapi.di.module

import co.penguinLab.rapidapi.di.scope.FragmentScope
import co.penguinLab.rapidapi.ui.detail.CardDetailFragment
import co.penguinLab.rapidapi.ui.detail.CardDetailFragmentModule
import co.penguinLab.rapidapi.ui.filter.FilterFragment
import co.penguinLab.rapidapi.ui.filter.FilterFragmentModule
import co.penguinLab.rapidapi.ui.search.SearchFragment
import co.penguinLab.rapidapi.ui.search.SearchFragmentModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilderModule {

    @FragmentScope
    @ContributesAndroidInjector(modules = [SearchFragmentModule::class])
    abstract fun SearchFragment(): SearchFragment

    @FragmentScope
    @ContributesAndroidInjector(modules = [FilterFragmentModule::class])
    abstract fun FilterFragment(): FilterFragment

    @FragmentScope
    @ContributesAndroidInjector(modules = [CardDetailFragmentModule::class])
    abstract fun CardDetailFragment(): CardDetailFragment

}