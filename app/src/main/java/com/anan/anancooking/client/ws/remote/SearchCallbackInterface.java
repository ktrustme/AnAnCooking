package com.anan.anancooking.client.ws.remote;

import com.anan.anancooking.model.RecipePreviewImplementation;

import java.util.List;

/**
 * Created by kuoxin on 5/1/15.
 */
public interface SearchCallbackInterface {
    void setSearchResultList(List<RecipePreviewImplementation> recommendationPreviewList);
    void displayDebug();
}
