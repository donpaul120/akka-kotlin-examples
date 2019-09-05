import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.testkit.javadsl.TestKit;
import com.examples.j.assets.AssetManager;
import com.examples.j.assets.AssetWorker;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AssetWorkerTest {

    private ActorSystem aSystem;

    @BeforeAll
    void setUp() {
        this.aSystem = ActorSystem.create();
    }

    @AfterAll
    void tearDown() {
        TestKit.shutdownActorSystem(this.aSystem);
    }

    @Test
    void assetWorker_should_respond_with_inserted_asset_message() {
        new TestKit(this.aSystem) {{
            //Arrange
            ActorRef assetWorker = aSystem.actorOf(AssetWorker.props());
            AssetWorker.InsertAssetsIntoCRM message = new AssetWorker.InsertAssetsIntoCRM("", new ArrayList<>());

            //Act
            assetWorker.tell(message, getRef());

            //Assert
            expectMsgClass(AssetManager.InsertedAssets.class);
        }};
    }
}
