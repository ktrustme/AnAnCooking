package com.anan.anancooking.client.ws.remote;

import com.anan.anancooking.model.RecipePreviewImplementation;
import com.anan.anancooking.model.RecipePreviewInterface;

import java.util.List;

/**
 * Created by kuoxin on 5/1/15.
 */
public interface FetchRecommendationPreviewListCallbackInterface {
    void setRecommendationPreviewList(List<RecipePreviewImplementation> recommendationPreviewList);
    void displayDebug();
}
