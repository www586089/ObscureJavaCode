package com.zf.obscurecode.nw.generator;

import com.zf.obscurecode.nw.common.Constant;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class NameGenerator implements Generator<String> {

    private Set<String> variableSet = null;

    public NameGenerator() {
        variableSet = new HashSet<>();
    }

    @Override
    public String generate(long seeds) {
        Random random = new Random(seeds);//3 4 3
        String name = null;
        int tryCount = 0;

        while (true) {
            int variableLength = random.nextInt(15);

            if (variableLength < 5) {
                variableLength = 5;
            }

            int firstCount, secondCount, thirdCount;
            if (5 == variableLength) {
                firstCount = 2;
                secondCount = 1;
                thirdCount = 2;
            } else if (6 == variableLength) {
                firstCount = 2;
                secondCount = 2;
                thirdCount = 2;
            } else if (7 == variableLength) {
                firstCount = 2;
                secondCount = 2;
                thirdCount = 3;
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

            name = stringBuilder.toString();
            if (variableSet.contains(name) && Constant.VARIABLE_TRY_COUNT == ++tryCount) {
                break;
            } else {
                variableSet.add(name);
            }
        }

        return name;
    }

    @Override
    public void destroy() {
        if (null != variableSet && variableSet.size() > 0) {
            variableSet.clear();
            variableSet = null;
        }
    }
}
