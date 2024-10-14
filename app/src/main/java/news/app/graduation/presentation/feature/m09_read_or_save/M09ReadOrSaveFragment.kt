package news.app.graduation.presentation.feature.m09_read_or_save

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import news.app.graduation.core.utils.Utility
import news.app.graduation.data.local.dao.NewsUrlDao
import news.app.graduation.data.local.database.AppLocalDatabase
import news.app.graduation.data.local.entity.NewsLocal
import news.app.graduation.databinding.M09ReadOrSaveFragmentBinding
import news.app.graduation.presentation.NavigationManager
import news.app.graduation.presentation.core.base.BaseFragment
import news.app.graduation.presentation.feature.m09_read_or_save.adapter.PersonalAdapter
import timber.log.Timber

@AndroidEntryPoint
class M09ReadOrSaveFragment: BaseFragment<M09ReadOrSaveFragmentBinding>(M09ReadOrSaveFragmentBinding::inflate) {
    private var type: Boolean? = null
    private var listData: MutableList<NewsLocal>? = null
    private var newsUrlDao: NewsUrlDao? = null
    private var adapter: PersonalAdapter? = null

    companion object {
        const val IS_READ = "IS_READ"
        fun newInstance(isRead: Boolean): M09ReadOrSaveFragment {
            val args = Bundle()
            args.putBoolean(IS_READ, isRead)
            val fragment = M09ReadOrSaveFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun initArgs() {
        super.initArgs()
        type = arguments?.getBoolean(IS_READ)
    }

    override fun initView() {
        setupRecyclerView()
        bindView()
    }

    private fun bindView() {
        newsUrlDao = AppLocalDatabase.getDatabase(requireContext()).newsUrlDao()
        bindingOrNull?.rlTitle?.tvTitleToolbarZone?.text = if (type == true) "Tin đã đọc" else "Tin đã lưu"
        bindingOrNull?.rlTitle?.imgBackCustom?.setOnClickListener {
            NavigationManager.getInstance().popBackStack()
        }
        getDataLocal()
    }

    private fun setupRecyclerView() {
        bindingOrNull?.rcvNews?.layoutManager = Utility.getLayoutVertical(context)
        adapter = PersonalAdapter(requireActivity())
        binding.rcvNews.adapter = adapter
    }

    private fun getDataLocal() {
        lifecycleScope.launch {
            listData = if (type == true) {
                newsUrlDao?.getAll() ?: mutableListOf()
            } else {
                newsUrlDao?.getAllNewsSave() ?: mutableListOf()
            }
            adapter?.setData(listData ?: mutableListOf())
        }
    }

    override fun initObserver() {

    }

    override fun getData() {

    }

    override fun onClick(v: View?) {

    }
}