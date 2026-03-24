package medium;

import java.util.ArrayList;
import java.util.List;

public class RestoreIPAddresses93 {
    List<String> res = new ArrayList<>();
    public List<String> restoreIpAddresses(String s) {
        restoreIpAddRecursively(s, "", 3);
        return res;
    }

    private void restoreIpAddRecursively(String s, String validIpSoFar, int remainingOctants) {
        if (remainingOctants == 0) {
            if (isValidOctant(s)) {
                res.add(new String(validIpSoFar + s));
            }
            return;
        }

        for (int i = 1; i < s.length(); i++) {
            String firstPart = s.substring(0, i);
            String secondPart = s.substring(i);
            // First part has to be valid octant and 2nd can not start with 0 except it's zero itself
            if (isValidOctant(firstPart) && isPossibleValidOctantStart(secondPart)) {
                // Recurse now with vallid ip so far
                String withOneMoreOctant = validIpSoFar.length() == 0 ? firstPart + "." : validIpSoFar + firstPart + ".";
                restoreIpAddRecursively(secondPart, withOneMoreOctant, remainingOctants - 1);
            }
        }
    }

    private boolean isPossibleValidOctantStart(String s) {
        if (s == null || s.length() == 0) {
            return false;
        }

        return true;
    }

    private boolean isValidOctant(String s) {
        if (s == null || s.length() == 0) {
            return false;
        }

        if (s.startsWith("0")) {
            // If this is starting with zero then only 0 itself is allowed.
            return s.length() == 1;
        }
        if (s.length() > 3) {
            return false;
        }

        // Otherwise number should be within 0 - 255
        int intValue = Integer.valueOf(s);
        return intValue >= 0 && intValue <= 255;
    }

    public static void main(String[] args) {
        RestoreIPAddresses93 restoreIPAddresses93 = new RestoreIPAddresses93();
        System.out.println(restoreIPAddresses93.restoreIpAddresses("25525511135"));
        RestoreIPAddresses93 restoreIPAddresses93_1 = new RestoreIPAddresses93();
        System.out.println(restoreIPAddresses93_1.restoreIpAddresses("0000"));
        RestoreIPAddresses93 restoreIPAddresses93_2 = new RestoreIPAddresses93();
        System.out.println(restoreIPAddresses93_2.restoreIpAddresses("101023"));
    }
}


