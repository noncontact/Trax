package action;


import view.Tile;

public class CheckWinTile {
	public boolean checkWinWhite_circle(int i_idx, int j_idx, Tile t) {

		int i = 0, prev_i = 0;
		int j = 0, prev_j = 0;	
		// ���� x,y   
		// ���ΰ��� i, ���ΰ��� j ���� x,y ��Ģ
		//tEnabled��  map.tiles[y][x].status��  0�� �ƴϸ� false ���
		// NW: 1
		// NE: 2
		// ES: 3
		// SW: 4
		// EW: 5
		// NS: 6
	
		if (t.geTileWithCoord(i_idx, j_idx) == 1) {    //geTileWithCoord��   map.tiles[y][x].status��
			if (!t.tEnabled(i_idx + 1, j_idx)) {       //(������)  map.tiles[y][x].status != 0  
				if (i_idx + 1 > 64) { 					 // 0�̸� �˻��� �ʿ䰡 ���� �װ� �ٸ� ���Ϸ� ��������
					return false;                        //�������� ��� �Ұ��� �ϸ� ����. �׿������� ���̸� false �� 
				}
				i = i_idx + 1;   //�˻��ϴ� Ÿ���� ���������� �Ѿ��.
				j = j_idx;	     //
			} else if (!t.tEnabled(i_idx, j_idx + 1)) {  //�����ʿ� Ÿ���� �ܵ�ϱ� �Ϸ� �´�  
				if (j_idx + 1 > 64) {    //�Ʒ����� ���� �Ʒ����� Ÿ���� �Ѿ�� false
					return false;
				}
				i = i_idx;             //�ȳѾ�� �� �Ʒ� �˻�
				j = j_idx + 1;         //�׾Ʒ��˻�
			} else {
				return false;         // �������̶� �Ʒ��� �� �ȳѾ�� �Ѿ ������ �����ϱ� false 
			}
		}

		else if (t.geTileWithCoord(i_idx, j_idx) == 2) {
			if (!t.tEnabled(i_idx + 1, j_idx)) {
				if (i_idx + 1 > 64) {
					return false;
				}
				i = i_idx + 1;
				j = j_idx;
			} else if (!t.tEnabled(i_idx, j_idx - 1)) {
				if (j_idx - 1 < 0) {
					return false;
				}
				i = i_idx;
				j = j_idx - 1;
			} else {
				return false;
			}
		} else if (t.geTileWithCoord(i_idx, j_idx) == 3) {
			if (!t.tEnabled(i_idx - 1, j_idx)) {
				if (i_idx - 1 < 0) {
					return false;
				}
				i = i_idx - 1;
				j = j_idx;
			} else if (!t.tEnabled(i_idx, j_idx - 1)) {
				if (j_idx - 1 < 0) {
					return false;
				}
				i = i_idx;
				j = j_idx - 1;
			} else {
				return false;
			}
		}

		else if (t.geTileWithCoord(i_idx, j_idx) == 4) {
			if (!t.tEnabled(i_idx - 1, j_idx)) {
				if (i_idx - 1 < 0) {
					return false;
				}
				i = i_idx - 1;
				j = j_idx;
			} 
			else if (!t.tEnabled(i_idx, j_idx + 1)) {
				if (j_idx + 1 > 64) {
					return false;
				}
				i = i_idx;
				j = j_idx + 1;
			} else {
				return false;
			}
		} else if (t.geTileWithCoord(i_idx, j_idx) == 5) {
			if (!t.tEnabled(i_idx - 1, j_idx)) {
				if (i_idx - 1 < 0) {
					return false;
				}
				i = i_idx - 1;
				j = j_idx;
			} else if (!t.tEnabled(i_idx + 1, j_idx)) {
				if (i_idx + 1 > 64) {
					return false;
				}
				i = i_idx + 1;
				j = j_idx;
			} else {
				return false;
			}
		} 
		else if (t.geTileWithCoord(i_idx, j_idx) == 6) {
			if (!t.tEnabled(i_idx, j_idx - 1)) {
				if (j_idx - 1 < 0) {
					return false;
				}
				i = i_idx;
				j = j_idx - 1;
			} else if (!t.tEnabled(i_idx, j_idx + 1)) {
				if (j_idx + 1 > 64) {
					return false;
				}
				i = i_idx;
				j = j_idx + 1;
			} else {
				return false;
			}
		}
		else {
			return false;
		}

		prev_i = i_idx;
		prev_j = j_idx;

		while (i_idx != i || j_idx != j) {
			if (t.geTileWithCoord(i, j) == 1) {
				if (prev_i == i + 1 && prev_j == j) {
					prev_i = i;
					prev_j = j;
					j = j + 1;
				} else if (prev_i == i && prev_j == j + 1) {
					prev_i = i;
					prev_j = j;
					i = i + 1;
				} else {
					return false;
				}
			} else if (t.geTileWithCoord(i, j) == 2) {
				if (prev_i == i + 1 && prev_j == j) {
					prev_i = i;
					prev_j = j;
					j = j - 1;
				} else if (prev_i == i && prev_j == j - 1) {
					prev_i = i;
					prev_j = j;
					i = i + 1;
				} else {
					return false;
				}
			}

			else if (t.geTileWithCoord(i, j) == 3) {
				if (prev_i == i - 1 && prev_j == j) {
					prev_i = i;
					prev_j = j;
					j = j - 1;
				} else if (prev_i == i && prev_j == j - 1) {
					prev_i = i;
					prev_j = j;
					i = i - 1;

				} else {
					return false;
				}
			} else if (t.geTileWithCoord(i, j) == 4) {
				if (prev_i == i - 1 && prev_j == j) {
					prev_i = i;
					prev_j = j;
					j = j + 1;
				} else if (prev_i == i && prev_j == j + 1) {
					prev_i = i;
					prev_j = j;
					i = i - 1;
				} else {
					return false;
				}

			} else if (t.geTileWithCoord(i, j) == 5) {
				if (prev_i == i - 1 && prev_j == j) {
					prev_i = i;
					prev_j = j;
					i = i + 1;
				} else if (prev_i == i + 1 && prev_j == j) {
					prev_i = i;
					prev_j = j;
					i = i - 1;
				} else {
					return false;
				}
			}

			else if (t.geTileWithCoord(i, j) == 6) {
				if (prev_i == i && prev_j == j - 1) {
					prev_i = i;
					prev_j = j;
					j = j + 1;
				} else if (prev_i == i && prev_j == j + 1) {
					prev_i = i;
					prev_j = j;
					j = j - 1;
				} else {
					return false;
				}
			} else {
				return false;
			}
		}

		return true;
	}
	
	
	public boolean checkWinBlack_circle(int i_idx, int j_idx, Tile t) {
		int i = 0, prev_i = 0;
		int j = 0, prev_j = 0;

		if (t.geTileWithCoord(i_idx, j_idx) == 1) {
			if (!t.tEnabled(i_idx - 1, j_idx)) {
				if (i_idx - 1 < 0) {
					return false;
				}
				i = i_idx - 1;
				j = j_idx;
			} else if (!t.tEnabled(i_idx, j_idx - 1)) {
				if (j_idx - 1 < 0) {
					return false;
				}
				i = i_idx;
				j = j_idx - 1;

			} else {
				return false;
			}

		} else if ((t.geTileWithCoord(i_idx, j_idx) == 2)) {
			if (!t.tEnabled(i_idx - 1, j_idx)) {
				if (i_idx - 1 < 0) {
					return false;
				}
				i = i_idx - 1;
				j = j_idx;
			} else if (!t.tEnabled(i_idx, j_idx + 1)) {
				if (j_idx + 1 > 64) {
					return false;
				}
				i = i_idx;
				j = j_idx + 1;
			} else {
				return false;
			}

		} else if (t.geTileWithCoord(i_idx, j_idx) == 3) {
			if (!t.tEnabled(i_idx + 1, j_idx)) {
				if (i_idx + 1 > 64) {
					return false;
				}
				i = i_idx + 1;
				j = j_idx;
			} else if (!t.tEnabled(i_idx, j_idx + 1)) {
				if (j_idx + 1 > 64) {
					return false;
				}
				i = i_idx;
				j = j_idx + 1;
			} else {
				return false;
			}
		} else if (t.geTileWithCoord(i_idx, j_idx) == 4) {
			if (!t.tEnabled(i_idx + 1, j_idx)) {
				if (i_idx + 1 > 64) {
					return false;
				}
				i = i_idx + 1;
				j = j_idx;
			} else if (!t.tEnabled(i_idx, j_idx - 1)) {
				if (j_idx - 1 < 0) {
					return false;
				}
				i = i_idx;
				j = j_idx - 1;

			} else {
				return false;
			}
		} else if (t.geTileWithCoord(i_idx, j_idx) == 5) {
			if (!t.tEnabled(i_idx, j_idx - 1)) {
				if (j_idx - 1 < 0) {
					return false;
				}
				i = i_idx;
				j = j_idx - 1;

			} else if (!t.tEnabled(i_idx, j_idx + 1)) {
				if (j_idx + 1 > 64) {
					return false;
				}
				i = i_idx;
				j = j_idx + 1;
			} else {
				return false;
			}
		} else if (t.geTileWithCoord(i_idx, j_idx) == 6) {
			if (!t.tEnabled(i_idx - 1, j_idx )) {
				if (i_idx - 1 < 0) {
					return false;
				}
				i = i_idx - 1;
				j = j_idx;
			} else if (!t.tEnabled(i_idx+1, j_idx )) {
				if (i_idx + 1 > 64) {
					return false;
				}
				i = i_idx + 1;
				j = j_idx;
			} else {
				return false;
			}

		} else {
			return false;
		}
		prev_i = i_idx;
		prev_j = j_idx;

		while (i_idx != i || j_idx != j) {

			if (t.geTileWithCoord(i, j) == 1) {
				if (prev_i == i - 1 && prev_j == j) {
					prev_i = i;
					prev_j = j;
					j = j - 1;
				} else if (prev_i == i && prev_j == j - 1) {
					prev_i = i;
					prev_j = j;
					i = i - 1;
				} else {
					return false;
				}

			} else if (t.geTileWithCoord(i, j) == 2) {
				if (prev_i == i - 1 && prev_j == j) {
					prev_i = i;
					prev_j = j;
					j = j + 1;
				} else if (prev_i == i && prev_j == j + 1) {
					prev_i = i;
					prev_j = j;
					i = i - 1;
				} else {
					return false;
				}

			} else if (t.geTileWithCoord(i, j) == 3) {
				if (prev_i == i + 1 && prev_j == j) {
					prev_i = i;
					prev_j = j;
					j = j + 1;
				} else if (prev_i == i && prev_j == j + 1) {
					prev_i = i;
					prev_j = j;
					i = i + 1;
				} else {
					return false;
				}
			}

			else if (t.geTileWithCoord(i, j) == 4) {
				if (prev_i == i + 1 && prev_j == j) {
					prev_i = i;
					prev_j = j;
					j = j - 1;
				} else if (prev_i == i && prev_j == j - 1) {
					prev_i = i;
					prev_j = j;
					i = i + 1;
				} else {
					return false;
				}

			}

			else if (t.geTileWithCoord(i, j) == 5) {

				if (prev_i == i && prev_j == j - 1) {
					prev_i = i;
					prev_j = j;
					j = j + 1;
				} else if (prev_i == i && prev_j == j + 1) {
					prev_i = i;
					prev_j = j;
					j = j - 1;
				} else {
					return false;
				}
			} else if (t.geTileWithCoord(i, j) == 6) {
				if (prev_i == i - 1 && prev_j == j) {
					prev_i = i;
					prev_j = j;
					i = i + 1;
				} else if (prev_i == i + 1 && prev_j == j) {
					prev_i = i;
					prev_j = j;
					i = i - 1;
				} else {
					return false;
				}
			} else {
				return false;
			}
		}
		return true;

	}
}
