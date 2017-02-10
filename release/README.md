The Observers CSSE374 Project
Keennan French and Joseph Brown

To run the program, run the following command:
java -jar myproject.jar <<Command line arguments>>

The following are command line arguments that you can use (all separated by spaces):

whitelist: Specify a list of class names to show in the UML diagram.
blacklist:  Specify a list of package names to not show in the UML diagram.
recursive: (true or false) Create the UML from recursive inheritance of the whitelisted classes
synthetic: (true or false) Show synthetic methods or not (like $lambda)
visibility: (public, protected, private)  the levels of 
detectors: List of detector classes to run on the UML (must extend ABstractDetector)


There are 2 different ways to use the above command line arguments: With or without a settings file.

Option 1) Specify a settings file with the command --settingsfile=fullpath
The settingsfile specified by this path must be a .properties file with all the above commands on new lines specified with =.  For example:
whitelist=list...
blacklist=list...
... etc


Option 2) Running with explicit command line arguments. Note: any unspecified args are substituted by a default settings file.
Run the program by typing in a list of class names in the run configuration separated by spaces.
Optional arguments for the visibility of methods/classes/fields: use --public, --private, or --protected. 
To see diagram with recursive inheritance, use -r.
All other options must be specified in a settings file.




![alt tag](release/Design.png)