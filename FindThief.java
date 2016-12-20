/* 
61. 房间数为n，sequence长度为k (天数)
找小偷问题，有n个房间，其中一个房间有小偷。早上我们可以打开一个房间的门看小偷在不在里面，晚上小偷会向左边或者右边的房间走。现在给你一个开门的sequence，你输出这个sequence能不能保证找到小偷。 

比如：如果只有三个房间那么如果打开房间的sequence是{1，1}那么一定会找到小偷。因为如果小偷在中间那么第一天就会被找到, 如果小偷在两边那么第二天一定回来到中间也会被找到。.跟着我开始brute force假设小偷在某个房间然后dfs所有路径，大概是O（n*n^k）。 考官说好，如果考虑cut branch呢？跟着我就说可以
拿一个n*k的matrix跟着根据sequence来cut branch，reduce到O（n*n*k）。他说有没有可能同时从所有房间开始呢？我说可以跟着直接
在那个n*kmatrix上做一个类似dp的东西。跟着reduce 到 O（n*k）。他说有没有可能把space reduce呢？我说可以我只要O（n）的space
跟着他就让我再写一个叫nextRow的function来实现O（n）space。 我觉得这题我基本是答得非常漂亮的而且思路很清晰，考官也很开心。
*/

// http://www.mitbbs.com/article_t/JobHunting/32978937.html
// https://gist.github.com/gcrfelix/19e0dd64a7f8e9192158

import java.util.Arrays;

public class FindTheafInRooms {
	
	public boolean alibaba(int numCaves, int[] strategy) {
		int days = strategy.length;
		boolean[][] res = new boolean[numCaves][days];
		for (int i = 0; i < days; i++) {
			res[strategy[i]][i] = true;
		}
		for (int i = 0; i < numCaves; i++) {
			for (int j = 1; j < days; j++) {
				if (i == numCaves - 1) {
					res[i][j] = res[i][j] || res[i - 1][j - 1];
				}
				else if (i == 0) {
					res[i][j] = res[i][j] || res[i + 1][j - 1];
				}
				else {
					res[i][j] = res[i][j] || res[i - 1][j - 1] && res[i + 1][j - 1];
				}
			}
		}
		boolean result = true;
		for (int i = 0; i < numCaves; i++) {
			result = result && res[i][days - 1];
		}
		return result;
	}
	
	
/*===========================================================================================*/
	
	
	
	public static void main(String[] args) {
		int[] strategy = {2,2};
		if (new FindTheafInRooms().findTheaf(4, strategy))
			System.out.println("true");
		else
			System.out.println("false");
	}
	public boolean findTheaf(int n, int[] strategy) {
		
		// 加两个元素。因为小偷不可能在最前面最后面两个房间。
		boolean survival[] = new boolean[n + 2];
		Arrays.fill(survival, true);
		survival[0] = false;
		survival[n + 1] = false;
		
		// 小偷不能在strategy[0]这个房间内.如果在了就false
		survival[strategy[0]] = false;
		
		// i为天数，j为房间数。strategy[i]为开启的房间
		for (int i = 1; i < strategy.length; i++) {
			for (int j = 1; j < n + 1; j++) {
				// 最后一个是小偷不能再j这个房间内
				survival[j] = (survival[j - 1] || survival[j + 1]) && strategy[i] != j;	
			}
		}
		for (int i = 1; i < n + 1; i++) {
			if (survival[i])
				return false;
		}
		return true;
	}
}
