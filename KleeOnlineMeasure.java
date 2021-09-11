import java.util.Collection;

public final class KleeOnlineMeasure implements OnlineMeasure<Unit> {
  public void initialize(Collection<? extends Unit> universe) {
    timesCovered_ = 0;
  }

  public void add(Unit element) {
    timesCovered_++;
  }

  public void remove(Unit element) {
    timesCovered_--;
  }

  public double measure() {
    return timesCovered_ > 0 ? 1 : 0;
  }

  private int timesCovered_;
}
