class Solution {
    public int convertTime(String current, String correct) {
        int curr = toMinutes(current);
        int corr = toMinutes(correct);

        int diff = corr - curr;
        int ops = 0;

        ops += diff / 60;
        diff %= 60;

        ops += diff / 15;
        diff %= 15;

        ops += diff / 5;
        diff %= 5;

        ops += diff;

        return ops;
    }

    private int toMinutes(String time) {
        int hours = Integer.parseInt(time.substring(0, 2));
        int minutes = Integer.parseInt(time.substring(3, 5));
        return hours * 60 + minutes;
    }
}