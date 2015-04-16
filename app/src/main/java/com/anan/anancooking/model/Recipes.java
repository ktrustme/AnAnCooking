package com.anan.anancooking.model;

import com.anan.anancooking.R;
import com.anan.anancooking.model.BriefRecipe;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

/**
 * Created by kuoxin on 4/4/15.
 */
public class Recipes {
    static final BriefRecipe[] recipes = {
            new BriefRecipe(R.drawable.fried_chicken, "Fried Chicken", "Yummy fried chicken just in 5 min!"),
            new BriefRecipe(R.drawable.fried_egg, "Fried Chicken", "Every day start with a fried egg"),
            new BriefRecipe(R.drawable.fried_rice, "Home Fried Rice", "The best fried rice ever."),
            new BriefRecipe(R.drawable.spanish_pallea, "Spanish Pallea", "Spanish Seafood Pallea!"),
            new BriefRecipe(R.drawable.kfc_chicken, "Chicken Leg", "Yummy fried KFC chicken just in 5 min!"),
            new BriefRecipe(R.drawable.lamian, "Lamian", "Traditional Japanese Noodle!"),
    };

    public static ArrayList<BriefRecipe> asList() {
        ArrayList<BriefRecipe> items = new ArrayList<BriefRecipe>();
        for (int i = 0, z = recipes.length; i < z; i++) {
            items.add(recipes[i]);
        }
        return items;
    }

    /**
     * Return a list of random cheeses.
     *
     * @param count the amount of cheeses to return.
     */
    public static ArrayList<BriefRecipe> randomList(int count) {
        Random random = new Random();
        HashSet<BriefRecipe> items = new HashSet<BriefRecipe>();

        // Make sure that don't infinity loop
        count = Math.min(count, recipes.length);

        while (items.size() < count) {
            items.add(recipes[random.nextInt(recipes.length)]);
        }

        return new ArrayList<BriefRecipe>(items);
    }
}
