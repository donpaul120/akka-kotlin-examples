import akka.actor.ActorSystem
import akka.testkit.javadsl.TestKit
import customers.CustomerManager
import customers.CustomerWorker
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CustomerWorkerTest {

    lateinit var aSystem:ActorSystem


    @BeforeAll
    fun setUp(){
        this.aSystem = ActorSystem.create()
    }

    @AfterAll
    fun tearDown(){
        TestKit.shutdownActorSystem(this.aSystem)
    }

    @Test
    fun `sending InsertCustomerIntoCRM message to CustomerWorker should respond with an InsertedCustomer message`(){
        TestKit(this.aSystem).apply {
            //Arrange
            val customerWorker = aSystem.actorOf(CustomerWorker.props())
            val insertIntoCRM = CustomerWorker.InsertCustomer.InsertCustomerIntoCRM("", listOf())

            //Act
            customerWorker.tell(insertIntoCRM, ref)

            //Assert
            expectMsgClass(CustomerManager.InsertedCustomers::class.java)
        }
    }

}