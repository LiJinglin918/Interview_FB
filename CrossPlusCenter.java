import java.util.*;

public class CroseMaxLen {
	
	HashMap<String, Integer> hm;
	int hitCount;
	Position maxPosition;
	
	public class Position {
		int i;
		int j;
		Position(int i, int j) {
			this.i = i;
			this.j = j;
		}
	}
	
	// 主要的函数，打印出最大尺寸的十字的中间位置。
	public void getMaxPlus(int[][] board) {
		List<Position> candidates = getCandidates(board);
		hm = new HashMap<String, Integer>();
		hitCount = 0;
		int maxSize = Integer.MIN_VALUE;
		for (Position p : candidates) {
			int size = getMaxSize(p, board);
			if (size > maxSize) {
				maxSize = size;
				maxPosition = p;
			}
		}
		System.out.println("hitCount: " + hitCount);
		if (maxPosition != null)
			System.out.println("The max plus is rooted at: ("+ maxPosition.i+", "+maxPosition.j+")");
		else
			System.out.println("No plus found");
	}
	
	// 知道matrix里面的可能的十字的中间的十字 
	public List<Position> getCandidates(int[][] board) {
		List<Position> candidates = new ArrayList<>();
		for (int i = 1; i < board.length - 1; i++) {
			for (int j = 1; j < board[0].length - 1; i++) {
				if (board[i][j] == 1 && board[i - 1][j] == 1 
						&& board[i + 1][j] == 1 && board[i][j - 1] == 1 
						&& board[i][j + 1] == 1)
					candidates.add(new Position(i, j));
			}
		}
		return candidates;
	}
	
	// 根据四个方向，找四个方向最短的那个延伸
	public int getMaxSize(Position p, int[][] board) {
		int left = getMaxSize(p.i, p.j - 1, "left", board);
		int right = getMaxSize(p.i, p.j - 1, "right", board);
		int up = getMaxSize(p.i - 1, p.j, "up", board);
		int down = getMaxSize(p.i + 1, p.j, "down", board);
		return Math.min(left,  Math.min(right,  Math.min(up, down)));
	}
	public int getMaxSize(int i, int j, String direction, int[][] board) {
		if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || board[i][j] == 0)
			return 0;
		String key = i + "" + j + "" + direction;
		hitCount++;
		if (hm.containsKey(key))
			return hm.get(key);
		int size = 1;
		switch(direction) {
			case "left":
				size += getMaxSize(i, j - 1, direction, board);
				break;
			case "right":
				size += getMaxSize(i, j + 1, direction, board);
				break;
			case "up":
				size += getMaxSize(i - 1, j, direction, board);
				break;
			case "down":
				size += getMaxSize(i + 1, j, direction, board);
				break;
		}
		hm.put(key, size);
		return size;
	}
}
