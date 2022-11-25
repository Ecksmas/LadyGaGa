package org.example;

public class Monster {
	private int x;
	private int y;
	private char symbol;
	private int previousX;
	private int previousY;

	public Monster(int x, int y) {
		this.x = x;
		this.y = y;
		this.symbol = 'M';
		this.previousX = x;
		this.previousY = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public char getSymbol() {
		return symbol;
	}

	public void setSymbol(char symbol) {
		this.symbol = symbol;
	}

	public int getPreviousX() {
		return previousX;
	}

	public void setPreviousX(int previousX) {
		this.previousX = previousX;
	}

	public int getPreviousY() {
		return previousY;
	}

	public void setPreviousY(int previousY) {
		this.previousY = previousY;
	}

	public void moveTowards(Player player) {
		// a monster wants to minimize the distance between itself and the player

		previousX = x;
		previousY = y;

		int diffX = this.x - player.getX();
		int absDiffX = Math.abs(diffX);
		int diffY = this.y - player.getY();
		int absDiffY = Math.abs(diffY);

		if (absDiffX > absDiffY) {
			// Move horizontal! <--->
			if (diffX < 0) {
				this.x += 1;
			} else {
				this.x -= 1;
			}
		} else if (absDiffX < absDiffY) {
			// Move vertical! v / ^
			if (diffY < 0) {
				this.y += 1;
			} else {
				this.y -= 1;
			}
		} else {
			// Move diagonal! / or \
			if (diffX < 0) {
				this.x += 1;
			} else {
				this.x -= 1;
			}
			if (diffY < 0) {
				this.y += 1;
			} else {
				this.y -= 1;
			}
		}
	}
}
