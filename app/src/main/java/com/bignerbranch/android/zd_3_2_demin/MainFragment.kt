package com.bignerbranch.android.zd_3_2_demin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso


class MainFragment : Fragment() {
    companion object {
        private const val ARG_TITLE = "title"
        private const val ARG_DESCR = "descr"
        private const val ARG_IMAGE = "img"

        fun newInstance(title: String, descr: String, img: String): MainFragment {
            return MainFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_TITLE, title)
                    putString(ARG_DESCR, descr)
                    putString(ARG_IMAGE, img)
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_main, container, false)

        val title = arguments?.getString(ARG_TITLE) ?: "Без названия"
        val descr = arguments?.getString(ARG_DESCR) ?: "Без описания"
        val img = arguments?.getString(ARG_IMAGE) ?: "" // ← исправлено

        val titleTextView = view.findViewById<TextView>(R.id.title)
        val descrTextView = view.findViewById<TextView>(R.id.descr_frag)
        val imageFrag = view.findViewById<ImageView>(R.id.ImageFrag) // ← стиль

        titleTextView.text = title
        descrTextView.text = descr

        if (img.isNotEmpty()) {
            Picasso.get().load(img).into(imageFrag)
        } // ← дополнительно: проверка на пустой URL

        return view
    }
}