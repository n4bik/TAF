package pl.tomaszbuga.utils;

import org.apache.commons.lang.RandomStringUtils;

public class Giraffe {
    private String name;
    private String lastname = "Zyrafa";
    private int age;
    private boolean check;

    public String setData (String lastname, int age, boolean check){
        name = RandomStringUtils.randomAlphabetic(18);
        lastname = this.lastname;
        this.age = age;
        System.out.println(this.check);
        this.check = check;

        return lastname;
    }
    public void setData(String lastname){
        this.lastname = lastname;
    }
    @Override
    public String toString()
    { return "Giraffe";}
}
