
public class Diamond implements Symbols {

	public int leftDiagonalUp(int row1, int column1, String[][] map1, int sayac1) {

		int smt;
		int satir = map1.length;
		int sutun = map1[0].length;

		
		
		if (row1 - 1 >= 0 && column1 - 1 >= 0 ) {

			if (map1[row1 - 1][column1 - 1].equals("D") && sayac1 != 3) {

				sayac1++;

				return leftDiagonalUp(row1 - 1, column1 - 1, map1, sayac1);

			} 
		}
		else {
			
			smt=-1;
		}

		if (sayac1 == 3) {
			smt = 1;
		} 
		else {
			smt = 0;
		}

		return smt;
	}

	public int leftDiagonalDown(int row1, int column1, String[][] map1, int sayac1){
		
		int smt;
		int satir = map1.length;
		int sutun = map1[0].length;
		
		if (row1 + 1 < satir && column1 + 1 < sutun ) {

			if (map1[row1 + 1][column1 + 1].equals("D") && sayac1 != 3) {

				sayac1++;

				return leftDiagonalDown(row1 + 1, column1 + 1, map1, sayac1);

			} 
		}
			
		if (sayac1 == 3) {
			smt = 9;
		} 
		else {
			smt = 0;
		}

		return smt;
		
	}
	
	public int rightDiagonalUp(int row2, int column2, String[][] map2, int sayac1) {

		int smt;
		int satir = map2.length;
		int sutun = map2[0].length;

		if (row2 - 1 >= 0 && column2 + 1 < sutun) {
			
			if (map2[row2 - 1][column2 + 1].equals("D") && sayac1 != 3) {
				
				sayac1++;
				
				return rightDiagonalUp(row2 - 1, column2 + 1, map2, sayac1);

			} 
		}
		else {
			
			smt=-1;
		}

		if (sayac1 == 3) {

			smt = 3;
		
		} 
		else {

			smt = 0;
		}
		
		return smt;
	}
	
	public int rightDiagonalDown(int row2, int column2, String[][] map2, int sayac1) {
		
		int smt;
		int satir = map2.length;
		int sutun = map2[0].length;
		
		if (row2 + 1 < satir && column2 - 1 >= 0 ) {
			
			if (map2[row2 + 1][column2 - 1].equals("D") && sayac1 != 3) {
				
				sayac1++;
			
				return rightDiagonalDown(row2 + 1, column2 - 1, map2, sayac1);

			}
			
				
		}
		else {
			smt=-1;
		}
		
		if (sayac1 == 3) {

			smt = 7;

		} else {

			smt = 0;
		}

	
		return smt;
	}

	@Override
	public int match(int row, int column, String[][] map) {

		int checkLeftUp;
		int checkLeftDown;
		int checkRightUp;
		int checkRightDown;
		int sayac1 = 1;
		
		

		checkLeftUp = leftDiagonalUp(row, column, map, sayac1);

		if (checkLeftUp == 0 || checkLeftUp==-1) {
			
			checkLeftDown = leftDiagonalDown(row, column, map, sayac1);
			
			if(checkLeftDown == 0 || checkLeftDown==-1) {
				
				checkRightUp = rightDiagonalUp(row, column, map, sayac1);
				
				if(checkRightUp == 0 || checkRightUp==-1) {
					
					checkRightDown=rightDiagonalDown(row, column, map, sayac1);
					
					if(checkRightDown == 0 || checkRightDown==-1) {
						return 0;
					}
					else {
						return checkRightDown;
					}
				}
				else {
					return checkRightUp;
				}
			}
			else {
				return checkLeftDown;
			}
			
		}
		else {
			return checkLeftUp;
		}

	}

	@Override
	public int point() {
		// TODO Auto-generated method stub
		return 30;
	}

}
