package utils;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Random;

/**
 * Created by oleg.semenov on 7/23/2015.
 */
public class RandomGenerator {

    public static String generateName() {
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
        return items.get(random.nextInt(items.size()));
    }
}
