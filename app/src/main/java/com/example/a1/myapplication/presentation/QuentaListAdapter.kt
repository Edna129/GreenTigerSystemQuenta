package com.example.a1.myapplication.presentation

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.a1.myapplication.R
import com.example.a1.myapplication.domain.models.view.ModelQuenta
import kotlinx.android.synthetic.main.layout_quenta.view.*

/**
 * Author 1
 * Since 21.01.2018.
 */

interface OnItemClick{
    fun onClick(item: String)
}

class QuentaListAdapter(
        var onItemClick: OnItemClick
) : RecyclerView.Adapter<QuentaListAdapter.ViewHolder>() {

    private var list: ArrayList<ModelQuenta> = arrayListOf<ModelQuenta>()

    fun setData(newList: ArrayList<ModelQuenta>){
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = list.size

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder =
            ViewHolder(LayoutInflater.from(parent?.context).inflate(R.layout.item_view_quenta, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        fun bind(item: ModelQuenta){
            itemView.tvName.text = item.name
            itemView.tvWorld.text = item.worlds
            Glide.with(itemView)
                    .load(item.avatar)
                    .into(itemView.ivAvatar)
            itemView.setOnClickListener {
                onItemClick.onClick(item.worlds)
            }
        }
    }

}