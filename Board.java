package JoculVietii;

import java.util.*;

public class Board {
	protected Set<Cell> _currentState = new HashSet<Cell>();
	private Set<Cell> _tmpCheckedCell = new HashSet<Cell>();
	private Queue<Cell> _nextStateGiveLife = new LinkedList<Cell>();
	private Queue<Cell> _nextStateKillLife = new LinkedList<Cell>();
	private Cell tmpCell = new Cell(0, 0);
	private int _counterMethod_giveLifeToNeighboursIfPossible;
	public int getCounterMethod_giveLifeToNeighboursIfPossible() {
		return _counterMethod_giveLifeToNeighboursIfPossible;
	}
	public Board() {
		_counterMethod_giveLifeToNeighboursIfPossible = 0;
	}
	public void addCell(int x, int y) {
		_currentState.add(new Cell(x, y));
	}
	public void removeCell(int x, int y) {
		_currentState.remove(new Cell(x, y));
	}
	public boolean isCellExist(int x, int y) {
		return _currentState.contains(new Cell(x, y));
	}
	public int countCells() {
		return _currentState.size();
	}
	public void nextState() {
		_counterMethod_giveLifeToNeighboursIfPossible = 0;
		int tmpNeighbours = 0;
		for (Cell cell : _currentState) {
			tmpNeighbours = countNeighbours(cell.x, cell.y);
			if (tmpNeighbours < 2 || tmpNeighbours > 3)
				addCellToNextStateKillLife(cell);
			giveLifeToNeighboursIfPossible(cell.x, cell.y);
			_tmpCheckedCell.clear();
		}
		while (_nextStateKillLife.size() > 0) {
			tmpCell = _nextStateKillLife.poll();
			removeCell(tmpCell.x, tmpCell.y);
		}
		while (_nextStateGiveLife.size() > 0) {
			tmpCell = _nextStateGiveLife.poll();
			addCell(tmpCell.x, tmpCell.y);
		}
	}
	private void giveLifeToNeighboursIfPossible(int x, int y) {
		_counterMethod_giveLifeToNeighboursIfPossible++;
		int tmpX = 0;
		int tmpY = 0;
		for (int i = 0; i < 8; i++) {
			switch (i) {
			case 0:
				tmpX = x - 1;
				tmpY = y - 1;
				break;
			case 1:
				tmpX = x;
				tmpY = y - 1;
				break;
			case 2:
				tmpX = x + 1;
				tmpY = y - 1;
				break;
			case 3:
				tmpX = x - 1;
				tmpY = y;
				break;
			case 4:
				tmpX = x + 1;
				tmpY = y;
				break;
			case 5:
				tmpX = x - 1;
				tmpY = y + 1;
				break;
			case 6:
				tmpX = x;
				tmpY = y + 1;
				break;
			case 7:
				tmpX = x + 1;
				tmpY = y + 1;
				break;
			}
			if (!_tmpCheckedCell.contains(new Cell(tmpX, tmpY)) && !isCellExist(tmpX, tmpY)	&& countNeighbours(tmpX, tmpY) == 3) {
				addCellToNextStateGiveLife(new Cell(tmpX, tmpY));
			}
			_tmpCheckedCell.add(new Cell(tmpX, tmpY));
		}
	}
	public int countNeighbours(int x, int y) {
		int neighbours = 0;
		if (isCellExist(x - 1, y - 1))
			neighbours++;
		if (isCellExist(x, y - 1))
			neighbours++;
		if (isCellExist(x + 1, y - 1))
			neighbours++;
		if (isCellExist(x - 1, y))
			neighbours++;
		if (isCellExist(x + 1, y))
			neighbours++;
		if (isCellExist(x - 1, y + 1))
			neighbours++;
		if (isCellExist(x, y + 1))
			neighbours++;
		if (isCellExist(x + 1, y + 1))
			neighbours++;
		return neighbours;
	}
	private void addCellToNextStateKillLife(Cell cell) {
		if (!_nextStateKillLife.contains(new Cell(cell.x, cell.y)))
			_nextStateKillLife.offer(cell);
	}
	private void addCellToNextStateGiveLife(Cell cell) {
		if (!_nextStateGiveLife.contains(new Cell(cell.x, cell.y)))
			_nextStateGiveLife.offer(cell);
	}
}