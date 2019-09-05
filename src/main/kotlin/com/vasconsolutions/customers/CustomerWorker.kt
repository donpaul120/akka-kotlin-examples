package com.vasconsolutions.customers

import akka.actor.Props
import com.vasconsolutions.base.actor.WorkerActor

class CustomerWorker : WorkerActor<CustomerWorker.InsertCustomer>() {

    override fun createReceive(): Receive {
        return receiveBuilder()
                .match(InsertCustomer::class.java, this::onBatchReceived)
                .build()
    }

    override fun onBatchReceived(batch: InsertCustomer) {
        when (batch) {
            is InsertCustomer.InsertCustomerIntoCRM -> insertCustomerIntoCRM(batch.customers)
            is InsertCustomer.InsertCustomerIntoIForce -> insertCustomerIntoIForce(batch.customers)
        }
    }

    private fun insertCustomerIntoCRM(customers: List<Customer>){
        sender.tell(CustomerManager.InsertedCustomers("1"), self)
    }

    private fun insertCustomerIntoIForce(customers: List<Customer>){
        sender.tell(CustomerManager.InsertedCustomers("1"), self)
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