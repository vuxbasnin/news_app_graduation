package news.app.graduation

import android.os.Bundle
import androidx.fragment.app.FragmentManager
import dagger.hilt.android.AndroidEntryPoint
import news.app.graduation.core.utils.PreferenceHelper
import news.app.graduation.databinding.ActivityMainBinding
import news.app.graduation.presentation.NavigationManager
import news.app.graduation.presentation.core.base.BaseActivity

@AndroidEntryPoint
class MainActivity : BaseActivity(), FragmentManager.OnBackStackChangedListener {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        NavigationManager.getInstance().init(this, supportFragmentManager, R.id.fragment_container)
        PreferenceHelper.init(this)
    }

    override fun onBackStackChanged() {

    }
}