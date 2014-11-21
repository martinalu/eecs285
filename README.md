Primer for v0.5 Spec
=======

There probably isn't time for a full 1.0, so we're just gonna have to go for a really good 0.5 build. The main points are that we'll be using the core technolgies specified below. Make sure you check out the links and read them througoughly, especially if you plan on working on any specific element.

## Core Technologies
##### GUI : Java SE 8 with JavaFX  
*There are a bunch of new layouts. Check them out.*

##### HTML & CSS : Twitter Bootstrap  
*Using the grid system with Bootstrap is gonna make the website layout stuff much easier.*

##### Database : JDBC  
*Check out the links below, they'll clear up most questions you may have. Databases are a piece of work, but it's really just another install local to your machine, and the instructions are two paragraphs long.*


## Useful Links

[The Proper Git Workflow](https://guides.github.com/introduction/flow/)
>Note: Pull requests are a feature of the github website. We don't need to worry about discussing anything, but if you're unsure about an implementation and you wanna let everyone review the code first, create a pull request by going to the website and hitting the green arrow lookin' button directly above and to the left of the file directory on the project's main page. Remember, we can only see your branch on GitHub if you use "Publish" in the GitHub application, or "push" in the terminal. Otherwise, it stays local and there's no way to review it. Publishing your branches also makes it really easy to see everyone's progress.

[Needed for Java SE 8 & JavaFX](http://www.eclipse.org/efxclipse/install.html#for-the-lazy)
>This will allow you to actually run JavaFX applications in eclipse. Don't ask me why this isn't in the standard distribution.

[JavaFX Layout Managers](https://blog.idrsolutions.com/2014/05/layout-manager-swing-javafx-tutorial/)
>Java SE 8 and JavaFX introduced now, simpler layout managers called Layout Panes. They might be worth checking out, but we'll probably just hard code the coordinates of all the elements in the GUI. It's worth taking a look at, though. There may be something that makes things easier on us down the line. Some additional reading can be found [here](https://docs.oracle.com/javafx/2/layout/jfxpub-layout.htm).

[JavaFX Web View](https://docs.oracle.com/javafx/2/webview/jfxpub-webview.htm)
>This is likely going to be the main web view companant. Previously, we were using a JEditorPane displaying to display text with the built a built in option to specify that it was HTML.

[JDBC Overview](http://www.tutorialspoint.com/jdbc/jdbc-quick-guide.htm)
>Note: You must be running MAMP running. This will allow you to have a local test server. If you don't know how to use MAMP, I'll teach you at meeting.
>OtherNote: You need to download a driver for JDBC. That's something I didn't know. MAMP uses SQL, so we're gonna need to look into how to hookup the MySQL JAR file to our Java Classpaths. If you don't know what I'm talking about, don't worry, follow this [link](http://dev.mysql.com/downloads/file.php?id=454396), download the .zip/.tar (whichever you prefer) and look at the connector-j.pdf located in the "docs" subdirectory.

[Twitter Bootstrap](http://getbootstrap.com/getting-started/#examples)
>If you don't know what this is, it's basically just a couple premade CSS and Javascript files that make building responsive websites easier. A responsive website is a website that restructures it's content when you resize the window. This style of website has become more an more prominant as phones have grown more popular. Fun fact: 1/3rd of all web browsing is done on a phone.
