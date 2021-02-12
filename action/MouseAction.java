package action;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;
import defs.*;
import view.*;

public class MouseAction implements MouseListener {
	static int tmp_status = 0;//배경타일
	static int tmp_x = -1, tmp_y = -1;//정적이니깐 함수가 유지 되는거고		//함수 끝날때 도 설정해줌
	//Count count= new Count();
	public void mouseClicked(MouseEvent e) {
		Tile t = (Tile) e.getSource();
		int next;
		
		Tile u;
		int predire,firstdire,checkdire,index;
				//t는 클릭한 현재 위치
		int k,whatcolor;
		boolean blackwin=false;
		boolean whitewin=false;
		
		switch(e.getButton()) {		//어떤 버튼인지 받아주는 것
		case MouseEvent.BUTTON3: // Right Click		//확정 타일
			if(tmp_x != t.x || tmp_y != t.y)	break;//이전위치가 같않을때는 그냥 나가야
			//t.x, t.y 는 클릭한 현재 위치
			t.setStatus(tmp_status);//이때 확적			//이전 위치랑 같으면 확정해줌
			try {
				TileJudge.autoJudge(t);		//주변 자동으로 확정해주는 함수 사용하
				CheckWinTile ckWinTile = new CheckWinTile();
				if(ckWinTile.checkWinWhite_circle(t.y, t.x, t))  //하얀색 서클 완성
					whitewin=true;
				if(ckWinTile.checkWinBlack_circle(t.y, t.x, t))  //검정색 서클 완성
					blackwin=true;
			}catch(Exception ex) {
				System.out.println("<!> AutoJudge Error...");
				return;
			}
			
			tmp_x = -1;
			tmp_y = -1;//이전에 눌린 버튼의 위치
			//확정 될때만 -1로 해주는것임
			(Count.c)++;	//확정하는 순간 count를 1을 올려준다
			if(t.map.tiles[t.y-1][t.x].status==0) {//up 위에가 빈타일이면
				
				predire=TileJudge.sameColor(t, 0);
				
				//0에서 시작하니깐 반방향은 2
				k=TileJudge.lineJudge(t, predire, 2, 1,t.y,t.x,t.y);
				//t는 검사할 타일, predire은 이전에 오던 방향,firstdire은 라인이 시작한곳의 반대방향
				//checkdire은 x좌표를 확인해야 하는지 y좌포를 확인해야 하는지	//y좌표는 1,x좌표는 0
				//index은 클릭한 곳의 x 혹은 y좌표
				if(k==1) {
					whatcolor=TileJudge.whatColor(t,0);
					if(whatcolor==0) {	//white
						whitewin=true;
					}
					else {
						blackwin=true;
					}
				}
			}
			
			if(t.map.tiles[t.y][t.x+1].status==0) {//right			
				predire=TileJudge.sameColor(t, 1);
				//0에서 시작하니깐 반방향은 2
				k=TileJudge.lineJudge(t, predire, 3, 0,t.x,t.x,t.y);
				if(k==1) {
					whatcolor=TileJudge.whatColor(t,1);
					if(whatcolor==0) {	//
						whitewin=true;
					}
					else {
						blackwin=true;
					}
				}
			}
			
			if(t.map.tiles[t.y][t.x-1].status==0) {//down
				
				predire=TileJudge.sameColor(t, 2);
				
				//0에서 시작하니깐 반방향은 2
				k=TileJudge.lineJudge(t, predire, 0, 1,t.y,t.x,t.y);
				
				if(k==1) {
					whatcolor=TileJudge.whatColor(t,2);
					if(whatcolor==0) {	//
						whitewin=true;
					}
					else {
						blackwin=true;
					}
				}
				
			}
			
			if(t.map.tiles[t.y-1][t.x].status==0) {//left
				
				predire=TileJudge.sameColor(t, 3);
				
				//0에서 시작하니깐 반방향은 2
				k=TileJudge.lineJudge(t, predire, 1, 0,t.x,t.x,t.y);
				
				if(k==1) {
					whatcolor=TileJudge.whatColor(t,3);
					if(whatcolor==0) {	//
						whitewin=true;
					}
					else {
						blackwin=true;
					}
				}
			}
			if(blackwin==true)
			{
				JOptionPane.showMessageDialog(null, "Black Win", "  Game Over", JOptionPane.INFORMATION_MESSAGE);
				System.out.println("Black win!!");
			}
			if(whitewin==true)
			{
				JOptionPane.showMessageDialog(null, "White Win", "  Game Over", JOptionPane.INFORMATION_MESSAGE);
				System.out.println("White win!!");
			}
			
			break;
		case MouseEvent.BUTTON1: // Left Click
			if(t.status != 0)	break;//현재 클릭한 타타일이 있는것 그러면 무시
					//t.x현재위치
			//System.out.printf("c==%d-\n",Count.c);
			if((Count.c!=0)&&(t.getAdjTile(tDirection.UP)==0&&t.getAdjTile(tDirection.DOWN)==0&&t.getAdjTile(tDirection.LEFT)==0&&t.getAdjTile(tDirection.RIGHT)==0)){
				break;
				
			}		//첫번째 클릭이 아닐때  동서남북에 아무것도 없으면 임시타일도 오지않게 나간다
			
			
			//System.out.printf("%d--\n",Count.c);
			if(t.x == tmp_x && t.y == tmp_y) {		//어디를 눌렀을때  다음꺼를 보고 싶은거면 이전꺼눌리면 다음것을 찾는다
				next = TileJudge.nextTile(tmp_status, //이미 한번 했으면 임시값을 넣어준다
						t.getAdjTile(tDirection.UP), 		//주변타일에 영향을 받아 
						t.getAdjTile(tDirection.DOWN), //올수있는 타일을 받아놈
						t.getAdjTile(tDirection.LEFT), 
						t.getAdjTile(tDirection.RIGHT));
				if(Count.c==0&&Count.firsttile==5) {
					next=3;
					Count.firsttile=3;
				}
				else if(Count.c==0&&Count.firsttile==3){
					next=5;
					Count.firsttile=5;
				}
			}else{
				if(tmp_x != -1 && tmp_y != -1) {//맨처음이 아닌경우 완전 눌린경우아닌경우 0셍//그전이 확정이 아닌상태
					t.map.tiles[tmp_y][tmp_x].setIcon(new ImageIcon("./res/0.png"));
				}
				next = TileJudge.nextTile(0,			//시작값이 0이니깐 0으로 설정 
						t.getAdjTile(tDirection.UP), 
						t.getAdjTile(tDirection.DOWN), 
						t.getAdjTile(tDirection.LEFT), 
						t.getAdjTile(tDirection.RIGHT));
				if(Count.c==0&&Count.firsttile==5) {
					next=3;
					Count.firsttile=3;
				}
				else if(Count.c==0&&Count.firsttile==6){
					next=5;
					Count.firsttile=5;
				}
			}
			//System.out.printf("%d---\n",Count.c);
			if(next == -1)	return;
			
			tmp_status = next;
			tmp_x = t.x;//임시 타일일 경우에는 tmp_x에 다음위치와 비교를 위해 현재 위치를 저장해준다
			tmp_y = t.y;
			t.setIcon(new ImageIcon("./res/" + tmp_status + "_tmp.png"));
			
			break;
		default:
			break;
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		Tile t = (Tile) e.getSource();
		
		//System.out.println("Mouse Pos : ("+t.x+","+t.y+")");
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}

