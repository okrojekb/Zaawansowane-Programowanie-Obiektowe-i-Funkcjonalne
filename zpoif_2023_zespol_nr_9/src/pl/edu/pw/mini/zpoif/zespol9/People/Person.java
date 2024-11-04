package pl.edu.pw.mini.zpoif.zespol9.People;

public class Person {

    protected SignInData signInData;
    private String name;
    private String surname;

    public Person(String name, String surname) {
        this.signInData = new SignInData(name, surname);
        this.name = name;
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public SignInData getSignInData() {
        return signInData;
    }

}
