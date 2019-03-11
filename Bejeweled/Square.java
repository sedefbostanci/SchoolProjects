
public class Square implements Symbols{

	public int leftHorizontal(int row1,int column1,String[][] map1,int sayac1) {
		
		int smt;
		int satir=map1.length;
		int sutun=map1[0].length;
		
		
		if(column1-1 >= 0 ) {
			
			if(map1[row1][column1-1].equals("S") && sayac1 !=3) {
				
				sayac1++;
				
				return leftHorizontal(row1,column1-1,map1,sayac1);
			}
			
			
		}
		else {
			
			smt=-1;
		}
				
		
		if(sayac1==3) {
			smt=4;
		}
		else {
			smt=0;
		}
		
		return smt;
	
	}
	
	public int rightHorizontal(int row2,int column2,String[][] map1,int sayac1) {
		
		int smt;
		int satir=map1.length;
		int sutun=map1[0].length;
		
		if(column2+1 < sutun ) {
			
			if(map1[row2][column2+1].equals("S") && sayac1 !=3) {
				
				sayac1++;
				
				return rightHorizontal(row2,column2+1,map1,sayac1);
			}
			
		}
		else {
			smt=-1;
		}
		
		if(sayac1==3) {
			smt=6;
		}
		else {
			smt=0;
		}
		
		return smt;
	
	}
	
	public int match(int row, int column, String[][] map) {
		
		int checkLeft;
		int checkRight;
		int sayac1=1;
		
		
		checkLeft=leftHorizontal(row,column,map,sayac1);
		
		if(checkLeft==0 || checkLeft==-1) {
			
			checkRight=rightHorizontal(row,column,map,sayac1);
			
			return checkRight;
		}
		else {
			
			return checkLeft;
		}
	}

	@Override
	public int point() {
		// TODO Auto-generated method stub
		return 15;
	}

}
