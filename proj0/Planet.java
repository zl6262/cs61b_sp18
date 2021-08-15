public class Planet {
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;

	private static final double G = 6.67e-11;

	public Planet(double xP, double yP, double xV, 
				  double yV, double m, String img) {
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}

	public Planet(Planet p) {
		// xxPos = p.xxPos;
		// yyPos = p.yyPos;
		// xxVel = p.xxVel;
		// yyVel = p.yyVel;
		// mass = p.mass;
		// imgFileName = p.imgFileName;
		this(p.xxPos, p.yyPos, p.xxVel, p.yyVel, p.mass, p.imgFileName);
	}

	public double calcDistance(Planet p) {
		double xDis = xxPos - p.xxPos;
		double yDis = yyPos - p.yyPos;
		double dis = Math.sqrt(xDis * xDis + yDis * yDis);
		return dis;
	}

	public double calcForceExertedBy(Planet p) {
		double dis = this.calcDistance(p);
		double force = G * mass * p.mass / (dis * dis);
		return force;
	}

	public double calcForceExertedByX(Planet p) {
		double force = this.calcForceExertedBy(p);
		double r = this.calcDistance(p);
		double fX= force * (p.xxPos - xxPos) / r;
		return fX;
	}

	public double calcForceExertedByY(Planet p) {
		double force = this.calcForceExertedBy(p);
		double r = this.calcDistance(p);
		double fY = force * (p.yyPos - yyPos) / r;
		return fY;
	}
	
	public double calcNetForceExertedByX(Planet[] allPlanets) {
		double fX = 0;
		for (Planet p : allPlanets) {
			if (this.equals(p)) {
				continue;
			} else {
				fX += this.calcForceExertedByX(p);
			}
		}
		return fX;
	}

	public double calcNetForceExertedByY(Planet[] allPlanets) {
		double fY = 0;
		for (Planet p : allPlanets) {
			if (this.equals(p)) {
				continue;
			} else {
				fY += this.calcForceExertedByY(p);
			}
		}
		return fY;
	}
	
	public void update(double dt, double fX, double fY) {
		double aX = fX / mass;
		double aY = fY / mass;
		xxVel += dt * aX;
		yyVel += dt * aY;
		xxPos += dt * xxVel;
		yyPos += dt * yyVel;
	}

	public void draw() {
		StdDraw.picture(xxPos, yyPos, "images/" + imgFileName);
	}
}