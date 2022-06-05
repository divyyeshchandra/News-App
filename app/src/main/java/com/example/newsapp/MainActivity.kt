package com.example.newsapp

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), NewsItemClicked {

    private lateinit var madapter:recyclerviewadapterclass
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.layoutManager=LinearLayoutManager(this)
        fetchdata()
        madapter= recyclerviewadapterclass(this)
        recyclerView.adapter=madapter
    }

    private fun fetchdata()
    {
        val url= "https://saurav.tech/NewsAPI/top-headlines/category/health/in.json"

        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null,
            Response.Listener {
                val newsJsonArray=it.getJSONArray("articles")
                val newsArray= ArrayList<news>()
                for(i in 0 until newsJsonArray.length())
                {
                    val newsJsonObject=newsJsonArray.getJSONObject(i)
                    val News=news(
                        newsJsonObject.getString("title"),
                        newsJsonObject.getString("author"),
                        newsJsonObject.getString("url"),
                        newsJsonObject.getString("urlToImage")
                    )
                    newsArray.add(News)
                }
                madapter.updateNews(newsArray)
            },
            Response.ErrorListener {

            }
        )

// Access the RequestQueue through your singleton class.
        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)
    }

    override fun onClicked(item: news) {
        val builder= CustomTabsIntent.Builder()
        val customTabsIntent = builder.build()
        customTabsIntent.launchUrl(this, Uri.parse(item.url))
    }
}