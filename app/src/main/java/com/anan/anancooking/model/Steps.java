package com.anan.anancooking.model;

import com.anan.anancooking.R;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

/**
 * Created by kuoxin on 4/4/15.
 */
public class Steps {
    static final RecipeStep[] steps={
            new RecipeStep(R.drawable.egg_beater,"Whip The Egg!","OK First we need to whip the egg, right? If you don't have an egg beater, either chopsticks or a spoon would be fine..."),
            new RecipeStep(R.drawable.rice,"Stir The Rice!","Now let's prepare the rice. Put some oil and keep stiring the rice in a big bow,"),
            new RecipeStep(R.drawable.chop_onion,"Cut The Evil Onion!","Chop it! Cut it! Kill it! Let's go buddy! Take care of your hand!"),
            new RecipeStep(R.drawable.fried_rice_wok,"You Are Almost Done!","Now it's the final time! Put them together! Stir it! Shake it all!"),
            new RecipeStep(R.drawable.fried_rice_finished,"That's It! You Make It!","OMG! Look at that! !%#%@$#%@$#@")
    };

    public static ArrayList<RecipeStep> asList() {
        ArrayList<RecipeStep> items = new ArrayList<RecipeStep>();
        for (int i = 0, z = steps.length ; i < z ; i++) {
            items.add(steps[i]);
        }
        return items;
    }
}
