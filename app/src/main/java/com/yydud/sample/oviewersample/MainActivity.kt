package com.yydud.sample.oviewersample

import android.util.Log
import com.yydud.oviewer.OViewer
import com.yydud.oviewer.OnPageListener
import com.yydud.oviewer.OnScrollListener
import com.yydud.oviewer.data.ModeType
import com.yydud.sample.oviewersample.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    val tag = "#### Viewer ####"

    override fun init() {
        binding.apply {

            viewer.setOnScrollListener(object:OnScrollListener{
                override fun onScrolled(dx: Int, dy: Int) {
                    Log.d(tag, "onScrolled dx, dy : $dx, $dy")
                    Log.d(tag, "onScrolled viewer.height : ${viewer.height}")
                }

                override fun onScrollStateChanged(newState: Int) {
                    Log.d(tag, "onScrollStateChanged newState : $newState")
                }

            })

            viewer.setOnPageListener(object:OnPageListener{
                override fun onChangePage(position: Int) {
                    Log.d(tag, "onChangePage position : $position")
                }
            })

            resetBtn.setOnClickListener {
                viewer.setData(listOf())
            }

            modeVBtn.setOnClickListener {
                viewer.setMode(ModeType.VERTICAL)
            }

            modeHBtn.setOnClickListener {
                viewer.setMode(ModeType.HORIZONTAL)
            }

            addBtn.setOnClickListener {

                var list = listOf("https://cdn.pixabay.com/photo/2023/05/07/01/55/man-7975388_1280.png")

                viewer.setData(list)

//                viewer.setData(list, mapOf())
            }
        }
    }


}