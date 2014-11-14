eecs285
=======

EECS 285 : Preliminary Spec : v0.1

##Abstract
The first prototype is going to be a proof of concept. This means that we don't have to worry about 90% of the project when we first start. The idea is to make sure that the base level of implementation works for us. There will be a limited feature set to implement on this first go round.

##GUI Description
There will be basic HTML template that we will be customizing and rendering. There will be two main panels.^(b) Customization of the HTML will be afforded through four main sections on the left side.

* Left Panel : This is where our element customization will go.
  * Header Content - The raw text inside the <h> html tag.
    * Content Options - User can select ONE of TWO fonts. Georgia or Arial.
  * Paragraph Content - The raw text inside the <\p> html tag.
    * Content Options - User can select from ONE of TWO font weights. 8 and 14.
  * Add Image Button - This will add an image to the project directory and update the html <img> tag's src attribute to reflect the location of the image. If no image is selected, use a default.
  *  Export Website - This button will allow the user to specify a location to export a folder containing the index.html file and the image selected by the user. If the user selects no image, a default should be used.
        EX: (User selects home directory)
            ~/myWebsite/index.html
            ~/myWebsite/myImage.jpg

    The right panel will be some sort of preview of the page. It should be a genuine render of the HTML.

    There is an accompanying image that outlines the general structure of the program. Specific implementation is up to the individual working on that piece of code unless otherwise agreed upon. In other words, no one has the time or the patience to micromanage, so do what you think is best. However, if you think a certain GUI element or package might influence someone else's code, then obviously be sure to inform that person.^(b)

(a) : Note: I am describing the project, not defining specific implementation. i.e. When I say "Panel", I don't mean "JPanel". I am merely describing an element. Implementation is up to you unless otherwise agreed upon.


(b) : "Should I use JDialogBox, JMessageBox, or JOptionPane?" - I have no idea.
    - Try one.

"I want to use X element, but using X will mean that we can't use elements A or B in the main left Panel"
    - Good to know, lets talk about our options.
