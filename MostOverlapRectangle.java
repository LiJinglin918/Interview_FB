// 88. MostOverlapRectangle
/*
几何算法问题。如果给你一堆的矩形， 求重合矩形重合最多的坐标位置。我上过一个算法课，大概思路就是做一个二维的meeting room II
*/
class Overlap {
    public Square findMostOverlap (Square[] squares) {
        HashMap<Integer, List<Pair>> indexToLine = new HashMap<>();
        for (Square square : squares) {
            if (!indexToLine.containsKey(square.x0)) {
                indexToLine.put(square.x0, new ArrayList<Pair>());
            }
            indexToLine.get(square.x0).add(new Pair(square.y0, true, true));
            indexToLine.get(square.x0).add(new Pair(square.y1, false, true));
            if (!indexToLine.containsKey(square.x1)) {
                indexToLine.put(square.x1, new ArrayList<Pair>());
            }
            indexToLine.get(square.x1).add(new Pair(square.y0, true, false));
            indexToLine.get(square.x1).add(new Pair(square.y1, false, false));
        }
        for (Square square : squares) {
            for (int index  = square.x0 + 1; index < square.x1; index++) {
                if (indexToLine.containsKey(index)) {
                    indexToLine.get(index).add(new Pair(square.y0, true, null));
                    indexToLine.get(index).add(new Pair(square.y1, false, null));
                }
            }
        }
        for (int index : indexToLine.keySet()) {
            Collections.sort(indexToLine.get(index), new Comparator<Pair>() {
                @Override
                public int compare(Pair p1, Pair p2) {
                    if (p1.index == p2.index) {
                        if (p2.isUp) {
                            return -1;
                        }
                        else if (p1.isUp) {
                            return 1;
                        }
                        return 0;
                    }
                    return p2.index - p1.index;
                }
            });
        }

        Square overlap = new Square(0, 0, 0, 0);
        int maxOverlap = 0;
        for (int index : indexToLine.keySet()) {
            List<Pair> line = indexToLine.get(index);
            int right = 0;
            int left = 0;
            for (Pair pair : line) {
                if (pair.isUp) {
                    if (pair.isLeft == null || (pair.isLeft != null && pair.isLeft)) {
                        right++;
                        if (right > maxOverlap) {
                            overlap.x0 = index;
                            overlap.y0 = pair.index;
                            maxOverlap = right;
                        }
                    }
                    if (pair.isLeft == null || (pair.isLeft != null && !pair.isLeft)) {
                        left++;
                    }
                }
                else {
                    if (pair.isLeft == null || (pair.isLeft != null && !pair.isLeft)) {
                        if (left == maxOverlap) {
                            overlap.x1 = index;
                            overlap.y1 = pair.index;
                        }
                        left--;
                    }
                    if (pair.isLeft == null || (pair.isLeft != null && pair.isLeft)) {
                        right--;
                    }
                }
            }
        }
        return overlap;
    }
    class Pair {
        public Boolean isUp;
        public Boolean isLeft;
        public int index;
        public Pair(int index, Boolean isUp, Boolean isLeft) {
            this.index = index;
            this.isUp= isUp;
            this.isLeft = isLeft;
        }

        @Override
        public String toString() {
            return "index: " + Integer.toString(index) + ", isUp: " + isUp + ", isLeft: " + isLeft + "\n";
        }
    }
}
class Square {
    int x0;
    int x1;
    int y0;
    int y1;
    public Square (int x0, int x1, int y0, int y1) {
        this.x0 = x0;
        this.x1 = x1;
        this.y0 = y0;
        this.y1 = y1;
    }
    @Override
    public String toString() {
        return "x0: " + Integer.toString(x0) + "\n" +
                "y0: " + Integer.toString(y0) + "\n" +
                "x1: " + Integer.toString(x1) + "\n" +
                "y1: " + Integer.toString(y1) + "\n";
    }
}
