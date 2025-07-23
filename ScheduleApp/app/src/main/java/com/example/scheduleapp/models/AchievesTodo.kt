import androidx.room.DatabaseView
import java.util.Date

@DatabaseView(
    viewName = "achieved_todos_view",
    value = """
        SELECT 
            id, 
            tittle, 
            description, 
            startDate, 
            dueDate, 
            isComplete, 
            place, 
            completedDate 
        FROM todo_table 
        WHERE isComplete = 1
    """
)
data class AchievesTodo(
    val id: Int,
    val tittle: String?,
    val description: String?,
    val startDate: Date,
    val dueDate: Date,
    val isComplete: Boolean,
    val place: String?,
    val completedDate: Date?
)
