package `in`.surelocal.mynewsapp

import `in`.surelocal.mynewsapp.ui.trial.BlankFragment
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle



class BlankActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContentView(R.layout.activity_blank)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, BlankFragment())
                .commitNow()
        }

    }
}