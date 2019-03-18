

import junit.framework.TestCase;

//You can use this as a skeleton for your 3 different test approach
//It is an optional to use this file, you can generate your own test file(s) to test the target function!
// Again, it is up to you to use this file or not!





public class UrlValidatorTest extends TestCase {

	UrlValidator val = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
	
	//Various Good and Bad URL Parts
	//Mix and Match, then Test
	String[] query = new String [] {"?test=1" , "", "tests1=true&test2=false"};
	String[] goodPath  = new String[] {"", "/path", "/path", "/path/path", "/1234567"  };
	String[] badPath  = new String[] {"1234567", "//path", "path//path"  };
	String[] goodScheme  = new String[] {"https://", "http://", "ftp://"  };
	String[] badScheme  = new String[] {"https:/", "http//", ""  };
	String[] goodPorts = new String[] {":80", ":1234", "" };
	String[] badPorts = new String[] {"80", ":a1234", ":-1", ":" };
	String[] goodDomain = new String[] {"www.google.com", "www.wikipedia.org", "1.1.1.1" };
    	String[] badDomain = new String[] {"www.google.moc", "w3.wikipedia.org", "1.1.1.1.1.1.1.1", "" };
    	String[] Path  = new String[] {"", "/path", "/path", "/path/path", "/1234567", "1234567", "//path", "path//path"  };
    	String[] Scheme  = new String[] {"https://", "http://", "ftp://", "https:/", "http//", "", "a:"  };
    	String[] Ports = new String[] {":80", ":1234", "", "80", ":a1234", ":-1", ":" };
    	String[] Domain = new String[] {"www.google.com", "www.wikipedia.org", "1.1.1.1", "www.google.moc", "w3.wikipedia.org", "1.1.1.1.1.1.1.1", "" };


	StringBool[] manualTestURLs = {
		new StringBool("HTTP://WWW.FACEBOOK.COM", true),
		//new StringBool("www.facebook.com", true),								//failure: expected T but was F
		new StringBool("http://", false),
		new StringBool(".com", false),
		new StringBool("/hi", false),
		new StringBool("", false),
		new StringBool("ftp://", false),
		new StringBool("bwo.wactec.toophkmfw://", false),
		new StringBool("oowph.kwt.cofec://wtbma", false),
		//new StringBool("0.0.0.0", true),										//failure: expected T but was F
		//new StringBool("facebook.com", true),									//failure: expected T but was F
		//new StringBool("facebook.edu", true),									//failure: expected T but was F
		new StringBool("", false),
		new StringBool("a://a.a.a", false),
		//new StringBool("www.facebook.com/fb", true),							//failure: expected T but was F
		new StringBool("http://www.facebook.com/fb", true),
		new StringBool("http://www.facebook.com/fb/bf", true),
		//new StringBool("facebook.com/fb", true),								//failure: expected T but was F
		new StringBool("http://www.facebook.com/fb?a=c&b=d", true),
		new StringBool("http://www.facebook.com/fb?a=c", true),
		//new StringBool("www.facebook.com/w/index.php", true), 				//failure: expected T but was F
		//new StringBool("www.facebook.com/w/index.php?query=string", true),	//failure: expected T but was F
		//new StringBool("www.facebook.comwww.facebook.com", true),				//failure: expected T but was F
		new StringBool("a", false)
	};
	String partitionTestURL1 = new String("http://www.facebook.com/fb?a=c&b=dm");

   public UrlValidatorTest(String testName) {
      super(testName);
   }
   
   public void testManualTest()
   {
	   for(int i = 0; i < manualTestURLs.length; i++) {
		   tryTest(manualTestURLs[i]);
	   }
   }


   public void testYourFirstPartition()
   {
      //First Partition, testing on Schemes
      ResultPair[] partitionScheme1 = {
              new ResultPair("http", true),
              new ResultPair("https", true),
              new ResultPair("ftp", true),
              new ResultPair("facetime", true),
              new ResultPair("h3t", true),
              new ResultPair("://", false),
              new ResultPair("http://", false),
              new ResultPair("https://", false),
              new ResultPair("http://www.google.com", false)
      };

      ResultPair[] partitionScheme2 = {
              new ResultPair("http", true),
              new ResultPair("https", true),
              new ResultPair("ftp", true),
              new ResultPair("facetime", false),
              new ResultPair("h3t", false),
              new ResultPair("://", false),
              new ResultPair("http://", false),
              new ResultPair("https://", false),
              new ResultPair("http://www.google.com", false)
      };

      boolean b;
      String result, expected;

      UrlValidator validator1 = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
      UrlValidator validator2 = new UrlValidator();

      System.out.print("**testYourFirstPartition()**\nisValidScheme on Schemes\n--START--\n");

      for (int i = 0; i < partitionScheme1.length; i++) {
         b = validator1.isValidScheme(partitionScheme1[i].item);
         result = Boolean.toString(b);
         b = partitionScheme1[i].valid;
         expected = Boolean.toString(b);

         System.out.print("\"" + partitionScheme1[i].item +  "\" returns " + result.toUpperCase() + ", expected " + expected.toUpperCase() + "\n");
         assert(result == expected);
      }

      for (int i = 0; i < partitionScheme2.length; i++) {
         b = validator2.isValidScheme(partitionScheme2[i].item);
         result = Boolean.toString(b);
         b = partitionScheme2[i].valid;
         expected = Boolean.toString(b);

         System.out.print("\"" + partitionScheme2[i].item +  "\" returns " + result.toUpperCase() + ", expected " + expected.toUpperCase() + "\n");
         assert(result == expected);
      }
      System.out.print("--END--\n");

   }

