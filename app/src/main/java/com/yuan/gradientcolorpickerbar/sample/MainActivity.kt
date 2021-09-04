package com.yuan.gradientcolorpickerbar.sample

import android.content.res.Resources
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import com.yuan.gradientcolorpickerbar.GradientColorPickerBar
import com.yuan.gradientcolorpickerbar.sample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val colorList: List<IntArray> by lazy {
        listOf(
            resources.getIntArray(R.array.colors),
            resources.getIntArray(R.array.color1),
            resources.getIntArray(R.array.color2)
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.changColor.setOnClickListener {
            binding.colorPickerBar.setColors(colorList.random())
        }
        binding.resetProgress.setOnClickListener {
            binding.colorPickerBar.setProgress(0f)
        }
        binding.changeBarStyle.setOnClickListener {
            binding.colorPickerBar.setBarStyle(10f.dp, 12f.dp)
        }
        binding.changeThumbStyle.setOnClickListener {
            binding.colorPickerBar.setThumbStyle(30f.dp, 15f.dp, Color.WHITE, 3f.dp)
        }
        binding.changeOrientation.setOnClickListener {
            binding.colorPickerBar.setOrientation(
                if (binding.colorPickerBar.getOrientation() == GradientColorPickerBar.HORIZONTAL)
                    GradientColorPickerBar.VERTICAL else GradientColorPickerBar.HORIZONTAL
            )
        }
        binding.colorPickerBar.setOnChangeListener(object :
            GradientColorPickerBar.OnChangeListener {
            override fun onProgressChanged(
                gradientColorPickBar: GradientColorPickerBar,
                progress: Float,
                color: Int,
                fromUser: Boolean
            ) {
                binding.tvColor.text = GradientColorPickerBar.parseColorInt(color)
                binding.tvColor.setTextColor(color)
            }
        })
    }
}

private val Float.dp
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this,
        Resources.getSystem().displayMetrics
    )