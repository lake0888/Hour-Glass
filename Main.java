import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

import java.lang.reflect.Array;

class Main {
    final static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        List<List<Integer>> arr = new ArrayList<>();
        IntStream.range(0, 6).forEach(i -> {
            arr.add(Stream.of(scanner.nextLine()
            .replaceAll("\\s+$", "")
            .split(" "))
            .map(Integer::parseInt)
            .collect(toList()));
        });

        System.out.println(maxHourGlasses(arr));
    }

    public static int maxHourGlasses(List<List<Integer>> arr) {
        int max = Integer.MIN_VALUE;
        int i = 0;
        Integer[] a = new Integer[arr.size() - 2];
        fillArray(a);
        int z = 0;
        while (i < arr.size() - 2) {            
            int j = 0;
            while (j < arr.get(i).size() - 2) {
                int currentPos = getCurrentPost(i, z);            
                if (z % 2 == 0) {
                    int sum = 0;
                    for (int k = j; k < j + 3; k++) {
                        sum += arr.get(currentPos).get(k);
                    }
                    a[j] = a[j] + sum;          
                } else {
                    a[j] = a[j] + arr.get(currentPos).get(j + 1);
                }
                j++;
            }
            z++;
            if (z == 3) {
                max = Math.max(max, Collections.max(Arrays.asList(a)));

                i++;  
                z = 0;
                a = new Integer[arr.size() - 2];
                fillArray(a);
            }     
        }
        return max;
    }

    public static void fillArray(Integer[] a) {
        for (int i = 0; i < a.length; i++) {
            a[i] = 0;
        }
    }

    public static int getCurrentPost(int i, int z) {
        if (z == 0) return i;
        else if (z == 1) return i + 1;
        return i + 2;
    }
}