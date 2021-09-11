public final class Segment {
  public Segment(Interval interval) {
    interval_ = interval;
  }

  public Segment(Segment leftChild, Segment rightChild) {
    interval_ = leftChild.interval_.span(rightChild.interval_);
    leftChild_ = leftChild;
    rightChild_ = rightChild;
    updateChildrenLength();
  }

  public void add(Interval interval) {
    add(interval, 1);
  }

  public void remove(Interval interval) {
    add(interval, -1);
  }

  private void add(Interval interval, int count) {
    if (interval.contains(interval_)) {
      timesCovered_ += count;
    } else if (interval.intersects(interval_)) {
      leftChild_.add(interval, count);
      rightChild_.add(interval, count);
      updateChildrenLength();
    }
  }

  public double length() {
    return timesCovered_ > 0 ? interval_.length() : childrenLength_;
  }

  private void updateChildrenLength() {
    childrenLength_ = leftChild_.length() + rightChild_.length();
  }

  private Interval interval_;
  private Segment leftChild_;
  private Segment rightChild_;
  private int timesCovered_;
  private double childrenLength_;
}
