package com.example.androidapp

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidapp.adapters.WallpaperAdapter
import com.example.androidapp.networks.UnplashApi
import com.example.androidapp.networks.Wallpapers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class WallpaperActivity : AppCompatActivity() {
    lateinit var myWallpaperRecyclerView: RecyclerView
    lateinit var wallpaperApaters: WallpaperAdapter
    lateinit var wallpapers: List<Wallpapers>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_wallpaper)
        myWallpaperRecyclerView = findViewById(R.id.wallpaperRecyclerView)
        myWallpaperRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        wallpapers = ArrayList()
        wallpaperApaters = WallpaperAdapter(wallpapers)
        myWallpaperRecyclerView.adapter = wallpaperApaters
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        getWallpapers()
        Log.i("Wallpaperpage","Reached the wallpaper page")
    }
    private fun getWallpapers() {
        GlobalScope.launch(Dispatchers.Main){
            var listWallpapers =   UnplashApi.retrofitService.getWallpapers()
            Log.i("Mars", listWallpapers.size.toString())
            wallpaperApaters.listWallpapers = listWallpapers
            wallpaperApaters.notifyDataSetChanged()
            Log.i("wallpapers",listWallpapers.size.toString())
            Log.i("wallpaper_url",listWallpapers.get(1).urls.full)
        }
    }
}