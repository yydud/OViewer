package com.yydud.oviewer

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.RecyclerListener
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.yydud.oviewer.data.ModeType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

public class OViewer : LinearLayout {
    // URL 데이터
    private var data: MutableList<String> = mutableListOf()

    // 레이아웃 정의
    private lateinit var rv: RecyclerView
//    private lateinit var nsv: NestedScrollView

    private var onScrollListener: OnScrollListener? = null


    private lateinit var adapter: RvAdapter
    private var snapHelper: PagerSnapHelper? = null

    public constructor(context: Context): super(context){
        init()
    }
    public constructor(context: Context, attrs: AttributeSet): super(context, attrs){
        init()
    }
    public constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int): super(context, attrs, defStyleAttr){
        init()
    }
    public constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int): super(context, attrs, defStyleAttr, defStyleRes){
        init()
    }

    private fun init(){
        // 레이아웃 초기화
        LayoutInflater.from(context).inflate(R.layout.oviewer_layout, this, true)

        // 레이아웃의 ID 정의
        rv = findViewById(R.id.rv)
//        nsv = findViewById(R.id.nsv)

        setupRecyclerView(ModeType.VERTICAL)
    }

    /**
     * Set scroll listener
     * 스크롤 이벤트 콜백을 위한 리스너 설정
     * @param listener
     */
    public fun setOnScrollListener(listener: OnScrollListener?) {
        this.onScrollListener = listener
    }

    private fun setupRecyclerView(mode: ModeType) {
        // 레이아웃 매니저 설정
        snapHelper?.attachToRecyclerView(null) // 스냅 헬퍼 분리
        rv.layoutManager = if (ModeType.VERTICAL == mode) {
            LinearLayoutManager(context)
        } else {
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false).also {
                snapHelper = PagerSnapHelper()
                snapHelper?.attachToRecyclerView(rv)
            }
        }

        rv.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                // 리사이클러뷰의 총 높이
                val totalHeight = recyclerView.computeVerticalScrollRange()
                // 리사이클러뷰의 현재 스크롤 위치
                val scrollY = recyclerView.computeVerticalScrollOffset()
                // 스크롤바의 높이
//                val scrollBarHeight = scrollBar.height
                // 스크롤바의 y좌표 계산
//                val scrollBarY = (scrollY.toFloat() / totalHeight) * (recyclerView.height - scrollBarHeight)
                val scrollBarY = (scrollY.toFloat() / totalHeight) * recyclerView.height
                // 스크롤바의 위치 변경
//                scrollBar.y = scrollBarY.toInt()

                onScrollListener?.onScrolled(dx, dy, scrollBarY.toInt())
            }

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                onScrollListener?.onScrollStateChanged(newState)
            }
        })

        // 어댑터 설정
        adapter = RvAdapter(context)
        rv.adapter = adapter
    }

    /**
     * Set mode
     * 스크롤 모드
     * 세로(스크롤), 가로(스와이프)
     * @param mode
     */
    public fun setMode(mode: ModeType){
        setupRecyclerView(mode)
    }

    /**
     * Set data
     * 표시할 데이터 넣기([Url, Url, Url])
     * @param data
     */
    public fun setData(data: List<String>){
        this.data.clear()
        this.data.addAll(data)

        adapter.setData(this.data)
    }
}