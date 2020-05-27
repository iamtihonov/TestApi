package com.example.testapi;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class AppOnboardingResponse  {

    @SerializedName("screens")
    private List<ApiScreen> list;

    public List<ApiScreen> getList() {
        return list;
    }

    @NonNull
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if(list != null && list.size() > 0) {
            for(ApiScreen value: list) {
                sb.append(value.toString());
                sb.append(";");
            }
        }

        return sb.toString();
    }
}