package com.morteza.shoppy.api.slider

import com.morteza.shoppy.model.ApiResponse
import com.morteza.shoppy.model.slider.Sliders
import retrofit2.http.GET

interface SliderApi {

    @GET("slider")
    suspend fun getSliders() : ApiResponse<Sliders>
}