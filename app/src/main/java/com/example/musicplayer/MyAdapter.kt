package com.example.musicplayer

import Data
import MyData
import android.app.Activity
import android.media.MediaPlayer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.squareup.picasso.Picasso

//adapter connects UI with data sources

class MyAdapter(val context:Activity, val dataList:List<Data>):
    RecyclerView.Adapter<MyAdapter.MyViewHolder>(){



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        //called when recyclerview needs a new viewHolder of the given type to represent an item
      val itemView=LayoutInflater.from(context).inflate(R.layout.each_item,parent,false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        //return the total number of itemm in the data set held by the adapter
        return dataList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        //called by the recycler view to display the data at the specified pos
        //populate the data into the view
        val currentData=dataList[position]

        val mediaPlayer=MediaPlayer.create(context,currentData.preview.toUri())
        holder.title.text=currentData.title

        //picaso for image url to image
        Picasso.get().load(currentData.album.cover).into(holder.image)

        holder.play.setOnClickListener{
            mediaPlayer.start()
        }

        holder.pause.setOnClickListener{
            mediaPlayer.pause()
        }
//aise he play pause ki trh next vgera bhi kr skte h
    }
    class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
//create the view incase layout manager fails to create the view for the data
        //holds the data

        val image :ImageView
        val title : TextView
        val play : ImageButton
        val pause : ImageButton

        init {

            image=itemView.findViewById(R.id.musicImage)
            title=itemView.findViewById(R.id.musicTitle)
            play=itemView.findViewById(R.id.btnPlay)
            pause=itemView.findViewById(R.id.btnPause)

        }

    }
}