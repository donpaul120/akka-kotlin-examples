package base.actor

import akka.actor.AbstractActor
import akka.actor.Props

abstract class WorkerActor<T> : AbstractActor() {

    abstract fun onBatchReceived(batch: T)

}
