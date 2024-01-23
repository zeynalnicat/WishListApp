package com.matrix.android_104_android.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.matrix.android_104_android.db.RoomDb
import com.matrix.android_104_android.db.wishlist.WishListEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WishListViewModel(private val roomDb: RoomDb) : ViewModel() {
    private val _products = MutableLiveData<List<WishListEntity>>()

    val products: LiveData<List<WishListEntity>>
        get() = _products

    fun getProducts() {
        val wishListDao = roomDb.wishListDao()
        CoroutineScope(Dispatchers.IO).launch {
            val products = wishListDao.getAll()
            _products.postValue(products ?: emptyList())
        }
    }
}