package com.codementor.android.starwarsbattlefrontcommunity.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by tonyk_000 on 3/31/2016.
 */
public class ContentObject implements Parcelable{


    /**
     * id : 3
     * post_id : 2
     * created_at : 2016-03-29T12:02:31.013Z
     * updated_at : 2016-03-29T12:02:31.013Z
     * content : {"body":"Haha, nope. Sorry to break it to you, Han.","image":[]}
     * author : {"name":"Greedo","profile_image_url":"https://pbs.twimg.com/profile_images/1233929440/greedo2.jpg"}
     */

    public int id;
    public int post_id;
    public String created_at;
    public String updated_at;
    /**
     * body : Haha, nope. Sorry to break it to you, Han.
     * image : []
     */

    public ContentEntity content;
    /**
     * name : Greedo
     * profile_image_url : https://pbs.twimg.com/profile_images/1233929440/greedo2.jpg
     */

    public AuthorEntity author;

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

    public ContentEntity getContent() {
        return content;
    }

    public void setContent(ContentEntity content) {
        this.content = content;
    }

    public AuthorEntity getAuthor() {
        return author;
    }

    public void setAuthor(AuthorEntity author) {
        this.author = author;
    }

    public static class ContentEntity implements Parcelable{
        private String body;
        private Image[] image_urls;

        protected ContentEntity(Parcel in) {
            body = in.readString();
            image_urls = in.createTypedArray(Image.CREATOR);
        }

        public static final Creator<ContentEntity> CREATOR = new Creator<ContentEntity>() {
            @Override
            public ContentEntity createFromParcel(Parcel in) {
                return new ContentEntity(in);
            }

            @Override
            public ContentEntity[] newArray(int size) {
                return new ContentEntity[size];
            }
        };

        public String getBody() {
            return body;
        }

        public void setBody(String body) {
            this.body = body;
        }

        public Image[] getImage_urls() {
            return image_urls;
        }

        public void setImage_urls(Image[] image_urls) {
            this.image_urls = image_urls;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(body);
            dest.writeTypedArray(image_urls, flags);
        }


        public static class Image implements Parcelable{
            String image_url;

            protected Image(Parcel in) {
                image_url = in.readString();
            }

            public static final Creator<Image> CREATOR = new Creator<Image>() {
                @Override
                public Image createFromParcel(Parcel in) {
                    return new Image(in);
                }

                @Override
                public Image[] newArray(int size) {
                    return new Image[size];
                }
            };

            public String getImage_url(){
                return image_url;
            }

            public void setImage_url(String url){
                this.image_url = url;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(image_url);
            }
        }

    }

    public static class AuthorEntity implements Parcelable {
        private String name;
        private String profile_image_url;

        protected AuthorEntity(Parcel in) {
            name = in.readString();
            profile_image_url = in.readString();
        }

        public static final Creator<AuthorEntity> CREATOR = new Creator<AuthorEntity>() {
            @Override
            public AuthorEntity createFromParcel(Parcel in) {
                return new AuthorEntity(in);
            }

            @Override
            public AuthorEntity[] newArray(int size) {
                return new AuthorEntity[size];
            }
        };

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getProfile_image_url() {
            return profile_image_url;
        }

        public void setProfile_image_url(String profile_image_url) {
            this.profile_image_url = profile_image_url;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(name);
            dest.writeString(profile_image_url);
        }
    }


    public ContentObject() {
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
//        dest.writeTypedArray(this.image_urls, flags);
    }

    protected ContentObject(Parcel in) {
        this.id = in.readInt();
        this.post_id = in.readInt();
        this.created_at = in.readString();
        this.updated_at = in.readString();
        this.content = in.readParcelable(ContentEntity.class.getClassLoader());
        this.author = in.readParcelable(AuthorEntity.class.getClassLoader());
//        this.image_urls = in.createTypedArray(ContentEntity.Image.CREATOR);
    }

}
