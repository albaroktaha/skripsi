package com.hazelnut.myjobs.Retrofit;

import com.hazelnut.myjobs.Model.ApiResponse;
import com.hazelnut.myjobs.Model.Data;
import com.hazelnut.myjobs.Model.Lamaran;
import com.hazelnut.myjobs.Model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {

//    PERUSAHAAN LOGIN DAN REGIS
    @FormUrlEncoded
    @POST("register.php")
    Call<ApiResponse> perfomUserSignIn(@Field("username") String username, @Field("password") String password,
                                       @Field("perusahaan") String perusahaan, @Field("nama") String nama, @Field("email") String email,
                                       @Field("notelp") String notelp, @Field("alamat") String alamat);

    @FormUrlEncoded
    @POST("login.php")
    Call<ApiResponse> perfomUserLogin(@Field("username") String username, @Field("password") String password);

//    CRUD PERUSAHAAN
    @FormUrlEncoded
    @POST("perusahaan/create-loker.php")
    Call<ApiResponse> createLoker(@Field("id_perusahaan") String idPerusahaan, @Field("kota") String kota,
                                  @Field("jobs") String jobs,@Field("jobs_desc") String jobsDesc, @Field("tanggal") String tanggal);

    @GET("list-loker.php")
    Call<List<Data>> lokerList(@Query("id_perusahaan") String idPerusahaan);

    @GET("perusahaan/list-lamaran.php")
    Call<List<Lamaran>> listLamaran(@Query("id_perusahaan") String idPerusahaan);


//    PELAMAR LOGIN DAN REGIS
    @FormUrlEncoded
    @POST("pelamar/register.php")
    Call<ApiResponse> UserSignUp(@Field("username") String username, @Field("password") String password,
                                 @Field("nama") String nama, @Field("email") String email,
                                 @Field("notelp") String notelp, @Field("alamat") String alamat);

    @FormUrlEncoded
    @POST("pelamar/login.php")
    Call<ApiResponse> UserLogin(@Field("username") String username, @Field("password") String password);

//    CRUD PELAMAR
    @GET("loker.php")
    Call<List<Data>> loker(@Query("id_loker") String idLoker);

    @GET("test.php")
    Call<List<Data>> quicksort(@Query("tanggal") String tanggal);

    @FormUrlEncoded
    @POST("pelamar/kirim-lamaran.php")
    Call<ApiResponse> sendCV(@Field("id_pelamar") String idPelamar, @Field("id_loker") String id_loker);

//    @FormUrlEncoded
//    @POST("pelamar/file.php")
//    Call<ApiResponse> uploadDocument(@Field("pdf") String encodedPDF);

    @FormUrlEncoded
    @POST("pelamar/update-user.php")
    Call<ApiResponse> userUpdate(@Field("id_loker") String idLoker, @Field("username") String username,
                                 @Field("password") String password, @Field("nama") String nama,
                                 @Field("email") String email, @Field("notelp") String notelp, @Field("alamat") String alamat);

    @GET("read-user.php")
    Call<List<User>> readUser(@Query("id_pelamar") String idPelamar);
}
