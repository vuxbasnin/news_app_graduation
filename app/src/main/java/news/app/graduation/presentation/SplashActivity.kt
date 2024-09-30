package news.app.graduation.presentation

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import news.app.graduation.MainActivity
import news.app.graduation.core.utils.InternetUtil
import news.app.graduation.core.utils.PreferenceHelper
import news.app.graduation.databinding.ActivitySplashBinding
import news.app.graduation.presentation.core.base.CommonState

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {
    private val viewModel: SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(ActivitySplashBinding.inflate(layoutInflater).root)
        PreferenceHelper.init(this)
        if (InternetUtil.isNetworkAvailable()){
            viewModel.getConfigApp()
        } else {
            //show popup no internet
        }
        initObserver()
    }

    private fun goToMainActivity() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    private fun initObserver() {
        viewModel.splashState.observe(this) { state ->
            when(state) {
                is CommonState.Success -> {
                    runCatching {
                        val data = state.data
                        Timber.d("SplashActivity => data config app $data")
                        goToMainActivity()
                    }.also {
                        goToMainActivity()
                    }
                }

                is CommonState.Fail -> {
                    goToMainActivity()
                }
                is CommonState.Loading -> {

                }
            }
        }
    }
}