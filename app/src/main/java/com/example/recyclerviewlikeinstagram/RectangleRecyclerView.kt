package com.example.recyclerviewlikeinstagram

import android.content.Context
import android.graphics.Point
import android.util.AttributeSet
import android.util.Log
import android.view.Display
import android.view.View
import android.view.WindowManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import java.util.*
import kotlin.collections.ArrayList

class RectangleRecyclerView: RecyclerView {

    constructor(context: Context) : super(context)

    constructor(context: Context, attributeSet: AttributeSet): super(context, attributeSet)

    private val lister: MutableList<Int> = mutableListOf()
    private var videoSurfaceDefaultHeight:Int = 0
    private var screenDefaultHeight: Int = 0
    private lateinit var videoUrls: List<String>
    private var currentPlayer: SimpleExoPlayer? = null

    init {
        val display: Display = (getContext().getSystemService(Context.WINDOW_SERVICE) as WindowManager).defaultDisplay
        val point = Point()
        display.getSize(point)
        videoSurfaceDefaultHeight = point.x
        screenDefaultHeight = point.y
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        Log.d("RecyclerView", "Attached To Window")
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        Log.d("RecyclerView", "Detached from Window")
    }

    override fun onChildAttachedToWindow(child: View) {
        super.onChildAttachedToWindow(child)
        Log.d("RecyclerView", "Child view Attached To Window")
        val item = child.tag as RectangleAdapter.RectangleViewHolder
        item.itemView.setBackgroundColor(resources.getColor(R.color.red))
        item.initializePlayer(item.videoUrl!!)
        lister.add(item.adapterPosition)
    }

    override fun onChildDetachedFromWindow(child: View) {
        super.onChildDetachedFromWindow(child)
        val item = child.tag as RectangleAdapter.RectangleViewHolder
        lister.remove(item.adapterPosition)
        item.releasePlayer()
        Log.d("RecyclerView", "Child Detached From Window")
    }

    override fun onScrollStateChanged(state: Int) {
        super.onScrollStateChanged(state)
        Log.d("RecyclerView", "Scroll state changed")
        if (state == SCROLL_STATE_IDLE) {
            Log.d("RecyclerView", "lister is $lister")
            val itemHeights: List<Int> = lister.map {
                getVisibleItemHeight(it)
            }
            Log.d("RecyclerView", "The height array is $itemHeights")
            val maxHeight = itemHeights.maxOrNull()!!
            Log.d("RecyclerView", "The max height is $maxHeight")
            val maxHeightPosition = itemHeights.indexOf(maxHeight)
            val gold = lister[maxHeightPosition]
            val superItem = findViewHolderForAdapterPosition(gold) as RectangleAdapter.RectangleViewHolder
            currentPlayer = superItem.player
            superItem.startPlaying()
            superItem.itemView.setBackgroundColor(resources.getColor(R.color.green))
            for (i in lister) {
                if (i != gold) {
                    val item = findViewHolderForAdapterPosition(i) as RectangleAdapter.RectangleViewHolder
                    item.itemView.setBackgroundColor(resources.getColor(R.color.red))
//                    val eplayer = SimpleExoPlayer.Builder(item.itemView.context)
//                        .build()
//                        .apply {
//                            item.playerView.player = this
//                            setMediaItem(MediaItem.fromUri(videoUrls[i]))
//                            playWhenReady = false
//                        }
                    item.pausePlaying()
                }
            }
        }
    }

    private fun getVisibleItemHeight(position: Int): Int {
        val child = findViewHolderForAdapterPosition(position) as RectangleAdapter.RectangleViewHolder
        val item = child.itemView
        val location = IntArray(2)
        val l2 = IntArray(2)
        item.getLocationInWindow(location)
        item.getLocationOnScreen(l2)
        Log.d("RecyclerView", "Location window is ${location.toList()}")
        Log.d("RecyclerView", "Location screen is ${l2.toList()}")
        return if (location[1] < 0) {
            location[1] + videoSurfaceDefaultHeight
        }
        else {
            screenDefaultHeight - location[1]
        }
    }

    override fun onScreenStateChanged(screenState: Int) {
        super.onScreenStateChanged(screenState)
        Log.d("RecyclerView", "Screen state changed")
    }

    override fun onWindowFocusChanged(hasWindowFocus: Boolean) {
        super.onWindowFocusChanged(hasWindowFocus)
        Log.d("RecyclerView", "Window focus changed")
        if (hasWindowFocus) {
            currentPlayer?.play()
        }
        else {
            currentPlayer?.pause()
        }
    }

    override fun onWindowVisibilityChanged(visibility: Int) {
        super.onWindowVisibilityChanged(visibility)
        Log.d("RecyclerView", "Window visibility changed!")
    }

    fun setUpVideoUrls(urls: List<String>) {
        this.videoUrls = urls
    }
}