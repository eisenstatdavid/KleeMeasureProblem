public final class Rectangle implements IntervalTimes<Interval> {
  public Rectangle(Interval x, Interval y) {
    x_ = x;
    y_ = y;
  }

  public Interval x() {
    return x_;
  }

  public Interval project() {
    return y_;
  }

  private Interval x_, y_;
}
