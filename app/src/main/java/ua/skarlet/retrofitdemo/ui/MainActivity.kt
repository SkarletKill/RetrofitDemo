package ua.skarlet.retrofitdemo.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ua.skarlet.retrofitdemo.data.GitHubClient
import ua.skarlet.retrofitdemo.data.GitHubRepo
import ua.skarlet.retrofitdemo.R
import ua.skarlet.retrofitdemo.data.adapter.SimpleAdapter

class MainActivity : AppCompatActivity() {
    private lateinit var adapter: SimpleAdapter<GitHubRepo>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.pagination_list)

        adapter = object : SimpleAdapter<GitHubRepo>(mutableListOf()) {
            override fun fetchTitle(item: GitHubRepo): String = item.name
        }
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        val builder: Retrofit.Builder = Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())

        val retrofit = builder.build()
        val client = retrofit.create(GitHubClient::class.java)
        val call = client.reposForUser("SkarletKill")

        call.enqueue(object : Callback<List<GitHubRepo>> {
            override fun onFailure(call: Call<List<GitHubRepo>>?, t: Throwable?) {
                Toast.makeText(this@MainActivity, "network error :(", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(
                call: Call<List<GitHubRepo>>?,
                response: Response<List<GitHubRepo>>?
            ) {
                response?.let {
                    adapter.setItems(it.body())
                } ?: Toast.makeText(this@MainActivity, "response error :(", Toast.LENGTH_SHORT)
                    .show()
            }
        })
    }
}