class GameObject {
	private int x;
	private int y;
	protected GameObject(int x, int y){
		this.x = x;
		this.y = y;
	}
	public int getX() {
		return x;
	}
	void setX(int x) {
		this.x = x;
	}
	int getY() {
		return y;
	}
	void setY(int y) {
		this.y = y;
	}
}
