
public class WildCard implements Symbols {

	public int checkW(int row1, int column1, String[][] map1,int direction) {

		String symbol;
		int satir = map1.length;
		int sutun = map1[0].length;
		
		
		if(row1 < satir && row1>=0 && column1 <sutun && column1>=0) {
			symbol = map1[row1][column1];
			
			if (symbol.equals("D")) {
				return 30*10+direction;
			} else if (symbol.equals("S") || symbol.equals("T")) {
				return 15*10+direction;

			} else if (symbol.equals("W")) {
				return 10*10+direction;
			} else {
				return 20*10+direction;
			}
		}
		else {
			return -2;
		}
		
	}

	public int Up(int row1, int column1, String[][] map1) {

		int sayac1 = 2;
		int check1,check3;
		int satir = map1.length;
		int sutun = map1[0].length;
		int smt;

		
		
		if(row1-1 >=0 ) {
			
			if (map1[row1 - 1][column1].equals("W") ) {

				smt = checkW(row1 - 2, column1, map1,2);
				
			} else {

				if (map1[row1 - 1][column1].equals("T")) {

					
					Triangle tri = new Triangle();
					check1 = tri.Upward(row1-1, column1, map1, sayac1);
					smt = check1;
						
					
				} else if (map1[row1 - 1][column1].equals("|") || map1[row1 - 1][column1].equals("+") ) {
					
					Math mat=new Math();
					check3=mat.match(row1-1, column1, map1,2);
					
					smt=check3;
					
				
				} 
				else {
					smt=0;
				}
			}
		}
		else {
			
			smt=-1;
			
		}
	
		return smt;

	}

	public int Down(int row1, int column1, String[][] map1) {
		
		int sayac1 = 2;
		int check1, check2,check3;
		int satir = map1.length;
		int sutun = map1[0].length;
		int smt;
		
		if(row1+1 < satir) {
			if(map1[row1 + 1][column1].equals("W")) {
				smt = checkW(row1 + 2, column1, map1,8);
			}
			else {
				if (map1[row1 + 1][column1].equals("T")) {
					
					Triangle tri2=new Triangle();
					check2 = tri2.Downward(row1+1, column1, map1, sayac1);
					smt=check2;
				} 
				 else if(map1[row1 + 1][column1].equals("|") || map1[row1 + 1][column1].equals("+")){

					 Math mat=new Math();
					 check3=mat.match(row1+1, column1, map1,2);
						
					 smt=check3;
				}
				else {
					smt=0;
				}
			}
		}
		
		else {
			smt=-1;
		}
			
		return smt;
		
	}
	
	public int Left(int row1, int column1, String[][] map1) {

		int sayac1 = 2;
		int check1, check2,check3;
		int satir = map1.length;
		int sutun = map1[0].length;
		int smt,a;

		
		if(column1-1 >=0 ) {
			if (map1[row1][column1-1].equals("W") ) {

				smt = checkW(row1, column1-2, map1,4);			

			} else {
				
				if (map1[row1][column1-1].equals("S")) {
					
					Square squ = new Square();
					check1 = squ.leftHorizontal(row1, column1-1, map1, sayac1);
					
					smt=check1;
					
				} else if (map1[row1][column1-1].equals("-") || map1[row1][column1-1].equals("+")) {
					Math mat=new Math();
					check3=mat.match(row1, column1-1, map1,2);
						
					smt=check3;
				} 
				else {
					smt=0;
				}
					
				
			}
		}
		else {
			smt=-1;
		}
		
		return smt;

	}
	
	public int Right(int row1, int column1, String[][] map1) {
		
		int sayac1 = 2;
		int check1, check2,check3;
		int satir = map1.length;
		int sutun = map1[0].length;
		int smt,a;
		
		if(column1 +1 < sutun) {
			
			if (map1[row1][column1 + 1].equals("W") ) {
				smt = checkW(row1, column1+2, map1,6);
			}
			else {
				
				
				if (map1[row1][column1+1].equals("S")) {
					
					Square squ2=new Square();
					check2 = squ2.rightHorizontal(row1, column1+1, map1, sayac1);
					if (check2 == 0) {
						smt = 0;
					} else {
						smt = check2;
					}
				} else if (map1[row1][column1+1].equals("-") || map1[row1][column1+1].equals("+")) {
					Math mat=new Math();
					check3=mat.match(row1, column1+1, map1,2);
						
					smt=check3;
				}  
				else {
					smt = 0;
				}
			}
		}
		else {
			smt=-1;
		}
		
		return smt;
	}
	
