package com.example.testapi;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetAppOnboardingService {

    @GET("app/onboarding")
    public Call<AppOnboardingResponse> getScreens();
}