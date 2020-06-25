package `in`.surelocal.mynewsapp

import `in`.surelocal.mynewsapp.login.LogInFragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, LogInFragment.newInstance())
                .commitNow()
        }
    }
}
