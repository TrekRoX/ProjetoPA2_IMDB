package br.com.willtrkapp.projetopa2

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import br.com.willtrkapp.projetopa2.Adapters.MovieAdapter
import br.com.willtrkapp.projetopa2.Models.Movie
import br.com.willtrkapp.projetopa2.Services.IMDBClient
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*
import org.jetbrains.anko.support.v4.ctx


class HomeFragment : Fragment() {

    lateinit var ctx: Context
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val layoutFragment = inflater.inflate(R.layout.fragment_home, null)
        //layoutFragment.limparButton.setOnClickListener(this)
        //layoutFragment.buscarButton.setOnClickListener(this)

        val imdbClient = IMDBClient(activity as MainActivity)

        //Call Back da requisição
        imdbClient.callback = object : IMDBClient.MovieCallback{
            override fun onResponse(obj: Movie) {
                if (obj.response.equals("True")) {

                    val movie = obj
                    val movieList = obj.search

                    val layoutManager = LinearLayoutManager(ctx)
                    layoutManager.orientation = LinearLayout.VERTICAL
                    movie_list_recyclerView.layoutManager = layoutManager

                    if (movieList.size > 0) {
                        //Fez busca em lista
                        val adapter = MovieAdapter(ctx, movieList)
                        movie_list_recyclerView.adapter = adapter

                    }
                    else
                    {
                        //Fez busca exata
                        var lstMovie: MutableList<Movie> = mutableListOf<Movie>()
                        lstMovie.add(movie)
                        val adapter = MovieAdapter(ctx, lstMovie)
                        movie_list_recyclerView.adapter = adapter
                    }
                    movie_list_recyclerView.adapter?.notifyDataSetChanged()

                }
            }

            override fun onResponseFail(obj: Movie) {
                limpaFiltro()

                movie_list_recyclerView.adapter = null
                movie_list_recyclerView.adapter?.notifyDataSetChanged()
                Toast.makeText(this@HomeFragment.context, obj.error, Toast.LENGTH_LONG).show()
            }

            override fun onRequestFail(err: Throwable) {
                Toast.makeText(this@HomeFragment.context, err.message, Toast.LENGTH_LONG).show()
            }
        }

        //Clique buscar
        layoutFragment.buscarButton.setOnClickListener{

            var nomeFilme: String = titFilmEditText.text.toString()
            var idIMBD: String = idIMDBEditText.text.toString()


            if(nomeFilme.trim().length > 0)
            {
                idIMDBEditText.setText("");

                //Buscar pelo titulo
                //imdbClient.searchListMovies(nomeFilme)
                imdbClient.findMovie(nomeFilme)
            }
            else if (idIMBD.trim().length > 0) {
                titFilmEditText.setText("");

                //Buscar pelo ID
                imdbClient.findMovieById(idIMBD)

            }
            else
            {
                Toast.makeText(this@HomeFragment.context, "Preencha pelo menos um dos campos", Toast.LENGTH_SHORT).show()
            }

        }

        //Clique limpar
        layoutFragment.limparButton.setOnClickListener{
            limpaFiltro()
        }

        return layoutFragment;
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (context != null){
            this.ctx = context as Context
        }
    }

    fun limpaFiltro(){
        titFilmEditText.text?.clear()
        idIMDBEditText.text?.clear()
    }
}
