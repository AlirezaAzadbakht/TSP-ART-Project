package localSearch;

import MST.needed_method;

public class Local_search {

	private void reverse(Point[] x, int from, int to) {
		for (int i = from, j = to; i < j; i++, j--) {
			Point tmp = x[i];
			x[i] = x[j];
			x[j] = tmp;
		}
	}

	private int wrap(int i, int max) {
		return (max + i) % max;
	}

	private double moveCost(Point a, Point b, Point c, Point d) {

		double ab = a.distanceWithoutSqrt(b);
		double cd = c.distanceWithoutSqrt(d);

		double ac = a.distanceWithoutSqrt(c);
		double bd = b.distanceWithoutSqrt(d);

		if (ab < ac && cd < bd)
			return 1;
		

		return (needed_method.sqrt(ac) + needed_method.sqrt(bd)) - (needed_method.sqrt(ab) + needed_method.sqrt(cd));
	}

	private void activate(Point a, Point b, Point c, Point d) {
		a.setActive(true);
		b.setActive(true);
		c.setActive(true);
		d.setActive(true);
	}

	// {0,1,2,3,4,5,6,7,8,9}, current = 4
	// (6,7) (7,8) (8,9) (9,0) (0,1) (1,2) (2,3)
	private boolean findMove(int current, Point currentPoint, Point[] points, int numCities) {

		int prev = wrap(current - 1, numCities);
		int next = wrap(current + 1, numCities);
		Point prevPoint = points[prev];
		Point nextPoint = points[next];

		for (int i = wrap(current + 2, numCities), j = wrap(current + 3, numCities); j != current; i = j, j = wrap(
				j + 1, numCities)) {

			Point c = points[i];
			Point d = points[j];

			double delta1 = moveCost(prevPoint, currentPoint, c, d);
			if (delta1 < 0) {
				activate(prevPoint, currentPoint, c, d);
				reverse(points, Math.min(prev, i) + 1, Math.max(prev, i));
				return true;
			}

			double delta2 = moveCost(currentPoint, nextPoint, c, d);
			if (delta2 < 0) {
				activate(currentPoint, nextPoint, c, d);
				reverse(points, Math.min(current, i) + 1, Math.max(current, i));
				return true;
			}

		}
		return false;
	}

	public void optimise(Point[] points) {

		int numCities = points.length;

		int visited = 0;
		int current = 0;

		int c = 0;
		while (visited < numCities) {

			if (((float) (visited) / (float) numCities * 100) >= c) {
				System.out.println(">>> " + c + "%");
				c = c + 10;
			}

			Point currentPoint = points[current];
			if (currentPoint.Status()) {

				boolean check = findMove(current, currentPoint, points, numCities);

				if (check) {
					current = wrap(current - 1, numCities);
					visited = 0;
					continue;
				}
				currentPoint.setActive(false);
			}

			current = wrap(current + 1, numCities);
			visited++;
		}
		System.out.println(">>> " + c + "%");
	}

}
