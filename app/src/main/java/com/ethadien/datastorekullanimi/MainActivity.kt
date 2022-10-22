package com.ethadien.datastorekullanimi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.ethadien.datastorekullanimi.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val ap = AppPref(this)

        CoroutineScope(Dispatchers.Main).launch {

            ap.saveName("John Doe")
            ap.deleteName()

            val storedName = ap.readName()
            Log.e("Gelen Ad", storedName)

            var storedCounter = ap.readCounter()
            ap.saveCounter(++storedCounter)

            binding.testView.text = " : $storedCounter"
        }

        binding.resetButton.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                ap.resetCounter()
                binding.testView.text = "Açılış Sayısı : ${ap.readCounter()}"
            }
        }
    }
}