import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

public final class BentleyOnlineMeasure implements OnlineMeasure<Interval> {
  public void initialize(Collection<? extends Interval> universe) {
    if (universe.isEmpty()) {
      return;
    }
    SortedSet<Double> bounds = new TreeSet<Double>();
    for (Interval element : universe) {
      bounds.add(element.min());
      bounds.add(element.max());
    }
    List<Segment> segments = new ArrayList<Segment>();
    double min = Double.NaN;
    for (double max : bounds) {
      if (!Double.isNaN(min)) {
        segments.add(new Segment(new Interval(min, max)));
      }
      min = max;
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
    root_ = segments.get(0);
  }

  public void add(Interval element) {
    root_.add(element);
  }

  public void remove(Interval element) {
    root_.remove(element);
  }

  public double measure() {
    return root_ != null ? root_.length() : 0;
  }

  private Segment root_;
}
