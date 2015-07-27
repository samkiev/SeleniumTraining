package utils;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Random;

/**
 * Created by oleg.semenov on 7/23/2015.
 */
public class StringGenerator {

    public static String generateRandomName() {
        char[] abcArray = new char[26];
        for (int i = 0; i < abcArray.length; i++) {
            abcArray[i] = (char) ('a' + i);
        }
        String name = null;
        for (int i = 0; i < 15; i++) {
            name = name + abcArray[(int) (Math.random() * abcArray.length - 1)];
        }
        return name;
    }

    public static String getRandomItem(@NotNull List<String> items) {
        Random random = new Random();
        String randomItem = null;
        try {
            randomItem = items.get(random.nextInt(items.size()));
        }catch (IllegalArgumentException e){
            System.out.println("Did not select items. Reason: " + e.getMessage());
        }
        return  randomItem;
    }

    public static String encode(@NotNull String word) {
        return word.replaceAll(" ", "%20");
    }
}
