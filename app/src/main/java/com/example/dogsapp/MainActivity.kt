package com.example.dogsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.dogsapp.ui.main.BreedsFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_breeds)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, BreedsFragment.newInstance())
                .commitNow()
        }
    }
}