	public int LeftDioUp(int row1, int column1, String[][] map1) {

		
		int check1, check3;
		int satir = map1.length;
		int sutun = map1[0].length;
		int smt;
	
		
		if(row1-1 >=0 && column1-1 >=0  ) {
			
			if (map1[row1 - 1][column1 -1].equals("W")) {
				
				smt = checkW(row1 - 2, column1-2, map1,1);
				

			} else {
				
				if (map1[row1 - 1][column1-1].equals("D")) {
					
					
					Diamond dio = new Diamond();
					check1 = dio.leftDiagonalUp(row1-1, column1-1, map1,2);
					smt=check1;
					
				}
				
				
				else if (map1[row1 - 1][column1-1].equals("\\")) {
					Math mat=new Math();
					check3=mat.match(row1-1, column1-1, map1,2);
						
					smt=check3;
				}
				else {
					
					smt=0;
						
				}
						
			}	
		}
		else {
			smt=-1;
		}
		
		return smt;

	}
	public int LeftDioDown(int row1, int column1, String[][] map1) {
		
		int check1, check2, check3, check4;
		int satir = map1.length;
		int sutun = map1[0].length;
		int smt;
		
		if(column1 +1 <sutun && row1 + 1 < satir ) {
			
			if(map1[row1 + 1][column1 + 1].equals("W")) {
				smt = checkW(row1 + 2, column1 + 2, map1,9);
			}
			else {
				
				if (map1[row1+1][column1+1].equals("D")) {
					Diamond dio2 = new Diamond();
					check2 = dio2.leftDiagonalDown(row1+1, column1+1, map1, 2);
					smt=check2;
				}
				else if (map1[row1 + 1][column1+1].equals("\\")) {
					Math mat=new Math();
					check3=mat.match(row1+1, column1+1, map1,2);
						
					smt=check3;
				}
				else {
					smt=0;
				}
			}
		}
		else {
			smt=-1;
		}
		
		return smt;
	}
	
	public int RightDioUp(int row1, int column1, String[][] map1) {

		
		int check1, check3;
		int satir = map1.length;
		int sutun = map1[0].length;
		int smt;
		
		
		
		if(row1-1 >= 0  && column1+1 < sutun) {
			
			if (map1[row1 - 1][column1 +1].equals("W") ) {

				smt = checkW(row1 - 2, column1+2, map1,3);
				

			} else {

				if (map1[row1 - 1][column1 + 1].equals("D")) {
					
					Diamond dio = new Diamond();
					check1 = dio.rightDiagonalUp(row1-1, column1+1, map1, 2);
					smt=check1;
					
				}
				
				else if (map1[row1 - 1][column1-1].equals("/")) {
					Math mat=new Math();
					check3=mat.match(row1-1, column1-1, map1,2);
						
					smt=check3;
				}
				else {
					
					smt=0;
						
				}
				
			
			}	
		}
		else {
			smt=-1;
		}
		return smt;

	}
	
	public int RightDioDown(int row1, int column1, String[][] map1) {
		
		int check1, check2, check3, check4;
		int satir = map1.length;
		int sutun = map1[0].length;
		int smt;
		
		if(row1+1 <satir && column1-1>=0 ) {
			
			if(map1[row1 + 1][column1 - 1].equals("W")) {
				smt = checkW(row1 + 2, column1 - 2, map1,7);
			}
			else {
				if (map1[row1+1][column1-1].equals("D")) {
					
					Diamond dio2 = new Diamond();
					check2 = dio2.rightDiagonalDown(row1+1, column1-1, map1, 2);
					
					smt = check2;
					
				}
				else if (map1[row1 + 1][column1-1].equals("/")) {
					
					Math mat=new Math();
					check3=mat.match(row1+1, column1-1, map1,2);
						
					smt=check3;
				}
				
				else {
					smt=0;
				}
			}
		}
		else {
			smt=-1;
		}
		
		return smt;
	}
	
	@Override
	public int match(int row, int column, String[][] map) {
		
		int checkUp,checkDown;
		int checkLeft,checkRight;
		int checkDioLeftUp,checkDioLeftDown;
		int checkDioRightUp,checkDioRightDown;
		int sayac1 = 1;
		int rtn;

		checkUp = Up(row, column, map);

		if (checkUp == 0 || checkUp==-1) {

			checkDown = Down(row, column, map);
			
			if(checkDown==0 || checkDown ==-1) {
				
				checkLeft=Left(row, column, map);
				if(checkLeft==0 || checkLeft==-1) {
					checkRight=Right(row, column, map);
					if(checkRight==0||checkRight==-1) {
						checkDioLeftUp=LeftDioUp(row, column, map);
						if(checkDioLeftUp==0 || checkDioLeftUp==-1) {
							checkDioLeftDown=LeftDioDown(row, column, map);
							if(checkDioLeftDown==0 || checkDioLeftDown==-1){
								checkDioRightUp=RightDioUp(row, column, map);
								if(checkDioRightUp==0 || checkDioRightUp==-1) {
									checkDioRightDown=RightDioDown(row, column, map);
										if(checkDioRightDown==0||checkDioRightDown==-1) {
											rtn=0;
										}
										else {
											rtn=checkDioRightDown;
									}
								}
								
								else {
									rtn=checkDioRightUp;
								}
							}
							else {
								rtn=checkDioLeftDown;
							}
						}
						else {
							rtn=checkDioLeftUp;
						}
						
						
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
		
		return rtn;
	}

	@Override
	public int point() {
		// TODO Auto-generated method stub
		return 10;
	}

}
