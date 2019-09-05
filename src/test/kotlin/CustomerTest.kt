import com.examples.base.PersistenceMgr
import com.examples.customers.Customer
import org.assertj.core.api.Assertions.*
import org.hibernate.testing.transaction.TransactionUtil.doInJPA
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import javax.persistence.EntityManagerFactory

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CustomerTest {

    //TODO for integration test use TestContainers

    @BeforeAll
    fun setUp() {
        doInJPA(({ this.entityManagerFactory() }), { entityManager ->
            Seeders.getCustomers().forEach {
                entityManager.persist(it)
            }
        })
    }

    @AfterAll
    fun tearDown() {
        //clear the database
        doInJPA(({ this.entityManagerFactory() }), { entityManager ->
            Seeders.getCustomers().forEach {
                entityManager.remove(entityManager.merge(it))
            }
        })
    }

    private fun entityManagerFactory(): EntityManagerFactory = PersistenceMgr.getEMFactory()

    @Test
    fun `Find a single customer should return the customer`() {
        doInJPA(({ this.entityManagerFactory() }), { entityManager ->
            //Arrange

            //Act
            val customer = entityManager.find(Customer::class.java, 266464)
            entityManager.detach(customer)

            //Assert
            assertThat(customer).isNotNull
            assertThat(customer.id).isEqualTo(266464)
        })
    }

    @Test
    fun `Test that we can retrieve a count of customers`() {
        doInJPA(({ this.entityManagerFactory() }), { entityManager ->
            //Arrange
            val customerSize = Seeders.getCustomers().size

            //Act
            val customers: Long = entityManager.createNamedQuery("countCustomers").singleResult as Long

            //Assert
            assertThat(customers).isEqualTo(customerSize)
        })
    }
}