class Profile {

    nickname
    birthdate
    hornlength
    gender
    attractedToGender
    description
    lastSeen
    photos

    constructor(nickname, birthdate, hornlength, gender, attractedToGender, description, lastSeen, photos) {
        this.nickname = nickname;
        this.birthdate = birthdate;
        this.hornlength = Number.parseInt(hornlength);
        this.gender = Number.parseInt(gender);
        this.attractedToGender = Number.parseInt(attractedToGender);
        this.description = description;
        this.lastSeen = lastSeen;
        this.photos = photos;
    }
}

export {Profile}