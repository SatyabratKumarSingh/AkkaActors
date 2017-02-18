package actors

import akka.actor.Actor
import stockorders.{IncomingOrder, Order, OrderOperation}

import scala.collection.mutable.ListBuffer

/**
  * Created by Satya on 16/02/2017.
  */
class OrderOperationActor(orders: ListBuffer[Order]) extends Actor{
   override def receive = {
    case incomingOrder: IncomingOrder => {
       if(incomingOrder.orderOperation == OrderOperation.Register){
          println("Registering a new order in the list ")
          orders += incomingOrder.order
          sender ! orders
       }
      else{
         println("Un-registering a new order in the list ")
         orders -= incomingOrder.order
         sender ! orders
       }
    }
  }
}
