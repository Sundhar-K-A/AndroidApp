package com.example.androidapp

import MarsAdapter
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidapp.kotlinegs.LoginManager
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import com.example.androidapp.networks.MarsPhoto
import okhttp3.Dispatcher
import kotlinx.coroutines.Dispatchers


class HomeActivity : AppCompatActivity() {
    private lateinit var homeTextView: TextView
    lateinit var myMarsRecyclerView: RecyclerView
    lateinit var marsAdapter: MarsAdapter
    lateinit var photos:List<MarsPhoto>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)
        homeTextView = findViewById(R.id.tvWelcomeHome)//username importing

        myMarsRecyclerView = findViewById(R.id.recyclerViewUrls)
        myMarsRecyclerView.layoutManager = LinearLayoutManager(this)
        photos = ArrayList()
        marsAdapter = MarsAdapter(photos)
        myMarsRecyclerView.adapter = marsAdapter

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val data = intent.extras?.getString("nkey")
        homeTextView.text = "welcome $data"//Welcome the user
    }
    private fun getMarsPhotos() {
        GlobalScope.launch(Dispatchers.Main){
            var listMarsPhotos =   MarsApi.retrofitService.getPhotos()
            Log.i("Mars", listMarsPhotos.size.toString())
//          photos = listMarsPhotos
            marsAdapter.listMarsPhotos = listMarsPhotos
            marsAdapter.notifyDataSetChanged()
            Log.i("Mars",listMarsPhotos.size.toString())
            Log.i("Mars-url",listMarsPhotos.get(1).imgSrc)
        }
    }
    fun getJson(view: View) {
        getMarsPhotos()

    }
    fun wallpaperPage(view: View) {
        Log.i("Wallpaperpage","transfer to wallpaper page")
        var hIntent: Intent = Intent(this, WallpaperActivity::class.java)
        startActivity(hIntent)
    }

}