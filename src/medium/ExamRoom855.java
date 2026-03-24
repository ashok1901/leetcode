package medium;

public class ExamRoom855 {
    /**
     * Approach:
     * - For each seat maintain, nearest left occupied and nearest right occupied
     * - To search a seat:
     *      - For each possible available seat, find the difference in left and right. Choose the seat with max
     *      difference and smallest seat number in case of tie. T: O(n)
     * - When a seat is occupied
     *      - Update it's left and right occupied T: O(n) this is worst case though we have to traverse upto nearest occupied.
     * - When is left
     *      - update it's left and right occupied T:O(n) this is worst case though we have to traverse upto nearest occupied.
     *
     * Complexity :
     *  - seat T : O(n)
     *  - leave T : O(n)
     *  - S O(n) as we need to maintain two arrays for left occupied and right occupied.
     *
     * <NotWorking>
     * Approach: Array with binary search.
     *  - If first and last seat is fill then try to get seat in the middle.
     * - If middle is full then try to find in the first half, if no middle seat in the first half then go to second half.
     *
     * Complexit :
     *  seat : O(n) because it checks all seats in the worst case
     *  leave : O(1)
     *  </NotWorking>
     * @param n
     */
    private int[] seats;
    private int n;
    // Not used
    private int occupiedSeatsCount;
    private int[] nearestLeftOccupiedSeat;
    private int[] nearestRightOccupiedSeat;
    public ExamRoom855(int n) {
        this.n = n;
        this.occupiedSeatsCount = 0;
        this.seats = new int[n];
        this.nearestLeftOccupiedSeat = new int[n];
        this.nearestRightOccupiedSeat = new int[n];
        this.initUnoccupiedSeats(nearestRightOccupiedSeat);
        this.initUnoccupiedSeats(nearestLeftOccupiedSeat);
    }

    private void initUnoccupiedSeats(int[] seats) {
        for (int i = 0; i < n; i++) {
            seats[i] = -1;
        }
    }

    public int seat() {
        int minCountSeatId = -1;
        int maxSeatNeighborDistance = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            // This seat is available
            if (seats[i] == 0) {
                int leftNeighbor = nearestLeftOccupiedSeat[i] == -1 ?  - 1 : nearestLeftOccupiedSeat[i];
                int rightNeighbor = nearestRightOccupiedSeat[i] == -1 ? -1 : nearestRightOccupiedSeat[i];
                if (leftNeighbor == -1 && rightNeighbor == -1) {
                    // No left neighbor no right neighbor and this seat is free, it means no one in the room.
                    minCountSeatId = 0;
                    break;
                } if (rightNeighbor == -1) {
                    // If no right neighbor, it menas we should occupy right most seat/
                    minCountSeatId = n - 1;
                    break;
                } else {
                    int maxDistance = Math.max(i - leftNeighbor, rightNeighbor - i);
                    if (maxDistance > maxSeatNeighborDistance) {
                        minCountSeatId = i;
                        maxSeatNeighborDistance = maxDistance;
                    }
                }
            }
        }
        if (minCountSeatId == -1) {
            // No seat available at all
            return -1;
        }

        // Before returning this seat id, lets update it's neighbors
        int left = nearestLeftOccupiedSeat[minCountSeatId];
        if (left == -1) {
            // No left neighbor for this seat so this become right neighbor for all seats on the left
            for (int k = minCountSeatId - 1; k >= 0; k++) {
                nearestRightOccupiedSeat[k] = minCountSeatId;
            }
        } else {
            nearestRightOccupiedSeat[left] = minCountSeatId;
        }

        int right = nearestRightOccupiedSeat[minCountSeatId];
        if (right == -1) {
            for (int k = minCountSeatId + 1; k < n; k++) {
                nearestLeftOccupiedSeat[k] = minCountSeatId;
            }
        } else {
            nearestLeftOccupiedSeat[right] = minCountSeatId;
        }

        seats[minCountSeatId] = 1;
        return minCountSeatId;
    }

    /**
     * Searching half half will not work
     * @param start
     * @param end
     * @return
     */
    private int findSeatRecursively(int start, int end) {
        if (occupiedSeatsCount == 0) {
            return 0;
        }
        int mid = start + (end - start) / 2;
        if (seats[mid] == 0) {
            return mid;
        }

        int seatInFirstHalf = findSeatRecursively(start, mid - 1);
        int seatInSecondHalf = findSeatRecursively(mid + 1, end);
        if (seatInFirstHalf >= 0) {
            return seatInFirstHalf;
        }

        occupiedSeatsCount++;
        return seatInSecondHalf;
    }

    public void leave(int p) {
        int leftNeighbor = nearestLeftOccupiedSeat[p];
        int rightNeighbor = nearestRightOccupiedSeat[p];
        // After leaving this seat, left occupied of right neighbor will be left neighbor of seat being vacated.
        nearestLeftOccupiedSeat[rightNeighbor] = leftNeighbor;
        // After leaving this seat, right occupied of left neighbor will be right neighbor of seat being vacated.
        nearestRightOccupiedSeat[leftNeighbor] = rightNeighbor;
        // Leave the seat
        seats[p] = 0;
        occupiedSeatsCount--;
    }

    public static void main(String[] args) {
        ExamRoom855 room = new ExamRoom855(10);
        System.out.println(room.seat());
        System.out.println(room.seat());
        System.out.println(room.seat());
        System.out.println(room.seat());
        room.leave(4);
        System.out.println(room.seat());
    }
}


