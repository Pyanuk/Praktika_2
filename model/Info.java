package model;

public class Info {

    private String Name;

    public String getName(){
        return this.Name;
    }
    public void setName(String Name){
        this.Name = Name;
    }


    private String Club;

    public String getClub(){
        return this.Club;
    }

    public void  setClub(String Club) {
        this.Club = Club;
    }


    private int Age;

    public int getAge(){
        return  this.Age;
    }

    public void setAge(int Age){
        this.Age = Age;
    }

   void Name(){

   }

   abstract class Info_Futbilict{
        String Club = "";
   }

   public void Information(){
       System.out.printf("Iмя футболиста: %s, Клуб футболиста: %s, Возраст футболиста: %d", Name, Club, Age );
   }


   
}
