package assets;

public class AssetKey {
    private String crmAssetID;
    private String iForceAssetID;

    public AssetKey(String crmAssetID, String iForceAssetID) {
        this.crmAssetID = crmAssetID;
        this.iForceAssetID = iForceAssetID;
    }

    public String getCrmAssetID() {
        return crmAssetID;
    }

    public void setCrmAssetID(String crmAssetID) {
        this.crmAssetID = crmAssetID;
    }

    public String getIForceAssetID() {
        return iForceAssetID;
    }

    public void setIForceAssetID(String iForceAssetID) {
        this.iForceAssetID = iForceAssetID;
    }
}
