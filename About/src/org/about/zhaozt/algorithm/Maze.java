package org.about.zhaozt.algorithm;
/**
 * @author zhaozt
 * @date 2019年11月16日 下午1:44:04
 * describe : 迷宫递归回溯
 */
public class Maze {

	public static void main(String[] args) {
		//初始化迷宫
		int[][] maze = new int[8][8];
		
		for (int i = 0; i < maze[0].length; i++) {
			maze[0][i] = 1;
			maze[maze.length-1][i] = 1;
		}
		for (int i = 0; i < maze.length; i++) {
			maze[i][0] = 1;
			maze[i][maze[0].length-1] = 1;
		}
		maze[3][1] = 1;
		maze[3][2] = 1;
		maze[5][3] = 1;
		maze[5][4] = 1;
		maze[5][5] = 1;
		maze[2][3] = 1;
		maze[2][5] = 1;
		maze[3][5] = 1;
		maze[4][5] = 1;
		//显示初始化的迷宫
		for (int i = 0; i < maze.length; i++) {
			for (int j = 0; j < maze[0].length; j++) {
				System.out.printf(maze[i][j] + "\t");
			}
			System.out.println();
		}
		//走迷宫
		outMaze(maze, 1, 1);
		System.out.println("-----------------------------------");
		//show maze
		for (int i = 0; i < maze.length; i++) {
			for (int j = 0; j < maze[0].length; j++) {
				System.out.printf(maze[i][j] + "\t");
			}
			System.out.println();
		}
	}
	
	//走迷宫 先向上->向下->向左->向右 i,j为当前位置
	//maze[2][2]为入口，maze[7][7]为出口，元素为1时为墙 为2表示可走 3表示走不通
	public static boolean outMaze(int[][] maze, int i, int j) {
		if(maze[6][6] == 2) {
			return true;
		}else {
			if(maze[i][j] == 0) {
				maze[i][j] = 2;
				if(outMaze(maze, i+1, j)) {
					return true;
				}else if(outMaze(maze, i-1, j)) {
					return true;
				}else if(outMaze(maze, i, j-1)) {
					return true;
				}else if(outMaze(maze, i, j+1)) {
					return true;
				}else {
					maze[i][j] = 3;
					return false;
				}
			}else{
				return false;
			}
		}
	}	

}

