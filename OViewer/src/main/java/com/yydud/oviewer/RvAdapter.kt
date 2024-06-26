package com.yydud.oviewer

import android.content.Context
import android.graphics.drawable.Drawable
import android.graphics.pdf.PdfRenderer
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import android.view.animation.RotateAnimation
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.target.Target
import com.bumptech.glide.signature.ObjectKey

public class RvAdapter(private var context: Context): RecyclerView.Adapter<RvAdapter.ViewHolder>() {
    private var items = mutableListOf<String>()
    private var headers = mapOf<String, String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rv_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]

        setImage(holder, item)

        holder.refresh.setOnClickListener {
            setAnimation(holder.refresh, true)
            setImage(holder, item)
        }
    }

    override fun getItemCount(): Int = items.size
    // headers 넣기
    public fun setHeaders(headers: Map<String, String>){
        this.headers = headers
    }
    // 데이터 넣기
    public fun setData(data: List<String>){
        items.clear()
        items.addAll(data)

        notifyDataSetChanged()
    }

    public fun setData(data: List<String>, headers: Map<String, String>){
        items.clear()
        items.addAll(data)

        this.headers = headers

        notifyDataSetChanged()
    }

    private fun setAnimation(imageView: ImageView, boolean: Boolean){
        // 현재 이미지뷰에 애니메이션이 설정되어 있는지 확인
        if (boolean) {
            if(imageView.animation != null){
                return
            }
            // 애니메이션이 없으면 새로운 애니메이션을 설정
            val rotateAnimation = RotateAnimation(
                0f, 360f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f
            )
            rotateAnimation.duration = 1000
            rotateAnimation.interpolator = LinearInterpolator()
            rotateAnimation.repeatCount = 0 // 애니메이션을 1번만 실행
            rotateAnimation.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationStart(animation: Animation?) {}

                override fun onAnimationEnd(animation: Animation?) {
                    // 애니메이션이 끝나면 null로 변경
//                    imageView.animation = null
                    imageView.clearAnimation()
                }

                override fun onAnimationRepeat(animation: Animation?) {}
            })
            imageView.startAnimation(rotateAnimation)
        } else {
            // 애니메이션이 있으면 멈춤
            imageView.clearAnimation()
        }
    }

    private fun setImage(holder: ViewHolder, url: String){
        val glideUrlBuilder = LazyHeaders.Builder()
        for ((key, value) in headers) {
            glideUrlBuilder.addHeader(key, value)
        }
        val glideUrl = GlideUrl(url, glideUrlBuilder.build())

        Glide.with(context).clear(holder.image)

        Glide.with(context)
            .load(glideUrl)
            .placeholder(R.drawable.mosic)
            .error(R.drawable.mosic)
            .diskCacheStrategy(DiskCacheStrategy.ALL) // 캐싱 모드
            .skipMemoryCache(false)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    // 이미지 로딩 실패 시 실행되는 콜백
                    // 필요한 로직을 여기에 작성하세요

                    holder.refresh.visibility = VISIBLE
                    return false
                }
                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    // 이미지 로딩 성공 시 실행되는 콜백
                    // 필요한 로직을 여기에 작성하세요

                    setAnimation(holder.refresh, false)
                    holder.refresh.visibility = GONE
                    return false
                }

            })
            .into(holder.image)
    }

    public inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        public var image: ImageView = itemView.findViewById(R.id.image)
        public var refresh: ImageView = itemView.findViewById(R.id.refresh)
    }
}