package com.examples.base.actor

import akka.actor.AbstractActor

abstract class WorkerActor<T> : AbstractActor() {

    abstract fun onBatchReceived(batch: T)

}
