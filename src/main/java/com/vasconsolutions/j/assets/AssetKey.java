package com.vasconsolutions.j.assets;

public class AssetKey {
    private final String crmAssetID;
    private final String iForceAssetID;
    private final String dtId;

    public AssetKey(String crmAssetID, String iForceAssetID, String dtId) {
        this.crmAssetID = crmAssetID;
        this.iForceAssetID = iForceAssetID;
        this.dtId = dtId;
    }

    public String getCrmAssetID() {
        return crmAssetID;
    }

    public String getIForceAssetID() {
        return iForceAssetID;
    }

    public String getDtId() {
        return dtId;
    }
}
