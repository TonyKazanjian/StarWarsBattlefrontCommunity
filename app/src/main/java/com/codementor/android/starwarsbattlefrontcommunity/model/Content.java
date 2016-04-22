package com.codementor.android.starwarsbattlefrontcommunity.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.JsonElement;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by tonyk_000 on 3/31/2016.
 */
public class Content implements Parcelable{

    public static final String FULLSCREEN_IMAGE_EXTRA = "fullscreenImage";

    public int id;
    public int post_id;
    public String created_at;
    public String updated_at;


    @SerializedName("content")
    public ContentBody content;

    public Author author;

    public Content() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPost_id() {
        return post_id;
    }

    public void setPost_id(int post_id) {
        this.post_id = post_id;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public ContentBody getContent() {
        return content;
    }

    public void setContent(ContentBody content) {
        this.content = content;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public static class ContentBody implements Parcelable{
        @SerializedName("body")
        private String mBody;
        @SerializedName("image_urls")
        private List<Image> mImageUrls;

        public String getBody() {
            return mBody;
        }

        public void setBody(String body) {
            this.mBody = body;
        }

        public List<Image> getImage_urls() {
            return mImageUrls;
        }

        public void setImage_urls(List<Image> image_urls) {
            this.mImageUrls = image_urls;
        }


        public ContentBody() {
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.mBody);
            dest.writeTypedList(mImageUrls);
        }

        protected ContentBody(Parcel in) {
            this.mBody = in.readString();
            this.mImageUrls = in.createTypedArrayList(Image.CREATOR);
        }

        public static final Creator<ContentBody> CREATOR = new Creator<ContentBody>() {
            @Override
            public ContentBody createFromParcel(Parcel source) {
                return new ContentBody(source);
            }

            @Override
            public ContentBody[] newArray(int size) {
                return new ContentBody[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeInt(this.post_id);
        dest.writeString(this.created_at);
        dest.writeString(this.updated_at);
        dest.writeParcelable(this.content, flags);
        dest.writeParcelable(this.author, flags);
    }

    protected Content(Parcel in) {
        this.id = in.readInt();
        this.post_id = in.readInt();
        this.created_at = in.readString();
        this.updated_at = in.readString();
        this.content = in.readParcelable(ContentBody.class.getClassLoader());
        this.author = in.readParcelable(Author.class.getClassLoader());
    }

    public static final Creator<Content> CREATOR = new Creator<Content>() {
        @Override
        public Content createFromParcel(Parcel source) {
            return new Content(source);
        }

        @Override
        public Content[] newArray(int size) {
            return new Content[size];
        }
    };
}
