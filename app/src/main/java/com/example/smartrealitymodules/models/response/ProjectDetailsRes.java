package com.example.smartrealitymodules.models.response;

/**
 * Created by user on 27/2/17.
 */

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by vijay on 23/11/16.
 */

public class ProjectDetailsRes {

    public String status;
    public Result result;
    public String message;

    public ProjectDetailsRes(String status, Result result, String message) {
        this.status = status;
        this.result = result;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static class ProjectImage implements Parcelable {
        public static final Creator<ProjectImage> CREATOR = new Creator<ProjectImage>() {
            @Override
            public ProjectImage createFromParcel(Parcel source) {
                return new ProjectImage(source);
            }

            @Override
            public ProjectImage[] newArray(int size) {
                return new ProjectImage[size];
            }
        };
        public String ProjectMediaResourceChildID;
        public String FileName;
        public String FilePath;

        public ProjectImage(String projectMediaResourceChildID, String fileName, String filePath) {
            ProjectMediaResourceChildID = projectMediaResourceChildID;
            FileName = fileName;
            FilePath = filePath;
        }

        protected ProjectImage(Parcel in) {
            this.ProjectMediaResourceChildID = in.readString();
            this.FileName = in.readString();
            this.FilePath = in.readString();
        }

        public String getProjectMediaResourceChildID() {
            return ProjectMediaResourceChildID;
        }

        public void setProjectMediaResourceChildID(String projectMediaResourceChildID) {
            ProjectMediaResourceChildID = projectMediaResourceChildID;
        }

        public String getFileName() {
            return FileName;
        }

        public void setFileName(String fileName) {
            FileName = fileName;
        }

        public String getFilePath() {
            return FilePath;
        }

        public void setFilePath(String filePath) {
            FilePath = filePath;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.ProjectMediaResourceChildID);
            dest.writeString(this.FileName);
            dest.writeString(this.FilePath);
        }
    }

    public static class Amenity implements Parcelable {

        public static final Creator<Amenity> CREATOR = new Creator<Amenity>() {
            @Override
            public Amenity createFromParcel(Parcel source) {
                return new Amenity(source);
            }

            @Override
            public Amenity[] newArray(int size) {
                return new Amenity[size];
            }
        };
        public String AmenitiesID;
        public String Title;
        public String IconPath;

        public Amenity(String amenitiesID, String title, String iconPath) {
            AmenitiesID = amenitiesID;
            Title = title;
            IconPath = iconPath;
        }

        protected Amenity(Parcel in) {
            this.AmenitiesID = in.readString();
            this.Title = in.readString();
            this.IconPath = in.readString();
        }

        public String getAmenitiesID() {
            return AmenitiesID;
        }

        public void setAmenitiesID(String amenitiesID) {
            AmenitiesID = amenitiesID;
        }

        public String getTitle() {
            return Title;
        }

        public void setTitle(String title) {
            Title = title;
        }

        public String getIconPath() {
            return IconPath;
        }

