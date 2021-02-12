package action;


import view.Tile;

public class CheckWinTile {
	public boolean checkWinWhite_circle(int i_idx, int j_idx, Tile t) {

		int i = 0, prev_i = 0;
		int j = 0, prev_j = 0;	
		// 변수 x,y   
		// 세로값이 i, 가로값이 j 생성 x,y 규칙
		//tEnabled은  map.tiles[y][x].status이  0이 아니면 false 출력
		// NW: 1
		// NE: 2
		// ES: 3
		// SW: 4
		// EW: 5
		// NS: 6
	
		if (t.geTileWithCoord(i_idx, j_idx) == 1) {    //geTileWithCoord은   map.tiles[y][x].status을
			if (!t.tEnabled(i_idx + 1, j_idx)) {       //(오른쪽)  map.tiles[y][x].status != 0  
				if (i_idx + 1 > 64) { 					 // 0이면 검사할 필요가 없고 그게 다른 다일로 들어가있으면
					return false;                        //오른쪽이 사용 불가능 하면 들어간다. 그오른쪽이 끝이면 false 지 
				}
				i = i_idx + 1;   //검사하는 타일은 오른쪽으로 넘어간다.
				j = j_idx;	     //
			} else if (!t.tEnabled(i_idx, j_idx + 1)) {  //오른쪽에 타일이 잔디니까 일로 온다  
				if (j_idx + 1 > 64) {    //아래값을 본다 아래쪽이 타일이 넘어가면 false
					return false;
				}
				i = i_idx;             //안넘어가면 그 아래 검사
				j = j_idx + 1;         //그아래검사
			} else {
				return false;         // 오른쪽이랑 아래쪽 다 안넘어가면 넘어갈 조건이 없으니까 false 
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
