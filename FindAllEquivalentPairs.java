// 96. Find All Equivalent Pairs
/*
Given an array A of integers, find the index of values that satisfy A + B =C + D, where A,B,C & D are integers values in the array. Find all combinations of quadruples.
*/
// 'Time complexity: O(n^2)'

class FindPair {
    public List<List<int[]>> find(int[] input) {
        HashMap<Integer, Indexs> sumToPair = new HashMap<>();
        for (int i = 0; i < input.length - 1; i++) {
            for (int j = i + 1; j < input.length; j++) {
                int sum = input[i] + input[j];
                if (!sumToPair.containsKey(sum)) {
                    sumToPair.put(sum, new Indexs());
                }
                int[] newPair = new int[]{i, j};
                Set<Integer> index = sumToPair.get(sum).index;
                if (!index.contains(i) && !index.contains(j)) {
                    sumToPair.get(sum).pairs.add(newPair);
                    sumToPair.get(sum).index.add(i);
                    sumToPair.get(sum).index.add(j);
                }
            }
        }
        List<List<int[]>> result = new ArrayList<>();
        for (int sum : sumToPair.keySet()) {
            List<int[]> pairs = sumToPair.get(sum).pairs;
            if (pairs.size() >= 2) {
                List<int[]> list = new ArrayList<>();
                for (int i = 0; i < pairs.size() - 1; i++) {
                    for (int j = i + 1; j < pairs.size(); j++) {
                        int[] pair1 = pairs.get(i);
                        int[] pair2 = pairs.get(j);
                        list.add(new int[]{input[pair1[0]], input[pair1[1]]});
                        list.add(new int[]{input[pair2[0]], input[pair2[1]]});
                    }
                }
                if (list.size() != 0) {
                    result.add(list);
                }
            }
        }
        return result;
    }
    class Indexs{
        public List<int[]> pairs = new ArrayList<>();
        public Set<Integer> index = new HashSet<>();
        public Indexs() {

        }
    }
}
