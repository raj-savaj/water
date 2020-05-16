package com.rj.watersupply.WebApi;

import com.rj.watersupply.modal.CustomerData;
import com.rj.watersupply.modal.Product;
import com.rj.watersupply.modal.ProductDetail;
import com.rj.watersupply.modal.ResponseLogin;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface API {
    String BASE_URL = "http://192.168.56.1/watersupply/";

    @POST("checkLogin.php")
    @FormUrlEncoded
    Call<ResponseLogin> checkLogin(@Field("email") String r_email, @Field("pass") String r_pwd);

    @POST("insertCustomer.php")
    @FormUrlEncoded
    Call<ResponseLogin> InsertCustomer(@FieldMap HashMap<String, String> hashFields);

    @POST("addBottle.php")
    @FormUrlEncoded
    Call<ResponseLogin> addBottle(@FieldMap HashMap<String, String> hashFields);

    @GET("customers.php")
    Call<List<CustomerData>> getAllCustomer();

    @GET("getProducts.php")
    Call<List<Product>> getAllProduct();

    @GET("getTodayProductDetail.php")
    Call<List<ProductDetail>> getProductDetail();


}
