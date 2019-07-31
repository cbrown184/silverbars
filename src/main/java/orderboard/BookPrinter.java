package orderboard;

import model.Order;

import java.util.HashSet;
import java.util.TreeMap;

public interface BookPrinter {
  void print(TreeMap<Double, HashSet<Order>> book);
}
