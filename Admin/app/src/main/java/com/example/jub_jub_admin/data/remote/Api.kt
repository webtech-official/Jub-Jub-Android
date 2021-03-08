package com.example.jub_jub_admin.data.remote

import com.example.jub_jub_admin.entity.dataclass.GetLaptopResponse
import com.example.jub_jub_admin.entity.dataclass.body.LaptopSaveDTO
import com.example.jub_jub_admin.entity.dataclass.body.AddLaptopSpec
import com.example.jub_jub_admin.entity.dataclass.body.Login
import com.example.jub_jub_admin.entity.dataclass.body.SignUp
import com.example.jub_jub_admin.entity.dataclass.response.*
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*


interface Api {

    @POST("signin")
    fun login(@Body login: Login): Call<LoginResponse>

    @POST("signup")
    fun signUp(@Body signUp: SignUp): Call<MyResponse>

    @GET("admin/applyview")
    fun getRentRequest(@Header("Authorization") token: String): Call<RentRequestResponse>

    @PUT("admin/approved/{eqa_Idx}")
    fun allowRentRequest(@Header("Authorization") token: String,
                         @Path("eqa_Idx")eqa_Idx: Int): Call<MyResponse>

    @PUT("admin/reject/{eqa_Idx}")
    fun denyRentRequest(@Header("Authorization") token: String,
                        @Path("eqa_Idx")eqa_Idx: Int): Call<MyResponse>

    @Multipart
    @POST("admin/equipment")
    fun addItem(@Header("Authorization") token: String,
                @Part img_equipment: MultipartBody.Part,
                @Part("name") name: RequestBody,
                @Part("content") content: RequestBody,
                @Part("count") count: RequestBody,
    ): Call<MyResponse>

    @GET("equipment/")
    fun getAllEquipment(@Header("Authorization") token: String): Call<GetEquipmentResponse>

    @Multipart
    @PUT("admin/equipmentAll/{oldName}")
    fun modifyEquipment(
            @Header("Authorization") token: String,
            @Part img_equipment: MultipartBody.Part,
            @Path("oldName") oldName: String,
            @Part("content") content: RequestBody,
            @Query("count") count: Int,
            @Part("newName") newName: RequestBody
    ): Call<MyResponse>

    //@GET("admin/laptop")
    //fun getLaptopData(@Header("Authorization") token: String): Call<LaptopResponse>)

    @GET("admin/laptop")
    fun getAllLaptop(@Header("Authorization") token: String): Call<GetLaptopResponse>

    @POST("admin/laptop")
    fun addLaptop(@Header("Authorization") token: String, @Body laptopSaveReqDto : LaptopSaveDTO): Call<MyResponse>

    @GET("admin/laptop-spec")
    fun getLaptopSpec(@Header("Authorization") token: String): Call<GETLaptopSpecResponse>

    @POST("admin/laptop-spec")
    fun addLaptopSpec(@Header("Authorization") token: String, @Body laptopSpecDto: AddLaptopSpec): Call<MyResponse>




}