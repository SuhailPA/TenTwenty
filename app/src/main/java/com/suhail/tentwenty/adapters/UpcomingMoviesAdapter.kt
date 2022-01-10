package com.suhail.tentwenty.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.size.Scale
import coil.transform.RoundedCornersTransformation
import com.suhail.tentwenty.R
import com.suhail.tentwenty.data.Result

class UpcomingMoviesAdapter : RecyclerView.Adapter<UpcomingMoviesAdapter.ViewHolderClass>() {

    private var itemClickListner: ((Result) -> Unit)? = null

    inner class ViewHolderClass(view: View) : RecyclerView.ViewHolder(view) {
        val moviePoster: ImageView = view.findViewById(R.id.itemMoviePoster)
        val movieName: TextView = view.findViewById(R.id.itemMovieName)
    }

    private val differCallBack = object : DiffUtil.ItemCallback<Result>() {
        override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem.originalTitle == newItem.originalTitle
        }

        override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, differCallBack)
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UpcomingMoviesAdapter.ViewHolderClass {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.movie_item_layout, parent, false)
        return ViewHolderClass(view)
    }

    override fun onBindViewHolder(holder: UpcomingMoviesAdapter.ViewHolderClass, position: Int) {
        val movieItem = differ.currentList[position]
        holder.movieName.text = movieItem.originalTitle
        holder.moviePoster.load("https://image.tmdb.org/t/p/w500${movieItem.backdropPath}") {
            transformations(RoundedCornersTransformation(20f))
            scale(Scale.FILL)
            error(R.drawable.media_library)
        }
        holder.itemView.setOnClickListener {
            itemClickListner?.let {
                movieItem?.let { it1 ->
                    it(it1)
                }
            }
        }

    }

    fun setOnClickListener(listner: (Result) -> Unit) {
        itemClickListner = listner
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}