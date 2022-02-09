package JoculVietii;

public class DisplayBoardConsole extends Board implements DisplayBoard {
	public int areaSize = 5;
	public int timeSleep = 250;
	public boolean displayCurrentCoordinates = false;
	public DisplayBoardConsole() {
	}
	public DisplayBoardConsole(int areaSize, int timeSleep, boolean displayCurrentCoordinates) {
		this.areaSize = areaSize;
		if (timeSleep > 0)
			this.timeSleep = timeSleep;
		else
			throw new IllegalArgumentException(
					"timeSleep must be bigger then zero.");
		this.displayCurrentCoordinates = displayCurrentCoordinates;
	}
	public void printCurrentStateCoordinates() {
		System.out.println();
		for (Cell cell : _currentState) {
			System.out.println("X: " + cell.x + ", Y: " + cell.y);
		}
		System.out.println("Cells on board: " + _currentState.size());
	}
	@Override
	public void displayCurrentStateOfBoard() {
		System.out.println();
		for (int i = (int) -areaSize; i < areaSize * 2; i++) {
			for (int j = (int) -areaSize * 4; j < areaSize * 4; j++) {
				if (isCellExist(j, i)) {
					System.out.print("O");
				} else {
					System.out.print(" ");
				}
			}
			System.out.println();
		}
		System.out.println("Cells on board: " + _currentState.size());
	}
	@Override
	public void playGame() {
		displayCurrentStateOfBoard();
		if (displayCurrentCoordinates)
			printCurrentStateCoordinates();
		try {
			Thread.sleep(timeSleep);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		while (true) {
			System.out.flush();
			nextState();
			displayCurrentStateOfBoard();
			if (displayCurrentCoordinates)
				printCurrentStateCoordinates();
			try {
				Thread.sleep(timeSleep);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}