package orderboard;

import model.OrdType;
import model.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class LiveOrderBoardImpl implements LiveOrderBoard {

  private final Lock readLock;
  private final Lock writeLock;
  private final Map<OrdType, TreeMap<Double, HashSet<Order>>> bookPriceLevelMap;
  private final EnumSet<OrdType> ordTypes;
  private final BookPrinter bookPrinter;

  private static final Logger logger = LoggerFactory.getLogger(LiveOrderBoardImpl.class);
  LiveOrderBoardImpl(BookPrinter bookPrinter) {
    this.bookPrinter = bookPrinter;
    ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
    readLock = reentrantReadWriteLock.readLock();
    writeLock = reentrantReadWriteLock.writeLock();
    bookPriceLevelMap = new HashMap<>();
    ordTypes = EnumSet.of(OrdType.BUY, OrdType.SELL);
    bookPriceLevelMap.put(OrdType.BUY, new TreeMap<>(Comparator.reverseOrder()));
    bookPriceLevelMap.put(OrdType.SELL, new TreeMap<>(Comparator.naturalOrder()));
  }

  @Override
  public void registerOrder(Order order) {
    //We could implement a lock with auto-reclosable interface and use try-with-resources
    //But better to show lock logic for clarity
    final OrdType ordType = order.getOrdType();
    final double px = order.getPrice();

    writeLock.lock();
    try {
      final TreeMap<Double, HashSet<Order>> book = bookPriceLevelMap.get(ordType);
      if (!book.containsKey(px)) {
        book.put(px, new HashSet<>());
      }
      book.get(px).add(order);
    } finally {
      writeLock.unlock();
    }

  }

  @Override
  public void cancelOrder(Order order) {

    final OrdType ordType = order.getOrdType();
    final double px = order.getPrice();

    writeLock.lock();
    try {
      final TreeMap<Double, HashSet<Order>> book = bookPriceLevelMap.get(ordType);

      if (book.containsKey(px) && book.get(px).contains(order)) {
        book.get(px).remove(order);
        if (book.get(px).isEmpty()) {
          book.remove(px);
        }
      } else {
        logger.error("Order cancel failed - are the details correct?" + order);
      }
    } finally {
      writeLock.unlock();
    }
  }

  @Override
  public void getSummary() {
    readLock.lock();
    try {
      for (OrdType ordType : ordTypes) {
        logger.info("Printing book " + ordType.toString());
        bookPrinter.print(bookPriceLevelMap.get(ordType));
      }
    } finally {
      System.out.println();
      readLock.unlock();
    }
  }




}
