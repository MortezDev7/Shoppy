package com.morteza.shoppy.repository.slider

import com.morteza.shoppy.api.slider.SliderApi
import com.morteza.shoppy.model.ApiResponse
import com.morteza.shoppy.model.api.slider.Sliders
import com.morteza.shoppy.repository.base.BaseRepository
import javax.inject.Inject

class SliderRepository @Inject constructor(
    private val api : SliderApi,
): BaseRepository() {

    suspend fun getSliders(): ApiResponse<Sliders> =
        safeApiCall {
            api.getSliders()
        }
}