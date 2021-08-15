public class NBody {

	public static void main(String[] args) {
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];
		double radius = readRadius(filename);
		Planet[] planets = readPlanets(filename);
		int pNum = planets.length;

		StdDraw.setScale(-radius, radius);
		StdDraw.clear();
		StdDraw.picture(0, 0, "images/starfield.jpg");
		for (int i = 0; i < pNum; i++) {
			planets[i].draw();
		}
		StdDraw.show();

		StdDraw.enableDoubleBuffering();
		double t = 0;
		while (t < T) {
			double[] xForces = new double[planets.length];
			double[] yForces = new double[planets.length];
			for (int i = 0; i < pNum; i++) {
				xForces[i] = planets[i].calcNetForceExertedByX(planets);
				yForces[i] = planets[i].calcNetForceExertedByY(planets);
			}
			for (int i = 0; i < pNum; i++) {
				planets[i].update(dt, xForces[i], yForces[i]);
			}
			StdDraw.clear();
			StdDraw.picture(0, 0, "images/starfield.jpg");
			for (int i = 0; i < pNum; i++) {
				planets[i].draw();
			}
			StdDraw.show();
			StdDraw.pause(10);
			t += dt;
		}

		StdOut.printf("%d\n", pNum);
		StdOut.printf("%.2e\n", radius);
		for (int i = 0; i < planets.length; i++) {
			StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n", 
						  planets[i].xxPos, planets[i].yyPos, planets[i].xxVel, 
						  planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
		}
	}


	public static double readRadius(String filePath) {
		In in = new In(filePath);
		int planetNum = in.readInt();
		double radius = in.readDouble();
		return radius;
	}

	public static Planet[] readPlanets(String filePath) {
		In in = new In(filePath);
		int planetNum = in.readInt();
		Planet[] planets = new Planet[planetNum];
		double radius = in.readDouble();
		for (int i = 0; i < planetNum; i++) {
			double xxPos = in.readDouble();
			double yyPos = in.readDouble();
			double xxVel = in.readDouble();
			double yyVel = in.readDouble();
			double mass  = in.readDouble();
			String imgFilePath = in.readString();
			planets[i] = new Planet(xxPos, yyPos, xxVel,
									yyVel, mass, imgFilePath);
		}
		return planets;
	}
}	