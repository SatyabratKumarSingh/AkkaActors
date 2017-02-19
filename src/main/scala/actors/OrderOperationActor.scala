package actors

import akka.actor.Actor
import stockorders.{IncomingOrder, Order, OrderOperation}

/**
  * Created by Satya on 16/02/2017.
  */
class OrderOperationActor extends Actor {
  var orders = List.empty[Order]

  override def receive = {
    case incomingOrder: IncomingOrder => {
      if (incomingOrder.orderOperation == OrderOperation.Register) {
        println("Registering a new order in the list ")
        orders = orders :+ incomingOrder.order
        sender ! orders
      }
      else {
        println("Un-registering a new order in the list ")
        orders =  orders.filter(x=>x.userId != incomingOrder.order.userId)
        sender ! orders
      }
    }
  }
}
