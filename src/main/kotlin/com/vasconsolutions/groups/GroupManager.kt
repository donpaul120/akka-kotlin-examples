package com.vasconsolutions.groups

import akka.actor.ActorRef
import akka.actor.Props
import akka.event.Logging
import com.vasconsolutions.base.actor.ETLSupervisor
import com.vasconsolutions.base.model.GroupKey

/**
 * The groupManager will load com.vasconsolutions.groups from CIS
 */
class GroupManager : ETLSupervisor() {
    private val log = Logging.getLogger(this)

    private val groups: HashMap<String, GroupKey> = hashMapOf()

    //ActorRefs
    private lateinit var groupWorker: ActorRef

    override fun createReceive(): Receive {
        return receiveBuilder()
                .match(String::class.java) {
                    when (it) {
                        "load" -> onLoad()
                    }
                }
                .match(GetGroups::class.java) { sender.tell(onGetGroups(it), self) }
                .match(InsertedGroups::class.java) { onGroupsInserted(it) }
                .build()
    }


    override fun preStart() {
        this.groupWorker = context.actorOf(GroupWorker.props())
        super.preStart()
    }


    override fun onLoad() {
        /*
         * Load com.vasconsolutions.groups from CIS and distribute to GroupWorkers
         * Each GroupWorker should know how it connects to the
         * destination
         */
        this.groupWorker.tell(GroupWorker.InsertGroups.InsertGroupsCRM("11-22", listOf()), self)
        this.groupWorker.tell(GroupWorker.InsertGroups.InsertGroupsIForce("11-22", listOf()), self)
    }

    /**
     * Triggered when com.vasconsolutions.groups are inserted
     */
    private fun onGroupsInserted(insertedGroups: InsertedGroups) {
        //process the com.vasconsolutions.groups inserted and save
        log.info(insertedGroups.toString())
    }

    /**
     * Triggered when GetGroups message is received
     */
    private fun onGetGroups(query: GetGroups): List<GroupKey> {
        return listOf()
    }


    companion object {
        @JvmStatic
        fun props(): Props {
            return Props.create(GroupManager::class.java)
        }
    }

    data class GetGroups(val type: String)
    data class InsertedGroups(val batchId: String, val groupKeys: List<GroupKey>)

}