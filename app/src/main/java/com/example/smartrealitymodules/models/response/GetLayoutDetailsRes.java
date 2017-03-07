package com.example.smartrealitymodules.models.response;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by user on 2/3/17.
 */
public class GetLayoutDetailsRes {

    public String status;
    public String message;
    public Result result ;

    public GetLayoutDetailsRes(String status, String message, Result result) {
        this.status = status;
        this.message = message;
        this.result = result;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public class Result {

        public ArrayList<MasterLayouts> MasterLayout = new ArrayList<MasterLayouts>();
        public ArrayList<BlockLayouts> BlockLayout = new ArrayList<BlockLayouts>();

        public Result(ArrayList<MasterLayouts> masterLayout, ArrayList<BlockLayouts> blockLayout) {
            MasterLayout = masterLayout;
            BlockLayout = blockLayout;
        }

        public ArrayList<MasterLayouts> getMasterLayout() {
            return MasterLayout;
        }

        public void setMasterLayout(ArrayList<MasterLayouts> masterLayout) {
            MasterLayout = masterLayout;
        }

        public ArrayList<BlockLayouts> getBlockLayout() {
            return BlockLayout;
        }

        public void setBlockLayout(ArrayList<BlockLayouts> blockLayout) {
            BlockLayout = blockLayout;
        }

        public class MasterLayouts {

            public String FilePath;
            public String CateGoryName;
            public String TagName;

            public MasterLayouts(String filePath, String cateGoryName, String tagName) {
                FilePath = filePath;
                CateGoryName = cateGoryName;
                TagName = tagName;
            }

            public String getFilePath() {
                return FilePath;
            }

            public void setFilePath(String filePath) {
                FilePath = filePath;
            }

            public String getCateGoryName() {
                return CateGoryName;
            }

            public void setCateGoryName(String cateGoryName) {
                CateGoryName = cateGoryName;
            }

            public String getTagName() {
                return TagName;
            }

            public void setTagName(String tagName) {
                TagName = tagName;
            }
        }

    }



    public static class BlockLayouts implements Parcelable {

        public String FilePath;
        public String CateGoryName;
        public String TagName;
        public ArrayList<FlatLayouts> FlatLayout = new ArrayList<FlatLayouts>();

        public BlockLayouts(String filePath, String cateGoryName, String tagName, ArrayList<FlatLayouts> flatLayout) {
            FilePath = filePath;
            CateGoryName = cateGoryName;
            TagName = tagName;
            FlatLayout = flatLayout;
        }

        public String getFilePath() {
            return FilePath;
        }

        public void setFilePath(String filePath) {
            FilePath = filePath;
        }

        public String getCateGoryName() {
            return CateGoryName;
        }

        public void setCateGoryName(String cateGoryName) {
            CateGoryName = cateGoryName;
        }

        public String getTagName() {
            return TagName;
        }

        public void setTagName(String tagName) {
            TagName = tagName;
        }

        public ArrayList<FlatLayouts> getFlatLayout() {
            return FlatLayout;
        }

        public void setFlatLayout(ArrayList<FlatLayouts> flatLayout) {
            FlatLayout = flatLayout;
        }




        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.FilePath);
            dest.writeString(this.CateGoryName);
            dest.writeString(this.TagName);
            dest.writeList(this.FlatLayout);
        }

        protected BlockLayouts(Parcel in) {
            this.FilePath = in.readString();
            this.CateGoryName = in.readString();
            this.TagName = in.readString();
            this.FlatLayout = new ArrayList<FlatLayouts>();
            in.readList(this.FlatLayout, FlatLayouts.class.getClassLoader());
        }

        public static final Creator<BlockLayouts> CREATOR = new Creator<BlockLayouts>() {
            @Override
            public BlockLayouts createFromParcel(Parcel source) {
                return new BlockLayouts(source);
            }

            @Override
            public BlockLayouts[] newArray(int size) {
                return new BlockLayouts[size];
            }
        };
    }

    public static class FlatLayouts implements Parcelable {

        public String FilePath;
        public String CateGoryName;
        public String TagName;

        public FlatLayouts(String filePath, String cateGoryName, String tagName) {
            FilePath = filePath;
            CateGoryName = cateGoryName;
            TagName = tagName;
        }

        public String getFilePath() {
            return FilePath;
        }

        public void setFilePath(String filePath) {
            FilePath = filePath;
        }

        public String getCateGoryName() {
            return CateGoryName;
        }

        public void setCateGoryName(String cateGoryName) {
            CateGoryName = cateGoryName;
        }

        public String getTagName() {
            return TagName;
        }

        public void setTagName(String tagName) {
            TagName = tagName;
        }


        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.FilePath);
            dest.writeString(this.CateGoryName);
            dest.writeString(this.TagName);
        }

        protected FlatLayouts(Parcel in) {
            this.FilePath = in.readString();
            this.CateGoryName = in.readString();
            this.TagName = in.readString();
        }

        public static final Creator<FlatLayouts> CREATOR = new Creator<FlatLayouts>() {
            @Override
            public FlatLayouts createFromParcel(Parcel source) {
                return new FlatLayouts(source);
            }

            @Override
            public FlatLayouts[] newArray(int size) {
                return new FlatLayouts[size];
            }
        };
    }
}
