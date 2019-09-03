package assets;

import akka.actor.ActorRef;
import base.actor.ETLSupervisor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AssetManager extends ETLSupervisor {

    private HashMap<String, AssetKey> assets = new HashMap<>();

    @Override
    public void onLoad() {
        ActorRef assetWorker = context().actorOf(AssetWorker.props());
        assetWorker.tell(new AssetWorker.InsertAssetsIntoCRM("", new ArrayList<>()), self());
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(String.class, msg -> onLoad())
                .match(InsertedAssets.class, this::onAssetsInserted)
                .build();
    }

    private void onAssetsInserted(InsertedAssets insertedAssets){

    }

    //Messages
    public static class InsertedAssets {
        private List<Asset> assets;

        InsertedAssets(String batchId, List<Asset> assets){
            this.assets = assets;
        }

        public List<Asset> getAssets() {
            return assets;
        }

        public void setAssets(List<Asset> assets) {
            this.assets = assets;
        }
    }
}
