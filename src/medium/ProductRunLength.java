package medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProductRunLength {

    /**
     * Approach - 1 : Expand the encoded arrays and then take product. Then encode it again.
     * Approach - 2 : Two pointers approach, iterate over the encoded array and produce encoded product.
     *      - index1 and index2 are pointing to the two ecoded array current element to be considered.
     *      - index1Count and index2Count counts how much is already consumed.
     *      - index3 and index3Count are the corresponding pointers for the product array.
     *      - If product stays same then increment index3Count
     *      - If index1Count is exhausted then increment it,If index2Count is exhausted then increment it,
     * @param encoded1
     * @param encoded2
     * @return
     */
    public List<List<Integer>> findRLEArray(int[][] encoded1, int[][] encoded2) {
        List<List<Integer>> result = new ArrayList<>();
        if (encoded1 == null || encoded1.length == 0) {
            // return same as encoded2
            if (encoded2 == null || encoded2.length == 0) {
                return result;
            }
            for (int[] val : encoded2) {
                result.add(List.of(val[0], val[1]));
            }
            return result;
        }

        if (encoded2 == null || encoded2.length == 0) {
            // return same as encoded1
            for (int[] val : encoded2) {
                result.add(List.of(val[0], val[1]));
            }
            return result;
        }

        int index1 = 0, index2 = 0, index3 = -1;
        int index1Count = 0, index2Count = 0, index3Count = 0;
        while (index1 < encoded1.length && index2 < encoded2.length) {
            if (index1Count >= encoded1[index1][1]) {
                // We already exhaused this entry so move to the next entry.
                index1++;
                index1Count = 1;
            } else {
                index1Count++;
            }
            if (index1 >= encoded1.length) {
                break;
            }
            int element1 = encoded1[index1][0];
            if (index2Count >= encoded2[index2][1]) {
                // We already exhaused this entry so move to the next entry.
                index2++;
                index2Count = 1;
            } else {
                index2Count++;
            }
            if (index2 >= encoded2.length) {
                break;
            }

            int element2 = encoded2[index2][0];
            // TODO: Mind the possible overflow. Will handle it in the next pass.
            int element3 = element1 * element2;
            if (index3 == -1) {
                index3 = 0;
                index3Count = 1;
                result.add(new ArrayList<>());
                result.get(index3).add(element3);
                result.get(index3).add(index3Count);
            } else {
                int lastProduct = result.get(index3).get(0);
                if (element3 == lastProduct) {
                    // Just add up to the last entry as per the run length algo
                    index3Count++;
                    result.get(index3).set(1, index3Count);
                } else {
                    // Create a new entry in the result.
                    index3++;
                    index3Count = 1;
                    List<Integer> newEntry = new ArrayList<>();
                    newEntry.add(element3);
                    newEntry.add(index3Count);
                    result.add(newEntry);
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        ProductRunLength productRunLength = new ProductRunLength();
        int[][] encoded1 = {{1,3},{2,3}}, encoded2 = {{6,3},{3,3}};
        System.out.println("Product run length " + productRunLength.findRLEArray(encoded1, encoded2));
        int[][] encoded11 = {{1,3},{2,1},{3,2}}, encoded22 = {{2,3},{3,3}};
        System.out.println("Product run length " + productRunLength.findRLEArray(encoded11, encoded22));
    }
}


