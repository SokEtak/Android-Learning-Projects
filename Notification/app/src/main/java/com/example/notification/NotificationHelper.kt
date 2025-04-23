import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat

class NotificationHelper(val context: Context) {

    // Create Notification Channel (for Android 8.0 and above)
    fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channelId = "1"
            val channelName = "My Channel"
            val channelDescription = "This is a description of my notification channel"
            val importance = NotificationManager.IMPORTANCE_DEFAULT

            // Create the NotificationChannel
            val channel = NotificationChannel(channelId, channelName, importance).apply {
                description = channelDescription
            }

            // Get the NotificationManager system service
            val notificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            // Register the channel with the system
            notificationManager.createNotificationChannel(channel)
        }
    }

    // Create and show a notification
    fun showNotification() {
        // Build the notification
        val notification = NotificationCompat.Builder(context, "1")  // Use the channel ID "1"
            .setContentTitle("Sample Notification")
            .setContentText("This is a sample notification in Android!")
            .setSmallIcon(android.R.drawable.ic_dialog_info)  // Use a system icon
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .build()

        // Show the notification
        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(0, notification)  // 0 is the unique notification ID
    }
}
