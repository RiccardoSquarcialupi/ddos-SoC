package devices.sensors

import akka.actor.ActorPath

class BasicSensor[A](destinationAddress: ActorPath, status: A) extends Sensor[A,A](destinationAddress,status):
  override def processingFunction: A => A = x => x