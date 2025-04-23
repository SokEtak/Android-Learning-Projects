import android.annotation.SuppressLint
import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.util.Log
import com.example.audioapp.R

class MainActivity : AppCompatActivity() {
    private var media: MediaPlayer? = null // Nullable MediaPlayer
    private var index = 0
    private val musicList: MutableList<MediaPlayer> by lazy {
        mutableListOf(
            MediaPlayer.create(this, R.raw.vandasong),
            MediaPlayer.create(this, R.raw.vd2),
            MediaPlayer.create(this, R.raw.m1),
            MediaPlayer.create(this, R.raw.m2),
            MediaPlayer.create(this, R.raw.m3),
            MediaPlayer.create(this, R.raw.m4)
        )
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Adjust the window insets to account for system UI such as the status and navigation bars
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
