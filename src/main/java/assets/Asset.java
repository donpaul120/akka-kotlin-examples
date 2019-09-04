package assets;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

abstract class Asset {

    private Asset() { }

    /*
     * For Distribution Transformers
     */
    @Entity
    @Table(name = "CRMS.G_TG")
    public static final class DistributionTransformer extends Asset {

        @Column(name = "TG_ID")
        private final String assetID;

        @Column(name = "TG_NAME")
        private final String assetName;

        @Column(name = "TG_NO")
        private final String serialNo;

        @Column(name = "INST_ADDR")
        private final String installedAddress;

        public DistributionTransformer(String assetID, String assetName, String serialNo, String installedAddress){
            this.assetID = assetID;
            this.assetName = assetName;
            this.serialNo = serialNo;
            this.installedAddress = installedAddress;
        }

        public String getAssetID() {
            return assetID;
        }

        public String getAssetName() {
            return assetName;
        }

        public String getSerialNo() {
            return serialNo;
        }

        public String getInstalledAddress() {
            return installedAddress;
        }
    }

    /*
     * For Feeders
     */
    @Entity
    @Table(name = "CRMS.G_LINE")
    public static final class Feeder extends Asset {

        @Column(name = "LINE_NAME")
        private final String assetName;

        @Column(name = "LINE_ID")
        private final String assetID;

        @Column(name = "LINE_NO")
        private final String serialNo;

        @Column(name = "VOLT_CODE")
        private final String assetType;

        public Feeder(String assetName, String assetID, String serialNo, String assetType){
            this.assetName = assetName;
            this.assetID = assetID;
            this.serialNo = serialNo;
            this.assetType = assetType;
        }

        public String getAssetName() {
            return assetName;
        }

        public String getAssetID() {
            return assetID;
        }

        public String getSerialNo() {
            return serialNo;
        }

        public String getAssetType() {
            return assetType;
        }
    }


    /*
     * Substation
     */
    @Entity
    @Table(name = "CRMS.G_SUBS")
    public static final class Substation extends Asset {

        @Column(name = "SUB_ID")
        private final String assetID;

        @Column(name = "SUB_NAME")
        private final String assetName;

        @Column(name = "SUB_NO")
        private final String serialNo;

        @Column(name = "SUBS_ADDR")
        private final String installedAddress;

        public Substation(String assetID, String assetName, String serialNo, String installedAddress){
            this.assetID = assetID;
            this.assetName = assetName;
            this.serialNo = serialNo;
            this.installedAddress = installedAddress;
        }

        public String getAssetID() {
            return assetID;
        }

        public String getAssetName() {
            return assetName;
        }

        public String getSerialNo() {
            return serialNo;
        }

        public String getInstalledAddress() {
            return installedAddress;
        }
    }
}
