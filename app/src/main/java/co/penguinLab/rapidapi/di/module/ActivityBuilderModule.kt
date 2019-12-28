package co.penguinLab.rapidapi.di.module

import co.penguinLab.rapidapi.di.scope.ActivityScope
import co.penguinLab.rapidapi.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [FragmentBuilderModule::class])
    abstract fun bindMainActivity(): MainActivity
}