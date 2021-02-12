package action;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;
import defs.*;
import view.*;

public class MouseAction implements MouseListener {
	static int tmp_status = 0;//���Ÿ��
	static int tmp_x = -1, tmp_y = -1;//�����̴ϱ� �Լ��� ���� �Ǵ°Ű�		//�Լ� ������ �� ��������
	//Count count= new Count();
	public void mouseClicked(MouseEvent e) {
		Tile t = (Tile) e.getSource();
		int next;
		
		Tile u;
		int predire,firstdire,checkdire,index;
				//t�� Ŭ���� ���� ��ġ
		int k,whatcolor;
		boolean blackwin=false;
		boolean whitewin=false;
		
		switch(e.getButton()) {		//� ��ư���� �޾��ִ� ��
		case MouseEvent.BUTTON3: // Right Click		//Ȯ�� Ÿ��
			if(tmp_x != t.x || tmp_y != t.y)	break;//������ġ�� ���������� �׳� ������
			//t.x, t.y �� Ŭ���� ���� ��ġ
			t.setStatus(tmp_status);//�̶� Ȯ��			//���� ��ġ�� ������ Ȯ������
			try {
				TileJudge.autoJudge(t);		//�ֺ� �ڵ����� Ȯ�����ִ� �Լ� �����
				CheckWinTile ckWinTile = new CheckWinTile();
				if(ckWinTile.checkWinWhite_circle(t.y, t.x, t))  //�Ͼ�� ��Ŭ �ϼ�
					whitewin=true;
				if(ckWinTile.checkWinBlack_circle(t.y, t.x, t))  //������ ��Ŭ �ϼ�
					blackwin=true;
			}catch(Exception ex) {
				System.out.println("<!> AutoJudge Error...");
				return;
			}
			
			tmp_x = -1;
			tmp_y = -1;//������ ���� ��ư�� ��ġ
			//Ȯ�� �ɶ��� -1�� ���ִ°���
			(Count.c)++;	//Ȯ���ϴ� ���� count�� 1�� �÷��ش�
			if(t.map.tiles[t.y-1][t.x].status==0) {//up ������ ��Ÿ���̸�
				
				predire=TileJudge.sameColor(t, 0);
				
				//0���� �����ϴϱ� �ݹ����� 2
				k=TileJudge.lineJudge(t, predire, 2, 1,t.y,t.x,t.y);
				//t�� �˻��� Ÿ��, predire�� ������ ���� ����,firstdire�� ������ �����Ѱ��� �ݴ����
				//checkdire�� x��ǥ�� Ȯ���ؾ� �ϴ��� y������ Ȯ���ؾ� �ϴ���	//y��ǥ�� 1,x��ǥ�� 0
				//index�� Ŭ���� ���� x Ȥ�� y��ǥ
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
				//0���� �����ϴϱ� �ݹ����� 2
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
				
				//0���� �����ϴϱ� �ݹ����� 2
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
				
				//0���� �����ϴϱ� �ݹ����� 2
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
			if(t.status != 0)	break;//���� Ŭ���� ŸŸ���� �ִ°� �׷��� ����
					//t.x������ġ
			//System.out.printf("c==%d-\n",Count.c);
			if((Count.c!=0)&&(t.getAdjTile(tDirection.UP)==0&&t.getAdjTile(tDirection.DOWN)==0&&t.getAdjTile(tDirection.LEFT)==0&&t.getAdjTile(tDirection.RIGHT)==0)){
				break;
				
			}		//ù��° Ŭ���� �ƴҶ�  �������Ͽ� �ƹ��͵� ������ �ӽ�Ÿ�ϵ� �����ʰ� ������
			
			
			//System.out.printf("%d--\n",Count.c);
			if(t.x == tmp_x && t.y == tmp_y) {		//��� ��������  �������� ���� �����Ÿ� ������������ �������� ã�´�
				next = TileJudge.nextTile(tmp_status, //�̹� �ѹ� ������ �ӽð��� �־��ش�
						t.getAdjTile(tDirection.UP), 		//�ֺ�Ÿ�Ͽ� ������ �޾� 
						t.getAdjTile(tDirection.DOWN), //�ü��ִ� Ÿ���� �޾Ƴ�
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
				if(tmp_x != -1 && tmp_y != -1) {//��ó���� �ƴѰ�� ���� �������ƴѰ�� 0��//������ Ȯ���� �ƴѻ���
					t.map.tiles[tmp_y][tmp_x].setIcon(new ImageIcon("./res/0.png"));
				}
				next = TileJudge.nextTile(0,			//���۰��� 0�̴ϱ� 0���� ���� 
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
			tmp_x = t.x;//�ӽ� Ÿ���� ��쿡�� tmp_x�� ������ġ�� �񱳸� ���� ���� ��ġ�� �������ش�
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

