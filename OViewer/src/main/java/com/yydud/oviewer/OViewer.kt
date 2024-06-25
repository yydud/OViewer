package com.yydud.oviewer

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.MotionEvent
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.yydud.oviewer.component.SnapPagerScrollListener
import com.yydud.oviewer.data.ModeType

public class OViewer : LinearLayout {
    // URL 데이터
    private var items: MutableList<String> = mutableListOf()
    private var headers = mapOf<String, String>()

    // 레이아웃 정의
    private lateinit var rv: RecyclerView
    private lateinit var scrollbar: ImageView


    // 스크롤바 관련
    private var onScrollListener: OnScrollListener? = null
    private var onPageListener: OnPageListener? = null
    private var isScrollBarEnabled = false
    private var isScrollBarDragging = false
    private var lastTouchY = 0f

    // 리사이클러뷰 관련
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
        scrollbar = findViewById(R.id.scrollbar)
//        nsv = findViewById(R.id.nsv)

        setupRecyclerView(ModeType.VERTICAL)

        if(isScrollBarEnabled){
            scrollbar.visibility = VISIBLE
        }
        else{
            scrollbar.visibility = GONE
        }
    }

    /**
     * Set scroll listener
     * 스크롤 이벤트 콜백을 위한 리스너 설정
     * @param listener
     */
    public fun setOnScrollListener(listener: OnScrollListener?) {
        this.onScrollListener = listener
    }

    /**
     * Set scroll listener
     * 페이징 이벤트 콜백을 위한 리스너 설정
     * @param listener
     */
    public fun setOnPageListener(listener: OnPageListener?) {
        this.onPageListener = listener
    }

    private fun setupRecyclerView(mode: ModeType) {
        // 레이아웃 매니저 설정
        snapHelper?.attachToRecyclerView(null) // 스냅 헬퍼 분리
        // 이벤트 제거
        rv.clearOnScrollListeners()

        rv.layoutManager = if (ModeType.VERTICAL == mode) {
            LinearLayoutManager(context).also {
                rv.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                        super.onScrolled(recyclerView, dx, dy)
                        // 리사이클러뷰의 총 높이
                        val totalHeight = recyclerView.computeVerticalScrollRange()
                        // 리사이클러뷰의 현재 스크롤 위치
                        val scrollY = recyclerView.computeVerticalScrollOffset()
                        // 리사이클러뷰의 실제 높이
                        val recyclerViewHeight = recyclerView.height
                        // 스크롤바의 높이
                        val scrollBarHeight = scrollbar.height

                        // 스크롤바의 y좌표 계산
                        var scrollBarY = (scrollY.toFloat() / (totalHeight - recyclerViewHeight)) * (recyclerViewHeight - scrollBarHeight)

                        // 스크롤바의 위치가 리사이클러뷰 영역을 벗어나지 않도록 보정
                        if (scrollBarY < 0) {
                            scrollBarY = 0f
                        } else if (scrollBarY > recyclerViewHeight - scrollBarHeight) {
                            scrollBarY = (recyclerViewHeight - scrollBarHeight).toFloat()
                        }

                        // 스크롤바의 위치 변경
                        scrollbar.y = scrollBarY

                        onScrollListener?.onScrolled(dx, dy)
                    }

                    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                        super.onScrollStateChanged(recyclerView, newState)
                        onScrollListener?.onScrollStateChanged(newState)
                    }
                })

                scrollbar.setOnTouchListener { _, event ->
                    if(!isScrollBarEnabled) return@setOnTouchListener false

                    when (event.action) {
                        MotionEvent.ACTION_DOWN -> {
                            isScrollBarDragging = true
                            lastTouchY = event.y
                            true
                        }
                        MotionEvent.ACTION_MOVE -> {
                            if (isScrollBarDragging) {
                                val deltaY = event.y - lastTouchY

                                // 스크롤바의 새로운 y좌표 계산
                                var newScrollBarY = scrollbar.y + deltaY
                                newScrollBarY = newScrollBarY.coerceIn(0f, (rv.height - scrollbar.height).toFloat())
                                scrollbar.y = newScrollBarY

                                // 리사이클러뷰의 스크롤 위치 업데이트
                                rv.scrollBy(0, (deltaY * (rv.computeVerticalScrollRange() - rv.height) / (rv.height - scrollbar.height)).toInt())

                                true
                            } else {
                                false
                            }
                        }
                        MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                            isScrollBarDragging = false
                            false
                        }
                        else -> false
                    }
                }
            }
        } else {
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false).also {
                snapHelper = PagerSnapHelper()
                snapHelper?.attachToRecyclerView(rv)

                val listener: SnapPagerScrollListener = SnapPagerScrollListener(
                    snapHelper!!,
                    SnapPagerScrollListener.ON_SCROLL,
                    true,
                    object : SnapPagerScrollListener.OnChangeListener {
                        override fun onSnapped(position: Int) {
                            // 현재 페이지 번호 사용
                            onPageListener?.onChangePage(position)
                        }
                    }
                )

                rv.addOnScrollListener(listener)
            }
        }

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
        this.items.clear()
        this.items.addAll(data)
        adapter.setData(this.items)
    }

    /**
     * Set data
     * 해더 정보 넣기
     * @param headers
     */
    public fun setHeaders(headers: Map<String, String>){
        this.headers = headers
        adapter.setHeaders(this.headers)
    }
}