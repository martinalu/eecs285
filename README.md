Primer for v0.5 Spec
====================

<b> OI! CHECK THE img FOLDER</b>

###### This is not a spec. I'll put together a full project spec Saturday after our meeting.


There probably isn't time for a full 1.0, so we're just gonna have to go for a really good 0.5 build. The main points are that we'll be using the core technolgies specified below. Make sure you check out the links and read them througoughly, especially if you plan on working on any specific element.

## New Project Folder

I've added a new project folder to the GitHub repository. This is our v0.5, and where all the new code is going to go. Some of you may not know how to add the new project folder to eclipse through the .git repo. You should definitely do this in order to stay up to date.

1. Go to Eclipse.
* Right click the empty space in the package explorer, below your other projects.
* Select "Import"
* Navigate to the "Git" folder and click the dropdown arrow.
* Select "Projects from Git" and hit next.
* Select "Existing local repository"
* If you don't immediately see the eecs285 git repository, click add and navigate to the folder where your .git file for eecs285 is.
* Once you've found it and it's been added to the list, select that repository and click next.
* Select the new project folder you added earlier and click next.
 * Make sure the "Import existing projects" radio button is selected at the top.
* Now, select "Finish" and the project should import.

Now, the new project is synced to your local

*Note: There may be a better way of doing this. If so, let me know. The above method should work for everyone, though.*


## Core Technologies
##### GUI : Java SE 8 with JavaFX
*There are a bunch of new layouts. Check them out.*

##### HTML & CSS : Twitter Bootstrap
*Using the grid system with Bootstrap is gonna make the website layout stuff much easier.*

##### Database : JDBC
*Check out the links below, they'll clear up most questions you may have. Databases are a piece of work, but it's really just another install local to your machine, and the instructions are two paragraphs long.*


## Recommended Reading / Useful Links

[The Proper Git Workflow](https://guides.github.com/introduction/flow/)
>Note: Pull requests are a feature of the github website. We don't need to worry about discussing anything, but if you're unsure about an implementation and you wanna let everyone review the code first, create a pull request by going to the website and hitting the green arrow lookin' button directly above and to the left of the file directory on the project's main page. Remember, we can only see your branch on GitHub if you use "Publish" in the GitHub application, or "push" in the terminal. Otherwise, it stays local and there's no way to review it. Publishing your branches also makes it really easy to see everyone's progress.

[e(fx)clipse](http://www.eclipse.org/efxclipse/install.html#for-the-lazy)
>This will allow you to actually run JavaFX applications in eclipse. Don't ask me why this isn't in the standard distribution.

[JavaFX Tutorial ](http://code.makery.ch/java/javafx-8-tutorial-part1/)
>This is the best tutorial I've encountered so far. As if you read one thing - *one thing* - from this README.md, it better be this page. Skim through it to get the idea if you must, but this clears up a lot. For instance, did you know that main(String[] args) isn't going to do anything anymore? You'll see why reading that page. It also explains our file hierarchy.

[JavaFX Web View](https://docs.oracle.com/javafx/2/webview/jfxpub-webview.htm)
>This is likely going to be the main web view companant. Previously, we were using a JEditorPane displaying to display text with the built a built in option to specify that it was HTML.

[JavaFX Drag'n'Drop](https://docs.oracle.com/javafx/2/drag_drop/jfxpub-drag_drop.htm)
>This documentation goes over the basics of the drag and drop capabilites of JavaFX. An example project can be found in the git repository.

[JDBC Overview](http://www.tutorialspoint.com/jdbc/jdbc-quick-guide.htm)
>Note: You must be running MAMP running. This will allow you to have a local test server. If you don't know how to use MAMP, I'll teach you at meeting.
>OtherNote: You need to download a driver for JDBC. That's something I didn't know. MAMP uses SQL, so we're gonna need to look into how to hookup the MySQL JAR file to our Java Classpaths. If you don't know what I'm talking about, don't worry, follow this [link](http://dev.mysql.com/downloads/file.php?id=454396), download the .zip/.tar (whichever you prefer) and look at the connector-j.pdf located in the "docs" subdirectory.

[Twitter Bootstrap](http://getbootstrap.com/getting-started/#examples)
>If you don't know what this is, it's basically just a couple premade CSS and Javascript files that make building responsive websites easier. A responsive website is a website that restructures it's content when you resize the window. This style of website has become more an more prominant as phones have grown more popular. Fun fact: 1/3rd of all web browsing is done on a phone.

## Adding Demo Projects

It's worth mentioning that you can add your own small example projects to the GitHub as well- the more the merrier. Here's a step-by-step on how to do it.

1. Make sure you've created a new branch in git, and you currently have that branch selected/checked out.
 * Do this either by using the GitHub application, or using git in the terminal.
* Create a new folder for your project in the same directory as your eecs285 .git file.
* Create a new project in Eclipse.
 * Deselect "use default location"
 * Create the project in the same directory you created earlier.
 * Make sure to select Java SE 8
* Now just commit the the changes to your branch.

Now your project is added to your branch. Create a pull request and tell me about it. I'll make sure nothing goes wrong (though there are few ways anything could).
