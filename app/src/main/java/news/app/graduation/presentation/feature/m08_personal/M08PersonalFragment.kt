package news.app.graduation.presentation.feature.m08_personal

import android.os.Bundle
import android.view.View
import dagger.hilt.android.AndroidEntryPoint
import news.app.graduation.databinding.M08PersonalFragmentBinding
import news.app.graduation.presentation.NavigationManager
import news.app.graduation.presentation.core.base.BaseFragment
import news.app.graduation.presentation.feature.m09_read_or_save.M09ReadOrSaveFragment

@AndroidEntryPoint
class M08PersonalFragment :
    BaseFragment<M08PersonalFragmentBinding>(M08PersonalFragmentBinding::inflate) {

    companion object {
        fun newInstance(): M08PersonalFragment {
            val args = Bundle()
            val fragment = M08PersonalFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun initView() {
        bindView()
    }

    private fun bindView() {
        bindingOrNull?.rlTitle?.tvTitleToolbarZone?.text = "Trang cá nhân"
        bindingOrNull?.rlTitle?.imgBackCustom?.setOnClickListener {
            NavigationManager.getInstance().popBackStack()
        }
        bindingOrNull?.txtNewsRead?.setOnClickListener {
            goToSaveOrRead(true)
        }

        bindingOrNull?.txtNewsSave?.setOnClickListener {
            goToSaveOrRead(false)
        }
    }

    override fun initObserver() {

    }

    override fun getData() {

    }

    override fun onClick(v: View?) {

    }

    private fun goToSaveOrRead(type: Boolean) {     //true -> read, false -> save
        NavigationManager.getInstance().openFragment(M09ReadOrSaveFragment.newInstance(type))
    }
}