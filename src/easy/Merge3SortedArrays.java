package easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Merge3SortedArrays {
    /**
     * Asked in Meta as a 2nd problem: I got confused a little bit and could not complete before time.
     * @param args
     */
    public int[] mergeArrays(int[] a, int[] b, int[] c) {
        List<Integer> result = new ArrayList<>();
        int ia = 0,ib = 0,ic = 0;
        while (ia < a.length && ib < b.length && ic < c.length) {
            int smallest = a[ia];
            if (b[ib] < smallest) {
                smallest = b[ib];
            }
            if (c[ic] < smallest) {
                smallest = c[ic];
            }

            if (result.isEmpty()) {
                result.add(smallest);
            } else {
                int lastElement = result.get(result.size() - 1);
                if (smallest != lastElement) {
                    result.add(smallest);
                }
            }

            if (a[ia] == smallest) {
                ia++;
            } else if (b[ib] == smallest) {
                ib++;
            } else {
                ic++;
            }
        }

        // one ore more lists exhausted.
        if (ia == a.length) {
            // a list is exhausted
            mergeTwoArrays(b, ib, c, ic, result);
        } else if (ib == b.length) {
            // b list exhausted
            mergeTwoArrays(a, ia, c, ic, result);
        } else {
            // c list exhauseted
            mergeTwoArrays(a, ia, b, ib, result);
        }


        return result.stream().mapToInt(i -> i).toArray();
    }

    private void mergeTwoArrays(int[] a, int starta, int[] b, int startb, List<Integer> result) {
        int ia = starta, ib = startb, ic = starta;
        while (ia < a.length && ib < b.length) {
            int smallest = a[ia];
            if (b[ib] < smallest) {
                smallest = b[ib];
            }

            if (result.isEmpty()) {
                result.add(smallest);
            } else {
                int lastElement = result.get(result.size() - 1);
                if (smallest != lastElement) {
                    result.add(smallest);
                }
            }

            if (a[ia] == smallest) {
                ia++;
            } else {
                ib++;
            }
        }

        if (ia == a.length) {
            // a exhausted, copy remaining of b to result
            copySubArray(b, ib, result);
        } else {
            // b exhausted, copy remainling of b to result
            copySubArray(a, ia, result);
        }
    }

    private void copySubArray(int[] a, int start, List<Integer> result) {
        int ia = start;
        while (ia < a.length) {
            if (result.isEmpty()) {
                result.add(a[ia]);
            } else {
                int lastElement = result.get(result.size() - 1);
                if (a[ia] != lastElement) {
                    result.add(a[ia]);
                }
            }
            ia++;
        }
    }

    public static void main(String[] args) {
        Merge3SortedArrays merge3SortedArrays = new Merge3SortedArrays();

        int[] a = {-100, -50, 0, 0, 0, 2, 4};
        int[] b = {-102, -51, 0, 0, 90};
        int[] c = {100, 102, 115, 115, 115, 115, 115};

        System.out.println(Arrays.toString(merge3SortedArrays.mergeArrays(a, b, c)));
    }
}


