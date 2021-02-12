package view;

import javax.swing.*;
import defs.*;

public class Tile extends JButton{
	public int status; // 0 = Empty, 1~6 = tile01~06
	public int x;
	public int y;
	public  Map map;
	
	public Tile(int x, int y, Map map) {
		super();
		
		this.x = x;
		this.y = y;
		this.map = map;
	}
	
	public int geTileWithCoord(int y, int x) {     // 상태를 나타내주는것 추가
		return this.map.tiles[y][x].status;
	}
	
	public boolean tEnabled(int y, int x) {    // 타일이 있는지 없는지 추가
		if(this.map.tiles[y][x].status != 0)
			return false;
		else 
			return true;
	}
	
	
	public int getAdjTile(tDirection d) {
		switch(d) {
		case UP:
			if(y <= 0)	return -1;
			return this.map.tiles[y-1][x].status;
		case RIGHT:
			if(x >= map.width)	return -1;
			return this.map.tiles[y][x+1].status;
		case DOWN:
			if(y >= map.height)	return -1;
			return this.map.tiles[y+1][x].status;
		case LEFT:
			if(x <= 0)	return -1;
			return this.map.tiles[y][x-1].status;
		default:
			return -1;
		}
	}
	public boolean setStatus(int status) {
		if(status < 0 || status > 6)	return false;
		
		this.status = status;
		this.setIcon(new ImageIcon("./res/" + status +".png"));
		
		return true;
	}
}
