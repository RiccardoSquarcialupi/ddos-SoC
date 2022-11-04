package devices.sensors

import akka.actor.ActorPath

trait ConditionSensor[A,B](destinationAddress:ActorPath, status: A, condition: Boolean) extends Sensor[A,B]:
  override def setStatus(phyInput: B): Unit =
    this.internalStatus = processingFunction(phyInput)
    if(condition)
      context.actorSelection(destinationAddress) ! status
