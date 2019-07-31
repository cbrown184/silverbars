### How to use
Clone the repository

```
cd silverbars/
mvn clean install 
```

Add silverbars/target/tech-challenge-1.0-SNAPSHOT.jar to your class path.

Import the following classes
```
import model.Order;
import model.OrderFactory;
import orderboard.LiveOrderBoardService;
```

LiveOrderBoardService represents a BUY and SELL book. It is thread safe. You cannot register the same order twice. All orders should be created via OrderFactory.
```
### Submit buy order and print
LiveOrderBoardService liveOrderBoardService = new LiveOrderBoardService();
OrderFactory orderFactory = OrderFactory.INSTANCE; 
Order buyOrder = orderFactory.createNewBuyOrder("user", 1.2, 1.5);
liveOrderBoardService.registerOrder(buyOrder);
liveOrderBoardService.getSummary();
 
    ### Output
    13:45:17.706 [main] INFO orderboard.LiveOrderBoardImpl - Printing book BUY
    1.2 KG for Â£1.5 // model.Order@e7c225f5
    13:45:17.719 [main] INFO orderboard.LiveOrderBoardImpl - Printing book SELL
    13:45:17.719 [main] INFO orderboard.LiveOrderBoardImpl - Book is empty..

### Cancel buy order and print
liveOrderBoardService.cancelOrder(buyOrder);
liveOrderBoardService.getSummary();

    ### Output
    14:06:51.507 [main] INFO orderboard.LiveOrderBoardImpl - Printing book BUY
    14:06:51.507 [main] INFO orderboard.LiveOrderBoardImpl - Book is empty..
    14:06:51.507 [main] INFO orderboard.LiveOrderBoardImpl - Printing book SELL
    14:06:51.507 [main] INFO orderboard.LiveOrderBoardImpl - Book is empty..
```

Buys are ordered by descending price starting with the highest. Sells are ordered in ascending price starting with the lowest.
```
Order buyOrder2 = orderFactory.createNewBuyOrder("user", 1.2, 1.6);
Order buyOrder3 = orderFactory.createNewBuyOrder("user", 1.2, 1.7);
liveOrderBoardService.registerOrder(buyOrder2);
liveOrderBoardService.registerOrder(buyOrder3);
liveOrderBoardService.getSummary();
    
    ### Output
    13:45:17.719 [main] INFO orderboard.LiveOrderBoardImpl - Book is empty..
    13:45:17.719 [main] INFO orderboard.LiveOrderBoardImpl - Printing book BUY
    1.2 KG for Â£1.7 // model.Order@c09225f7
    1.2 KG for Â£1.6 // model.Order@542a3139
    1.2 KG for Â£1.5 // model.Order@e7c225f5

Order sellOrder = orderFactory.createNewSellOrder("user", 1.2, 1.5);
Order sellOrder2 = orderFactory.createNewOrder("user", 1.6, 3.2, OrdType.SELL);
liveOrderBoardService.registerOrder(sellOrder);
liveOrderBoardService.registerOrder(sellOrder2);
liveOrderBoardService.getSummary();
    
    ### Output
    13:45:17.720 [main] INFO orderboard.LiveOrderBoardImpl - Printing book SELL
    1.2 KG for Â£1.5 // model.Order@7e17d95a
    1.6 KG for Â£3.2 // model.Order@941141bb

```
