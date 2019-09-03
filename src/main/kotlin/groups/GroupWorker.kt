package groups

import akka.actor.Props
import akka.event.Logging
import base.actor.WorkerActor

class GroupWorker : WorkerActor<GroupWorker.InsertGroups>() {
    private val log = Logging.getLogger(this)

    override fun createReceive(): Receive {
        return receiveBuilder().match(InsertGroups::class.java) { onBatchReceived(it) }.build()
    }

    override fun onBatchReceived(batch: InsertGroups) {
        when (batch) {
            is InsertGroups.InsertGroupsCRM -> insertGroupsIntoCRM(batch.groups)
            is InsertGroups.InsertGroupsIForce -> insertGroupsIntoIForce(batch.groups)
        }
    }

    private fun insertGroupsIntoCRM(groups: List<Group>) {
        sender.tell(GroupManager.InsertedGroups("", listOf()), self)
    }

    private fun insertGroupsIntoIForce(groups: List<Group>) {
        sender.tell(GroupManager.InsertedGroups("", listOf()), self)
    }

    //Messages
    sealed class InsertGroups {
        data class InsertGroupsCRM(val batchId: String, val groups: List<Group>) : InsertGroups()
        data class InsertGroupsIForce(val batchId: String, val groups: List<Group>) : InsertGroups()
    }

    companion object {
        @JvmStatic
        fun props(): Props {
            return Props.create(GroupWorker::class.java)
        }
    }

}