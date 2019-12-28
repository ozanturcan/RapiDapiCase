package co.penguinLab.rapidapi.di.component

import co.penguinLab.data.di.module.DataModule
import co.penguinLab.rapidapi.di.module.ActivityBuilderModule
import co.penguinLab.rapidapi.di.module.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ActivityBuilderModule::class,
        ViewModelModule::class,
        DataModule::class
    ]
)
interface AppComponent : AndroidInjector<co.penguinLab.rapidapi.Application> {
    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<co.penguinLab.rapidapi.Application>() {
        @BindsInstance
        abstract fun app(application: android.app.Application): Builder
    }
}