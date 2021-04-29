package mobi.mobileforce.nbsassessment.search

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import mobi.mobileforce.nbsassessment.R

class SearchAdapter(val context: Context): RecyclerView.Adapter<SearchAdapter.SearchHolder>() {
    val items = ArrayList<ItemModel>()

    fun setItems(newItems: List<ItemModel>){
        items.clear()
        items.addAll(newItems)
    }

    class SearchHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val image = itemView.findViewById<ImageView>(R.id.image)
        val title = itemView.findViewById<TextView>(R.id.title)
        val casts = itemView.findViewById<TextView>(R.id.casts)
        init {
            itemView.setOnClickListener {  }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchHolder {
        val view= LayoutInflater.from(context).inflate(R.layout.search_item_layout, parent, false)
        return SearchHolder(view)
    }

    override fun onBindViewHolder(holder: SearchHolder, position: Int) {
        with(holder){
            Glide.with(context)
                .load(items[position].urlImage)
                .into(image)

            title.text = items[position].title
            casts.text = items[position].casts
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

}