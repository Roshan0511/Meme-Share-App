package com.roshan.simpleapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.roshan.simpleapp.databinding.ActivityMainBinding
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        initdata()

        binding!!.nextBtn.setOnClickListener {
            initdata()
        }
    }

    private fun initdata(){
        binding!!.progressBar.visibility = View.VISIBLE
        val apiInterface: ApiInterface = RetrofitInstance.getInstance().create(ApiInterface::class.java)
        apiInterface.getMeme().enqueue(object : Callback<Pojo> {
            override fun onResponse(call: Call<Pojo>, response: Response<Pojo>) {
                val url: String? = response.body()?.url
                Picasso.get()
                    .load(url)
                    .into(binding!!.memeImage)
                binding!!.progressBar.visibility = View.GONE


                binding!!.shareBtn.setOnClickListener {

                    val intent = Intent(Intent.ACTION_SEND)
                    intent.putExtra(Intent.EXTRA_TEXT, "Hey! See this joke...$url")
                    intent.type = "text/plain"
                    startActivity(Intent.createChooser(intent, "Share Via.."))
                }
            }

            override fun onFailure(call: Call<Pojo>, t: Throwable) {

            }
        })
    }
}