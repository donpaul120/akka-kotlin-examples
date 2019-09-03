package customers

import base.actor.ETLSupervisor

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

    //Messages
    data class InsertedCustomers(val batchId: String)
}