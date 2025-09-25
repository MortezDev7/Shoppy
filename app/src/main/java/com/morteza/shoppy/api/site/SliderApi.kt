package com.morteza.shoppy.api.site

import com.morteza.shoppy.model.ApiResponse
import com.morteza.shoppy.model.site.Sliders
import retrofit2.http.GET

interface SliderApi {

    @GET("slider")
    suspend fun getSliders() : ApiResponse<Sliders>
}