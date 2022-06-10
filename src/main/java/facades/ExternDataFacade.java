package facades;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.BoredDTO;
import dtos.Bored_DogFactDTO;
import dtos.DogFactDTO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class ExternDataFacade {
   private static ExternDataFacade instance;
   private static EntityManagerFactory emf;

   private static Gson gson = new GsonBuilder().setPrettyPrinting().create();

   private final static String BORED_URL = "https://www.boredapi.com/api/activity";
   private final static String DOGFACT_URL = "https://some-random-api.ml/facts/dog";

   //Private Constructor to ensure Singleton
   private ExternDataFacade() {}

   /**
    *
    * @param _emf
    * @return an instance of this facade class.
    */
   public static ExternDataFacade getExternDataFacade(EntityManagerFactory _emf) {
      if (instance == null) {
         emf = _emf;
         instance = new ExternDataFacade();
      }
      return instance;
   }

   private EntityManager getEntityManager() {
      return emf.createEntityManager();
   }

   public String getData(String uRL_STRING){
      String jsonStr = "";

      try {
         URL url = new URL(uRL_STRING);
         HttpURLConnection con = (HttpURLConnection) url.openConnection();
         con.setRequestMethod("GET");
         con.setRequestProperty("Accept", "application/json");
         con.setRequestProperty("User-Agent", "MyApp");
         try (Scanner scan = new Scanner(con.getInputStream())) {
            while (scan.hasNext()) {
               jsonStr += scan.nextLine();
            }
         }
      } catch (IOException ioex) {
         System.out.println("Error: " + ioex.getMessage());
      }
      return jsonStr;
   }

   private DogFactDTO getDogFact(){
      return gson.fromJson(getData(DOGFACT_URL), DogFactDTO.class);
   }

   private BoredDTO getBored(){
      return gson.fromJson(getData(BORED_URL), BoredDTO.class);
   }

   public Bored_DogFactDTO getCombinedData(){
      DogFactDTO dogFactDTO = getDogFact();
      BoredDTO boredDTO = getBored();

      return new Bored_DogFactDTO(dogFactDTO, boredDTO);
   }

}
