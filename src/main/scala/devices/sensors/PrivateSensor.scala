package devices.sensors

import akka.actor.ActorPath
import devices.resources.SendType.NormalSendStatus

trait PrivateSensor[A,B](destinationAddress: ActorPath,status: A) extends Sensor[A,B]:
  override def sendMessage(msg: NormalSendStatus): Unit =
    if(msg.getOwnerPath.equals(context.self.path))
      context.actorSelection(destinationAddress) ! status
    else
      println("owner is not equal to RemoteReference of this Actor")
