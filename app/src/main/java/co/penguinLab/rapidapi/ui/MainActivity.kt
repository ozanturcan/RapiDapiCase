package co.penguinLab.rapidapi.ui

import android.os.Bundle
import co.penguinLab.common.runIfNull
import co.penguinLab.common.setupWithNavController
import co.penguinLab.rapidapi.R
import co.penguinLab.rapidapi.databinding.ActivityMainBinding
import co.penguinLab.rapidapi.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseActivity<ActivityMainBinding, MainActivityViewModel>() {

    override val viewModelClass: Class<MainActivityViewModel> = MainActivityViewModel::class.java
    override val layoutRes: Int = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        savedInstanceState.runIfNull {
            setupBottomNavigationBar()
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        // Now that BottomNavigationBar has restored its instance state
        // and its selectedItemId, we can proceed with setting up the
        // BottomNavigationBar with Navigation
        setupBottomNavigationBar()
    }

    /**
     * Called on first creation and when restoring state.
     */
    private fun setupBottomNavigationBar() {


        // 2 navigation files exist for 4 bottomNavigationView item
        val navGraphIds = listOf(
            R.navigation.nav_search,
            R.navigation.nav_filter
        )

        // Connect the bottom navigation view with NavHostFragment
        val controller = bottom_navigation.setupWithNavController(
            navGraphIds = navGraphIds,
            fragmentManager = supportFragmentManager,
            containerId = R.id.fragment_frame,
            intent = intent
        )
        viewModel.currentNavController = controller
    }

    override fun onSupportNavigateUp(): Boolean {
        return viewModel.currentNavController?.value?.navigateUp() ?: false
    }
}