import java.util.Random;

public final class Cuboid implements IntervalTimes<Rectangle> {
  public Cuboid(Interval x, Interval y, Interval z) {
    x_ = x;
    y_ = y;
    z_ = z;
  }

  public static Cuboid random(Random gen) {
    return new Cuboid(Interval.random(gen), Interval.random(gen), Interval.random(gen));
  }

  public boolean contains(Point point) {
    return x_.contains(point.x()) && y_.contains(point.y()) && z_.contains(point.z());
  }

  public Interval x() {
    return x_;
  }

  public Rectangle project() {
    return new Rectangle(y_, z_);
  }

  private Interval x_, y_, z_;
}
