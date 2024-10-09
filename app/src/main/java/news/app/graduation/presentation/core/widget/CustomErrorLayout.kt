package news.app.graduation.presentation.core.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import news.app.graduation.databinding.CustomErrorLayoutBinding


class CustomErrorLayout(context: Context, attrs: AttributeSet? = null) : LinearLayout(context, attrs) {
    var onRetryClickListener: (() -> Unit)? = null
    var binding: CustomErrorLayoutBinding =
        CustomErrorLayoutBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        binding.btnRetry.setOnClickListener {
            onRetryClickListener?.invoke()
        }
    }
}