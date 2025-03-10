package news.app.graduation.presentation.core.widget.swipe.model;


import android.graphics.Color;
import android.view.View;

import androidx.annotation.ColorInt;
import androidx.annotation.FloatRange;
import androidx.customview.widget.ViewDragHelper;

import com.news.thanhnien.presentation.core.widget.swipe.model.SliderListener;
import com.news.thanhnien.presentation.core.widget.swipe.model.SliderPosition;

import java.util.List;


/**
 * This class contains the configuration information for all the options available in
 * this library
 */
public class SliderConfig {

    private int colorPrimary = -1;
    private int colorSecondary = -1;
    private float touchSize = -1f;
    private float sensitivity = 1f;
    private int scrimColor = Color.BLACK;
    private float scrimStartAlpha = 0.8f;
    private float scrimEndAlpha = 0f;
    private float velocityThreshold = 5f;
    private float distanceThreshold = 0.4f;
    private boolean edgeOnly = false;
    private float edgeSize = 0.18f;

    private List<View> touchDisabledViews;

    private SliderPosition position = SliderPosition.LEFT;
    private SliderListener listener;


    private SliderConfig() {
        // Unused.
    }


    /***********************************************************************************************
     *
     * Getters
     *
     */

    /**
     * Get the primary color that the slider will interpolate. That is this color is the color
     * of the status bar of the Activity you are returning to
     *
     * @return the primary status bar color
     */
    public int getPrimaryColor() {
        return colorPrimary;
    }


    /**
     * Get the secondary color that the slider will interpolatel That is the color of the Activity
     * that you are making slidable
     *
     * @return the secondary status bar color
     */
    public int getSecondaryColor() {
        return colorSecondary;
    }


    /**
     * Get the color of the background scrim
     *
     * @return the scrim color integer
     */
    @ColorInt
    public int getScrimColor() {
        return scrimColor;
    }

    public void setScrimColor(@ColorInt int scrimColor) {
        this.scrimColor = scrimColor;
    }

    /**
     * Get teh start alpha value for when the activity is not swiped at all
     *
     * @return the start alpha value (0.0 to 1.0)
     */
    public float getScrimStartAlpha() {
        return scrimStartAlpha;
    }

    public void setScrimStartAlpha(float scrimStartAlpha) {
        this.scrimStartAlpha = scrimStartAlpha;
    }

    /**
     * Get the end alpha value for when the user almost swipes the activity off the screen
     *
     * @return the end alpha value (0.0 to 1.0)
     */
    public float getScrimEndAlpha() {
        return scrimEndAlpha;
    }

    public void setScrimEndAlpha(float scrimEndAlpha) {
        this.scrimEndAlpha = scrimEndAlpha;
    }

    /**
     * Get the position of the slidable mechanism for this configuration. This is the position on
     * the screen that the user can swipe the activity away from
     *
     * @return the slider position
     */
    public SliderPosition getPosition() {
        return position;
    }

    /**
     * Get the touch 'width' to be used in the gesture detection. This value should incorporate with
     * the device's touch slop
     *
     * @return the touch area size
     */
    public float getTouchSize() {
        return touchSize;
    }

    public void setTouchSize(float touchSize) {
        this.touchSize = touchSize;
    }

    /**
     * Get the velocity threshold at which the slide action is completed regardless of offset
     * distance of the drag
     *
     * @return the velocity threshold
     */
    public float getVelocityThreshold() {
        return velocityThreshold;
    }

    public void setVelocityThreshold(float velocityThreshold) {
        this.velocityThreshold = velocityThreshold;
    }

    /**
     * Get at what % of the screen is the minimum viable distance the activity has to be dragged
     * in-order to be slinged off the screen
     *
     * @return the distant threshold as a percentage of the screen size (width or height)
     */
    public float getDistanceThreshold() {
        return distanceThreshold;
    }

    public void setDistanceThreshold(float distanceThreshold) {
        this.distanceThreshold = distanceThreshold;
    }

    /**
     * Get the touch sensitivity set in the {@link ViewDragHelper} when
     * creating it.
     *
     * @return the touch sensitivity
     */
    public float getSensitivity() {
        return sensitivity;
    }

    public void setSensitivity(float sensitivity) {
        this.sensitivity = sensitivity;
    }

    /**
     * Get the slidr listener set by the user to respond to certain events in the sliding
     * mechanism.
     *
     * @return the slidr listener
     */
    public SliderListener getListener() {
        return listener;
    }

    /**
     * Has the user configured slidr to only catch at the edge of the screen ?
     *
     * @return true if is edge capture only
     */
    public boolean isEdgeOnly() {
        return edgeOnly;
    }

    /**
     * Get the size of the edge field that is catchable
     *
     * @return the size of the edge that is grabable
     * @see #isEdgeOnly()
     */
    public float getEdgeSize(float size) {
        return edgeSize * size;
    }

    public List<View> getTouchDisabledViews() {
        return touchDisabledViews;
    }

    /***********************************************************************************************
     *
     * Setters
     *
     */

    public void setTouchDisabledViews(List<View> views) {
        touchDisabledViews = views;
    }

    public void setColorPrimary(int colorPrimary) {
        this.colorPrimary = colorPrimary;
    }

    public void setColorSecondary(int colorSecondary) {
        this.colorSecondary = colorSecondary;
    }

    /**
     * The Builder for this configuration class. This is the only way to create a
     * configuration
     */
    public static class Builder {

        private final SliderConfig config;

        public Builder() {
            config = new SliderConfig();
        }

        public Builder primaryColor(@ColorInt int color) {
            config.colorPrimary = color;
            return this;
        }

        public Builder secondaryColor(@ColorInt int color) {
            config.colorSecondary = color;
            return this;
        }

        public Builder position(SliderPosition position) {
            config.position = position;
            return this;
        }

        public Builder touchSize(float size) {
            config.touchSize = size;
            return this;
        }

        public Builder sensitivity(float sensitivity) {
            config.sensitivity = sensitivity;
            return this;
        }

        public Builder scrimColor(@ColorInt int color) {
            config.scrimColor = color;
            return this;
        }

        public Builder scrimStartAlpha(@FloatRange(from = 0.0, to = 1.0) float alpha) {
            config.scrimStartAlpha = alpha;
            return this;
        }

        public Builder scrimEndAlpha(@FloatRange(from = 0.0, to = 1.0) float alpha) {
            config.scrimEndAlpha = alpha;
            return this;
        }

        public Builder velocityThreshold(float threshold) {
            config.velocityThreshold = threshold;
            return this;
        }

        public Builder distanceThreshold(@FloatRange(from = .1f, to = .9f) float threshold) {
            config.distanceThreshold = threshold;
            return this;
        }

        public Builder edge(boolean flag) {
            config.edgeOnly = flag;
            return this;
        }

        public Builder edgeSize(@FloatRange(from = 0f, to = 1f) float edgeSize) {
            config.edgeSize = edgeSize;
            return this;
        }

        public Builder listener(SliderListener listener) {
            config.listener = listener;
            return this;
        }

        public Builder touchDisabledViews(List<View> views) {
            config.touchDisabledViews = views;
            return this;
        }

        public SliderConfig build() {
            return config;
        }

    }

}
