package com.example.androidapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.androidapp.R
import com.example.androidapp.networks.Wallpapers

class WallpaperAdapter(var listWallpapers: List<Wallpapers>) : RecyclerView.Adapter<WallpaperAdapter.WallpaperViewHolder>() {

    inner class WallpaperViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var wallpaperView: ImageView = itemView.findViewById(R.id.wallpaperView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WallpaperViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.horizontal_layout, parent, false)
        return WallpaperViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listWallpapers.size
    }

    override fun onBindViewHolder(holder: WallpaperViewHolder, position: Int) {
        val wallpaper = listWallpapers[position]
        holder.wallpaperView.load(wallpaper.urls.full) {
            crossfade(true)
        }
    }
}
