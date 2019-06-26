package br.com.willtrkapp.projetopa2.Adapters

import android.annotation.SuppressLint
import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import br.com.willtrkapp.projetopa2.Models.Movie
import br.com.willtrkapp.projetopa2.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.movie_item.view.*

class MovieAdapter(val context: Context,  val lstMovie: List<Movie>) : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.movie_item , parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return lstMovie.size
    }

    override fun onBindViewHolder(holder: ViewHolder, pPosition: Int) {
        //holder.setData(lstMovie[pPosition])

        val movie = lstMovie[pPosition]
        holder.setData(movie, context)
    }

    class ViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun setData(movie: Movie, context: Context){
            itemView.idIMDB.text = movie.imdbID
            itemView.titleMovie.text = movie.title
            itemView.yearMovie.text = movie.year
            itemView.typeMovie.text = movie.type
            itemView.rateIMDB.text = movie.imdbRating
            itemView.releaseDate.text = movie.released
            itemView.director.text = movie.director
            itemView.language.text = movie.language
            itemView.actors.text = movie.actors
            itemView.country.text = movie.country
            itemView.production.text = movie.production
            itemView.webSite.text = movie.website


            if (movie.poster != "N/A"){
                Picasso.get().load(movie.poster).into(itemView.imgMovie);
            }
            else{
                //itemView.imgMovie.setBackgroundColor(R.color.colorAccent)
            }

        }
    }
}