package co.penguinLab.rapidapi.ui.base

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import co.penguinLab.rapidapi.R
import dagger.android.AndroidInjection
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


abstract class BaseActivity<DB : ViewDataBinding, VM : ViewModel> : DaggerAppCompatActivity() {
    @Inject
    internal lateinit var viewModelProviderFactory: ViewModelProvider.Factory
    lateinit var binding: DB
    lateinit var viewModel: VM
    abstract val viewModelClass: Class<VM>
    abstract val layoutRes: Int


    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutRes)
        viewModel = ViewModelProviders.of(this, viewModelProviderFactory).get(viewModelClass)
        initBottomNavigationView()
    }

    fun loadFragment(
        containerId: Int,
        fragment: androidx.fragment.app.Fragment,
        addToBackStack: Boolean
    ) {
        val fm = supportFragmentManager
        val ft = fm.beginTransaction()
        if (addToBackStack) {
            ft.addToBackStack("")
        }
        ft.replace(containerId, fragment)
        ft.commit()
    }

    protected fun isScreenActive(fragmentClassName: String?): Boolean {
        return supportFragmentManager.fragments[0]::class.java.simpleName == fragmentClassName
    }

    private fun initBottomNavigationView() {
        bottom_navigation.setOnNavigationItemSelectedListener { item: MenuItem ->
            return@setOnNavigationItemSelectedListener when (item.itemId) {
                R.id.nav_search -> {
                    Toast.makeText(
                        this,
                        "nav_favorites item click",
                        Toast.LENGTH_SHORT
                    ).show()
                    true
                }
                R.id.nav_filter -> {
                    Toast.makeText(
                        this,
                        "nav_filter item click",
                        Toast.LENGTH_SHORT
                    ).show()
                    true
                }
                else -> false
            }
        }
    }
}