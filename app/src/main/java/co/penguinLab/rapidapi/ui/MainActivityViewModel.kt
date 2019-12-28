package co.penguinLab.rapidapi.ui

import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import co.penguinLab.common.RxAwareViewModel
import javax.inject.Inject

class MainActivityViewModel @Inject constructor() :
    RxAwareViewModel() {

    var currentNavController: LiveData<NavController>? = null

}