package com.projetopublicado;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface TimeService {
    @GET("scores/live.json?key=HPWQZC5mFOKAhHCp&secret=ZvSfIOufLmvXqK17zYYyA3VfRFP9nsym")
    Call<ApiResult> listarProjetos();

}
