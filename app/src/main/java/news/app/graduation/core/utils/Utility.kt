package news.app.graduation.core.utils

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.provider.Settings
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import news.app.graduation.R
import news.app.graduation.presentation.core.widget.LinearLayoutManagerWrapper

object Utility {
    @SuppressLint("HardwareIds")
    fun getDeviceId(context: Context?): String {
        return Settings.Secure.getString(context?.contentResolver, Settings.Secure.ANDROID_ID)
    }

    fun getLayoutVertical(context: Context?): LinearLayoutManager {
        return LinearLayoutManagerWrapper(context, LinearLayoutManager.VERTICAL, false)
    }

    fun getLayoutHorizontal(context: Context?): LinearLayoutManager {
        return LinearLayoutManagerWrapper(context, LinearLayoutManager.HORIZONTAL, false)
    }

    fun setImage(context: Context, imageView: ImageView, imageUrl: String?) {
        if (imageUrl.isNullOrBlank()) {
            imageView.setImageResource(R.drawable.img_error)
            imageView.adjustViewBounds = true
            imageView.scaleType = ImageView.ScaleType.CENTER_CROP
            return
        }

        val request = RequestOptions().diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .placeholder(getProgressDrawable(context))

        Glide.with(context).load(imageUrl).apply(request).thumbnail(0.5f)
            .error(R.drawable.img_error).listener(object : RequestListener<Drawable?> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable?>,
                    isFirstResource: Boolean
                ): Boolean {
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable,
                    model: Any,
                    target: Target<Drawable?>?,
                    dataSource: DataSource,
                    isFirstResource: Boolean
                ): Boolean {
                    return false
                }

            }).into(imageView)
    }

    private fun getProgressDrawable(context: Context): CircularProgressDrawable {
        val circularProgressDrawable = CircularProgressDrawable(context)
        circularProgressDrawable.strokeWidth = 5f
        circularProgressDrawable.centerRadius = 30f
        circularProgressDrawable.setColorFilter(
            ContextCompat.getColor(context, R.color.primary),
            PorterDuff.Mode.SRC_IN
        )
        circularProgressDrawable.start()
        return circularProgressDrawable
    }
}