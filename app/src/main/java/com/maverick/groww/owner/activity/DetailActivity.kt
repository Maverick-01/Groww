package com.maverick.groww.owner.activity

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.maverick.groww.R
import com.maverick.groww.databinding.ActivityDetailBinding
import com.maverick.groww.owner.observer.TopGainerLoserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private val viewModel: TopGainerLoserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail)

        val symbolName = intent.getStringExtra("symbolName")

        if (symbolName != null) {
            Log.e("symbolname", symbolName)
            viewModel.getCompanyDetail(symbol = symbolName)
        }
        observeCompanyDetail()
        observeLoading()
        searchStock()
        observeSearchStock()
    }

    private fun observeCompanyDetail() {
        val price = intent.getStringExtra("price")
        val changePercentage = intent.getStringExtra("changePercentage")
        viewModel.getCompanyDetailObservable.observe(this) { response ->
            Log.e("company data", response.toString())
            if (response.isSuccessful) {
                response.let {
                    binding.data = it.body()
                    binding.price.text = price
                    binding.changePercentage.text = changeInValue(changePercentage)
                    binding.changePercentage.setTextColor(changeTextColor(changePercentage))
//            binding.seekBar.progress = it.convertToInt(price)!!
                    binding.editTextSearch.text.clear()
                }
            }
        }
    }

    private fun observeLoading() {
        viewModel.loading.observe(this) { loading ->
            if (loading) {
                binding.progress.visibility = View.VISIBLE
            } else
                binding.progress.visibility = View.GONE
        }
    }

    private fun observeSearchStock(){
        viewModel.getSearchStockObservable.observe(this){response->
            Log.e("search data", response.toString())
            if (response.body()?.bestMatches?.isEmpty() != true){
                viewModel.getCompanyDetail(binding.editTextSearch.text.trim().toString())
            }else{
                Toast.makeText(this, "Please enter valid stock name", Toast.LENGTH_SHORT).show()
                binding.editTextSearch.text.clear()
            }
        }
    }

    private fun searchStock() {
        binding.buttonSearch.setOnClickListener {
            viewModel.getSearchStock(binding.editTextSearch.text.trim().toString())
        }
    }

    private fun changeInValue(changePercentage: String?): String {
        return if (changePercentage?.contains("-") == true) {
            changePercentage
        } else {
            "+$changePercentage"
        }
    }

    private fun changeTextColor(changePercentage: String?): Int {
        return if (changePercentage?.contains("-") == true) {
            ContextCompat.getColor(this, R.color.red)
        } else {
            ContextCompat.getColor(this, R.color.green)
        }
    }
}