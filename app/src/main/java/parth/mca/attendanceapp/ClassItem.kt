package parth.mca.attendanceapp

open class ClassItem {

    var id : Long = 0
        get() = field
        set(value) {
            field = value
        }

    var Class = ""
        get() = field
        set(value) {
            field = value
        }

    var Subject = ""
        get() = field
        set(value) {
            field = value
        }


    constructor(Date : String,Place:String){
        this.Class = Date
        this.Subject = Place
    }

    constructor(id: Long, Class: String, Subject: String) {
        this.id = id
        this.Class = Class
        this.Subject = Subject
    }


}