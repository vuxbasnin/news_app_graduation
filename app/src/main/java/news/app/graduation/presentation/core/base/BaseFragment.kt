package news.app.graduation.presentation.core.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber
import news.app.graduation.presentation.NavigationManager

abstract class BaseFragment<T: ViewBinding>(private val bindingInflater: (layoutInflater: LayoutInflater) -> T): Fragment(), View.OnClickListener {
    var _binding: T? = null
    protected val binding get() = _binding!!
    var TAG: String = this.javaClass.simpleName
    var isClickAble = true

    //child
    var tabPosition: Int = -1

    fun isFragmentFocusing(fragment: BaseFragment<*> = this): Boolean {
        val parentFragment = fragment.parentFragment
        //if fragment current check is root(not in tab layout) -> cancel
        if (parentFragment == null)
            return NavigationManager.getInstance().getCurrentFragment() == fragment
        else {
            if (parentFragment is BaseTabFragment<*>) {
                //parent fragment is opening === current fragment checking
                return parentFragment.getViewPager()?.currentItem == fragment.tabPosition && isFragmentFocusing(parentFragment)
            }
        }
        return false
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = bindingInflater.invoke(inflater)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initArgs()
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Timber.d("BaseFragment => onViewCreated")
        initView()
        initObserver()
        lifecycleScope.launch {
            if (isInitGetData()){
                delay(200L)
                getData()
            }
        }
    }

    /**
     * implement function
     */

    open fun initArgs(){}

    open fun resetData(){}

    open fun isInitGetData(): Boolean = true

    abstract fun initView()
    abstract fun initObserver()
    abstract fun getData()

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}