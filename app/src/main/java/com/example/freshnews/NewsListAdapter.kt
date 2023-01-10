package com.example.freshnews

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class NewsListAdapter(private val listener: NewsItemClicked): RecyclerView.Adapter<NewsViewHolder>() {


    private val items: ArrayList<News> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {

        // LayoutInflater converts an xml file to a view
        // the line below converts item_news xml file to a view
        val view=LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false)
        val viewHolder=NewsViewHolder(view)

        view.setOnClickListener {
            listener.onItemClicked(items[viewHolder.adapterPosition])
        }

        // returning the view just created from xml file
        return viewHolder
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val currentItem=items[position]
        holder.titleView.text=currentItem.title
        holder.author.text=currentItem.author

        Glide.with(holder.itemView.context).load(currentItem.imageToUrl).into(holder.image)
    }

    fun updateNews(updatedNews: ArrayList<News>){
        items.clear()
        items.addAll(updatedNews)

        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return items.size
    }
}


// making view holder class for the adapter
class NewsViewHolder(view: View): RecyclerView.ViewHolder(view){
    val image: ImageView=view.findViewById(R.id.image)
    val titleView: TextView=view.findViewById(R.id.title1)
    val author: TextView=view.findViewById(R.id.author)
}

// making an interface for callback
interface NewsItemClicked{
    fun onItemClicked(item: News){

    }
}


