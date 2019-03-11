import java.util.Arrays;

public class Math implements Maths{

	public int Upward(int row1, int column1, String[][] map1, int sayac1,String[] arr) {

		int smt;
		int satir = map1.length;
		int sutun = map1[0].length;

		
		if (row1 - 1 >= 0) {

			if (Arrays.asList(arr).contains(map1[row1 - 1][column1] ) && sayac1 != 3) {

				sayac1++;

				return Upward(row1 - 1, column1, map1, sayac1,arr);
			}

		}
		else {
			smt=-1;
		}
		

		if (sayac1 == 3) {
			smt = 2000;
		} else {
			smt = 0;
		}

		return smt;

	}
	public int Downward(int row2, int column2, String[][] map1, int sayac1,String[] arr) {

		int smt;
		int satir = map1.length;
		int sutun = map1[0].length;

		if (row2 + 1 < satir) {

			if (Arrays.asList(arr).contains(map1[row2 + 1][column2]) && sayac1 != 3) {

				sayac1++;

				return Downward(row2 + 1, column2, map1, sayac1,arr);
			}

		}
		else {
			smt=-1;
		}

		if (sayac1 == 3) {
			smt = 8000;
		} else {
			smt = 0;
		}

		return smt;

	}
	
	public int Left(int row1,int column1,String[][] map1,int sayac1,String[] arr) {
		
		int smt;
		int satir=map1.length;
		int sutun=map1[0].length;
		
		
		if(column1-1 >= 0 ) {
			
			if(Arrays.asList(arr).contains(map1[row1][column1-1]) && sayac1 !=3) {
				
				sayac1++;
				
				return Left(row1,column1-1,map1,sayac1,arr);
			}
			
			
		}
		else {
			
			smt=-1;
		}
		
		
		
		if(sayac1==3) {
			smt=4000;
		}
		else {
			smt=0;
		}
		
		return smt;
	
	}
	
	public int Right(int row2,int column2,String[][] map1,int sayac1,String[] arr) {
		
		int smt;
		int satir=map1.length;
		int sutun=map1[0].length;
		
		if(column2+1 < sutun ) {
			
			if(Arrays.asList(arr).contains(map1[row2][column2+1] )&& sayac1 !=3) {
				
				sayac1++;
				
				return Right(row2,column2+1,map1,sayac1,arr);
			}
			
		}
		else {
			smt=-1;
		}
		
		if(sayac1==3) {
			smt=6000;
		}
		else {
			smt=0;
		}
		
		return smt;
	
	}
	
	public int leftDiagonalUp(int row1, int column1, String[][] map1, int sayac1,String[] arr) {

		int smt;
		int satir = map1.length;
		int sutun = map1[0].length;

		
		
		if (row1 - 1 >= 0 && column1 - 1 >= 0 ) {

			if (Arrays.asList(arr).contains(map1[row1 - 1][column1 - 1])&& sayac1 != 3) {

				sayac1++;

				return leftDiagonalUp(row1 - 1, column1 - 1, map1, sayac1,arr);

			} 
		}
		else {
			
			smt=-1;
		}

		
		

		if (sayac1 == 3) {
			smt = 1000;
		} 
		else {
			smt = 0;
		}

		return smt;
	}
	
	public int leftDiagonalDown(int row1, int column1, String[][] map1, int sayac1,String[] arr){
		
		int smt;
		int satir = map1.length;
		int sutun = map1[0].length;
		
		if (row1 + 1 < satir && column1 + 1 < sutun ) {

			if (Arrays.asList(arr).contains(map1[row1 + 1][column1 + 1])&& sayac1 != 3) {

				sayac1++;

				return leftDiagonalDown(row1 + 1, column1 + 1, map1, sayac1,arr);

			} 
		}
			
		if (sayac1 == 3) {
			smt = 9000;
		} 
		else {
			smt = 0;
		}

		return smt;
		
	}
	
