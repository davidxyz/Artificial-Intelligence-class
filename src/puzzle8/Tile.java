package puzzle8;

public class Tile {
	int vertical = -1;
	int horizontal = -1;

	public void setCoords(int vert, int hor) {
		this.vertical = vert;
		this.horizontal = hor;
	}

	Tile(int vert, int hor) {
		this.vertical = vert;
		this.horizontal = hor;
	}

	public Tile() {
		// TODO Auto-generated constructor stub
	}

	public Tile clone() {
		return new Tile(this.vertical, this.horizontal);
	}

}
