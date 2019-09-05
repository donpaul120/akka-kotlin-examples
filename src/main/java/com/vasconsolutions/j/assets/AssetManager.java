package com.vasconsolutions.j.assets;

import akka.actor.ActorRef;
import akka.actor.Props;
import com.vasconsolutions.base.actor.ETLSupervisor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AssetManager extends ETLSupervisor {

    private HashMap<String, AssetKey> assets = new HashMap<>();

    @Override
    public void onLoad() {
        ActorRef assetWorker = context().actorOf(AssetWorker.props());
        List<Asset> nAssets = new ArrayList<>();
//        nAssets.add(new Asset.DistributionTransformer("", "", "", "", ""));
//        assetWorker.tell(new AssetWorker.InsertAssetsIntoCRM("", nAssets), self());
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(String.class, msg -> onLoad())
                .match(InsertedAssets.class, this::onAssetsInserted)
                .build();
    }

    private void onAssetsInserted(InsertedAssets insertedAssets) {
        //Do something with the data inserted
    }

    //@Messages
    public static class InsertedAssets {
        private List<Asset> assets;

        InsertedAssets(String batchId, List<Asset> assets) {
            this.assets = assets;
        }

        public List<Asset> getAssets() {
            return assets;
        }

    }

    public static Props props() {
        return Props.create(AssetManager.class);
    }
}
