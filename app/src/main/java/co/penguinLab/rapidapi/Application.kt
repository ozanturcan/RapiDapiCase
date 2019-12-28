package co.penguinLab.rapidapi

import android.app.Activity
import android.app.Application
import co.penguinLab.rapidapi.di.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class Application : Application(), HasActivityInjector {

    @Inject
    internal lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector(): AndroidInjector<Activity> {
        return dispatchingAndroidInjector
    }

    override fun onCreate() {
        super.onCreate()

        DaggerAppComponent
            .builder()
            .app(this)
            .create(this)
            .inject(this)
    }
}