package com.yydud.oviewer

public interface OnScrollListener {
    public fun onScrolled(dx: Int, dy: Int, y: Int)
    public fun onScrollStateChanged(newState: Int)
}