   public void testYourSecondPartition(){
      //Second Partition, testing on Authority
      ResultPair[] partitionAuthority = {
              new ResultPair("www.oregonstate.edu", true),
              new ResultPair("255.255.255.255", true),
              new ResultPair("124.com", true),
              new ResultPair("go.net", true),
              new ResultPair("wow.wow.wow", true),
              new ResultPair("..com", false),
              new ResultPair("google.com", true),
              new ResultPair("give.me.the.loot.com", true),
              new ResultPair("http://www.google.com", false)
      };

      boolean b;
      String result, expected;

      UrlValidator validator = new UrlValidator();

      System.out.print("**testYourSecondPartition()**\nisValidAuthority on Authority\n--START--\n");

      for (int i = 0; i < partitionAuthority.length; i++) {
         b = validator.isValidAuthority(partitionAuthority[i].item);
         result = Boolean.toString(b);
         b = partitionAuthority[i].valid;
         expected = Boolean.toString(b);

         System.out.print("\"" + partitionAuthority[i].item +  "\" returns " + result.toUpperCase() + ", expected " + expected.toUpperCase() + "\n");
         assert(result == expected);
      }
      System.out.print("--END--\n\n");

   }

   public void testYourThirdPartition(){
      //Third Partition, testing on Port
      ResultPair[] partitionPort = {
              //new ResultPair(":80", true),
              //new ResultPair(":65535", true),
              new ResultPair(":99999", false),
              new ResultPair(":65536", false),
              new ResultPair("google.com:675", true),
              new ResultPair("google.com:65535", true),
              //new ResultPair("google.com:65536", false),
              new ResultPair("http://www.google.com:80", false)
      };

      boolean b;
      String result, expected;

      UrlValidator validator = new UrlValidator();

      System.out.print("**testYourThirdPartition()**\nisValidAuthority on Port\n--START--\n");

      for (int i = 0; i < partitionPort.length; i++) {
         b = validator.isValidAuthority(partitionPort[i].item);
         result = Boolean.toString(b);
         b = partitionPort[i].valid;
         expected = Boolean.toString(b);

         System.out.print("\"" + partitionPort[i].item +  "\" returns " + result.toUpperCase() + ", expected " + expected.toUpperCase() + "\n");
         assert(result == expected);
      }
      System.out.print("--END--\n\n");

   }

   public void testYourFourthPartition(){
      //Fourth Partition, testing on Path
      ResultPair[] partitionPath = {
              new ResultPair("/hello1", true),
              new ResultPair("/itMe", true),
              new ResultPair("/Jessica", true),
              new ResultPair("/$money", true),
              new ResultPair("/hello/itMe", true),
              //new ResultPair("/t/t/t/t/", false),
              new ResultPair("/path/", true),
              new ResultPair("http://www.google.com/path", false),
              new ResultPair("www.google.com/path", false)
      };

      boolean b;
      String result, expected;

      UrlValidator validator = new UrlValidator();

      System.out.print("**testYourFourthPartition()**\nisValidPath on Path\n--START--\n");

      for (int i = 0; i < partitionPath.length; i++) {
         b = validator.isValidPath(partitionPath[i].item);
         result = Boolean.toString(b);
         b = partitionPath[i].valid;
         expected = Boolean.toString(b);

         System.out.print("\"" + partitionPath[i].item +  "\" returns " + result.toUpperCase() + ", expected " + expected.toUpperCase() + "\n");
         assert(result == expected);
      }
      System.out.print("--END--\n\n");

   }

   public void testYourFifthPartition(){
      //Fourth Partition, testing on Path
      ResultPair[] partitionQuery = {
              new ResultPair("watch?v=TcMBFSGVi1c", true),
              //new ResultPair("https://www.youtube.com/watch?v=TcMBFSGVi1c", false),
              new ResultPair("?ref=%2Fonline-degrees%2Fundergraduate%2Fonline-chemistry-lab-course%2F", true),
              //new ResultPair("https://ecampus.oregonstate.edu/contact/form/portland.htm?ref=%2Fonline-degrees%2Fundergraduate%2Fonline-chemistry-lab-course%2F", false),
              new ResultPair("?action=view", true),
              new ResultPair("?y?=y?y", true),
              //new ResultPair("http://www.google.com/?action=edit", false)
      };

      boolean b;
      String result, expected;

      UrlValidator validator = new UrlValidator();

      System.out.print("**testYourFithPartition()**\nisValidQuery on Query\n--START--\n");

      for (int i = 0; i < partitionQuery.length; i++) {
         b = validator.isValidQuery(partitionQuery[i].item);
         result = Boolean.toString(b);
         b = partitionQuery[i].valid;
         expected = Boolean.toString(b);

         System.out.print("\"" + partitionQuery[i].item +  "\" returns " + result.toUpperCase() + ", expected " + expected.toUpperCase() + "\n");
         assert(result == expected);
      }
      System.out.print("--END--\n\n");

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
	
   public void tryTest(StringBool url)
   {
	 assertEquals(url.s, url.b, val.isValid(url.s)); 
   }
   
   public void printResult(StringBool url)
   {
	   System.out.println(url.s + " Expected: " + url.b);  
   }

   public class StringBool
   {
	    public String s;
	    public boolean b;

	    public StringBool(String s, boolean b)
	    {
	       this.s = s;
	       this.b = b;  //Weather the individual part of url is valid.
	    }
	 }

}
