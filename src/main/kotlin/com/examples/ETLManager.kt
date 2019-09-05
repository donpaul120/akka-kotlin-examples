package com.examples

import akka.actor.AbstractActor
import akka.actor.ActorRef
import akka.actor.Props
import com.examples.j.assets.AssetManager
import com.examples.customers.CustomerManager
import com.examples.groups.GroupManager

class ETLManager : AbstractActor() {

    //contains all managers
    private lateinit var groupManager: ActorRef
    private lateinit var customerManager: ActorRef
    private lateinit var assetManager: ActorRef

    override fun createReceive(): Receive {
        return receiveBuilder()
                .match(String::class.java) {

                }
                .build()
    }

    override fun preStart() {
        this.groupManager = context.actorOf(GroupManager.props())
        this.customerManager = context.actorOf(CustomerManager.props())
        this.assetManager = context.actorOf(AssetManager.props())
        super.preStart()
    }

    companion object {
        @JvmStatic
        fun props(): Props {
            return Props.create(ETLManager::class.java)
        }
    }
}