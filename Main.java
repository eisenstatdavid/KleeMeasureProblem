import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

public final class Main {
  public static void main(String[] args) {
    Random gen = new Random();
    Collection<Cuboid> cuboids = new ArrayList<Cuboid>();
    for (int i = 0; i < 10; i++) {
      cuboids.add(Cuboid.random(gen));
    }

    System.out.println(new Sweep<Rectangle>().measure(cuboids,
        new SweepOnlineMeasure<Interval>(new SweepOnlineMeasure<Unit>(new KleeOnlineMeasure()))));

    System.out.println(new Sweep<Rectangle>().measure(
        cuboids, new SweepOnlineMeasure<Interval>(new BentleyOnlineMeasure())));

    double hits = 0;
    int samples = 1000000;
    for (int i = 0; i < samples; i++) {
      Point point = Point.random(gen);
      for (Cuboid cuboid : cuboids) {
        if (cuboid.contains(point)) {
          hits++;
          break;
        }
      }
    }
    System.out.println(hits / samples);
  }
}
