import akka.actor.ActorSystem
import akka.testkit.javadsl.TestKit
import com.examples.groups.GroupManager
import com.examples.groups.GroupWorker

import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class GroupWorkerTest {

    private lateinit var aSystem: ActorSystem

    @BeforeAll
    fun setUp() {
        this.aSystem = ActorSystem.create("group-worker-test")
    }


    @AfterAll
    fun tearDown() {
        TestKit.shutdownActorSystem(this.aSystem)
    }


    @Test
    fun `sending InsertGroupIntoCRM message to GroupWorker should respond with an InsertedGroups message`() {
        TestKit(aSystem).apply {
            //Arrange
            val groupWorker = aSystem.actorOf(GroupWorker.props())

            //Act
            groupWorker.tell(GroupWorker.InsertGroups.InsertGroupsCRM("1", listOf()), ref)

            //Assert
            expectMsgClass(GroupManager.InsertedGroups::class.java)
        }
    }
}