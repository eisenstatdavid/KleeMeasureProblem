import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;

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

  public static Segment newTree(SortedSet<Double> bounds) {
    List<Segment> segments = new ArrayList<Segment>();
    double min = Double.NaN;
    for (double max : bounds) {
      if (!Double.isNaN(min)) {
        segments.add(new Segment(new Interval(min, max)));
      }
      min = max;
    }
    if (segments.isEmpty()) {
      return null;
    }
    while (segments.size() > 1) {
      List<Segment> parentSegments = new ArrayList<Segment>();
      for (int i = 1; i < segments.size(); i += 2) {
        parentSegments.add(new Segment(segments.get(i - 1), segments.get(i)));
      }
      if (segments.size() % 2 == 1) {
        parentSegments.add(segments.get(segments.size() - 1));
      }
      segments = parentSegments;
    }
    return segments.get(0);
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
