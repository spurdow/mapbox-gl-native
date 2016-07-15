// This file is generated. Do not edit.
package com.mapbox.mapboxsdk.style.layers;

import android.annotation.SuppressLint;
import android.support.annotation.ColorInt;

/**
 * Constructs paint/layout properties for Layers
 * @see <a href="https://www.mapbox.com/mapbox-gl-style-spec/#layers>Layer style documentation</a>
 *
 */
public class PropertyFactory {

    public static Property<String> lineCap(@Property.LINE_CAP String value) {
        return new Property<>("line-cap", value);
    }

    public static Property<String> lineJoin(@Property.LINE_JOIN String value) {
        return new Property<>("line-join", value);
    }

    public static Property<Float> lineMiterLimit(Float value) {
        return new Property<>("line-miter-limit", value);
    }

    public static Property<Float> lineRoundLimit(Float value) {
        return new Property<>("line-round-limit", value);
    }

    public static Property<String> symbolPlacement(@Property.SYMBOL_PLACEMENT String value) {
        return new Property<>("symbol-placement", value);
    }

    public static Property<Float> symbolSpacing(Float value) {
        return new Property<>("symbol-spacing", value);
    }

    public static Property<Boolean> symbolAvoidEdges(Boolean value) {
        return new Property<>("symbol-avoid-edges", value);
    }

    public static Property<Boolean> iconAllowOverlap(Boolean value) {
        return new Property<>("icon-allow-overlap", value);
    }

    public static Property<Boolean> iconIgnorePlacement(Boolean value) {
        return new Property<>("icon-ignore-placement", value);
    }

    public static Property<Boolean> iconOptional(Boolean value) {
        return new Property<>("icon-optional", value);
    }

    public static Property<String> iconRotationAlignment(@Property.ICON_ROTATION_ALIGNMENT String value) {
        return new Property<>("icon-rotation-alignment", value);
    }

    public static Property<Float> iconSize(Float value) {
        return new Property<>("icon-size", value);
    }

    public static Property<String> iconTextFit(@Property.ICON_TEXT_FIT String value) {
        return new Property<>("icon-text-fit", value);
    }

    public static Property<Float[]> iconTextFitPadding(Float[] value) {
        return new Property<>("icon-text-fit-padding", value);
    }

    public static Property<String> iconImage(String value) {
        return new Property<>("icon-image", value);
    }

    public static Property<Float> iconRotate(Float value) {
        return new Property<>("icon-rotate", value);
    }

    public static Property<Float> iconPadding(Float value) {
        return new Property<>("icon-padding", value);
    }

    public static Property<Boolean> iconKeepUpright(Boolean value) {
        return new Property<>("icon-keep-upright", value);
    }

    public static Property<Float[]> iconOffset(Float[] value) {
        return new Property<>("icon-offset", value);
    }

    public static Property<String> textPitchAlignment(@Property.TEXT_PITCH_ALIGNMENT String value) {
        return new Property<>("text-pitch-alignment", value);
    }

    public static Property<String> textRotationAlignment(@Property.TEXT_ROTATION_ALIGNMENT String value) {
        return new Property<>("text-rotation-alignment", value);
    }

    public static Property<String> textField(String value) {
        return new Property<>("text-field", value);
    }

    public static Property<String[]> textFont(String[] value) {
        return new Property<>("text-font", value);
    }

    public static Property<Float> textSize(Float value) {
        return new Property<>("text-size", value);
    }

    public static Property<Float> textMaxWidth(Float value) {
        return new Property<>("text-max-width", value);
    }

    public static Property<Float> textLineHeight(Float value) {
        return new Property<>("text-line-height", value);
    }

    public static Property<Float> textLetterSpacing(Float value) {
        return new Property<>("text-letter-spacing", value);
    }

    public static Property<String> textJustify(@Property.TEXT_JUSTIFY String value) {
        return new Property<>("text-justify", value);
    }

    public static Property<String> textAnchor(@Property.TEXT_ANCHOR String value) {
        return new Property<>("text-anchor", value);
    }

