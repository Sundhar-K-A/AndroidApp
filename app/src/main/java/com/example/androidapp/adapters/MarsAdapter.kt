import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.androidapp.R
import com.example.androidapp.networks.MarsPhoto

class MarsAdapter(var listMarsPhotos: List<MarsPhoto>) :RecyclerView.Adapter<MarsAdapter.MarsViewHolder>() {

    class MarsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var textView:TextView = itemView.findViewById(R.id.tvRow)
        var marsImageView:ImageView = itemView.findViewById(R.id.ivPhoto)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarsViewHolder {
        var rowPlank = LayoutInflater.from(parent.context).inflate(R.layout.row_layout,parent,false)
        return  MarsViewHolder(rowPlank)
    }

    override fun getItemCount(): Int {
        return listMarsPhotos.size
    }

    override fun onBindViewHolder(holder: MarsViewHolder, position: Int) {
        var url:String = listMarsPhotos.get(position).imgSrc
        holder.textView.setText(url)
        holder.marsImageView.load(url)
    }
}