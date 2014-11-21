Core Technologies
====

GUI : Java SE 8 with JavaFX
There are a bunch of new layouts. Check them out.

HTML & CSS : Twitter Bootstrap

Database : JDBC



We're going to create a JavaFX based application. Java 8 introduced a few new technologies, and it's going to require us to do a bit of reading/work, but I guarantee you we'll be experts at this shit overnight.

Things I've learned so far.
There are new layout managers in Java8 that are way more logical than the ones in Java7. There is also a "Web Engine" object that inherits from the java.scene.node class. Don't know what a node is? Don't worry about it. The short explanation is that it's an element of the scene. A scene is not the overall window pane.


Useful links

Needed for Java SE 8 & JavaFX
http://www.eclipse.org/efxclipse/install.html#for-the-lazy

JavaFX Layout Managers
https://blog.idrsolutions.com/2014/05/layout-manager-swing-javafx-tutorial/
https://docs.oracle.com/javafx/2/layout/jfxpub-layout.htm

JavaFX Web View
https://docs.oracle.com/javafx/2/webview/jfxpub-webview.htm

JDBC Overview
http://www.tutorialspoint.com/jdbc/jdbc-quick-guide.htm
Note: You must be running MAMP running. This will allow you to have a local test server. If you don't know how to use MAMP, I'll teach you at meeting.
OtherNote: You need to download a driver for JDBC. That's something I didn't know. MAMP uses SQL, so we're gonna need to look into how to hookup the MySQL JAR file to our Java Classpaths. If you don't know what I'm talking about, don't worry, follow this link http://dev.mysql.com/downloads/file.php?id=454396, download the .zip/.tar (whichever you prefer) and look at the connector-j.pdf located in the "docs" subdirectory.

The Proper Git Workflow
https://guides.github.com/introduction/flow/
Note: Pull requests are a feature of the github website. We don't need to worry about discussing anything, but if you're unsure about an implementation and you wanna let everyone review the code first, create a pull request by going to the website and hitting the green arrow lookin' button directly above and to the left of the file directory on the project's main page. Remember, we can only see your branch on GitHub if you use "Publish" in the GitHub application. Otherwise, it stays local and there's no way to review it. Publishing your branches also makes it really easy to see everyone's prgress.
