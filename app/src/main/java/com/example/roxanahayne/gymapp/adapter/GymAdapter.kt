package com.example.roxanahayne.gymapp.adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.roxanahayne.gymapp.R
import com.example.roxanahayne.gymapp.data.Gym
import kotlinx.android.synthetic.main.gym_row.view.*
import android.widget.Button
import android.widget.TextView
import com.example.roxanahayne.gymapp.DetailsActivity
import com.example.roxanahayne.gymapp.ResultsActivity
import com.example.roxanahayne.gymapp.data.AppDatabase
import com.example.roxanahayne.gymapp.touch.GymTouchHelperAdapter
import java.util.*


class GymAdapter: RecyclerView.Adapter<GymAdapter.ViewHolder>, GymTouchHelperAdapter {

    private var gymList = mutableListOf<Gym>()

    companion object{
        const val KEY_DATA = "KEY_DATA"
    }

    private val context : Context

    constructor(context: Context, itemList: List<Gym>) : super() {
        this.context = context
        this.gymList.addAll(itemList)

    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.gym_row, parent, false
        )
        return ViewHolder(view)

    }

    override fun getItemCount(): Int {

        return gymList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val gym = gymList[position]

        holder.tvName.text = gym.name

        holder.btnDelete.setOnClickListener {

            deleteGym(holder.adapterPosition)

        }

        setItemClick(holder)

    }

    private fun setItemClick(holder: ViewHolder) {
        holder.itemView.setOnClickListener {

            var intentDetails = Intent(context, DetailsActivity::class.java)
            intentDetails.putExtra(KEY_DATA, gymList[holder.adapterPosition].name)
            context.startActivity(intentDetails)
        }
    }

    fun addGym(gym: Gym) {

        gymList.add(0, gym)

        notifyItemInserted(0)
    }

    private fun deleteGym(adapterPosition: Int) {
        Thread {
            AppDatabase.getInstance(
                context).gymDao().deleteGym(gymList[adapterPosition])

            gymList.removeAt(adapterPosition)

            (context as ResultsActivity).runOnUiThread {
                notifyItemRemoved(adapterPosition)
            }
        }.start()
    }


    override fun onDismissed(position: Int) {
        deleteItem(position)
    }

    private fun deleteItem(adapterPosition: Int) {
        Thread {
            AppDatabase.getInstance(
                context).gymDao().deleteGym(gymList[adapterPosition])

            gymList.removeAt(adapterPosition)

            (context as ResultsActivity).runOnUiThread {
                notifyItemRemoved(adapterPosition)
            }
        }.start()
    }

    override fun onItemMoved(fromPosition: Int, toPosition: Int) {
        Collections.swap(gymList, fromPosition, toPosition)
        notifyItemMoved(fromPosition, toPosition)
    }


    inner class ViewHolder(gymView: View) : RecyclerView.ViewHolder(gymView) {
        val tvName: TextView = gymView.tvGymName
        val btnDelete: Button = gymView.btnGymDelete
    }

}