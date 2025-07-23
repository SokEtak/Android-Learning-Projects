package com.example.retrofit

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofit.databinding.ActivityMainBinding
import retrofit2.Callback
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    lateinit var mainBinding: ActivityMainBinding
    var postsList = ArrayList<Posts>()
    val BASE_URL = "https://jsonplaceholder.typicode.com"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        /*these 3 line added by ourselves*/
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        val view = mainBinding.root
        setContentView(view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        mainBinding.recyclerView.layoutManager = LinearLayoutManager(this)
        showPost()
    }
    fun showPost() {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val retrofitApi = retrofit.create(RetrofitApi::class.java)
        val call = retrofitApi.getAllPosts()

        call.enqueue(object : Callback<List<Posts>> {

            override fun onResponse(
                call: Call<List<Posts>?>,
                response: Response<List<Posts>?>,
            ) {
                if(response.isSuccessful){
                    Toast.makeText(applicationContext,"Api Success", Toast.LENGTH_SHORT).show()
                    postsList = response.body() as ArrayList<Posts>
                    val postsAdapter = PostsAdapter(postsList)

                    //setup recyclerview
                    mainBinding.recyclerView.adapter = postsAdapter
                    //use it to see the progressbar clearly
                    Thread.sleep(5000)
                    //disappear the progressBar and textview
                    mainBinding.progressBar.isEnabled = false
                    mainBinding.textView.visibility = View.GONE
                }
            }

            override fun onFailure(call: Call<List<Posts>>, t: Throwable) {
//                Log.e("API_ERROR", "Network call failed: ${t.message}", t)
                Toast.makeText(applicationContext,"Api Error", Toast.LENGTH_SHORT).show()
            }
        })
    }

}