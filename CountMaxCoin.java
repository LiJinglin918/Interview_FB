/*
 * N coins in the coordinates(x, y)
 * start at (0,0) and only do steps to form (dx, dy) (往右上角走)。 返回能到达的最多的coin数。
 * https://www.careercup.com/question?id=5722807649435648
 */

// DP method
import java.util.*;
public class CountMaxCoin {
	public int maxCoins(boolean[][] board) {
		return helper(board, 0, 0, new HashMap<>());
	}
	public int helper(boolean[][] board, int i, int j, HashMap<Point, Integer> cache) {
		if (i == board.length || j == board[0].length)
			return 0;
		Point p = new Point(i, j);
		if (cache.containsKey(p))
			return cache.get(p);
		int coinsCount = 0;
		for (int dx = 1; dx + i < board.length; dx++) {
			for (int dy = 1; dy + j < board[0].length; dy++) {			
				// DP
				coinsCount = Math.max(coinsCount, helper(board, i + dx, j +  dy, cache));
			}
		}
		coinsCount += board[i][j]? 1 : 0;
		cache.put(p, coinsCount);
		return coinsCount;
	}
}
