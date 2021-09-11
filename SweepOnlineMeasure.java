import java.util.ArrayList;
import java.util.Collection;

public final class SweepOnlineMeasure<P> implements OnlineMeasure<IntervalTimes<? extends P>> {
  public SweepOnlineMeasure(OnlineMeasure<? super P> onlineMeasure) {
    onlineMeasure_ = onlineMeasure;
  }

  public void initialize(Collection<? extends IntervalTimes<? extends P>> elements) {
    elements_ = new ArrayList<IntervalTimes<? extends P>>();
  }

  public void add(IntervalTimes<? extends P> element) {
    elements_.add(element);
  }

  public void remove(IntervalTimes<? extends P> element) {
    elements_.remove(element);
  }

  public double measure() {
    return new Sweep<P>().measure(elements_, onlineMeasure_);
  }

  private Collection<IntervalTimes<? extends P>> elements_;
  private OnlineMeasure<? super P> onlineMeasure_;
}
