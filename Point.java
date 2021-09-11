import java.util.Random;

public final class Point {
  public Point(double x, double y, double z) {
    x_ = x;
    y_ = y;
    z_ = z;
  }

  public static Point random(Random gen) {
    return new Point(gen.nextDouble(), gen.nextDouble(), gen.nextDouble());
  }

  public double x() {
    return x_;
  }

  public double y() {
    return y_;
  }

  public double z() {
    return z_;
  }

  private double x_, y_, z_;
}
