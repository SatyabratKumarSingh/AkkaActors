package app
import scala.concurrent.duration._
import akka.pattern.ask
import actors.OrderOperationActor
import akka.actor.{ActorSystem, Props}
import akka.util.Timeout
import stockorders.{IncomingOrder, Order, OrderOperation, OrderType}

import scala.collection.mutable.ListBuffer
import scala.concurrent.{Await, Future}

/**
  * Created by Satya on 16/02/2017.
  */
object ActorApp extends App{
    var orderList =new ListBuffer[Order]

    val system = ActorSystem("HelloSystem")
    // default Actor constructor
    val orderActor = system.actorOf(Props(new OrderOperationActor()), name = "orderActor")

    val incomingOrder = new IncomingOrder(new Order("SATYA",12,300.20,OrderType.Buy),OrderOperation.Register)
    orderActor ! incomingOrder

        val incomingOrder1 = new IncomingOrder(new Order("MUNISH",15,400.20,OrderType.Buy),OrderOperation.Register)
    orderActor ! incomingOrder1
    val incomingOrder2 = new IncomingOrder(new Order("SAMEER",18,500.20,OrderType.Buy),OrderOperation.Register)
    orderActor ! incomingOrder2

   implicit val timeout = Timeout(5 seconds)
    val future = orderActor ? incomingOrder
    val result = Await.result(future, timeout.duration).asInstanceOf[List[Order]]
    println(result)

    val incomingOrder3 = new IncomingOrder(new Order("SAMEER",18,500.20,OrderType.Buy),OrderOperation.UnRegister)
    //orderActor ! incomingOrder3

    val future1 = orderActor ? incomingOrder3
    val result1 = Await.result(future1, timeout.duration).asInstanceOf[List[Order]]
    println("Unregistered list " + result1)

}
