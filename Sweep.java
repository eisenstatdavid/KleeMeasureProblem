import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public final class Sweep<P> {
  public double measure(Collection<? extends IntervalTimes<? extends P>> elements,
      OnlineMeasure<? super P> onlineMeasure) {
    Collection<P> projections = new ArrayList<P>();
    List<Event<P>> events = new ArrayList<Event<P>>();
    for (IntervalTimes<? extends P> element : elements) {
      P projection = element.project();
      projections.add(projection);
      events.add(new Event<P>(element.x().min(), Event.Type.ADD, projection));
      events.add(new Event<P>(element.x().max(), Event.Type.REMOVE, projection));
    }
    onlineMeasure.initialize(projections);
    Collections.sort(events);
    double sum = 0;
    double previous_x = Double.NaN;
    for (Event<P> event : events) {
      if (!Double.isNaN(previous_x)) {
        sum += (event.x() - previous_x) * onlineMeasure.measure();
      }
      previous_x = event.x();
      switch (event.type()) {
        case ADD:
          onlineMeasure.add(event.element());
          break;
        case REMOVE:
          onlineMeasure.remove(event.element());
          break;
      }
    }
    return sum;
  }
}
