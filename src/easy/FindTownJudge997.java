package easy;

import java.util.HashSet;
import java.util.Set;

public class FindTownJudge997 {
    /**
     * Approach : Count inDegree and outDegree of each guest.
     *
     * trust = [a, b]  increase the out degree of a and increase the indegree of b.
     * At the end, find a guest with indegree n - 1 and outdegree = 0
     * @param n
     * @param trust
     * @return
     */
    public int findJudge(int n, int[][] trust) {
        int[] inDegree = new int[n + 1];
        int[] outDegree = new int[n + 1];
        for (int[] trustRelation : trust) {
            int truster = trustRelation[0];
            int trusted = trustRelation[1];
            outDegree[truster]++;
            inDegree[trusted]++;
        }

        // Iterate to find a gues with n - 1 indegree and 0 outdegree
        for (int i = 1; i <= n; i++) {
            if (inDegree[i] == n - 1 && outDegree[i] == 0) {
                return i;
            }
        }

        return -1;
    }

    /**
     * This implementation assumes that trust relationship is not transitive.
     * @param n
     * @param trust
     * @return
     *
     * Apporach:
     * - Iterate over trust array and trust[i][1] possibly can be a judge.
     * - If trust[i][0] exists in possible judges then remove it.
     * - return -1 if possible judge set has 0 ore more than 1 elements otherwise element in the set is the judge.
     *
     * Note: This approach miss the point when judge should be trusted by all other members :-(
     */
    public int findJudge1(int n, int[][] trust) {
        Set<Integer> possibleJudges = new HashSet<>();
        Set<Integer> notPossibleJudge = new HashSet<>();
        for (int[] trustRelation : trust) {
            int truster = trustRelation[0];
            notPossibleJudge.add(truster);
            if (possibleJudges.contains(truster)) {
                possibleJudges.remove(truster);
            }

            // If we yet not sure that trusted person can be judge or not then add that to possible judges.
            int trusted = trustRelation[1];
            if (!notPossibleJudge.contains(trusted)) {
                possibleJudges.add(trustRelation[1]);
            }
        }

        return notPossibleJudge.size() == n ? -1 : (possibleJudges.size() == 1 ? possibleJudges.iterator().next() : -1);
    }

    public static void main(String[] args) {
        int n = 3; int[][] trust = {{1,3}, {2,3}, {3,1}};
        System.out.println(new FindTownJudge997().findJudge(n, trust));
        int n1 = 3; int[][] trust1 = {{1,3},{2,3}};
        System.out.println(new FindTownJudge997().findJudge(n1, trust1));
    }
}


