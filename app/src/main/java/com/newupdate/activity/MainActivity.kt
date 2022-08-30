package com.newupdate.activity

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.mahindra.database.AppDatabase
import com.newupdate.common.BaseActivity
import com.newupdate.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {

    private lateinit var mainViewModel: MainViewModel
    private lateinit var _binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        val view = _binding.root
        setContentView(view)

        mainViewModel = androidx.lifecycle.ViewModelProvider(this).get(MainViewModel::class.java)

        showLoading()
        mainViewModel.getPinCodes()?.observe(this, Observer {

            val db = Room.databaseBuilder(
                this,
                AppDatabase::class.java, "pwos"
            ).allowMainThreadQueries().build()
            val pincodesDao = db.pincodeDao()
            val pincode_list = pincodesDao.getAll()
            hideLoading()

            _binding.rvZipcode.adapter = ZipcodeAdapter(this, pincode_list)
            _binding.rvZipcode.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        })

    }


}