package news.app.graduation.presentation.core.widget

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.constraintlayout.widget.ConstraintLayout
import news.app.graduation.R
import news.app.graduation.core.common.clickWithThrottle
import news.app.graduation.core.common.hide
import news.app.graduation.core.common.show
import news.app.graduation.presentation.my_interface.OnClickCustomTopBar
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


class CustomLayoutToolBarMain(
    context: Context, attrs: AttributeSet? = null,
) : ConstraintLayout(context, attrs) {
    private var btnNotify: ImageView? = null
    var btnMenu: ImageView? = null
    private var onClickCustomTopBar: OnClickCustomTopBar? = null
    private var tvTime: TextView? = null
    private var number_top: TextView? = null
    private var logo: AppCompatImageView? = null

    init {
        bindView()
    }

    private fun bindView() {
        val view = LayoutInflater.from(context).inflate(R.layout.custom_layout_toolbar_main, this)
        tvTime = view.findViewById(R.id.tv_time)
        btnNotify = view.findViewById(R.id.btn_notify)
        btnMenu = view.findViewById(R.id.btn_menu)
        number_top = view.findViewById(R.id.number_top)
        logo = view.findViewById(R.id.appCompatImageView)
        initView()
    }

    fun setCallBack(onClickCustomTopBar: OnClickCustomTopBar) {
        this.onClickCustomTopBar = onClickCustomTopBar
    }

    fun setBadgeNotify(numBadge: Int) {
        when (numBadge) {
            in 1..99 -> {
                number_top.show()
                number_top?.text = numBadge.toString()
            }

            0 -> {
                number_top.hide()
            }

            else -> {
                number_top.show()
                number_top?.text = "99+"
            }
        }
    }

    fun updateWhenHaveNotification() {
        try {
            Handler(Looper.getMainLooper()).postDelayed({
                if (number_top?.text.toString() != "99+") {
                    if (number_top?.text.toString() == "" || number_top?.text.toString()
                            .toInt() <= 0
                    ) {
                        number_top?.text = "1"
                    } else if (this.number_top?.text.toString().toInt() > 99) {
                        number_top?.text = "99+"
                    } else {
                        val number = number_top?.text.toString().toInt()
                        number_top?.text = (number + 1).toString()
                    }
                }
                number_top.show()
            }, 1000)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun initView() {
        val df = SimpleDateFormat("EEEE, dd/MM/yyyy", Locale("vi", "VN"))
        val time: String = df.format(Date())
        tvTime?.text = time
        // logo app
        logo?.let {
//            Glide.with(context)
//                .load(getLogoFromConfig() ?: R.drawable.ic_national_emblem)
//                .error(R.drawable.ic_national_emblem)
//                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
//                .into(it)
        }

        btnNotify?.clickWithThrottle {
            onClickCustomTopBar?.onClickNotify()
        }
        btnMenu?.clickWithThrottle {
            onClickCustomTopBar?.onClickMenu()
        }
    }

//    private fun getLogoFromConfig(): String? {
//        val configResponse = DataConfig.getResponseFromJson()
//        val logo = configResponse?.logo
//        return if (!logo.isNullOrEmpty())
//            logo
//        else
//            null
//    }
}