package devices.sensors

import akka.actor.ActorPath

class ProcessDataSensor[A,B](destinationAddress: ActorPath,status: A, processFun: B => A) extends Sensor[A,B](destinationAddress,status):
  override def processingFunction: B => A = processFun
