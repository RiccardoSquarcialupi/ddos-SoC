package devices.resources

import akka.actor.ActorPath

object SendType:
  class NormalSendStatus(ownerPath: ActorPath):
    def getOwnerPath: ActorPath = this.ownerPath
  class SelfSend
