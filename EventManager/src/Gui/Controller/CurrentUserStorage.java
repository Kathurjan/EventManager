package Gui.Controller;

import BE.Person;

public class CurrentUserStorage {

    private static CurrentUserStorage instance;

    private Person currentPerson;

    private CurrentUserStorage(){

    }

    public static CurrentUserStorage getInstance(){
        if(instance == null){
            instance = new CurrentUserStorage();
        }
        return instance;
    }

    public Person getCurrentPerson() {
        return currentPerson;
    }

    public void setCurrentPerson(Person currentPerson) {
        this.currentPerson = currentPerson;
    }
}
