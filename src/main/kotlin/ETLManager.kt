import akka.actor.AbstractActor
import akka.actor.ActorRef
import akka.actor.Props
import groups.GroupManager

class ETLManager : AbstractActor() {

    //contains all managers
    private lateinit var groupManager:ActorRef

    override fun createReceive(): Receive {
        return receiveBuilder()
                .match(String::class.java){

                }
                .build()
    }

    override fun preStart() {
        this.groupManager = context.actorOf(GroupManager.props())
        super.preStart()
    }

    companion object {
        @JvmStatic
        fun props(): Props {
            return Props.create(ETLManager::class.java)
        }
    }
}