    public static Property<Float> textMaxAngle(Float value) {
        return new Property<>("text-max-angle", value);
    }

    public static Property<Float> textRotate(Float value) {
        return new Property<>("text-rotate", value);
    }

    public static Property<Float> textPadding(Float value) {
        return new Property<>("text-padding", value);
    }

    public static Property<Boolean> textKeepUpright(Boolean value) {
        return new Property<>("text-keep-upright", value);
    }

    public static Property<String> textTransform(@Property.TEXT_TRANSFORM String value) {
        return new Property<>("text-transform", value);
    }

    public static Property<Float[]> textOffset(Float[] value) {
        return new Property<>("text-offset", value);
    }

    public static Property<Boolean> textAllowOverlap(Boolean value) {
        return new Property<>("text-allow-overlap", value);
    }

    public static Property<Boolean> textIgnorePlacement(Boolean value) {
        return new Property<>("text-ignore-placement", value);
    }

    public static Property<Boolean> textOptional(Boolean value) {
        return new Property<>("text-optional", value);
    }

    public static Property<Boolean> fillAntialias(Boolean value) {
        return new Property<>("fill-antialias", value);
    }

    public static Property<Float> fillOpacity(Float value) {
        return new Property<>("fill-opacity", value);
    }

    public static Property<String> fillColor(@ColorInt int value) {
        return new Property<>("fill-color", colorToRgbaString(value));
    }

    public static Property<String> fillColor(String value) {
        return new Property<>("fill-color", value);
    }

    public static Property<String> fillOutlineColor(@ColorInt int value) {
        return new Property<>("fill-outline-color", colorToRgbaString(value));
    }

    public static Property<String> fillOutlineColor(String value) {
        return new Property<>("fill-outline-color", value);
    }

    public static Property<Float[]> fillTranslate(Float[] value) {
        return new Property<>("fill-translate", value);
    }

    public static Property<String> fillTranslateAnchor(@Property.FILL_TRANSLATE_ANCHOR String value) {
        return new Property<>("fill-translate-anchor", value);
    }

    public static Property<String> fillPattern(String value) {
        return new Property<>("fill-pattern", value);
    }

    public static Property<Float> lineOpacity(Float value) {
        return new Property<>("line-opacity", value);
    }

    public static Property<String> lineColor(@ColorInt int value) {
        return new Property<>("line-color", colorToRgbaString(value));
    }

    public static Property<String> lineColor(String value) {
        return new Property<>("line-color", value);
    }

    public static Property<Float[]> lineTranslate(Float[] value) {
        return new Property<>("line-translate", value);
    }

    public static Property<String> lineTranslateAnchor(@Property.LINE_TRANSLATE_ANCHOR String value) {
        return new Property<>("line-translate-anchor", value);
    }

    public static Property<Float> lineWidth(Float value) {
        return new Property<>("line-width", value);
    }

    public static Property<Float> lineGapWidth(Float value) {
        return new Property<>("line-gap-width", value);
    }

    public static Property<Float> lineOffset(Float value) {
        return new Property<>("line-offset", value);
    }

    public static Property<Float> lineBlur(Float value) {
        return new Property<>("line-blur", value);
    }

    public static Property<Float[]> lineDasharray(Float[] value) {
        return new Property<>("line-dasharray", value);
    }

    public static Property<String> linePattern(String value) {
        return new Property<>("line-pattern", value);
    }

    public static Property<Float> iconOpacity(Float value) {
        return new Property<>("icon-opacity", value);
    }

    public static Property<String> iconColor(@ColorInt int value) {
        return new Property<>("icon-color", colorToRgbaString(value));
    }

    public static Property<String> iconColor(String value) {
        return new Property<>("icon-color", value);
    }

    public static Property<String> iconHaloColor(@ColorInt int value) {
        return new Property<>("icon-halo-color", colorToRgbaString(value));
    }

    public static Property<String> iconHaloColor(String value) {
        return new Property<>("icon-halo-color", value);
    }

    public static Property<Float> iconHaloWidth(Float value) {
        return new Property<>("icon-halo-width", value);
    }

