

import junit.framework.TestCase;

//You can use this as a skeleton for your 3 different test approach
//It is an optional to use this file, you can generate your own test file(s) to test the target function!
// Again, it is up to you to use this file or not!





public class UrlValidatorTest extends TestCase {


   public UrlValidatorTest(String testName) {
      super(testName);
   }

   
   
   public void testManualTest()
   {
//You can use this function to implement your manual testing	   
	   
   }
   
   
   public void testYourFirstPartition()
   {
	 //You can use this function to implement your First Partition testing	   

   }
   
   public void testYourSecondPartition(){
		 //You can use this function to implement your Second Partition testing	   

   }

   /**
    * Programming Based Unit Tests
    */
   public void testIsValid() {
      int testRounds = 10; // run the test 10 times
      int bugCount = 0;

      // define arrays of valid url parts
      String[] validSchemes = {"http://", "https://", "ftp://"};
      String[] validAuthority = {"www.google.com", "google.com"};
      String[] validPort = {":80", ":9", ":100"};
      String[] validPath = {"/test1", "/"};
      String[] validQueries = {"?action=view", ""};

      // define an array of invalid URLS
      String[] invalidUrls = new String[testRounds];
      int invalidUrlsCount = 0;
      System.out.println("\n***START isValid TEST***\n");
      System.out.println("Testing combinations of valid URL parts to form valid URLs");

      UrlValidator validator = new UrlValidator();

      for(int i = 0; i < testRounds; i++) {
         // randomize url parts by getting random index for each array
         int schemeIndex = (int) (Math.random() * 3);
         int authorityIndex = (int) (Math.random() * 2);
         int portIndex = (int) (Math.random() * 3);
         int pathIndex = (int) (Math.random() * 2);
         int queriesIndex = (int) (Math.random() *2);

         String url = validSchemes[schemeIndex] + validAuthority[authorityIndex] + validPort[portIndex] + validPath[pathIndex] + validQueries[queriesIndex];

         // use the isValid function to check if the final Url is valid
         boolean valid = validator.isValid(url);

         // if the Url is not valid, increase the bug count and add this url to invalidUrls array
         if(valid == false) {
            bugCount++;
            invalidUrls[invalidUrlsCount] = url;
            invalidUrlsCount++;
         }

      }

      // print results
      if(bugCount >0){
         System.out.println("\nNumber of invalid Urls from valid URL parts: " + bugCount);
         System.out.println("\nInvalid URLs: \n");
         for (int j = 0; j < invalidUrls.length; j++) {
            if(invalidUrls[j] != null){
               System.out.println(invalidUrls[j] + "\n");
            }
         }

      }

      try{
         assertEquals(0, bugCount);
         System.out.println("Result: Passed");
      }catch(Error e){
         System.out.println("Result: Failed\n" + e);
         throw e;
      };

      System.out.println("\n***END isValid TEST***\n");


   }


   public void testisValidAuthority()
   {
      UrlValidator validator = new UrlValidator();
      System.out.println("\n***START isValidAuthority TEST***");
      try{
         System.out.println("\nTesting www.google.com:80");
         assertEquals( true,validator.isValidAuthority("www.google.com:80"));
         System.out.println("\nTesting www.google.com:3000");
         assertEquals(true,validator.isValidAuthority("www.google.com:3000"));
         System.out.println("\nTesting www.google.com:900000");
         assertEquals(false,validator.isValidAuthority("www.google.com:900000"));
         System.out.println("Result: Passed");
      }catch(Error e){
         System.out.println("Result: Failed\n" + e);
         throw e;
      };

      System.out.println("\n***END isValidAuthority TEST***");
   }

   public void testisValidSchemeTest()
   {
      UrlValidator validator = new UrlValidator();
      System.out.println("\n***START isValidScheme TEST***\n");
      try{
         System.out.println("\nTesting http");
         assertEquals( true,validator.isValidScheme("http"));
         System.out.println("\nTesting https");
         assertEquals( true,validator.isValidScheme("https"));
         System.out.println("\nTesting ftp");
         assertEquals( true,validator.isValidScheme("ftp"));
         System.out.println("\nTesting err");
         assertEquals( false,validator.isValidScheme("err"));
         System.out.println("Result: Passed");
      }catch(Error e){
         System.out.println("Result: Failed\n" + e);
         throw e;
      };
      System.out.println("\n***END isValidScheme TEST***\n");

   }


}
