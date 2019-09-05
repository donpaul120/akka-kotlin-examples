package com.examples.base.actor

import akka.actor.AbstractActor

/**
 * @require EntityManager
 */
abstract class ETLSupervisor : AbstractActor() {

    abstract fun onLoad()

}