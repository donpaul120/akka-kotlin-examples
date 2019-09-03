package customers

import akka.actor.Props
import base.actor.WorkerActor

class CustomerWorker : WorkerActor<CustomerWorker.InsertCustomer>() {

    override fun createReceive(): Receive {
        return receiveBuilder()
                .match(InsertCustomer::class.java) {

                }.build()
    }

    override fun onBatchReceived(batch: InsertCustomer) {
        when (batch) {
            is InsertCustomer.InsertCustomerIntoCRM -> insertCustomerIntoCRM(batch.customers)
            is InsertCustomer.InsertCustomerIntoIForce -> insertCustomerIntoIForce(batch.customers)
        }
    }

    private fun insertCustomerIntoCRM(customers: List<Customer>){

    }

    private fun insertCustomerIntoIForce(customers: List<Customer>){

    }

    sealed class InsertCustomer {
        data class InsertCustomerIntoCRM(val batchId: String, val customers: List<Customer>) : InsertCustomer()
        data class InsertCustomerIntoIForce(val batchId: String, val customers: List<Customer>) : InsertCustomer()
    }

    companion object{
        @JvmStatic
        fun props():Props{
            return Props.create(CustomerWorker::class.java)
        }
    }
}