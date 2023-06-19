package com.yumtaufikhidayat.tourismappcleanarchitecture.core.domain.repository

import androidx.lifecycle.LiveData
import com.yumtaufikhidayat.tourismappcleanarchitecture.core.data.Resource
import com.yumtaufikhidayat.tourismappcleanarchitecture.core.domain.model.Tourism

interface ITourismRepository {
    fun getAllTourism(): LiveData<Resource<List<Tourism>>>

    fun getFavoriteTourism(): LiveData<List<Tourism>>

    fun setFavoriteTourism(tourism: Tourism, state: Boolean)

}