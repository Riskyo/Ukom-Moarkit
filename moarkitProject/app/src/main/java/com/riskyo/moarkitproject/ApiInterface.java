package com.riskyo.moarkitproject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {
    @GET("/api/menu")
    Call<GetMenu> getMenu();

    @POST("/api/login")
    Call<GetLogin> loginReg(@Body Login login);

    @POST("api/register")
    Call<GetRegister> register(@Body RegisterModel registerModel);

//    @FormUrlEncoded
//    @POST("kontak")
//    Call<PostPutDelKontak> postKontak(@Field("nama") String nama,
//                                      @Field("nomor") String nomor);
//    @FormUrlEncoded
//    @PUT("kontak")
//    Call<PostPutDelKontak> putKontak(@Field("id") String id,
//                                     @Field("nama") String nama,
//                                     @Field("nomor") String nomor);
//    @FormUrlEncoded
//    @HTTP(method = "DELETE", path = "kontak", hasBody = true)
//    Call<PostPutDelKontak> deleteKontak(@Field("id") String id);
}
