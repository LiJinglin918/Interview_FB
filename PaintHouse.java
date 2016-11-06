// 76. Paint House
/*
paint house大变种. n houses, k colors. neighboring houses cannot be painted with the same color.
NOTICE: neighboring relationship is given by adjacent list which means a house may have multiple neighbors.
*/
class PaintHouseGraph {
    public int paint(int[] cost, HashMap<Integer, List<Integer>> houses) {
        List<Set<Integer>> groups = new ArrayList<>();
        Set<Integer> group = new HashSet<>();
        for (int house : houses.keySet()) {
            group.add(house);
        }
        while (group.size() != 0) {
            Iterator<Integer> iter = group.iterator();
            Set<Integer> next = new HashSet<>();
            while (iter.hasNext()) {
                int house = iter.next();
                if (next.contains(house)) {
                    iter.remove();
                    continue;
                }
                for (int neighbor : houses.get(house)) {
                    if (!group.contains(neighbor)) {
                        continue;
                    }
                    next.add(neighbor);
                }
            }
            groups.add(group);
            group = next;
        }
        Collections.sort(groups, new Comparator<Set<Integer>>() {
            @Override
            public int compare(Set<Integer> set1, Set<Integer> set2) {
                return set2.size() - set1.size();
            }
        });
        int totalCost = 0;
        int index = 0;
        for (Set<Integer> set : groups) {
            totalCost += set.size() * cost[index++];
        }
        return totalCost;
    }
}
