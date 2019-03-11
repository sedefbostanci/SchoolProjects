
public class Triangle implements Symbols {

	public int Upward(int row1, int column1, String[][] map1, int sayac1) {

		int smt;
		int satir = map1.length;
		int sutun = map1[0].length;

		
		if (row1 - 1 >= 0) {

			if (map1[row1 - 1][column1].equals("T") && sayac1 != 3) {

				sayac1++;

				return Upward(row1 - 1, column1, map1, sayac1);
			}

		}
		else {
			smt=-1;
		}
		

		if (sayac1 == 3) {
			smt = 2;
		} else {
			smt = 0;
		}

		return smt;

	}

	public int Downward(int row2, int column2, String[][] map1, int sayac1) {

		int smt;
		int satir = map1.length;
		int sutun = map1[0].length;

		if (row2 + 1 < satir) {

			if (map1[row2 + 1][column2].equals("T") && sayac1 != 3) {

				sayac1++;

				return Downward(row2 + 1, column2, map1, sayac1);
			}

		}
		else {
			smt=-1;
		}

		if (sayac1 == 3) {
			smt = 8;
		} else {
			smt = 0;
		}

		return smt;

	}

	@Override
	public int match(int row, int column, String[][] map) {

		int checkUp;
		int checkDown;
		int sayac1 = 1;

		checkUp = Upward(row, column, map, sayac1);

		if (checkUp == 0 || checkUp==-1) {

			checkDown = Downward(row, column, map, sayac1);
			
			return checkDown;
		} else {

			return checkUp;
		}
	}

	@Override
	public int point() {
		// TODO Auto-generated method stub
		return 15;
	}

}
