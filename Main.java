package JoculVietii;

import java.util.Random;

public class Main{
	public static void main(String[] args) {
		DisplayBoardConsole b = new DisplayBoardConsole(5, 250, false);
		b.areaSize = 7;
		b.addCell(1, 1);
		b.addCell(1, 2);
		b.addCell(1, 3);
		b.addCell(2, 2);
		b.addCell(2, 3);
		b.addCell(2, 4);
		b.addCell(-5, 0);
		b.addCell(-5, 2);
		b.addCell(-6, 1);
		b.addCell(-4, 1);
		b.addCell(-18, -5);
		b.addCell(-18, -4);
		b.addCell(-18, -3);
		b.addCell(-19, -3);
		b.addCell(-20, -4);
		b.addCell(10, 0);
		b.addCell(11, 0);
		b.addCell(12, 0);
		b.addCell(-10, 5);
		b.addCell(-10, 6);
		b.addCell(-10, 7);
		Random random = new Random();
		int randomX, randomY;
		for (int i = 0; i < 100; i++) {
			randomX = random.nextInt(20 - -20 + 1) + -20;
			randomY = random.nextInt(20 - -20 + 1) + -20;
			b.addCell(randomX, randomY);
		}
		b.playGame();
	}
}