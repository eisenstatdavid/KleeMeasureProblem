import java.util.Collection;

public interface OnlineMeasure<E> {
  void initialize(Collection<? extends E> universe);
  void add(E element);
  void remove(E element);
  double measure();
}
