# Am I Unique ? Source #

This repository contains all the source code from the [AmIUnique.org](https://amiunique.org/) website.

### Requirements ###
**JDK8** is needed to build the application.

### Database ###
A database is needed to store the fingerprints.
To facilitate the creation process, the "**fpDB.sql**" file located at the root of the repository contains the structure of the table. You just need to import it with mysqldump or through phpMyAdmin to have everything set up properly.
Then, you have to modify the  "**amiunique-source/website/conf/application.conf**" to include your database connection credentials (user and password).
Same action required in "**amiunique-source /website/conf/META-INF/persistence.xml**" to activate the persistence of data.

### Run ###
Like other play applications, run "**./activator run**" at the root of the website directory to launch the web application.  You will then be able to access your app via "**localhost:9000**".
You may also need to regenerate the Play application secret. To do so, run "**play-update-secret**" in the Play console.


