package com.rainz;

public class LongestIncreasingPathInAMatrix {
    public static void test(String args[]) {
//    	int [][]nums = {
//    	        {9,9,4},
//    	        {6,6,8},
//    	        {2,1,1}
//    	        };
    	int[][] nums = {
    			{1}
    	};
    	System.out.println(longestIncreasingPath(nums));
    }

	private static int[][] OFFSETS = {
		{1, 0}, //up
		{0, 1}, //right
		{-1,0}, //down
		{0,-1}, //left
	};
	
    private static class Record {
    	public int length;
    	public int next; // 0=up, 1=right, 2=down, 3=left, -1=none
    	
    	public Record(int l, int n) {
    		length = l;
    		next = n;
    	}
    }
    
    private static Record[][] records;
    private static int _longest = 0;
    private static int[][] _matrix;
    private static int _numRows;
    private static int _numCols;
    
    public static int longestIncreasingPath(int[][] matrix) {
    	_matrix = matrix;
        _numRows = matrix.length;
        if (_numRows == 0) {
        	return 0;
        }
        _numCols = matrix[0].length;
        records = new Record[_numRows][_numCols];
        for (int row = 0; row < _numRows; ++row) {
        	for (int col = 0; col < _numCols; ++col) {
        		helper(row, col);
        	}
        }
        return _longest;
    }
    
    private static int helper(int row, int col) {
    	Record rec = records[row][col];
    	if (rec != null) {
    		return rec.length;
    	}
    	int maxLen = 0;
    	int maxDir = -1;
    	for (int dir = 0; dir < 4; ++dir) {
    		int newRow = OFFSETS[dir][0] + row;
    		int newCol = OFFSETS[dir][1] + col;
    		if (newRow >= 0 && newRow < _numRows &&
    			newCol >= 0 && newCol < _numCols)
    		{
    			if (_matrix[row][col] >= _matrix[newRow][newCol]) {
    				continue;
    			}
    			int len = helper(newRow, newCol);
    			if (len > maxLen) {
    				maxLen = len;
    				maxDir = dir;
    			}
    		}
    	}
    	int result = maxLen + 1;
    	records[row][col] = new Record(result, maxDir);
    	if (result > _longest) {
    		_longest = result;
    	}
    	return result;
    }
}