        public void setIconPath(String iconPath) {
            IconPath = iconPath;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.AmenitiesID);
            dest.writeString(this.Title);
            dest.writeString(this.IconPath);
        }
    }

    public static class Constructions implements Parcelable {

        public static final Creator<Constructions> CREATOR = new Creator<Constructions>() {
            @Override
            public Constructions createFromParcel(Parcel source) {
                return new Constructions(source);
            }

            @Override
            public Constructions[] newArray(int size) {
                return new Constructions[size];
            }
        };
        public String ProjectMediaResourceChildID;
        public String FileName;
        public String MediaType;
        public String FilePath;

        public Constructions(String projectMediaResourceChildID, String fileName, String mediaType, String filePath) {
            ProjectMediaResourceChildID = projectMediaResourceChildID;
            FileName = fileName;
            MediaType = mediaType;
            FilePath = filePath;
        }

        protected Constructions(Parcel in) {
            this.ProjectMediaResourceChildID = in.readString();
            this.FileName = in.readString();
            this.MediaType = in.readString();
            this.FilePath = in.readString();
        }

        public String getProjectMediaResourceChildID() {
            return ProjectMediaResourceChildID;
        }

        public void setProjectMediaResourceChildID(String projectMediaResourceChildID) {
            ProjectMediaResourceChildID = projectMediaResourceChildID;
        }

        public String getFileName() {
            return FileName;
        }

        public void setFileName(String fileName) {
            FileName = fileName;
        }

        public String getMediaType() {
            return MediaType;
        }

        public void setMediaType(String mediaType) {
            MediaType = mediaType;
        }

        public String getFilePath() {
            return FilePath;
        }

        public void setFilePath(String filePath) {
            FilePath = filePath;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.ProjectMediaResourceChildID);
            dest.writeString(this.FileName);
            dest.writeString(this.MediaType);
            dest.writeString(this.FilePath);
        }
    }

    public static class Architectures implements Parcelable {

        public static final Creator<Architectures> CREATOR = new Creator<Architectures>() {
            @Override
            public Architectures createFromParcel(Parcel source) {
                return new Architectures(source);
            }

            @Override
            public Architectures[] newArray(int size) {
                return new Architectures[size];
            }
        };
        public String ProjectMediaResourceChildID;
        public String FileName;
        public String MediaType;
        public String FilePath;

        public Architectures(String projectMediaResourceChildID, String fileName, String mediaType, String filePath) {
            ProjectMediaResourceChildID = projectMediaResourceChildID;
            FileName = fileName;
            MediaType = mediaType;
            FilePath = filePath;
        }

        protected Architectures(Parcel in) {
            this.ProjectMediaResourceChildID = in.readString();
            this.FileName = in.readString();
            this.MediaType = in.readString();
            this.FilePath = in.readString();
        }

        public String getProjectMediaResourceChildID() {
            return ProjectMediaResourceChildID;
        }

        public void setProjectMediaResourceChildID(String projectMediaResourceChildID) {
            ProjectMediaResourceChildID = projectMediaResourceChildID;
        }

        public String getFileName() {
            return FileName;
        }

        public void setFileName(String fileName) {
            FileName = fileName;
        }

        public String getMediaType() {
            return MediaType;
        }

        public void setMediaType(String mediaType) {
            MediaType = mediaType;
        }

        public String getFilePath() {
            return FilePath;
        }

        public void setFilePath(String filePath) {
            FilePath = filePath;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.ProjectMediaResourceChildID);
            dest.writeString(this.FileName);
            dest.writeString(this.MediaType);
            dest.writeString(this.FilePath);
        }
    }

    public static class AmenityImage implements Parcelable {

        public static final Creator<AmenityImage> CREATOR = new Creator<AmenityImage>() {
            @Override
            public AmenityImage createFromParcel(Parcel source) {
                return new AmenityImage(source);
            }

            @Override
            public AmenityImage[] newArray(int size) {
                return new AmenityImage[size];
            }
        };
        public String ProjectMediaResourceChildID;
        public String FileName;
        public String FilePath;

        public AmenityImage(String projectMediaResourceChildID, String fileName, String filePath) {
            ProjectMediaResourceChildID = projectMediaResourceChildID;
            FileName = fileName;
            FilePath = filePath;
        }

        protected AmenityImage(Parcel in) {
            this.ProjectMediaResourceChildID = in.readString();
            this.FileName = in.readString();
            this.FilePath = in.readString();
        }

        public String getProjectMediaResourceChildID() {
            return ProjectMediaResourceChildID;
        }

        public void setProjectMediaResourceChildID(String projectMediaResourceChildID) {
            ProjectMediaResourceChildID = projectMediaResourceChildID;
        }

        public String getFileName() {
            return FileName;
        }

        public void setFileName(String fileName) {
            FileName = fileName;
        }

        public String getFilePath() {
            return FilePath;
        }

        public void setFilePath(String filePath) {
            FilePath = filePath;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.ProjectMediaResourceChildID);
            dest.writeString(this.FileName);
            dest.writeString(this.FilePath);
        }
    }

    public class Result {

        public String InterestedIn;
        public String ProjectID;
        public String ProjectName;
        public String AboutProject;
        public String Lat;
        public String Long;
        public String Location;
        public String CityName;
        public String LocalArea;
        public String View360;
        public String ApartmentType;
        public String MinBudget;
        public String ContactNo;
        public String WalkThroughURL;
        public String LocationPath;
        public ArrayList<AmenityImage> AmenityImages = new ArrayList<AmenityImage>();
        public ArrayList<Amenity> Amenities = new ArrayList<Amenity>();
        public ArrayList<ProjectImage> ProjectImages = new ArrayList<ProjectImage>();
        public ArrayList<FloorPlan> FloorPlan = new ArrayList<FloorPlan>();
        public ArrayList<FlatLayout> FlatLayout = new ArrayList<FlatLayout>();
        public ArrayList<Gallerys> Gallery = new ArrayList<Gallerys>();
        public ArrayList<SiteImage> SiteImages = new ArrayList<SiteImage>();
        public ArrayList<ProjectDocument> ProjectDocument = new ArrayList<ProjectDocument>();

        public Result(String interestedIn, String projectID, String projectName, String aboutProject, String lat, String aLong, String location, String cityName, String localArea, String view360, String apartmentType, String minBudget, String contactNo, String walkThroughURL, String locationPath, ArrayList<AmenityImage> amenityImages, ArrayList<Amenity> amenities, ArrayList<ProjectImage> projectImages, ArrayList<FloorPlan> floorPlan, ArrayList<FlatLayout> flatLayout, ArrayList<Gallerys> gallery, ArrayList<SiteImage> siteImages, ArrayList<ProjectDocument> projectDocument) {
            InterestedIn = interestedIn;
            ProjectID = projectID;
            ProjectName = projectName;
            AboutProject = aboutProject;
            Lat = lat;
            Long = aLong;
            Location = location;
            CityName = cityName;
            LocalArea = localArea;
            View360 = view360;
            ApartmentType = apartmentType;
            MinBudget = minBudget;
            ContactNo = contactNo;
            WalkThroughURL = walkThroughURL;
            LocationPath = locationPath;
            AmenityImages = amenityImages;
            Amenities = amenities;
            projectImages = projectImages;
            FloorPlan = floorPlan;
            FlatLayout = flatLayout;
            Gallery = gallery;
            SiteImages = siteImages;
            ProjectDocument = projectDocument;
        }

        public String getInterestedIn() {
            return InterestedIn;
        }

        public void setInterestedIn(String interestedIn) {
            InterestedIn = interestedIn;
        }

        public String getProjectID() {
            return ProjectID;
        }

        public void setProjectID(String projectID) {
            ProjectID = projectID;
        }

        public String getProjectName() {
            return ProjectName;
        }

        public void setProjectName(String projectName) {
            ProjectName = projectName;
        }

        public String getAboutProject() {
            return AboutProject;
        }

        public void setAboutProject(String aboutProject) {
            AboutProject = aboutProject;
        }

        public String getLat() {
            return Lat;
        }

        public void setLat(String lat) {
            Lat = lat;
        }

        public String getLong() {
            return Long;
        }

        public void setLong(String aLong) {
            Long = aLong;
        }

        public String getLocation() {
            return Location;
        }

        public void setLocation(String location) {
            Location = location;
        }

        public String getCityName() {
            return CityName;
        }

        public void setCityName(String cityName) {
            CityName = cityName;
        }

        public String getLocalArea() {
            return LocalArea;
        }

        public void setLocalArea(String localArea) {
            LocalArea = localArea;
        }

        public String getView360() {
            return View360;
        }

        public void setView360(String view360) {
            View360 = view360;
        }

        public String getApartmentType() {
            return ApartmentType;
        }

        public void setApartmentType(String apartmentType) {
            ApartmentType = apartmentType;
        }

        public String getMinBudget() {
            return MinBudget;
        }

        public void setMinBudget(String minBudget) {
            MinBudget = minBudget;
        }

        public String getContactNo() {
            return ContactNo;
        }

        public void setContactNo(String contactNo) {
            ContactNo = contactNo;
        }

        public String getWalkThroughURL() {
            return WalkThroughURL;
        }

        public void setWalkThroughURL(String walkThroughURL) {
            WalkThroughURL = walkThroughURL;
        }

        public String getLocationPath() {
            return LocationPath;
        }

        public void setLocationPath(String locationPath) {
            LocationPath = locationPath;
        }

        public ArrayList<AmenityImage> getAmenityImages() {
            return AmenityImages;
        }

        public void setAmenityImages(ArrayList<AmenityImage> amenityImages) {
            AmenityImages = amenityImages;
        }

        public ArrayList<Amenity> getAmenities() {
            return Amenities;
        }

        public void setAmenities(ArrayList<Amenity> amenities) {
            Amenities = amenities;
        }

        public ArrayList<ProjectImage> getProjectImages() {
            return ProjectImages;
        }

        public void setProjectImages(ArrayList<ProjectImage> projectImages) {
            ProjectImages = projectImages;
        }

        public ArrayList<FloorPlan> getFloorPlan() {
            return FloorPlan;
        }

        public void setFloorPlan(ArrayList<FloorPlan> floorPlan) {
            FloorPlan = floorPlan;
        }

        public ArrayList<FlatLayout> getFlatLayout() {
            return FlatLayout;
        }

        public void setFlatLayout(ArrayList<FlatLayout> flatLayout) {
            FlatLayout = flatLayout;
        }

        public ArrayList<Gallerys> getGallery() {
            return Gallery;
        }

        public void setGallery(ArrayList<Gallerys> gallery) {
            Gallery = gallery;
        }

        public ArrayList<SiteImage> getSiteImages() {
            return SiteImages;
        }

        public void setSiteImages(ArrayList<SiteImage> siteImages) {
            SiteImages = siteImages;
        }

        public ArrayList<ProjectDocument> getProjectDocument() {
            return ProjectDocument;
        }

        public void setProjectDocument(ArrayList<ProjectDocument> projectDocument) {
            ProjectDocument = projectDocument;
        }

        public class Gallerys {

            public ArrayList<Architectures> Architecture = new ArrayList<Architectures>();
            public ArrayList<Constructions> Construction = new ArrayList<Constructions>();
            public String WalkThroughURL;

            public Gallerys(ArrayList<Architectures> architecture, ArrayList<Constructions> construction, String walkThroughURL) {
                Architecture = architecture;
                Construction = construction;
                WalkThroughURL = walkThroughURL;
            }

            public ArrayList<Architectures> getArchitecture() {
                return Architecture;
            }

            public void setArchitecture(ArrayList<Architectures> architecture) {
                Architecture = architecture;
            }

            public ArrayList<Constructions> getConstruction() {
                return Construction;
            }

            public void setConstruction(ArrayList<Constructions> construction) {
                Construction = construction;
            }

            public String getWalkThroughURL() {
                return WalkThroughURL;
            }

            public void setWalkThroughURL(String walkThroughURL) {
                WalkThroughURL = walkThroughURL;
            }
        }


    }

    public class FloorPlan {

        public String ProjectMediaResourceChildID;
        public String FileName;
        public String FilePath;

        public FloorPlan(String projectMediaResourceChildID, String fileName, String filePath) {
            ProjectMediaResourceChildID = projectMediaResourceChildID;
            FileName = fileName;
            FilePath = filePath;
        }

        public String getProjectMediaResourceChildID() {
            return ProjectMediaResourceChildID;
        }

        public void setProjectMediaResourceChildID(String projectMediaResourceChildID) {
            ProjectMediaResourceChildID = projectMediaResourceChildID;
        }

        public String getFileName() {
            return FileName;
        }

        public void setFileName(String fileName) {
            FileName = fileName;
        }

        public String getFilePath() {
            return FilePath;
        }

        public void setFilePath(String filePath) {
            FilePath = filePath;
        }
    }

    public class FlatLayout {

        public String ProjectMediaResourceChildID;
        public String TagName;
        public String FileName;
        public String FilePath;

        public FlatLayout(String projectMediaResourceChildID, String tagName, String fileName, String filePath) {
            ProjectMediaResourceChildID = projectMediaResourceChildID;
            TagName = tagName;
            FileName = fileName;
            FilePath = filePath;
        }

        public String getProjectMediaResourceChildID() {
            return ProjectMediaResourceChildID;
        }

        public void setProjectMediaResourceChildID(String projectMediaResourceChildID) {
            ProjectMediaResourceChildID = projectMediaResourceChildID;
        }

        public String getTagName() {
            return TagName;
        }

        public void setTagName(String tagName) {
            TagName = tagName;
        }

        public String getFileName() {
            return FileName;
        }

        public void setFileName(String fileName) {
            FileName = fileName;
        }

        public String getFilePath() {
            return FilePath;
        }

        public void setFilePath(String filePath) {
            FilePath = filePath;
        }
    }

    public class SiteImage {

        public String ProjectMediaResourceChildID;
        public String FileName;
        public String FilePath;

        public SiteImage(String projectMediaResourceChildID, String fileName, String filePath) {
            ProjectMediaResourceChildID = projectMediaResourceChildID;
            FileName = fileName;
            FilePath = filePath;
        }

        public String getProjectMediaResourceChildID() {
            return ProjectMediaResourceChildID;
        }

        public void setProjectMediaResourceChildID(String projectMediaResourceChildID) {
            ProjectMediaResourceChildID = projectMediaResourceChildID;
        }

        public String getFileName() {
            return FileName;
        }

        public void setFileName(String fileName) {
            FileName = fileName;
        }

        public String getFilePath() {
            return FilePath;
        }

        public void setFilePath(String filePath) {
            FilePath = filePath;
        }
    }

    public class ProjectDocument {

        public String ProjectMediaResourceChildID;
        public String FileName;
        public String FilePath;

        public ProjectDocument(String projectMediaResourceChildID, String fileName, String filePath) {
            ProjectMediaResourceChildID = projectMediaResourceChildID;
            FileName = fileName;
            FilePath = filePath;
        }

        public String getProjectMediaResourceChildID() {
            return ProjectMediaResourceChildID;
        }

        public void setProjectMediaResourceChildID(String projectMediaResourceChildID) {
            ProjectMediaResourceChildID = projectMediaResourceChildID;
        }

        public String getFileName() {
            return FileName;
        }

        public void setFileName(String fileName) {
            FileName = fileName;
        }

        public String getFilePath() {
            return FilePath;
        }

        public void setFilePath(String filePath) {
            FilePath = filePath;
        }
    }


}
