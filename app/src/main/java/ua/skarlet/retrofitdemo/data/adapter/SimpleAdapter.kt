/**
 * Copyright © 2020 by Lavreniuk Vladyslav
 */

package ua.skarlet.retrofitdemo.data.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ua.skarlet.retrofitdemo.R

abstract class SimpleAdapter<T>(private val mDataset: MutableList<T>) :
    RecyclerView.Adapter<SimpleAdapter.SimpleViewHolder>() {

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder.
    // Each data item is just a string in this case that is shown in a TextView.
    class SimpleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView = itemView.findViewById(R.id.item_title)
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimpleViewHolder {
        // create a new view
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.simple_list_item, parent, false)
        // set the view's size, margins, padding and layout parameters
        return SimpleViewHolder(
            itemView
        )
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: SimpleViewHolder, position: Int) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.textView.text = fetchTitle(mDataset[position])
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount(): Int {
        return mDataset.size
    }

    fun setItems(items: List<T>) {
        mDataset.clear()
        mDataset.addAll(items)
        notifyDataSetChanged()
    }

    abstract fun fetchTitle(item: T): String
}