package com.vasconsolutions.base.actor

import akka.actor.AbstractActor

abstract class WorkerActor<T> : AbstractActor() {

    abstract fun onBatchReceived(batch: T)

}
