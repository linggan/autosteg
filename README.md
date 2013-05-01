You only need to compile the AutoSteg class: javac AutoSteg.java.  
As for running it, here's the format: java AutoSteg directory_filepath [e/x] [options]

The command has only been tested with directories of only pictures--don't know how it handles other file types.

e is for the Embed function.
x is for the Extract function.


You have the following options:

-e <file to embed>  default: embed nothing

-p <password>  	default: "abc123", only used when -e is specified

-q <quality 0 ... 100>	default: 80

-c <comment>		default: "JPEG Encoder Copyright 1998, James R. Weeks and BioElectroMech.  "
