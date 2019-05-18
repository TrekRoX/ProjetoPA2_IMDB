package br.com.willtrkapp.projetopa2

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*


class HomeFragment : Fragment(), View.OnClickListener {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val layoutFragment = inflater.inflate(R.layout.fragment_home, null)

        layoutFragment.limparButton.setOnClickListener(this)
        layoutFragment.buscarButton.setOnClickListener(this)

        return layoutFragment;
    }

    override fun onClick(v: View?) {

        when(v){
            limparButton -> {
                titFilmEditText.text?.clear()
                idIMDBEditText.text?.clear()
            }
            buscarButton ->{

            }
        }
    }
}
