package model;

import java.util.concurrent.atomic.AtomicLong;

//Force order builder to be singleton
//ensures unique orderID per order
public enum OrderFactory {
  INSTANCE;

    private final AtomicLong count = new AtomicLong(0);
    public Order createNewOrder(String userId, double quantity, double price, OrdType ordType){
      return new Order(userId, quantity, price, ordType, count.incrementAndGet());
    }
    public Order createNewBuyOrder(String userId, double quantity, double price){
      return new Order(userId, quantity, price, OrdType.BUY, count.incrementAndGet());
    }
    public Order createNewSellOrder(String userId, double quantity, double price){
      return new Order(userId, quantity, price, OrdType.SELL, count.incrementAndGet());
    }

    public static OrderFactory getInstance(){
      return INSTANCE;
    }
}
