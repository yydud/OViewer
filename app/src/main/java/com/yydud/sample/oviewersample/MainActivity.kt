package com.yydud.sample.oviewersample

import android.util.Log
import com.yydud.oviewer.OViewer
import com.yydud.oviewer.OnScrollListener
import com.yydud.oviewer.data.ModeType
import com.yydud.sample.oviewersample.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    override fun init() {
        binding.apply {

            viewer.setOnScrollListener(object:OnScrollListener{
                override fun onScrolled(dx: Int, dy: Int) {
                    Log.d("AAAA", "onScrolled dx, dy : $dx, $dy")
                    Log.d("AAAA", "onScrolled viewer.height : ${viewer.height}")
                }

                override fun onScrollStateChanged(newState: Int) {
                    Log.d("AAAA", "onScrollStateChanged newState : $newState")
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
                var list = listOf(
                    "https://image-comic.pstatic.net/webtoon/762279/128/20240501210706_d25fc3b2cf77b989db9678c7b8347da1_IMAG01_1.jpg",
                    "https://image-comic.pstatic.net/webtoon/762279/128/20240501210706_d25fc3b2cf77b989db9678c7b8347da1_IMAG01_2.jpg",
                    "https://image-comic.pstatic.net/webtoon/762279/128/20240501210706_d25fc3b2cf77b989db9678c7b8347da1_IMAG01_3.jpg",
                    "https://image-comic.pstatic.net/webtoon/762279/128/20240501210706_d25fc3b2cf77b989db9678c7b8347da1_IMAG01_4.jpg",
                    "https://image-comic.pstatic.net/webtoon/762279/128/20240501210706_d25fc3b2cf77b989db9678c7b8347da1_IMAG01_5.jpg",
                    "https://image-comic.pstatic.net/webtoon/762279/128/20240501210706_d25fc3b2cf77b989db9678c7b8347da1_IMAG01_6.jpg",
                    "https://image-comic.pstatic.net/webtoon/762279/128/20240501210706_d25fc3b2cf77b989db9678c7b8347da1_IMAG01_7.jpg",
                    "https://image-comic.pstatic.net/webtoon/762279/128/20240501210706_d25fc3b2cf77b989db9678c7b8347da1_IMAG01_8.jpg",
                    "https://image-comic.pstatic.net/webtoon/762279/128/20240501210706_d25fc3b2cf77b989db9678c7b8347da1_IMAG01_9.jpg",
                    "https://image-comic.pstatic.net/webtoon/762279/128/20240501210706_d25fc3b2cf77b989db9678c7b8347da1_IMAG01_10.jpg",
                    "https://image-comic.pstatic.net/webtoon/762279/128/20240501210706_d25fc3b2cf77b989db9678c7b8347da1_IMAG01_11.jpg",
                    "https://image-comic.pstatic.net/webtoon/762279/128/20240501210706_d25fc3b2cf77b989db9678c7b8347da1_IMAG01_12.jpg",
                    "https://image-comic.pstatic.net/webtoon/762279/128/20240501210706_d25fc3b2cf77b989db9678c7b8347da1_IMAG01_13.jpg",
                    "https://image-comic.pstatic.net/webtoon/762279/128/20240501210706_d25fc3b2cf77b989db9678c7b8347da1_IMAG01_14.jpg",
                    "https://image-comic.pstatic.net/webtoon/762279/128/20240501210706_d25fc3b2cf77b989db9678c7b8347da1_IMAG01_15.jpg",
                    "https://image-comic.pstatic.net/webtoon/762279/128/20240501210706_d25fc3b2cf77b989db9678c7b8347da1_IMAG01_16.jpg",
                    "https://image-comic.pstatic.net/webtoon/762279/128/20240501210706_d25fc3b2cf77b989db9678c7b8347da1_IMAG01_17.jpg",
                    "https://image-comic.pstatic.net/webtoon/762279/128/20240501210706_d25fc3b2cf77b989db9678c7b8347da1_IMAG01_18.jpg",
                    "https://image-comic.pstatic.net/webtoon/762279/128/20240501210706_d25fc3b2cf77b989db9678c7b8347da1_IMAG01_19.jpg",
                    "https://image-comic.pstatic.net/webtoon/762279/128/20240501210706_d25fc3b2cf77b989db9678c7b8347da1_IMAG01_20.jpg",
                    "https://image-comic.pstatic.net/webtoon/762279/128/20240501210706_d25fc3b2cf77b989db9678c7b8347da1_IMAG01_21.jpg",
                    "https://image-comic.pstatic.net/webtoon/762279/128/20240501210706_d25fc3b2cf77b989db9678c7b8347da1_IMAG01_22.jpg",
                    "https://image-comic.pstatic.net/webtoon/762279/128/20240501210706_d25fc3b2cf77b989db9678c7b8347da1_IMAG01_23.jpg",
                    "https://image-comic.pstatic.net/webtoon/762279/128/20240501210706_d25fc3b2cf77b989db9678c7b8347da1_IMAG01_24.jpg",
                    "https://image-comic.pstatic.net/webtoon/762279/128/20240501210706_d25fc3b2cf77b989db9678c7b8347da1_IMAG01_25.jpg",
                    "https://image-comic.pstatic.net/webtoon/762279/128/20240501210706_d25fc3b2cf77b989db9678c7b8347da1_IMAG01_26.jpg",
                    "https://image-comic.pstatic.net/webtoon/762279/128/20240501210706_d25fc3b2cf77b989db9678c7b8347da1_IMAG01_27.jpg",
                    "https://image-comic.pstatic.net/webtoon/762279/128/20240501210706_d25fc3b2cf77b989db9678c7b8347da1_IMAG01_28.jpg",
                    "https://image-comic.pstatic.net/webtoon/762279/128/20240501210706_d25fc3b2cf77b989db9678c7b8347da1_IMAG01_29.jpg",
                    "https://image-comic.pstatic.net/webtoon/762279/128/20240501210706_d25fc3b2cf77b989db9678c7b8347da1_IMAG01_30.jpg",
                    "https://image-comic.pstatic.net/webtoon/762279/128/20240501210706_d25fc3b2cf77b989db9678c7b8347da1_IMAG01_31.jpg",
                    "https://image-comic.pstatic.net/webtoon/762279/128/20240501210706_d25fc3b2cf77b989db9678c7b8347da1_IMAG01_32.jpg",
                    "https://image-comic.pstatic.net/webtoon/762279/128/20240501210706_d25fc3b2cf77b989db9678c7b8347da1_IMAG01_33.jpg",
                    "https://image-comic.pstatic.net/webtoon/762279/128/20240501210706_d25fc3b2cf77b989db9678c7b8347da1_IMAG01_34.jpg",
                    "https://image-comic.pstatic.net/webtoon/762279/128/20240501210706_d25fc3b2cf77b989db9678c7b8347da1_IMAG01_35.jpg",
                    "https://image-comic.pstatic.net/webtoon/762279/128/20240501210706_d25fc3b2cf77b989db9678c7b8347da1_IMAG01_36.jpg",
                    "https://image-comic.pstatic.net/webtoon/762279/128/20240501210706_d25fc3b2cf77b989db9678c7b8347da1_IMAG01_37.jpg",
                    "https://image-comic.pstatic.net/webtoon/762279/128/20240501210706_d25fc3b2cf77b989db9678c7b8347da1_IMAG01_38.jpg",
                    "https://image-comic.pstatic.net/webtoon/762279/128/20240501210706_d25fc3b2cf77b989db9678c7b8347da1_IMAG01_39.jpg",
                    "https://image-comic.pstatic.net/webtoon/762279/128/20240501210706_d25fc3b2cf77b989db9678c7b8347da1_IMAG01_40.jpg",
                    "https://image-comic.pstatic.net/webtoon/762279/128/20240501210706_d25fc3b2cf77b989db9678c7b8347da1_IMAG01_41.jpg",
                    "https://image-comic.pstatic.net/webtoon/762279/128/20240501210706_d25fc3b2cf77b989db9678c7b8347da1_IMAG01_42.jpg",
                    "https://image-comic.pstatic.net/webtoon/762279/128/20240501210706_d25fc3b2cf77b989db9678c7b8347da1_IMAG01_43.jpg",
                    "https://image-comic.pstatic.net/webtoon/762279/128/20240501210706_d25fc3b2cf77b989db9678c7b8347da1_IMAG01_44.jpg",
                    "https://image-comic.pstatic.net/webtoon/762279/128/20240501210706_d25fc3b2cf77b989db9678c7b8347da1_IMAG01_45.jpg",
                    "https://image-comic.pstatic.net/webtoon/762279/128/20240501210706_d25fc3b2cf77b989db9678c7b8347da1_IMAG01_46.jpg",
                    "https://image-comic.pstatic.net/webtoon/762279/128/20240501210706_d25fc3b2cf77b989db9678c7b8347da1_IMAG01_47.jpg",
                    "https://image-comic.pstatic.net/webtoon/762279/128/20240501210706_d25fc3b2cf77b989db9678c7b8347da1_IMAG01_48.jpg",
                    "https://image-comic.pstatic.net/webtoon/762279/128/20240501210706_d25fc3b2cf77b989db9678c7b8347da1_IMAG01_49.jpg",
                    "https://image-comic.pstatic.net/webtoon/762279/128/20240501210706_d25fc3b2cf77b989db9678c7b8347da1_IMAG01_50.jpg",
                    "https://image-comic.pstatic.net/webtoon/762279/128/20240501210706_d25fc3b2cf77b989db9678c7b8347da1_IMAG01_51.jpg",
                    "https://image-comic.pstatic.net/webtoon/762279/128/20240501210706_d25fc3b2cf77b989db9678c7b8347da1_IMAG01_52.jpg",
                    "https://image-comic.pstatic.net/webtoon/762279/128/20240501210706_d25fc3b2cf77b989db9678c7b8347da1_IMAG01_53.jpg",
                    "https://image-comic.pstatic.net/webtoon/762279/128/20240501210706_d25fc3b2cf77b989db9678c7b8347da1_IMAG01_54.jpg",
                    "https://image-comic.pstatic.net/webtoon/762279/128/20240501210706_d25fc3b2cf77b989db9678c7b8347da1_IMAG01_55.jpg",
                    "https://image-comic.pstatic.net/webtoon/762279/128/20240501210706_d25fc3b2cf77b989db9678c7b8347da1_IMAG01_56.jpg",
                    "https://image-comic.pstatic.net/webtoon/762279/128/20240501210706_d25fc3b2cf77b989db9678c7b8347da1_IMAG01_57.jpg",
                    "https://image-comic.pstatic.net/webtoon/762279/128/20240501210706_d25fc3b2cf77b989db9678c7b8347da1_IMAG01_58.jpg",
                    "https://image-comic.pstatic.net/webtoon/762279/128/20240501210706_d25fc3b2cf77b989db9678c7b8347da1_IMAG01_59.jpg",
                    "https://image-comic.pstatic.net/webtoon/762279/128/20240501210706_d25fc3b2cf77b989db9678c7b8347da1_IMAG01_60.jpg",
                    "https://image-comic.pstatic.net/webtoon/762279/128/20240501210706_d25fc3b2cf77b989db9678c7b8347da1_IMAG01_61.jpg",
                    "https://image-comic.pstatic.net/webtoon/762279/128/20240501210706_d25fc3b2cf77b989db9678c7b8347da1_IMAG01_62.jpg",
                    "https://image-comic.pstatic.net/webtoon/762279/128/20240501210706_d25fc3b2cf77b989db9678c7b8347da1_IMAG01_63.jpg",
                    "https://image-comic.pstatic.net/webtoon/762279/128/20240501210706_d25fc3b2cf77b989db9678c7b8347da1_IMAG01_64.jpg",
                    "https://image-comic.pstatic.net/webtoon/762279/128/20240501210706_d25fc3b2cf77b989db9678c7b8347da1_IMAG01_65.jpg",
                    "https://image-comic.pstatic.net/webtoon/762279/128/20240501210706_d25fc3b2cf77b989db9678c7b8347da1_IMAG01_66.jpg",
                    "https://image-comic.pstatic.net/webtoon/762279/128/20240501210706_d25fc3b2cf77b989db9678c7b8347da1_IMAG01_67.jpg",
                    "https://image-comic.pstatic.net/webtoon/762279/128/20240501210706_d25fc3b2cf77b989db9678c7b8347da1_IMAG01_68.jpg",
                    "https://image-comic.pstatic.net/webtoon/762279/128/20240501210706_d25fc3b2cf77b989db9678c7b8347da1_IMAG01_69.jpg",
                    "https://image-comic.pstatic.net/webtoon/762279/128/20240501210706_d25fc3b2cf77b989db9678c7b8347da1_IMAG01_70.jpg",
                    "https://image-comic.pstatic.net/webtoon/762279/128/20240501210706_d25fc3b2cf77b989db9678c7b8347da1_IMAG01_71.jpg",
                    "https://image-comic.pstatic.net/webtoon/762279/128/20240501210706_d25fc3b2cf77b989db9678c7b8347da1_IMAG01_72.jpg",
                    "https://image-comic.pstatic.net/webtoon/762279/128/20240501210706_d25fc3b2cf77b989db9678c7b8347da1_IMAG01_73.jpg",
                    "https://image-comic.pstatic.net/webtoon/762279/128/20240501210706_d25fc3b2cf77b989db9678c7b8347da1_IMAG01_74.jpg",
                    "https://image-comic.pstatic.net/webtoon/762279/128/20240501210706_d25fc3b2cf77b989db9678c7b8347da1_IMAG01_75.jpg",
                    "https://image-comic.pstatic.net/webtoon/762279/128/20240501210706_d25fc3b2cf77b989db9678c7b8347da1_IMAG01_76.jpg",
                    "https://image-comic.pstatic.net/webtoon/762279/128/20240501210706_d25fc3b2cf77b989db9678c7b8347da1_IMAG01_77.jpg",
                    "https://image-comic.pstatic.net/webtoon/762279/128/20240501210706_d25fc3b2cf77b989db9678c7b8347da1_IMAG01_78.jpg",
                    "https://image-comic.pstatic.net/webtoon/762279/128/20240501210706_d25fc3b2cf77b989db9678c7b8347da1_IMAG01_79.jpg",
                    "https://image-comic.pstatic.net/webtoon/762279/128/20240501210706_d25fc3b2cf77b989db9678c7b8347da1_IMAG01_80.jpg",
                    "https://image-comic.pstatic.net/webtoon/762279/128/20240501210706_d25fc3b2cf77b989db9678c7b8347da1_IMAG01_81.jpg",
                    "https://image-comic.pstatic.net/webtoon/762279/128/20240501210706_d25fc3b2cf77b989db9678c7b8347da1_IMAG01_82.jpg",
                    "https://image-comic.pstatic.net/webtoon/762279/128/20240501210706_d25fc3b2cf77b989db9678c7b8347da1_IMAG01_83.jpg",
                    "https://image-comic.pstatic.net/webtoon/762279/128/20240501210706_d25fc3b2cf77b989db9678c7b8347da1_IMAG01_84.jpg",
                    "https://image-comic.pstatic.net/webtoon/762279/128/20240501210706_d25fc3b2cf77b989db9678c7b8347da1_IMAG01_85.jpg",
                    "https://image-comic.pstatic.net/webtoon/762279/128/20240501210706_d25fc3b2cf77b989db9678c7b8347da1_IMAG01_86.jpg",
                    "https://image-comic.pstatic.net/webtoon/762279/128/20240501210706_d25fc3b2cf77b989db9678c7b8347da1_IMAG01_87.jpg",
                    "https://image-comic.pstatic.net/webtoon/762279/128/20240501210706_d25fc3b2cf77b989db9678c7b8347da1_IMAG01_88.jpg",
                    "https://image-comic.pstatic.net/webtoon/762279/128/20240501210706_d25fc3b2cf77b989db9678c7b8347da1_IMAG01_89.jpg",
                    "https://image-comic.pstatic.net/webtoon/762279/128/20240501210706_d25fc3b2cf77b989db9678c7b8347da1_IMAG01_90.jpg",
                    "https://image-comic.pstatic.net/webtoon/762279/128/20240501210706_d25fc3b2cf77b989db9678c7b8347da1_IMAG01_91.jpg",
                    "https://image-comic.pstatic.net/webtoon/762279/128/20240501210706_d25fc3b2cf77b989db9678c7b8347da1_IMAG01_92.jpg",
                    "https://image-comic.pstatic.net/webtoon/762279/128/20240501210706_d25fc3b2cf77b989db9678c7b8347da1_IMAG01_93.jpg",
                    "https://image-comic.pstatic.net/webtoon/762279/128/20240501210706_d25fc3b2cf77b989db9678c7b8347da1_IMAG01_94.jpg",
                    "https://image-comic.pstatic.net/webtoon/762279/128/20240501210706_d25fc3b2cf77b989db9678c7b8347da1_IMAG01_95.jpg",
                    "https://image-comic.pstatic.net/webtoon/762279/128/20240501210706_d25fc3b2cf77b989db9678c7b8347da1_IMAG01_96.jpg",
                    "https://image-comic.pstatic.net/webtoon/762279/128/20240501210706_d25fc3b2cf77b989db9678c7b8347da1_IMAG01_97.jpg",
                    "https://image-comic.pstatic.net/webtoon/762279/128/20240501210706_d25fc3b2cf77b989db9678c7b8347da1_IMAG01_98.jpg",
                    "https://image-comic.pstatic.net/webtoon/762279/128/20240501210706_d25fc3b2cf77b989db9678c7b8347da1_IMAG01_99.jpg",
                    "https://image-comic.pstatic.net/webtoon/762279/128/20240501210706_d25fc3b2cf77b989db9678c7b8347da1_IMAG01_100.jpg",
                    "https://image-comic.pstatic.net/webtoon/762279/128/20240501210706_d25fc3b2cf77b989db9678c7b8347da1_IMAG01_101.jpg",
                    "https://image-comic.pstatic.net/webtoon/762279/128/20240501210706_d25fc3b2cf77b989db9678c7b8347da1_IMAG01_102.jpg",
                    "https://image-comic.pstatic.net/webtoon/762279/128/20240501210706_d25fc3b2cf77b989db9678c7b8347da1_IMAG01_103.jpg",
                    "https://image-comic.pstatic.net/webtoon/762279/128/20240501210706_d25fc3b2cf77b989db9678c7b8347da1_IMAG01_104.jpg"
                )

//                var list = listOf("https://i.pinimg.com/originals/1a/13/2d/1a132dee024ad10ac3a14dcb69ca0755.gif")
//                var list = listOf("https://drive.google.com/file/d/1XEFm25FLyyp9v7ru0DMZqcGGGGHNK15h/view?usp=drive_link")
                viewer.setData(list, mapOf(
                    "Referer" to "https://comic.naver.com",
                    "User-Agent" to "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.3"
                ))

//                viewer.setData(list, mapOf())
            }
        }
    }


}