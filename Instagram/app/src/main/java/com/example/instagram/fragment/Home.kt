package com.example.instagram.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.instagram.R
import com.example.instagram.adaptor.HomeAdaptor
import com.example.instagram.entrenador.HomeEntrenador

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

var recyclerVStory : RecyclerView? = null
var recyclerVPost : RecyclerView? = null

/**
 * A simple [Fragment] subclass.
 * Use the [home.newInstance] factory method to
 * create an instance of this fragment.
 */
class Home : Fragment() {
    var totalLikes = 0
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val fragmentHome = inflater.inflate(R.layout.fragment_home, container, false)
        recyclerVStory = fragmentHome.findViewById(R.id.recycler_view_story)
        recyclerVPost = fragmentHome.findViewById(R.id.recycler_view_photos)
        // Inflate the layout for this fragment
        return fragmentHome
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment home.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Home().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val listaPosts = arrayListOf<HomeEntrenador>()
        listaPosts.add(HomeEntrenador("danny",R.drawable.imagen,"lorem ipsum dolor sit amet",0))
        listaPosts.add(HomeEntrenador("estefania",R.drawable.imagen2,"lorem ipsum dolor sit amet",2))
        listaPosts.add(HomeEntrenador("alejandro",R.drawable.imagen4,"lorem ipsum dolor sit amet",0))
        listaPosts.add(HomeEntrenador("pamela",R.drawable.imagen4,"lorem ipsum dolor sit amet",2))


        recyclerVStory(listaPosts,this, recyclerVStory!!,
            HomeAdaptor(this,listaPosts, recyclerVStory!!)
        )
       // recyclerVPost(listaPosts,this, recyclerVPost!!,PostAdaptor(this,listaPosts,recyclerVPost!!))

    }

    fun recyclerVStory(lista: List<*>, actividad: Home, recyclerView: RecyclerView, adaptador:RecyclerView.Adapter<*>){
        recyclerView.adapter = adaptador
        recyclerView.itemAnimator= androidx.recyclerview.widget.DefaultItemAnimator()
        recyclerView.layoutManager = GridLayoutManager(context, 1)
         //recyclerView.layoutManager = LinearLayoutManager(actividad.context, LinearLayoutManager.HORIZONTAL, false)
        adaptador.notifyDataSetChanged()
    }

    fun aumentarTotalLikes() {
        totalLikes = totalLikes + 1
        val textView = view?.findViewById<TextView>(R.id.post_txtv_likes)
        textView!!.text = totalLikes.toString()
    }

}
