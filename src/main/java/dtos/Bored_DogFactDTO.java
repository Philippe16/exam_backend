package dtos;

public class Bored_DogFactDTO { 
   private String dogfact;
   private String randomActivity;

   public Bored_DogFactDTO(DogFactDTO dogFactDTO, BoredDTO boredDTO){
      this.dogfact = dogFactDTO.getFact();
      this.randomActivity = boredDTO.getActivity();
   }

   public String getDogfact() {
      return dogfact;
   }

   public void setDogfact(String dogfact) {
      this.dogfact = dogfact;
   }

   public String getRandomActivity() {
      return randomActivity;
   }

   public void setRandomActivity(String randomActivity) {
      this.randomActivity = randomActivity;
   }
}
