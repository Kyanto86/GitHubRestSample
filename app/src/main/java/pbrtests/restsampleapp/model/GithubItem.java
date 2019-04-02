package pbrtests.restsampleapp.model;

public class GithubItem {

    /*This class represents the JSON data of the Github API. It contains only the data that I want to represent inside the app.*/

    private String description, created_at, name;
    private Owner owner;

    public GithubItem(String description, String created_at, String name, Owner owner) {
        this.description = description;
        this.created_at = created_at;
        this.name = name;
        this.owner = owner;
    }

    public String getDescription() {
        return description;
    }

    public String getCreated_at() {
        return created_at;
    }

    public String getName() {
        return name;
    }

    public String getLogin (){ return getOwner().getLogin();}

    public String getAvatarUrl(){ return getOwner().getAvatar_url(); }

    public Owner getOwner() {
        return owner;
    }

    static class Owner {

        final String avatar_url;
        final String login;

        public Owner(String avatar_url, String login) {
            this.avatar_url = avatar_url;
            this.login = login;
        }

        public String getAvatar_url() {
            return avatar_url;
        }

        public String getLogin() {
            return login;
        }
    }
}
