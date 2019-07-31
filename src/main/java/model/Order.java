package model;


import java.util.Objects;

public class Order {

  private final String userId;
  private final double quantity;
  private final double price;
  private final OrdType ordType;
  private final long id;
  private final int hashCode;

  Order(String userId, double quantity, double price, OrdType ordType, long id) {
    this.userId = userId;
    this.quantity = quantity;
    this.price = price;
    this.ordType = ordType;
    this.id = id;
    this.hashCode = Objects.hash(userId, quantity, price, ordType, id);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Order order = (Order) o;
    return Double.compare(order.quantity, quantity) == 0 &&
        Double.compare(order.price, price) == 0 &&
        id == order.id &&
        Objects.equals(userId, order.userId) &&
        ordType == order.ordType;
  }

  @Override
  public int hashCode() {
    return hashCode;
  }

  public double getPrice() {
    return price;
  }

  public double getQuantity() {
    return quantity;
  }

  public OrdType getOrdType() {
    return ordType;
  }

  @Override
  public String toString() {
    return "Order{" +
        "userId='" + userId + '\'' +
        ", quantity=" + quantity +
        ", price=" + price +
        ", ordType=" + ordType +
        ", id=" + id +
        '}';
  }

}
