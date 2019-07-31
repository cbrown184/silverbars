package orderboard;

import model.Order;

public class LiveOrderBoardService {
  private final BookPrinter bookPrinter;
  private final LiveOrderBoard liveOrderBoard;

  public LiveOrderBoardService() {
    this.bookPrinter = new BookPrinterSystemOut();
    this.liveOrderBoard = new LiveOrderBoardImpl(bookPrinter);
  }

  public LiveOrderBoardService(LiveOrderBoard liveOrderBoard){
    this.bookPrinter = new BookPrinterSystemOut();
    this.liveOrderBoard = liveOrderBoard;
  }

  public void registerOrder(Order order){
    liveOrderBoard.registerOrder(order);
  }

  public void cancelOrder(Order order){
    liveOrderBoard.cancelOrder(order);
  }

  public void getSummary(){
    liveOrderBoard.getSummary();
  }
}
