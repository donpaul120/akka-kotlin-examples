package customers

import akka.actor.Props
import base.actor.ETLSupervisor
import javax.persistence.EntityManager
import javax.persistence.Persistence

class CustomerManager : ETLSupervisor() {

    override fun onLoad() {
        val customerWorker = context.actorOf(CustomerWorker.props())
        customerWorker.tell(CustomerWorker.InsertCustomer.InsertCustomerIntoIForce("", listOf()), self)
        customerWorker.tell(CustomerWorker.InsertCustomer.InsertCustomerIntoCRM("", listOf()), self)
    }

    override fun createReceive(): Receive {
        return receiveBuilder()
                .match(String::class.java) {
                    when (it) {
                        "load" -> onLoad()
                    }
                }
                .match(InsertedCustomers::class.java) { onCustomersInserted(it) }
                .build()
    }

    private fun onCustomersInserted(insertedCustomers: InsertedCustomers) {
        //Do the needful
    }

    override fun preStart() {
        super.preStart()
        val el = Persistence.createEntityManagerFactory("")
    }

    //Messages
    data class InsertedCustomers(val batchId: String)

    companion object {
        @JvmStatic
        fun props(): Props {
            return Props.create(CustomerManager::class.java)
        }
    }
}