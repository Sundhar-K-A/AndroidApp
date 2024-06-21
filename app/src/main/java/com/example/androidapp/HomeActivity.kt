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
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidapp.databinding.ActivityHomeBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import com.example.androidapp.networks.MarsPhoto
import kotlinx.coroutines.Dispatchers


class HomeActivity : AppCompatActivity() {
    private lateinit var homeTextView: TextView
    private lateinit var binding: ActivityHomeBinding
    //lateinit var myMarsRecyclerView: RecyclerView
    lateinit var marsAdapter: MarsAdapter
    lateinit var photos:List<MarsPhoto>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        //setContentView(R.layout.activity_home)


        binding = ActivityHomeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        homeTextView = findViewById(R.id.tvWelcomeHome)//username importing

        // imageView = findViewById(R.id.imageView)
        // marsRecyclerView = findViewById(R.id.recyclerViewUrls)
        binding.recyclerViewUrls.layoutManager = LinearLayoutManager(this)
        photos = ArrayList()
        marsAdapter = MarsAdapter(photos)
        binding.recyclerViewUrls.adapter = marsAdapter


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val data = intent.extras?.getString("nkey")
        binding.tvWelcomeHome.text = "welcome $data"//Welcome the user
    }
    private fun getMarsPhotos() {
        GlobalScope.launch(Dispatchers.Main){

            try{
                var listMarsPhotos = MarsApi.retrofitService.getPhotos()
//          photos = listMarsPhotos
                marsAdapter.listMarsPhotos = listMarsPhotos
                marsAdapter.notifyDataSetChanged()
                Log.i("homeactiviy", listMarsPhotos.size.toString())
                Log.i("homeactivity-url", listMarsPhotos.get(1).imgSrc)
            }catch (e:Exception){
                Log.i("HomeError",e.toString())
            }
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