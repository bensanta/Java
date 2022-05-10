package brickBreaker;

import java.awt.Color;
import java.awt.Graphics2D;

public class MapGenerator {
	public int map[][];//declare 2d array
	public int brickWidth;
	public int brickHeight;
	public MapGenerator(int row, int col) {
		map = new int[row][col]; //create 2d array called "map"
		for(int i = 0; i < map.length; i++) { //rpw
			for(int j = 0; j < map[0].length; j++) { //col
				map[i][j] = 1; 	//Adding one logic - one will detect if the brick
								//has been intersected with the ball
			}
		}
		brickWidth = 540/col;
		brickHeight = 150/row;
	}
	public void draw(Graphics2D g) {
		for(int i = 0; i < map.length; i++) { //rpw
			for(int j = 0; j < map[0].length; j++) { //col
				if(map[i][j] > 0) { 	//create that particular brick,
										//in that particular position
					g.setColor(Color.WHITE);
					g.fillRect(j * brickWidth + 80,  i * brickHeight + 50,  brickWidth,  brickHeight);
				} 
			}
		}
		
	}
}
