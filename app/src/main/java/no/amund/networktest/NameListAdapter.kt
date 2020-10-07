package no.amund.networktest

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.name_list_item.view.*

/**
 * Created by Amund Bogetvedt on 02/09/2020.
 */

class NameListAdapter(val nameList: MutableList<String> = mutableListOf()): RecyclerView.Adapter<NameListAdapter.NameViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NameViewHolder {
        val view = parent.inflate(R.layout.name_list_item)
        return NameViewHolder(view)
    }

    override fun onBindViewHolder(holder: NameViewHolder, position: Int) {
        holder.itemView.name.text = nameList[position]
    }

    override fun getItemCount() = nameList.size

    fun replaceList(list: List<String>) {
        nameList.clear()
        nameList.addAll(list)
        notifyDataSetChanged()
    }

    class NameViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    }

}