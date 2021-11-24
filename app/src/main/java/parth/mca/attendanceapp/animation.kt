package parth.mca.attendanceapp

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class animation : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animation)
        this.supportActionBar?.hide()
        window.statusBarColor = Color.WHITE

        Handler().postDelayed({
            startActivity(Intent(this,MainActivity::class.java))

        },3000)
    }
}