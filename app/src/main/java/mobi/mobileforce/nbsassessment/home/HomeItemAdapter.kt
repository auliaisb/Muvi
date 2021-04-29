package mobi.mobileforce.nbsassessment.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import mobi.mobileforce.nbsassessment.R
import java.lang.IndexOutOfBoundsException

class HomeItemAdapter(val context: Context): RecyclerView.Adapter<HomeItemAdapter.HomeItemHolder>() {
    val urls = ArrayList<ItemModel>()

    fun setItems(newUrls: List<ItemModel>){
        urls.clear()
        urls.addAll(newUrls)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeItemHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.home_item_layout, parent, false)
        return HomeItemHolder(view)
    }

    override fun onBindViewHolder(holder: HomeItemHolder, position: Int) {
        try{
            Glide.with(context)
                .load(urls[position].url)
                .into(holder.image)
        } catch (e: IndexOutOfBoundsException){
            e.printStackTrace()
        }
    }

    override fun getItemCount(): Int {
        return urls.size
    }

    class HomeItemHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val image = itemView.findViewById<ImageView>(R.id.image)
        init {
            image.setOnClickListener {  }
        }
    }

}