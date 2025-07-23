import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.scheduleapp.databinding.ItemAchievedTodoBinding
import com.example.scheduleapp.models.Todo

class AchievedTodosAdapter(
    private var items: List<Todo> = listOf()
) : RecyclerView.Adapter<AchievedTodosAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemAchievedTodoBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemAchievedTodoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val todo = items[position]
        holder.binding.tvTitle.text = todo.tittle ?: "No title"
        holder.binding.tvDescription.text = todo.description ?: "No description"
        holder.binding.tvDueDate.text = todo.dueDate.toString()
        holder.binding.tvPlace.text = todo.place ?: "No place"
        holder.binding.cbIsComplete.isChecked = todo.isComplete
    }

    // Add this getter for external access
    val currentItems: List<Todo>
        get() = items

    fun updateList(newItems: List<Todo>?) {
        val diffResult = DiffUtil.calculateDiff(AchievesTodoDiffCallback(this.items, newItems))
        if (newItems != null) {
            this.items = newItems
        }
        diffResult.dispatchUpdatesTo(this)
    }

    class AchievesTodoDiffCallback(
        private val oldList: List<Todo>,
        private val newList: List<Todo>?
    ) : DiffUtil.Callback() {

        override fun getOldListSize(): Int = oldList.size
        override fun getNewListSize(): Int = newList?.size ?: 0

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].id == newList?.get(newItemPosition)?.id
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] == newList?.get(newItemPosition)
        }
    }
}
