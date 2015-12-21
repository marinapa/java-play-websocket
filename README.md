Data collection service

Application collects and stores user data into DB.
User can add new field, modify or delete existing one.
 
Content on response page and header info are updated automatically after user submit data.

To run application you can use play activator(https://www.playframework.com).
Config your system. Go to the application directory and execute in 
cmd : activator run.

Application will be able on http://localhost:9000

To storage of data is used PostgreSQL. Database stored on a remote server. DB schemas created automatically used Hibernate ORM. 



