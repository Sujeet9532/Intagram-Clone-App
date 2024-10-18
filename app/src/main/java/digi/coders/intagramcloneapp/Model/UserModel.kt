package digi.coders.intagramcloneapp.Model

class UserModel {
    var image: String? = null
    var username: String? = null
    var email: String? = null
    var bio: String? = null

    var psssword: String? = null

    constructor(
        image: String?,
        username: String?,
        email: String?,
        bio: String?,
        psssword: String?
    ) {
        this.image = image
        this.username = username
        this.email = email
        this.bio = bio
        this.psssword = psssword
    }

    constructor()
    constructor(email: String?, psssword: String?) {
        this.email = email
        this.psssword = psssword
    }

    constructor(username: String?, email: String?, bio: String?) {
        this.username = username
        this.email = email
        this.bio = bio
    }

    constructor(image: String?, username: String?, email: String?, bio: String?) {
        this.image = image
        this.username = username
        this.email = email
        this.bio = bio
    }
}