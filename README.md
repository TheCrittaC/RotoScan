This program scans the Rotoworld website for updates and can email individual
blurbs to a desired email address. It can also send these as 160-character
blocks, so one can send them to a phone as a text message. However, the "email
address" of the phone must be specified in the program (for example, Verizon's
are phonenumber@vtext.com).

As of version 0.7, there is also a rudimentary Swing GUI included with the
program. Executable .jar files of both the CLI and GUI versions of RotoScan
can be found in the [GitHub repository](https://github.com/TheCrittaC/RotoScan).

A screenshot of the GUI version is shown below.

![RotoScan GUI Screenshot](https://i.minus.com/ib2m3J61tI0Ht5.png)

The code to send email via a GMail address was modified from code at [this page](http://www.mkyong.com/java/javamail-api-sending-email-via-gmail-smtp-example/).

This program uses JSoup and JavaMail. Both of these jar files are included in
the GitHub repository if you wish to compile your own version of this software.

JSoup is distributed under the terms of the [MIT License](http://opensource.org/licenses/MIT).

JavaMail is distributed under the terms of the [Oracle Binary Code License
Agreement for Java EE Technologies](http://download.oracle.com/otn-pub/java/licenses/OTN_JavaEE_Legacy_Binary-Code-License_30Jan2012.txt).

My code (all other .java files included in this repository) is licensed under
the GNU General Public license, version two or later. Version two is included
in this GitHub repository as LICENSE.txt.