package com.vasconsolutions.j.assets;

import javax.persistence.*;


abstract class Asset {

    private Asset() {
    }

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

        @Column(name = "ORG_NO")
        private final String orgNo;

        DistributionTransformer(String assetID, String assetName,
                                String serialNo, String installedAddress, String orgNo) {
            this.assetID = assetID;
            this.assetName = assetName;
            this.serialNo = serialNo;
            this.installedAddress = installedAddress;
            this.orgNo = orgNo;
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

        public String getOrgNo() {
            return orgNo;
        }
    }

    /*
     * For Feeders
     */
    @Entity
    @Table(name = "CRMS.G_LINE")
    @NamedQueries({
            @NamedQuery(name = "countFeeders", query = "SELECT COUNT(*) AS count FROM CRMS.G_LINE")
    })
    @NamedNativeQueries({
            @NamedNativeQuery(
                    name = "Feeder_FindCISFeeders",
                    query = "SELECT * FROM (SELECT CRMS.G_LINE.LINE_ID, CRMS.G_LINE.LINE_NO, CRMS.G_LINE.LINE_NAME,CRMS.G_LINE.ORG_NO,CRMS.G_LINE.LINE_TYPE,CRMS.G_LINE.VOLT_CODE, ROW_NUMBER() OVER (ORDER BY CRMS.G_LINE.LINE_ID ASC) AS row_n FROM CRMS.G_LINE) WHERE row_n BETWEEN :offset AND :limit",
                    resultClass = Feeder.class
            )
    })
    public static final class Feeder extends Asset {

        @Column(name = "LINE_NAME")
        private final String assetName;

        @Column(name = "LINE_ID")
        private final String assetID;

        @Column(name = "LINE_NO")
        private final String serialNo;

        @Column(name = "VOLT_CODE")
        private final String assetType;

        @Column(name = "ORG_NO")
        private final String orgNo;

        public Feeder(String assetName, String assetID, String serialNo, String assetType, String  orgNo) {
            this.assetName = assetName;
            this.assetID = assetID;
            this.serialNo = serialNo;
            this.assetType = assetType;
            this.orgNo = orgNo;
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

        public String getOrgNo() {
            return orgNo;
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

        @Column(name = "ORG_NO")
        private final String orgNo;

        public Substation(String assetID, String assetName, String serialNo, String installedAddress, String  orgNo) {
            this.assetID = assetID;
            this.assetName = assetName;
            this.serialNo = serialNo;
            this.installedAddress = installedAddress;
            this.orgNo = orgNo;
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

        public String getOrgNo() {
            return orgNo;
        }
    }
}
