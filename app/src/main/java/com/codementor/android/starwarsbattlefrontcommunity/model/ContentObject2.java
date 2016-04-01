package com.codementor.android.starwarsbattlefrontcommunity.model;

import java.util.List;

/**
 * Created by tonyk_000 on 4/1/2016.
 */
public class ContentObject2 {

    /**
     * title : Check out this crazy action shot!
     * id : 2
     * topic_id : 1
     * created_at : 2016-03-28T23:33:31.013Z
     * updated_at : 2016-03-28T23:33:31.013Z
     * comment_count : 3
     * content : {"body":"This is seriously insane. Check this out!","image_urls":[{"image_url":"http://images.eurogamer.net/2013/articles/1/7/7/1/4/1/4/star-wars-battlefront-details-team-based-blast-mode-1438302156234.jpg/EG11/thumbnail/900x450/format/jpg/1771414.jpg"}]}
     * author : {"name":"Luke Skywalker","profile_image_url":"http://vignette1.wikia.nocookie.net/starwars/images/d/d9/Luke-rotjpromo.jpg/revision/latest?cb=20091030151422"}
     */

    private List<PostsEntity> posts;

    public List<PostsEntity> getPosts() {
        return posts;
    }

    public void setPosts(List<PostsEntity> posts) {
        this.posts = posts;
    }

    public static class PostsEntity {
        private String title;
        private int id;
        private int topic_id;
        private String created_at;
        private String updated_at;
        private int comment_count;
        /**
         * body : This is seriously insane. Check this out!
         * image_urls : [{"image_url":"http://images.eurogamer.net/2013/articles/1/7/7/1/4/1/4/star-wars-battlefront-details-team-based-blast-mode-1438302156234.jpg/EG11/thumbnail/900x450/format/jpg/1771414.jpg"}]
         */

        private ContentEntity content;
        /**
         * name : Luke Skywalker
         * profile_image_url : http://vignette1.wikia.nocookie.net/starwars/images/d/d9/Luke-rotjpromo.jpg/revision/latest?cb=20091030151422
         */

        private AuthorEntity author;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getTopic_id() {
            return topic_id;
        }

        public void setTopic_id(int topic_id) {
            this.topic_id = topic_id;
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

        public int getComment_count() {
            return comment_count;
        }

        public void setComment_count(int comment_count) {
            this.comment_count = comment_count;
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
            /**
             * image_url : http://images.eurogamer.net/2013/articles/1/7/7/1/4/1/4/star-wars-battlefront-details-team-based-blast-mode-1438302156234.jpg/EG11/thumbnail/900x450/format/jpg/1771414.jpg
             */

            private List<ImageUrlsEntity> image_urls;

            public String getBody() {
                return body;
            }

            public void setBody(String body) {
                this.body = body;
            }

            public List<ImageUrlsEntity> getImage_urls() {
                return image_urls;
            }

            public void setImage_urls(List<ImageUrlsEntity> image_urls) {
                this.image_urls = image_urls;
            }

            public static class ImageUrlsEntity {
                private String image_url;

                public String getImage_url() {
                    return image_url;
                }

                public void setImage_url(String image_url) {
                    this.image_url = image_url;
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
}
