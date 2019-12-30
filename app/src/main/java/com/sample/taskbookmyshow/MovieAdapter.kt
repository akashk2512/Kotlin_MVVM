package com.sample.taskbookmyshow

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.sample.taskbookmyshow.model.MovieData

/**
 * Created by AKASH on 29/12/19.
 */
class MovieAdapter(private val mContext: Context, private val movieDataList: List<MovieData>) :
    RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.movie_row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = movieDataList[position]
        holder.txt_movie.text = data.language
        holder.type.text = data.type
        holder.title.text = data.title
        holder.txt_rating.text = data.rating

        Glide.with(mContext)
            .load(data.image)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(holder.img_movie)

    }

    override fun getItemCount(): Int {
        return movieDataList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var txt_movie: TextView
        internal var title: TextView
        internal var type: TextView
        internal var txt_rating: TextView
        internal var img_movie: ImageView

        init {
            txt_movie = itemView.findViewById(R.id.txt_movie)
            title = itemView.findViewById(R.id.title)
            type = itemView.findViewById(R.id.type)
            txt_rating = itemView.findViewById(R.id.txt_rating)
            img_movie = itemView.findViewById(R.id.img_movie)

        }
    }
}
