
Borrower plan generator:

* To build: mvn clean package
    
* To test: mvn test
    
* To run: mvn spring-boot:run
     
* Then:

<pre>

  curl --request POST --header "Content-Type: application/json" \
  --data '{"username":"xyz","password":"xyz"}' \
  http://localhost:8080/generate-plan | jq
  
</pre>  