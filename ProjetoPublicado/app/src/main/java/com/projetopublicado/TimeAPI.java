package com.projetopublicado;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TimeAPI {

    private static TimeService instance;
    static String BASE_API = "http://livescore-api.com/api-client/";
                                                //verificar se funciona com ou sem o /clubes
    public static TimeService getInstance() {
        if (instance == null){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_API)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            instance = retrofit.create(TimeService.class);
        }
        return instance;
    }
}
