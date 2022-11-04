package devices

import akka.actor.{Actor, ActorPath, ActorSystem, Props, Timers}
import com.typesafe.config.ConfigFactory
import devices.resources.SendType.{NormalSendStatus, SelfSend}
import devices.sensors.{BasicSensor, TimedSensor}
import main.getClass

import java.io.File
import scala.concurrent.duration
import scala.concurrent.duration.{FiniteDuration, SECONDS}

class UserDefinedSensor[A](destinationAddress: ActorPath, status: A, duration:FiniteDuration) extends BasicSensor[A](destinationAddress,status), TimedSensor[A,A](destinationAddress,status,duration)


object main extends App:
  val configFile = getClass.getClassLoader.getResource("application.conf").getFile
  val config = ConfigFactory.parseFile(new File(configFile))
  val system = ActorSystem("RemoteSystem", config)
  //TODO need to create a UserDefinedSensor
  val recevingSensor = system.actorOf(Props[BasicSensor[Int]](new BasicSensor[Int](ActorPath.fromString("akka://RemoteSystem/user/remote3"),0)), "remote1")
  val sensor1 = system.actorOf(Props[UserDefinedSensor[Int]](new UserDefinedSensor[Int](recevingSensor.path,0,new FiniteDuration(5,SECONDS))), "remote2")
  sensor1 ! "First child"
  println(sensor1)