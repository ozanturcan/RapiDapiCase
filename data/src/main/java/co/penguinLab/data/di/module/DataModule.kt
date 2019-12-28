package co.penguinLab.data.di.module

import dagger.Module

@Module(
    includes = [
        NetworkModule::class]
)
class DataModule