package com.codementor.android.starwarsbattlefrontcommunity.model;

import android.os.Parcelable;

import java.util.List;

/**
 * Created by tonyk_000 on 3/31/2016.
 */
public abstract class ContentObject implements Parcelable {


    /**
     * id : 3
     * post_id : 2
     * created_at : 2016-03-29T12:02:31.013Z
     * updated_at : 2016-03-29T12:02:31.013Z
     * content : {"body":"Haha, nope. Sorry to break it to you, Han.","image":[]}
     * author : {"name":"Greedo","profile_image_url":"https://pbs.twimg.com/profile_images/1233929440/greedo2.jpg"}
     */

    private int id;
    private int post_id;
    private String created_at;
    private String updated_at;
    /**
     * body : Haha, nope. Sorry to break it to you, Han.
     * image : []
     */

    private ContentEntity content;
    /**
     * name : Greedo
     * profile_image_url : https://pbs.twimg.com/profile_images/1233929440/greedo2.jpg
     */

    private AuthorEntity author;


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

    public static class ContentEntity {
        private String body;
        private List<Image> image_urls;

        public String getBody() {
            return body;
        }

        public void setBody(String body) {
            this.body = body;
        }

        public List<Image> getImages(){
            return image_urls;
        }

        public void setImage_urls(List<Image> image_urls) {
            this.image_urls = image_urls;
        }

        public static class Image{
            String image_url;

            public String getImage_url(){
                return image_url;
            }

            public void setImage_url(String url){
                this.image_url = url;
            }
        }
    }

    public static class AuthorEntity {
        private String name;
        private String profile_image_url;

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
    }

}
