package action;

import javax.swing.ImageIcon;

import defs.*;
import view.*;

public class TileJudge {
	private static final tColor[][] colors = new tColor[][]{
		{tColor.NONE, tColor.NONE, tColor.NONE, tColor.NONE},
		{tColor.BLACK, tColor.WHITE, tColor.WHITE, tColor.BLACK},
		{tColor.BLACK, tColor.BLACK, tColor.WHITE, tColor.WHITE},
		{tColor.WHITE, tColor.BLACK, tColor.BLACK, tColor.WHITE},
		{tColor.WHITE, tColor.WHITE, tColor.BLACK, tColor.BLACK},
		{tColor.WHITE, tColor.BLACK, tColor.WHITE, tColor.BLACK},
		{tColor.BLACK, tColor.WHITE, tColor.BLACK, tColor.WHITE}
	};	
	
	public static boolean isMatch(int self, int up, int down, int left, int right) {
		boolean x = true;
		
		x = x && (up == -1 || up == 0 || colors[self][0] == colors[up][2]);
		x = x && (down == -1 || down == 0 || colors[self][2] == colors[down][0]);
		x = x && (left == -1 || left == 0 || colors[self][3] == colors[left][1]);
		x = x && (right == -1 || right == 0 ||  colors[self][1] == colors[right][3]);
		
		return x;
	}
	public static int nextTile(int self, int up, int down, int left, int right) {
		int i, t;
		
		for(i=1; i<=6; i++) {
			t = (self + i) % 7;
			if(t != 0 && isMatch(t, up, down, left, right))	return t;
		}
		
		return -1;
	}
	/*public static int checkStatus(int up,int down,int left,int right) {
		//위쪽의 status가 0이 아니면 return 0을 해주고 0이면 return 1을 해준다
		return -1;
	}*/
	
	public static int cntNext(Tile t) {
		int self = t.status;
		int up = t.getAdjTile(tDirection.UP);
		int down = t.getAdjTile(tDirection.DOWN);
		int left = t.getAdjTile(tDirection.LEFT);
		int right = t.getAdjTile(tDirection.RIGHT);
		int i, j, cnt = 0;
		
		for(i=1; i<=6; i++) {
			j = (self + i) % 7;
			if(j != 0 && isMatch(j, up, down, left, right)) cnt++;	
		}
		
		return cnt;
	}
	//자기 타일내에서 같은 색깔부분방향 찾기
	public static int sameColor(Tile t,int nowdire) {
		int i;
		int self=t.status;
		
		
		for(i=0;i<4;i++) {	//자기 타일내에서 같은 색깔부분방향 찾기
			if(nowdire==i) {		//자기랑 같은 방향은 넘기고
				i++;				
			}
			if(colors[self][nowdire]==colors[self][i]) {
					break;
			}
		}
		return i;
		
	}
	public static int whatColor(Tile t,int dire) {
		if(colors[t.status][dire]==tColor.WHITE) {
			
			return 0;		//white 는 0으로 리턴
		}
		else {
			return 1;		//black 는 1으로 리턴
			
		}
		
	}
	
