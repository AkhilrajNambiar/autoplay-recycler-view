package com.example.recyclerviewlikeinstagram

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.ui.PlayerView

class RectangleAdapter(val itemsList: List<String>): RecyclerView.Adapter<RectangleAdapter.RectangleViewHolder>() {

    inner class RectangleViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var parent: View? = null
        var videoView: PlayerView? = null
        val playerView = itemView.findViewById<PlayerView>(R.id.player)
        var playWhenReadyBool = false
        var currentWindow = 0
        var playbackPosition = 0L
        var player: SimpleExoPlayer? = null
        var videoUrl: String? = null

        fun bind(url: String) {
            parent = itemView
            parent!!.tag = this
            videoView = playerView
            videoUrl = url
        }

        fun initializePlayer(url: String) {
            val trackSelector = DefaultTrackSelector(itemView.context).apply {
                setParameters(buildUponParameters().setMaxVideoSizeSd())
            }
            player = SimpleExoPlayer.Builder(itemView.context)
                .setTrackSelector(trackSelector)
                .build()
                .also {
                    videoView?.player = it
                    it.setMediaItem(MediaItem.fromUri(videoUrl!!))
                    it.playWhenReady = playWhenReadyBool
                    it.seekTo(currentWindow, playbackPosition)
                    it.prepare()
                }
        }

        fun startPlaying() {
            player?.play()
        }

        fun pausePlaying() {
            player?.pause()
        }

        fun releasePlayer() {
            player?.run {
                playWhenReadyBool = this.playWhenReady
                playbackPosition = this.currentPosition
                currentWindow = this.currentWindowIndex
                release()
            }
            player = null
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RectangleViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.backgound_color_item, parent, false)
        return RectangleViewHolder(layout)
    }

    override fun onBindViewHolder(holder: RectangleViewHolder, position: Int) {
        val item = itemsList.get(position)
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return itemsList.size
    }

}