	public int rightDiagonalUp(int row2, int column2, String[][] map2, int sayac1,String[] arr) {

		int smt;
		int satir = map2.length;
		int sutun = map2[0].length;

		
		if (row2 - 1 >= 0 && column2 + 1 < sutun) {
			
			if (Arrays.asList(arr).contains(map2[row2 - 1][column2 + 1])&& sayac1 != 3) {
				
				sayac1++;
				
				return rightDiagonalUp(row2 - 1, column2 + 1, map2, sayac1,arr);

			} 
		}
		else {
			
			smt=-1;
		}


		if (sayac1 == 3) {

			smt = 3000;
		
		} 
		else {

			smt = 0;
		}
		
		return smt;
	}
	public int rightDiagonalDown(int row2, int column2, String[][] map2, int sayac1,String[] arr) {
		
		int smt;
		int satir = map2.length;
		int sutun = map2[0].length;
		
		if (row2 + 1 < satir && column2 - 1 >= 0 ) {
			
			
				
			if (Arrays.asList(arr).contains(map2[row2 + 1][column2 - 1]) && sayac1 != 3) {
				
				sayac1++;
				
				return rightDiagonalDown(row2 + 1, column2 - 1, map2, sayac1,arr);

			}
			
				
		}
		else {
			smt=-1;
		}
		
		if (sayac1 == 3) {

			smt = 7000;

		} else {

			smt = 0;
		}

		return smt;
	}
	
	
	@Override
	public int match(int row, int column, String[][] map,int sayac) {
		// TODO Auto-generated method stub
		String[] maths=new String[] {"+","-","/","\\","|"};
		int checkUp,checkDown;
		int checkLeft,checkRight;
		int checkDioLeftUp,checkDioLeftDown;
		int checkDioRightUp,checkDioRightDown;
		int rtn;
		
		
		if(map[row][column].equals("+")) {
			
			checkUp = Upward(row, column, map,sayac,maths);

			if (checkUp == 0 || checkUp==-1) {

				checkDown = Downward(row, column, map,sayac,maths);
				
				if(checkDown==0 || checkDown ==-1) {
					
					checkLeft=Left(row, column, map,sayac,maths);
					if(checkLeft==0 || checkLeft==-1) {
						checkRight=Right(row, column, map,sayac,maths);
						if(checkRight==0||checkRight==-1) {
							rtn=0;
						}
						else {
							rtn=checkRight;
						}
					}
					else {
						rtn=checkLeft;
					}
									
				}
				else {
					rtn=checkDown;
				}
			}
			else {
				rtn=checkUp;	
			}
		}
		else if(map[row][column].equals("-")){
			checkLeft=Left(row,column,map,sayac,maths);
			
			if(checkLeft==0 || checkLeft==-1) {
				
				checkRight=Right(row,column,map,sayac,maths);
				if(checkRight==0 || checkRight==-1) {
					rtn=0;
				}
				else{
					rtn= checkRight;
				}
			}
			else {
				
				rtn= checkLeft;
			}
		}
		else if(map[row][column].equals("/")){
			
			checkDioRightUp = rightDiagonalUp(row, column, map, sayac,maths);
			
			if(checkDioRightUp == 0 || checkDioRightUp==-1) {
				
				checkDioRightDown=rightDiagonalDown(row, column, map, sayac,maths);
				
				if(checkDioRightDown == 0 || checkDioRightDown==-1) {
					return 0;
				}
				else {
					rtn= checkDioRightDown;
				}
			}
			else {
				rtn= checkDioRightUp;
			}
		}
		else if(map[row][column].equals("\\")){
			
			checkDioLeftUp=leftDiagonalUp(row, column, map,sayac,maths);
			if(checkDioLeftUp==0 || checkDioLeftUp==-1) {
				checkDioLeftDown=leftDiagonalDown(row, column, map,sayac,maths);
				if(checkDioLeftDown==0 || checkDioLeftDown==-1){
					return 0;
				}
				else {
					rtn=checkDioLeftDown;
				}
			
			}else {
				rtn=checkDioLeftUp;
			}
		}
		else {
			
			checkUp = Upward(row, column, map, sayac,maths);

			if (checkUp == 0 || checkUp==-1) {

				checkDown = Downward(row, column, map, sayac,maths);
				if(checkDown==0 || checkDown==-1) {
					return 0;
				}
				else{
					rtn =checkDown;
				}
			} else {

				rtn= checkUp;
			}
		}
		
		return rtn;
	}

	

}
