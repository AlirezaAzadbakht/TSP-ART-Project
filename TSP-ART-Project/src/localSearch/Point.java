package localSearch;

public final class Point {

	public double x;
	public double y;
	private boolean active = true;

	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public double distance(Point to) {
		return Math.sqrt(distanceWithoutSqrt(to));
	}

	public double distanceWithoutSqrt(Point to) {
		double dx = this.x - to.x;
		double dy = this.y - to.y;
		return (dx * dx) + (dy * dy);
	}

	public boolean Status() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

}
