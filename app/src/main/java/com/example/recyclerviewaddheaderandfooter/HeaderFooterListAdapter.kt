package com.example.recyclerviewaddheaderandfooter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class HeaderFooterListAdapter(
    private var headerData: String,
    private var footerData: String
): ListAdapter<String, RecyclerView.ViewHolder>(DiffCallback()) {
    companion object {
        const val HEADER = 0
        const val CONTENT = 1
        const val FOOTER = 2
    }

    override fun getItemCount(): Int {
        return super.getItemCount() + 2
    }

    override fun getItemViewType(position: Int): Int {
        return when(position) {
            0 -> HEADER
            itemCount - 1 -> FOOTER
            else -> CONTENT
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            HEADER -> {
                val v = LayoutInflater.from(parent.context).inflate(R.layout.header, parent, false)
                HeaderViewHolder(v)
            }
            FOOTER -> {
                val v = LayoutInflater.from(parent.context).inflate(R.layout.footer, parent, false)
                FooterViewHolder(v)
            }
            else -> {
                val v = LayoutInflater.from(parent.context).inflate(R.layout.content, parent, false)
                ContentViewHolder(v)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (position) {
            0 -> {
                holder as HeaderViewHolder
                holder.header.text = headerData
            }
            itemCount - 1 -> {
                holder as FooterViewHolder
                holder.footer.text = footerData
            }
            else -> {
                holder as ContentViewHolder
                holder.content.text = getItem(position - 1)
            }
        }
    }

    inner class ContentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val content: TextView = itemView.findViewById(R.id.content)
    }

    inner class HeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val header: TextView = itemView.findViewById(R.id.header)
    }
    inner class FooterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val footer: TextView = itemView.findViewById(R.id.footer)
    }

    class DiffCallback: DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }
    }
}