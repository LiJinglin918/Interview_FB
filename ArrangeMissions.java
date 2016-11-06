// 42. 给一个String, 如AABACCDCD, 插入'_'使同一个字母间隔为k: 如果k=3: A___AB__AC___CD__CD

class ArrangeMissions {
    public String arrange(String input, int k) {
        if (input.length() <= 1) {
            return input;
        }
        StringBuilder result = new StringBuilder();
        HashMap<Character, Integer> missionToTime = new HashMap<>();
        int time = 0;
        char[] missions = input.toCharArray();
        for (int i = 0; i < missions.length; i++) {
            time++;
            if (!missionToTime.containsKey(missions[i]) || time - missionToTime.get(missions[i]) > k) {
                missionToTime.put(missions[i], time);
            }
            else {
                int gap = k - (time - missionToTime.get(missions[i]) - 1);
                while (gap > 0) {
                    result.append('_');
                    gap--;
                }
                time = k + missionToTime.get(missions[i]) + 1;
                missionToTime.put(missions[i], time);
            }
            result.append(missions[i]);
        }
        return result.toString();
    }
}
