package stockorders

import stockorders.OrderOperation.OrderOperation
import stockorders.OrderType.OrderType

/**
  * Created by Satya on 17/02/2017.
  */
object OrderType extends Enumeration {
  type OrderType = Value
  val Buy,Sell = Value
}

object OrderOperation extends Enumeration {
  type OrderOperation = Value
  val Register,UnRegister = Value
}

case class Order(userId:String,orderQuantity: BigDecimal,price:BigDecimal,orderType: OrderType)

case class IncomingOrder(order: Order,orderOperation: OrderOperation)


