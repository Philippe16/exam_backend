package dtos;

import entities.DogFact;

public class DogFactDTO {
   private String fact;

   public DogFactDTO(){

   }

   public DogFactDTO(DogFact dogFact){
      this.fact = dogFact.getFact();
   }

   public String getFact() {
      return fact;
   }

   public void setFact(String fact) {
      this.fact = fact;
   }
}
