package orderboard;

import model.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeMap;

public class BookPrinterSystemOut implements BookPrinter {

  private static final Logger logger = LoggerFactory.getLogger(BookPrinter.class);

  @Override
  public void print(TreeMap<Double, HashSet<Order>> book) {
    if (book.isEmpty()) {
      logger.info("Book is empty..");
      return;
    }
    final Set<Double> prices = book.keySet();
    for (Double price : prices) {
      final double levelQty =
          book.get(price).stream()
              .mapToDouble(Order::getQuantity)
              .sum();
      final HashSet<Order> orders = book.get(price);

      StringBuilder sb=new StringBuilder();
      sb.append(levelQty + " KG for £" + price + " // ");
      for (Order order : orders) {
        sb.append(order);
      }
      logger.info(sb.toString());
    }
  }
}