	public static int lineJudge(Tile t,int predire,int firstdire,int checkdire,int index,int indexX,int indexY){
		int i,j;		
		int self=t.status;
		int fina;
		int nowdire=0,finaldire=0;
		Tile u;
		//checkdire은 x좌표를 확인하면 되는 혹은 y좌표를 확인하면 되는지
		//t는 현재타일을 의미함
		//firstdire은 함수들어가지전에 반대방향을 미리 주어줘야함
		
		u = t.map.tiles[t.y-1][t.x];		//초기값 설정
		
		
		if(predire==0) {	//전타일에 들어오는 방향의 반대방향을 현재타일의 방향으로 설정
			nowdire=2;
		}
		else if(predire==1) {
			nowdire=3;			
		}
		else if(predire==2) {
			nowdire=0;			
		}
		else if(predire==3) {
			nowdire=1;			
		}
		
		for(i=0;i<4;i++) {	//자기 타일내에서 같은 색깔부분방향 찾기
			if(nowdire==i) {
				i++;				
			}
			if(colors[self][nowdire]==colors[self][i]) {
					break;
			}
		}
		
		//현재 i 값은 같은 색깔 방향이 어딘지 저장되어 있음
		
		

		if(i==0) {	//up		//색깔과 매칭되는 가는 방향의 타일 설정
			u = t.map.tiles[t.y-1][t.x];		
		}
		else if(i==1) {	//right
			u = t.map.tiles[t.y][t.x+1];
		}
		else if(i==2) {//down
			
			u = t.map.tiles[t.y+1][t.x];
		}
		else if(i==3) {	//left
			u = t.map.tiles[t.y][t.x-1];
		}
		//u는 다음 들어갈 타일설정
		
		
		
		
		if(checkdire==0) {	//x좌표를 확인해주는경우 
			if(((t.x)-index==7)||((t.x)-index==-7)) {	//초기 인텍스와 현재인덱스 차이가 7이면
				if(i==firstdire) {		//그리고 방향 마저 같다면
					return 1;
				}
			}
		}
		else if(checkdire==1) {
			if(((t.y)-index==7)||((t.y)-index==-7)) {
				if(i==firstdire) {
					return 1;
				}
			}
		}
		if(u.status==0) {	//다음들어가 타일의 상태강 0인 경우 ,즉 타일이 없는경우 0을 반환하기
			return 0;
			
		}
		if(u.x==indexX&&u.y==indexY) {
			return 0;
			
		}
		
		fina=lineJudge(u,i,firstdire,checkdire,index,indexX,indexY);
		return fina;
		
		
		
		
	}
	public static void autoJudge(Tile t) throws InterruptedException{
		int up = t.getAdjTile(tDirection.UP);
		int down = t.getAdjTile(tDirection.DOWN);
		int left = t.getAdjTile(tDirection.LEFT);
		int right = t.getAdjTile(tDirection.RIGHT);
		Tile u;
		int next;
		
		if(up == 0) {//아무것도 업는경우
			u = t.map.tiles[t.y-1][t.x];
			if(cntNext(u)==1) {//다음에 가능한 타일 개수
				next = TileJudge.nextTile(0, 
						u.getAdjTile(tDirection.UP), 
						u.getAdjTile(tDirection.DOWN), 
						u.getAdjTile(tDirection.LEFT), 
						u.getAdjTile(tDirection.RIGHT));
				u.setStatus(next);
				autoJudge(u);
			}
		}
		
		if(down == 0) {
			u = t.map.tiles[t.y+1][t.x];
			if(cntNext(u)==1) {
				next = TileJudge.nextTile(0, 
						u.getAdjTile(tDirection.UP), 
						u.getAdjTile(tDirection.DOWN), 
						u.getAdjTile(tDirection.LEFT), 
						u.getAdjTile(tDirection.RIGHT));
				u.setStatus(next);
				autoJudge(u);
			}
		}
		
		if(left == 0) {
			u = t.map.tiles[t.y][t.x-1];
			if(cntNext(u)==1) {
				next = TileJudge.nextTile(0, 
						u.getAdjTile(tDirection.UP), 
						u.getAdjTile(tDirection.DOWN), 
						u.getAdjTile(tDirection.LEFT), 
						u.getAdjTile(tDirection.RIGHT));
				u.setStatus(next);
				autoJudge(u);
			}
		}
		
		if(right == 0) {
			u = t.map.tiles[t.y][t.x+1];
			if(cntNext(u)==1) {
				next = TileJudge.nextTile(0, 
						u.getAdjTile(tDirection.UP), 
						u.getAdjTile(tDirection.DOWN), 
						u.getAdjTile(tDirection.LEFT), 
						u.getAdjTile(tDirection.RIGHT));
				u.setStatus(next);
				autoJudge(u);
			}
		}
	}
}


