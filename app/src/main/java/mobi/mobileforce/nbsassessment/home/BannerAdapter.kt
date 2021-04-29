package mobi.mobileforce.nbsassessment.home

import android.content.Context
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.StatefulAdapter
import com.bumptech.glide.Glide
import mobi.mobileforce.nbsassessment.R
import java.lang.IndexOutOfBoundsException

class BannerAdapter(val context: Context): RecyclerView.Adapter<BannerAdapter.BannerHolder>() {
    val urls = ArrayList<ItemModel>()

    fun setItems(newUrls: List<ItemModel>){
        urls.clear()
        urls.addAll(newUrls)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.banner_item_layout, parent, false)
        return BannerHolder(view)
    }

    override fun onBindViewHolder(holder: BannerHolder, position: Int) {
        try{
            Glide.with(context)
                .load(urls[position].url)
                .into(holder.imageView)
        } catch (e: IndexOutOfBoundsException){
            e.printStackTrace()
        }

    }

    override fun getItemCount(): Int {
        return urls.size
    }

    class BannerHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val imageView = itemView.findViewById<ImageView>(R.id.image)
    }
}