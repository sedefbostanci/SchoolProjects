
public class UpdateMap {
	
	public void make(int direction,int row,int column,String[][] map) {
		
		if(direction==2 || direction==8 || direction==2000 || direction==8000 || direction==152 || direction==158 || direction==202 || direction==208 || direction==302|| direction==308 || direction==102 || direction==108) {
			
			Update(row,column,map);
			Update(row,column,map);
			Update(row,column,map);
			
		}
		else if(direction==4 || direction==4000 || direction==154 || direction==104 || direction==204 || direction==304) {
			Update(row,column,map);
			Update(row,column-1,map);
			Update(row,column-2,map);
		}
		else if(direction==6 || direction==6000 || direction==156 || direction==106 || direction==206 || direction==306) {
			Update(row,column,map);
			Update(row,column+1,map);
			Update(row,column+2,map);
		}
		else if(direction==1 || direction==1000 || direction==301 || direction==201 || direction==101 || direction==151) {
			Update(row,column,map);
			Update(row-1,column-1,map);
			Update(row-2,column-2,map);
		}
		else if(direction==9 || direction==9000 || direction==309 || direction==209 || direction==109 || direction==159) {
			Update(row,column,map);
			Update(row+1,column+1,map);
			Update(row+2,column+2,map);
		}
		else if(direction==3 || direction==3000 || direction==303 || direction==203 || direction==103 || direction==153) {
			Update(row,column,map);
			Update(row-1,column+1,map);
			Update(row-2,column+2,map);
		}
		else if(direction==7 ||  direction==7000 || direction==307 || direction==207 || direction==107 || direction==157) {
			Update(row,column,map);
			Update(row+1,column-1,map);
			Update(row+2,column-2,map);
		}
	}
	
	public void Update(int Row,int column,String[][] map) {
		
		int i;
		
		for(i=Row ;i >= 0 ; i--) {
			
			if(i==0) {
				map[0][column]=" ";
			}
			else {
				map[i][column]=map[i-1][column];
			}
			
		}
		
	}
}
