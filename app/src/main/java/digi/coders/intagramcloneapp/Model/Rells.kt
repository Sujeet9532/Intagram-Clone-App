package digi.coders.intagramcloneapp.Model

class Rells {
    var rellurl: String=""
    var caption: String=""
    var profileLink: String?=null
    var rellname: String?=null
    constructor()
    constructor(rellurl: String, caption: String) {
        this.rellurl = rellurl
        this.caption = caption
    }

    constructor(rellurl: String, caption: String, profileLink: String?, rellname: String?) {
        this.rellurl = rellurl
        this.caption = caption
        this.profileLink = profileLink
        this.rellname = rellname
    }


}