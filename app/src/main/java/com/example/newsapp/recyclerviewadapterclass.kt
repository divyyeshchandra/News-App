package com.example.newsapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class recyclerviewadapterclass(private val listner:NewsItemClicked): RecyclerView.Adapter<newsViewHolder>() {

    private val item: ArrayList<news> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): newsViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.item_news,parent, false)
        val viewHolder=newsViewHolder(view)
        view.setOnClickListener{
            listner.onClicked(item[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: newsViewHolder, position: Int) {
        val currentItems= item[position]
        holder.titleView.text=currentItems.title
        holder.author.text=currentItems.author
        Glide.with(holder.itemView.context).load(currentItems.Imageurl).into(holder.image)


    }

    override fun getItemCount(): Int {
        return item.size
    }

    fun updateNews(updatedNews:ArrayList<news>)
    {
        item.clear()
        item.addAll(updatedNews)
        notifyDataSetChanged()
    }

}

class newsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
{
    val titleView: TextView= itemView.findViewById(R.id.title)
    val image: ImageView=itemView.findViewById(R.id.image)
    val author: TextView=itemView.findViewById(R.id.author)
}

interface NewsItemClicked{
    fun onClicked(item:news)
}