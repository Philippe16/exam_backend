package entities;

public class DogFact {
   private String fact;

   public DogFact(){

   }

   public DogFact(String fact){
      this.fact = fact;
   }

   public String getFact() {
      return fact;
   }

   public void setFact(String fact) {
      this.fact = fact;
   }
}
