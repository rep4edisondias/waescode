WAES Code Challenge/Assignment

= = = = = = = = = = = = = = = = = = = = = = = = = =
1. Environment:
= = = = = = = = = = = = = = = = = = = = = = = = = =
- Eclipse Luna
- Java8
- Tomcat8
- Local References
	- Jersey 2.9
	- Moxy 2.13
	- Base64 Codec 1.5
	
= = = = = = = = = = = = = = = = = = = = = = = = = =
2. Run testing (Unit and Integration)
= = = = = = = = = = = = = = = = = = = = = = = = = =
	Unit Test : The suite is ready to execute (src\diff.rest.unit.test\AllTests.java: Run As > JUnit Test)
	Integration Test: The suite needs Eclipse Apache Server up and running, please do these steps:
		1. Project: Run As > Run on Server
		2. src\diff.rest.integration.test\AllTests.java: Run As > JUnit Test

= = = = = = = = = = = = = = = = = = = = = = = = = =
3. Generate War File
= = = = = = = = = = = = = = = = = = = = = = = = = =
	Open Eclipse and access: File > Export > Web > War file
	Save War file in local drive
	
= = = = = = = = = = = = = = = = = = = = = = = = = =
4. How to deploy the War File
= = = = = = = = = = = = = = = = = = = = = = = = = =
	Follow the steps below:
	1) Access tomcat management page: http:\\<host:port>\manager
	2) Access GUI section "WAR file to deploy"
	3) Choose File and select the war generated and saved in local drive and click Deploy
	4) Note in Applications section that now app was deployed

= = = = = = = = = = = = = = = = = = = = = = = = = =	
5. How to access the App
= = = = = = = = = = = = = = = = = = = = = = = = = =	
	Using a Rest API Client, you will be able to use:
	1) Post Rest API endpoints (upload Base64 values for document id in Left and Right revisions)
		http:\\<host:port>\v1\diff\<id>\left
		http:\\<host:port>\v1\diff\<id>\right
	*Both endpoints are waiting for JSON with Base64 binary in body and will return a String
	**This is the JSON Format expected in body {"value":"<data encoded in Base64>"}
	***This is the string value format: "Document <id> Revision Uploaded to <Left|Right> with Success"
	
	2) Get Rest API endpoints
		http:\\<host:port>\v1\diff\<id>
	*Endpoint returns a JSON with diff result
	**This is the JSON Format returned: "{"offSet":[<list offset intervals found>],"result":"<Diff result in plain-text format>"}"
	
= = = = = = = = = = = = = = = = = = = = = = = = = =	
6. Improvements
= = = = = = = = = = = = = = = = = = = = = = = = = =	

	1. Replace Revision Bytes using the offSets
	2. Delete Document, using a DELETE HTTP method