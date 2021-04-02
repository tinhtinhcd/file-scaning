# file-scanner
This is the interview assignment from opswat.

Problem Description:
Generate a simple program to scan a file against our metadefender.opswat.com API. OPSWAT online help contains details of our publicly
available API along with sample code that shows how to scan a file. However, it is costly to multi-scan a file so we would like you to implement a
hash lookup prior to deciding to upload a file, either way you should retrieve results and display them. Please read through the documentation
and sample code found at https://onlinehelp.opswat.com/mdcloud/2._Public_APIs.html to perform the following logic.

Calculate the hash of the given samplefile.txt
- Perform a hash lookup against metadefender.opswat.com and see if their are previously cached results for the file
- If results found then skip to 6
- If results not found then upload the file, receive a data_id
- Repeatedly pull on the data_id to retrieve results
- Display results in format below
- You should also have some basic error handling for common HTTP results, but its not necessary to account for every idiosyncrasy of our API. You can show any errors to the standard error and exit the application.

To running the application:
- checkout source code.
- using maven command: 'mvn clean install' to build and package applications. Please make sure you have JDK 8+.
- config the apikey in your environment variable : opswat-apikey-demo-scan-file={your apikey}
- run the application: java -jar {your jar file, default will be in folder target in your source code after install successfully} 

