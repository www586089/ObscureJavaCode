package com.zf.obscurecode.nw.generator;

import java.util.Random;

public class NameGenerator implements Generator<String> {
    @Override
    public String generator(long seeds) {
        Random random = new Random(System.nanoTime());//3 4 3

        int variableLength = random.nextInt(10);

        if (variableLength < 3) {
            variableLength = 3;
        }

        int firstCount, secondCount, thirdCount;
        if (3 == variableLength) {
            firstCount = 1;
            secondCount = 1;
            thirdCount = 1;
        } else if (4 == variableLength) {
            firstCount = 1;
            secondCount = 2;
            thirdCount = 1;
        } else if (5 == variableLength) {
            firstCount = 2;
            secondCount = 2;
            thirdCount = 1;
        } else {
            firstCount = random.nextInt(variableLength) -1;

            if (firstCount <= 0) {
                firstCount = 2;
            }
            secondCount = firstCount / 2 + 1;
            thirdCount = variableLength - firstCount - secondCount;
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(randomAlpha[random.nextInt(randomAlpha.length)]);

        for (int i = 0; i < firstCount; i++) {
            stringBuilder.append(randomAlpha[random.nextInt(randomAlpha.length)]);
        }

        for (int i = 0; i < secondCount; i++) {
            stringBuilder.append(randomDigit[random.nextInt(randomDigit.length)]);
        }
        for (int i = 0; i < thirdCount; i++) {
            stringBuilder.append(randomDigit[random.nextInt(randomDigit.length)]);
        }

        return stringBuilder.toString();

    }
}
