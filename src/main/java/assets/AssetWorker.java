package assets;

import akka.actor.Props;
import base.actor.WorkerActor;

import java.util.ArrayList;
import java.util.List;

public class AssetWorker extends WorkerActor<AssetWorker.InsertAssets> {

    @Override
    public Receive createReceive() {
        return receiveBuilder().match(InsertAssets.class, this::onBatchReceived).build();
    }

    @Override
    public void onBatchReceived(InsertAssets batch) {
        if (batch instanceof InsertAssetsIntoCRM) {
            this.onInsertAssetsIntoCRM(((InsertAssetsIntoCRM) batch).getAssets());
        } else if (batch instanceof InsertAssetsIntoIForce) {
            this.onInsertAssetsIntoIForce(((InsertAssetsIntoIForce) batch).getAssets());
        }
    }

    private void onInsertAssetsIntoCRM(List<Asset> assets){
        getSender().tell(new AssetManager.InsertedAssets("1", new ArrayList<>()), self());
    }

    private void onInsertAssetsIntoIForce(List<Asset> assets){

    }

    public static Props props(){
        return Props.create(AssetWorker.class);
    }

    //Messages
    interface InsertAssets {
    }

    public static class InsertAssetsIntoCRM implements InsertAssets {
        private List<Asset> assets;
        public InsertAssetsIntoCRM(String batchId, List<Asset> assets){
            this.assets = assets;
        }

        List<Asset> getAssets() {
            return assets;
        }

    }

    public static class InsertAssetsIntoIForce implements InsertAssets {
        private List<Asset> assets;
        InsertAssetsIntoIForce(String batchId, List<Asset> assets){
            this.assets = assets;
        }

        List<Asset> getAssets() {
            return assets;
        }
    }
}
