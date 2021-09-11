import java.util.Random;

public final class Interval implements IntervalTimes<Unit> {
  public Interval(double a, double b) {
    min_ = Math.min(a, b);
    max_ = Math.max(a, b);
  }

  public static Interval random(Random gen) {
    return new Interval(gen.nextDouble(), gen.nextDouble());
  }

  public double min() {
    return min_;
  }

  public double max() {
    return max_;
  }

  public boolean contains(double x) {
    return min() < x && x < max();
  }

  public boolean contains(Interval that) {
    return min() <= that.min() && that.max() <= max();
  }

  public boolean intersects(Interval that) {
    return Math.max(min(), that.min()) < Math.min(max(), that.max());
  }

  public double length() {
    return max() - min();
  }

  public Interval span(Interval that) {
    return new Interval(Math.min(min(), that.min()), Math.max(max(), that.max()));
  }

  public Interval x() {
    return this;
  }

  public Unit project() {
    return new Unit();
  }

  private double min_, max_;
}
