import java.util.ArrayList;
import java.util.Collection;
import java.util.SortedSet;
import java.util.TreeSet;

public final class BentleyOnlineMeasure implements OnlineMeasure<Interval> {
  public void initialize(Collection<? extends Interval> universe) {
    SortedSet<Double> bounds = new TreeSet<Double>();
    for (Interval element : universe) {
      bounds.add(element.min());
      bounds.add(element.max());
    }
    root_ = Segment.newTree(bounds);
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
