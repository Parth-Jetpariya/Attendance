package parth.mca.attendanceapp

class StudentItem {
    var sid : Long = 0
        get() = field
        set(value) {
            field = value
        }

    var Roll = ""
        get() = field
        set(value) {
            field = value
        }

    var Name = ""
        get() = field
        set(value) {
            field = value
        }

    var Status = ""
        get() = field
        set(value) {
            field = value
        }

    constructor(roll: String, name: String) {
        this.Roll = roll
        this.Name = name
        Status = ""
    }

    constructor(sid: Long, Roll: String, Name: String) {
        this.sid = sid
        this.Roll = Roll
        this.Name = Name
        Status = ""
    }

    constructor(sid: Long, Roll: String, Name: String, Status: String) {
        this.sid = sid
        this.Roll = Roll
        this.Name = Name
        this.Status = Status
    }

    constructor()


}