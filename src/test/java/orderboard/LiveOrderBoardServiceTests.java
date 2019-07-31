package orderboard;

import model.OrdType;
import model.Order;
import model.OrderFactory;
import orderboard.LiveOrderBoard;
import orderboard.LiveOrderBoardService;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class LiveOrderBoardServiceTests {

  LiveOrderBoard liveOrderBoard = mock(LiveOrderBoard.class);


  @Test
  public void submitNewOrder(){

    OrderFactory orderFactory = OrderFactory.getInstance();
    LiveOrderBoardService liveOrderBoardService = new LiveOrderBoardService(liveOrderBoard);
    Order order = orderFactory.createNewOrder("user", 0.1, 1.5, OrdType.BUY);
    liveOrderBoardService.registerOrder(order);

    verify(liveOrderBoard, times(1)).registerOrder(order);
    verify(liveOrderBoard, never()).cancelOrder(order);
    verify(liveOrderBoard, never()).getSummary();

  }

  @Test
  public void submitNewOrderThenCancel(){
    OrderFactory orderFactory = OrderFactory.getInstance();
    LiveOrderBoardService liveOrderBoardService = new LiveOrderBoardService(liveOrderBoard);
    Order order = orderFactory.createNewOrder("user", 0.1, 1.5, OrdType.BUY);
    liveOrderBoardService.registerOrder(order);
    liveOrderBoardService.cancelOrder(order);

    verify(liveOrderBoard, times(1)).registerOrder(order);
    verify(liveOrderBoard, times(1)).cancelOrder(order);
    verify(liveOrderBoard, never()).getSummary();
  }

  @Test
  public void submitNewOrderAndPrint(){

    OrderFactory orderFactory = OrderFactory.getInstance();
    LiveOrderBoardService liveOrderBoardService = new LiveOrderBoardService(liveOrderBoard);
    Order order = orderFactory.createNewOrder("user", 0.1, 1.5, OrdType.BUY);
    liveOrderBoardService.registerOrder(order);
    liveOrderBoard.getSummary();

    verify(liveOrderBoard, times(1)).registerOrder(order);
    verify(liveOrderBoard, never()).cancelOrder(order);
    verify(liveOrderBoard, times(1)).getSummary();

  }

  @Test
  public void endToEnd(){
    OrderFactory orderFactory = OrderFactory.INSTANCE;
    LiveOrderBoardService liveOrderBoardService = new LiveOrderBoardService();

    Order buyOrder = orderFactory.createNewBuyOrder("user", 1.2, 1.5);
    liveOrderBoardService.registerOrder(buyOrder);
    liveOrderBoardService.getSummary();

    liveOrderBoardService.cancelOrder(buyOrder);
    liveOrderBoardService.getSummary();

    Order buyOrder2 = orderFactory.createNewBuyOrder("user", 1.2, 1.6);
    Order buyOrder3 = orderFactory.createNewBuyOrder("user", 1.2, 1.7);
    liveOrderBoardService.registerOrder(buyOrder2);
    liveOrderBoardService.registerOrder(buyOrder3);
    liveOrderBoardService.getSummary();

    Order sellOrder = orderFactory.createNewSellOrder("user", 1.2, 1.5);
    Order sellOrder2 = orderFactory.createNewOrder("user", 1.6, 3.2, OrdType.SELL);
    liveOrderBoardService.registerOrder(sellOrder);
    liveOrderBoardService.registerOrder(sellOrder2);
    liveOrderBoardService.getSummary();
  }
}
