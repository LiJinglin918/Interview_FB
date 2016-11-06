/* 
61. 找小偷问题，有n个房间，其中一个房间有小偷。早上我们可以打开一个房间的门看小偷在不在里面，晚上小偷会向左边或者右边的房间走。现在给你一个开门的sequence，你输出这个sequence能不能保证找到小偷。 

比如：如果只有三个房间那么如果打开房间的sequence是{1，1}那么一定会找到小偷。因为如果小偷在中间那么第一天就会被找到, 如果小偷在两边那么第二天一定回来到中间也会被找到。房间数为n，sequence长度为k.跟着我开始brute force假设小偷在某个房间然后dfs所有路径，大概是O（n*n^k）。 考官说好，如果考虑cut branch呢？跟着我就说可以
拿一个n*k的matrix跟着根据sequence来cut branch，reduce到O（n*n*k）。他说有没有可能同时从所有房间开始呢？我说可以跟着直接
在那个n*kmatrix上做一个类似dp的东西。跟着reduce 到 O（n*k）。他说有没有可能把space reduce呢？我说可以我只要O（n）的space
跟着他就让我再写一个叫nextRow的function来实现O（n）space。 我觉得这题我基本是答得非常漂亮的而且思路很清晰，考官也很开心。
*/
class FindTheaf {
    public boolean findTheaf(int rooms, int[] strategy) {
        boolean[] found = new boolean[rooms];
        found[strategy[0]] = true;
        for (int day = 1; day < strategy.length; day++) {
            boolean[] lastDay = Arrays.copyOf(found, rooms);
            for (int room = 0; room < rooms; room++) {
                if (room == 0) {
                    found[room] = lastDay[room + 1];
                }
                else if (room == rooms - 1) {
                    found[room] = lastDay[room - 1];
                }
                else {
                    found[room] = lastDay[room - 1] || lastDay[room + 1];
                }
            }
            found[strategy[day]] = true;
        }
        boolean result = true;
        for (int i = 0; i < rooms; i++) {
            result = result && found[i];
        }
        return result;
    }
}
