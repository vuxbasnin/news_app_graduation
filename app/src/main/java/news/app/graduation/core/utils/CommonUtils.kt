package news.app.graduation.core.utils

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Point
import android.media.AudioManager
import android.net.wifi.WifiManager
import android.provider.Settings
import android.text.Html
import android.text.SpannableStringBuilder
import android.text.TextUtils
import android.text.style.ForegroundColorSpan
import android.view.WindowManager
import android.widget.EditText
import android.widget.TextView

object CommonUtils {
    var videoIdLogging: String? = null
    var dataVideoLogging = ""
    private var vietIdLogging = "0"

    //     check null object , string , properties
    fun isNullOrEmpty(input: Any?): Boolean {
        if (input == null) {
            return true
        }
        if (input is String) {
            return input.toString().trim { it <= ' ' }.isEmpty()
        }
        if (input is EditText) {
            return input.text.toString().trim { it <= ' ' }.isEmpty()
        }
        if (input is List<*>) {
            return input.isEmpty()
        }
        return if (input is HashMap<*, *>) {
            input.isEmpty()
        } else false
    }

    fun setColorActor(view: TextView, textName: String, subtext: String, colorNameResId: Int, colorSubTextResId: Int) {
        try {
            val context = view.context
            val spannableBuilder = SpannableStringBuilder()

            // Set the name text with the desired color
            spannableBuilder.append(textName)
            spannableBuilder.setSpan(ForegroundColorSpan(context.resources.getColor(colorNameResId, null)), 0, textName.length, 0)

            // Set the subtext with the desired color
            spannableBuilder.append(" ")
            spannableBuilder.append(subtext)
            val start = textName.length + 1
            val end = start + subtext.length
            spannableBuilder.setSpan(ForegroundColorSpan(context.resources.getColor(colorSubTextResId, null)), start, end, 0)
            view.text = spannableBuilder
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun getColoredSpanned(text: String, color: String): String {
        return "<font color=$color>$text</font>"
    }

    fun getBoldSpanned(text: String, color: String): String {
        return "<font color=$color><b>$text</b></font>"
    }

    fun getUnderLineSpanned(text: String, color: String): String {
        return "<font color=$color><u>$text</u></font>"
    }

    fun shareInApp(context: Context, url: String?) {
        try {
            val sharingIntent = Intent(Intent.ACTION_SEND)
            sharingIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            sharingIntent.putExtra(Intent.EXTRA_TEXT, url)
            sharingIntent.type = "text/plain"
            val chooserIntent = Intent.createChooser(sharingIntent, url)
            chooserIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(chooserIntent)
        } catch (e: Exception) {
            e.stackTrace
        }
    }

    fun openGmail(context: Context, email: String) {
        try {
            val intent = Intent(Intent.ACTION_SEND)
            val recipients = arrayOf(email)
            intent.putExtra(Intent.EXTRA_EMAIL, recipients)
            intent.type = "text/html"
            intent.setPackage("com.google.android.gm")
            context.startActivity(Intent.createChooser(intent, "Send mail"))
        } catch (e: Exception) {
            e.stackTrace
        }
    }

    @SuppressLint("all")
    fun getDeviceId(context: Context?): String {
        return if (context == null) "-1" else try {
            Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
        } catch (e: Exception) {
            "-1"
        }
    }

    fun isDomainApp(url: String): Boolean {
        return (url.startsWith("https://thanhnien.vn") || url.startsWith("http://thanhnien.vn")
                || url.startsWith("https://m.thanhnien.vn") || url.startsWith(
            "http://m.thanhnien.vn"
        ) || url.startsWith("http://app.thanhnien.vn/app"))
    }

    fun isNumeric(str: String): Boolean {
        try {
            val d = str.toDouble()
        } catch (nfe: NumberFormatException) {
            return false
        }
        return true
    }

    fun getDownTest(context: Context?): Int {
        if (context == null) return -1
        val wifiManager = context.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
        val wifiInfo = wifiManager.connectionInfo
        var linkSpeed = 0
        if (wifiInfo != null) {
            linkSpeed = wifiInfo.linkSpeed
        }
        return linkSpeed
    }

    fun getAppVersion(context: Context?): Int {
        var versionCode = 400
        if (context == null) return versionCode
        try {
            versionCode = context.packageManager.getPackageInfo(context.packageName, 0).versionCode
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }
        return versionCode
    }

    fun getCountVolume(activity: Context?): Int {
        if (activity == null) return 0
        val am = activity.getSystemService(Context.AUDIO_SERVICE) as AudioManager
        val volume_level = am.getStreamVolume(AudioManager.STREAM_MUSIC) * 100
        val max = am.getStreamMaxVolume(AudioManager.STREAM_MUSIC)
        return volume_level / max
    }

    fun returnAview(width: Int, height: Int, context: Context): Double {
        var twoDigitsF: Double
        val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display = wm.defaultDisplay
        val size = Point()
        display.getSize(size)
        val widthScreen = size.x
        val heightScreen = size.y
        val aView = (width * height * 100 / (widthScreen * heightScreen)).toDouble()
        twoDigitsF = aView / 100
        twoDigitsF = round(twoDigitsF, 2)
        return twoDigitsF
    }

    fun setTextBoldBetween(view: TextView, nameFirst: String, textBetween: String, nameEnd: String) {
        try {
            val mNameFirst = getColoredSpanned(nameFirst, "#000000")
            val mNameEnd = getColoredSpanned(nameEnd, "#000000")
            val mTextBetween = getBoldSpanned(textBetween, "#000000")
            view.text = Html.fromHtml("$mNameFirst $mTextBetween $mNameEnd")
        } catch (e: Exception) {
            e.stackTrace
        }
    }

    fun setTextUnderLine(view: TextView, nameFirst: String, nameEnd: String) {
        try {
            val mNameFirst = getColoredSpanned(nameFirst, "#000000")
            val mNameEnd = getUnderLineSpanned(nameEnd, "#000000")
            view.text = Html.fromHtml("$mNameFirst $mNameEnd")
        } catch (e: Exception) {
            e.stackTrace
        }
    }

    fun round(valueToRound: Double, numberOfDecimalPlaces: Int): Double {
        val multipicationFactor = Math.pow(10.0, numberOfDecimalPlaces.toDouble())
        val interestedInZeroDPs = valueToRound * multipicationFactor
        return Math.round(interestedInZeroDPs) / multipicationFactor
    }

    fun isEmpty(s: String?): Boolean {
        return TextUtils.isEmpty(s)
    }
}
