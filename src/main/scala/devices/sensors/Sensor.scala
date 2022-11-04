package devices.sensors

import akka.actor.{Actor, ActorPath}
import devices.resources.SendType.NormalSendStatus

trait Sensor[A,B](destinationAddress: ActorPath, status: A) extends Actor:
  def processingFunction: B => A
  var internalStatus: A = status
  def setStatus(phyInput:B): Unit = this.internalStatus = processingFunction(phyInput)

  def sendMessage(msg: NormalSendStatus) : Unit =
    println("I'm "+context.self.path+ " sending msg to:" + destinationAddress)
    context.actorSelection(destinationAddress) ! status

  def receive: Receive =
    case x: NormalSendStatus => sendMessage(x)
    case x: String =>
    println("I'm the first children, Evangelion Reference ")
    case _ =>println("I'm " +context.self.path +" but I received unknown msg ")
