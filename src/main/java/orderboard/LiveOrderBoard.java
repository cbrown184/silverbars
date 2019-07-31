package orderboard;

import model.Order;

public interface LiveOrderBoard {
  void registerOrder(Order order) ;
  void cancelOrder(Order order);
  void getSummary();
}
