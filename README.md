### How to use
Clone the repository

```
cd silverbars/
mvn clean install 
```

Add tech-challenge-1.0-SNAPSHOT.jar to your class path

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
### Break down into end to end tests

Explain what these tests test and why

```
Give an example
```

### And coding style tests

Explain what these tests test and why

```
Give an example
```

## Deployment

Add additional notes about how to deploy this on a live system

## Built With

* [Dropwizard](http://www.dropwizard.io/1.0.2/docs/) - The web framework used
* [Maven](https://maven.apache.org/) - Dependency Management
* [ROME](https://rometools.github.io/rome/) - Used to generate RSS Feeds

## Contributing

Please read [CONTRIBUTING.md](https://gist.github.com/PurpleBooth/b24679402957c63ec426) for details on our code of conduct, and the process for submitting pull requests to us.

## Versioning

We use [SemVer](http://semver.org/) for versioning. For the versions available, see the [tags on this repository](https://github.com/your/project/tags). 

## Authors

* **Billie Thompson** - *Initial work* - [PurpleBooth](https://github.com/PurpleBooth)

See also the list of [contributors](https://github.com/your/project/contributors) who participated in this project.

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

## Acknowledgments

* Hat tip to anyone whose code was used
* Inspiration
* etc
