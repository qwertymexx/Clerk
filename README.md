# Clerk
Welcome to the world easy - best document/template generator from google_doc to pdf.

How to Use:
1. Download your google secret file and placed it inside our source/resources directory into file 'google_secrets.json'. Do not commit your changed file into repository.
2. In terminal `sbt` `run`  - start `TryMe.class`
3. The server starts and following url's are available:
* http://localhost:8080/hello/{put_your_email}
* http://localhost:8080/hello/{put_your_email}/access?code={put_google_acces_code}
* http://localhost:8080/tokens/{put_your_email}

4.Tbd


___



#####TODO

Create following logic 
* get document {template}
* get token collection from {template}
* submit new template (if exist update) -> return version and the previos version info
* generate pdf (token collection): document.pdf
* generate pdf (token collection): link on public/private google drive document.pdf