    public static Property<Float> iconHaloBlur(Float value) {
        return new Property<>("icon-halo-blur", value);
    }

    public static Property<Float[]> iconTranslate(Float[] value) {
        return new Property<>("icon-translate", value);
    }

    public static Property<String> iconTranslateAnchor(@Property.ICON_TRANSLATE_ANCHOR String value) {
        return new Property<>("icon-translate-anchor", value);
    }

    public static Property<Float> textOpacity(Float value) {
        return new Property<>("text-opacity", value);
    }

    public static Property<String> textColor(@ColorInt int value) {
        return new Property<>("text-color", colorToRgbaString(value));
    }

    public static Property<String> textColor(String value) {
        return new Property<>("text-color", value);
    }

    public static Property<String> textHaloColor(@ColorInt int value) {
        return new Property<>("text-halo-color", colorToRgbaString(value));
    }

    public static Property<String> textHaloColor(String value) {
        return new Property<>("text-halo-color", value);
    }

    public static Property<Float> textHaloWidth(Float value) {
        return new Property<>("text-halo-width", value);
    }

    public static Property<Float> textHaloBlur(Float value) {
        return new Property<>("text-halo-blur", value);
    }

    public static Property<Float[]> textTranslate(Float[] value) {
        return new Property<>("text-translate", value);
    }

    public static Property<String> textTranslateAnchor(@Property.TEXT_TRANSLATE_ANCHOR String value) {
        return new Property<>("text-translate-anchor", value);
    }

    public static Property<Float> circleRadius(Float value) {
        return new Property<>("circle-radius", value);
    }

    public static Property<String> circleColor(@ColorInt int value) {
        return new Property<>("circle-color", colorToRgbaString(value));
    }

    public static Property<String> circleColor(String value) {
        return new Property<>("circle-color", value);
    }

    public static Property<Float> circleBlur(Float value) {
        return new Property<>("circle-blur", value);
    }

    public static Property<Float> circleOpacity(Float value) {
        return new Property<>("circle-opacity", value);
    }

    public static Property<Float[]> circleTranslate(Float[] value) {
        return new Property<>("circle-translate", value);
    }

    public static Property<String> circleTranslateAnchor(@Property.CIRCLE_TRANSLATE_ANCHOR String value) {
        return new Property<>("circle-translate-anchor", value);
    }

    public static Property<String> circlePitchScale(@Property.CIRCLE_PITCH_SCALE String value) {
        return new Property<>("circle-pitch-scale", value);
    }

    public static Property<Float> rasterOpacity(Float value) {
        return new Property<>("raster-opacity", value);
    }

    public static Property<Float> rasterHueRotate(Float value) {
        return new Property<>("raster-hue-rotate", value);
    }

    public static Property<Float> rasterBrightnessMin(Float value) {
        return new Property<>("raster-brightness-min", value);
    }

    public static Property<Float> rasterBrightnessMax(Float value) {
        return new Property<>("raster-brightness-max", value);
    }

    public static Property<Float> rasterSaturation(Float value) {
        return new Property<>("raster-saturation", value);
    }

    public static Property<Float> rasterContrast(Float value) {
        return new Property<>("raster-contrast", value);
    }

    public static Property<Float> rasterFadeDuration(Float value) {
        return new Property<>("raster-fade-duration", value);
    }

    public static Property<String> backgroundColor(@ColorInt int value) {
        return new Property<>("background-color", colorToRgbaString(value));
    }

    public static Property<String> backgroundColor(String value) {
        return new Property<>("background-color", value);
    }

    public static Property<String> backgroundPattern(String value) {
        return new Property<>("background-pattern", value);
    }

    public static Property<Float> backgroundOpacity(Float value) {
        return new Property<>("background-opacity", value);
    }

    @SuppressLint("DefaultLocale")
    static String colorToRgbaString(@ColorInt int value) {
        return String.format("rgba(%d, %d, %d, %d)", (value >> 16) & 0xFF, (value >> 8) & 0xFF, value & 0xFF, (value >> 24) & 0xFF);
    }

}
