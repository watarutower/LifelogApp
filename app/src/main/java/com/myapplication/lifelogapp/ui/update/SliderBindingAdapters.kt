package com.myapplication.lifelogapp.ui.update

import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import com.google.android.material.slider.Slider



    object SliderBindingAdapters {

        /** Model --> View */
        @JvmStatic
        @BindingAdapter("android:value")
        fun bindSliderValue(slider: Slider, value: Float?) {
            if (value != null && slider.value != value) {
                slider.value = value
            }
        }

        /** View --> Model */
        @JvmStatic
        @InverseBindingAdapter(attribute = "android:value")
        fun bindSliderValueInverse(slider: Slider) : Float {
            return slider.value
        }

        /**
         * `android:value`が更新されたことを検知するリスナを登録して、
         * 値変更時に`bindSliderValueInverse`を呼び出す
         */
        @JvmStatic
        @BindingAdapter("android:valueAttrChanged")
        fun bindListeners(slider: Slider, valueAttrChanged: InverseBindingListener?) {
            slider.addOnChangeListener { _, _, _ ->
                valueAttrChanged?.onChange()
            }
        }
    }
