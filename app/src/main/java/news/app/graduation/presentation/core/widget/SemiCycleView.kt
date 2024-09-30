package news.app.graduation.presentation.core.widget

import android.animation.PropertyValuesHolder
import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import news.app.graduation.R

class SemiCycleView (context: Context, attrs: AttributeSet) : View(context, attrs) {
    private val backgroundPaint = Paint()
    private val mainPain = Paint()
    private var progress = 0f
    private val margin: Float = resources.getDimension(R.dimen.size_6)

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val frame = RectF(margin, margin, width.toFloat() - margin, height.toFloat() - margin)
        canvas.drawArc(
            frame,
            -180f,
            180f,
            true,
            backgroundPaint
        )
        progress.times(180f).let { canvas.drawArc(frame, -180f, it, true, mainPain) }
    }

    fun setProcess(v: Float) {
        val currentProgress = this.progress
        val animator =
            ValueAnimator().apply { // Thực hiện hiệu ứng chuyển động khi thay đổi progress
                setValues(
                    PropertyValuesHolder.ofFloat(
                        "percent",
                        currentProgress, v
                    )
                )
                duration = 300
                interpolator = AccelerateDecelerateInterpolator()

                addUpdateListener {
                    val newValue = it.getAnimatedValue("percent") as Float
                    this@SemiCycleView.progress = newValue
                    invalidate()
                }
            }
        animator.start()
    }

    fun setUpUI(colorRes: Int?) {
        setupPaint(backgroundPaint, R.color.color_line)
        setupPaint(mainPain, colorRes)
    }

    private fun setupPaint(paint: Paint?, colorRes: Int?) {
        paint?.isAntiAlias = true   //vien view min hon
        paint?.color = context.resources.getColor(R.color.primary)      //mau cua duong tron
        paint?.style = Paint.Style.STROKE   //chon dang stroke
        paint?.strokeCap = Paint.Cap.SQUARE     //hinh dang 2 dau
        paint?.strokeWidth = resources.getDimension(R.dimen.size_12)    //do rong duong tron
    }
}