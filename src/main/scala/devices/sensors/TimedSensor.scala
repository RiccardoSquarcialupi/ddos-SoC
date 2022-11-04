package devices.sensors

import akka.actor.{ActorPath, Timers}
import devices.resources.SendType.{NormalSendStatus, SelfSend}

import scala.concurrent.duration.FiniteDuration

trait TimedSensor[A,B](destinationAddress: ActorPath,status: A, duration:FiniteDuration) extends Sensor[A,B], Timers:
  timers.startTimerAtFixedRate("1", new SelfSend, duration)
  
  override def receive: Receive =
    case x: SelfSend => sendMessage(new NormalSendStatus(destinationAddress))
    case _ => println("Received unknown msg -- Silently Ignored")
