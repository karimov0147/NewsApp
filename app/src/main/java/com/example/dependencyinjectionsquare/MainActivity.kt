package com.example.dependencyinjectionsquare

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.dependencyinjectionsquare.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewBinding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater, null, false) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         setContentView(viewBinding.root)
    }
}