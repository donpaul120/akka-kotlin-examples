import akka.actor.ActorSystem
import akka.testkit.javadsl.TestKit
import com.examples.groups.GroupManager
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class GroupManagerTest {

    private lateinit var actorSystem: ActorSystem

    @BeforeAll
    fun setUp() {
        this.actorSystem = ActorSystem.create()
    }

    @AfterAll
    fun tearDown() {
        TestKit.shutdownActorSystem(actorSystem)
    }

    //lets
    @Test
    fun `test that we can tell groupManager to load`() {
        TestKit(actorSystem).apply {
            val subject = actorSystem.actorOf(GroupManager.props())

            subject.tell("mankind", ref)

            expectMsgClass(String::class.java)
        }
    }
}