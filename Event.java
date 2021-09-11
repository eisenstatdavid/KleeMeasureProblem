public final class Event<E> implements Comparable<Event<E>> {
  public enum Type { ADD, REMOVE }

  public Event(double x, Type type, E element) {
    x_ = x;
    type_ = type;
    element_ = element;
  }

  public double x() {
    return x_;
  }

  public Type type() {
    return type_;
  }

  public E element() {
    return element_;
  }

  public int compareTo(Event<E> that) {
    return Double.compare(x(), that.x());
  }

  private double x_;
  private Type type_;
  private E element_;
}
