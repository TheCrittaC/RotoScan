import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.*;
import java.util.Scanner;
import java.lang.Thread;
public class RotoScan{
    public static void main(String args[]){
	Scanner scan = new Scanner(System.in);
	String input;
	String email = "";
	String text = "";
	String to = "";
	String password = ""; //initial strings that are initialized
	String NFLURL = "http://www.rotoworld.com/playernews/nfl/football/";
	String MLBURL = "http://www.rotoworld.com/playernews/mlb/baseball/";
	String NBAURL = "http://www.rotoworld.com/playernews/nba/basketball/";
	String NHLURL = "http://www.rotoworld.com/playernews/nhl/hockey/";
	String PGAURL = "http://www.rotoworld.com/playernews/gol/golf/";
	String NASCARURL = "http://www.rotoworld.com/playernews/nas/NASCAR/";
	String CFBURL = "http://www.rotoworld.com/playernews/cfb/nfl-draft"; //URLs
	Thread NFLThread;
	Thread MLBThread;
	Thread NBAThread;
	Thread NHLThread;
	Thread PGAThread;
	Thread NASCARThread;
	Thread CFBThread; //threads
	ScanRoto NFLRoto;
	ScanRoto MLBRoto;
	ScanRoto NBARoto;
	ScanRoto NHLRoto;
	ScanRoto PGARoto;
	ScanRoto NASCARRoto;
	ScanRoto CFBRoto; //ScanRoto objects

	System.out.println("Do you want this program to send emails?");
	input = scan.nextLine();
	if (input.startsWith("y") || input.startsWith("Y")){
	    System.out.println("What is your email address? GMail only at this time.");
	    email = scan.nextLine();
	    System.out.println("Where will these emails be going?");
	    to = scan.nextLine();
	    System.out.println("What is your GMail password?");
	    password = scan.nextLine();
	} //gets user email information
	System.out.println("Do you want to send text messages?");
	input = scan.nextLine();
	if (input.startsWith("y") || input.startsWith("Y")){
	    if ((email.equals(""))){
		System.out.println("What is your email address? GMail only at this time.");
		email = scan.nextLine();
		System.out.println("What is your GMail password?");
		password = scan.nextLine();
	    } //gets email account info if it is not already entered
	    System.out.println("Where will these messages be going? Enter an email address.");
	    text = scan.nextLine();
	}
	System.out.println("Do you want to scan for football updates?");
	input = scan.nextLine();
	if (input.startsWith("y") || input.startsWith("Y")){
	    NFLRoto = new ScanRoto(NFLURL, email, to, password, text);
	    NFLThread = new Thread(NFLRoto, "NFL");
	    NFLThread.start();
	} //creates NFL thread
	
	System.out.println("Do you want to scan for baseball updates?");
	input = scan.nextLine();
	if (input.startsWith("y") || input.startsWith("Y")){
	    MLBRoto = new ScanRoto (MLBURL, email, to, password, text);
	    MLBThread = new Thread(MLBRoto, "MLB");
	    MLBThread.start();
	} //creates MLB thread
	
	System.out.println("Do you want to scan for basketball updates?");
	input = scan.nextLine();
	if (input.startsWith("y") || input.startsWith("Y")){
	    NBARoto = new ScanRoto (NBAURL, email, to, password, text);
	    NBAThread = new Thread(NBARoto, "NBA");
	    NBAThread.start();
	} //creates NBA thread
	
	System.out.println("Do you want to scan for hockey updates?");
	input = scan.nextLine();
	if (input.startsWith("y") || input.startsWith("Y")){
	    NHLRoto = new ScanRoto (NHLURL, email, to, password, text);
	    NHLThread = new Thread(NHLRoto, "NHL");
	    NHLThread.start();
	} //creates NHL thread
	
	System.out.println("Do you want to scan for Golf updates?");
	input = scan.nextLine();
	if (input.startsWith("y") || input.startsWith("Y")){
	    PGARoto = new ScanRoto (PGAURL, email, to, password, text);
	    PGAThread = new Thread(PGARoto, "PGA");
	    PGAThread.start();
	} //creates PGA thread
	
	System.out.println("Do you want to scan for NASCAR updates?");
	input = scan.nextLine();
	if (input.startsWith("y") || input.startsWith("Y")){
	    NASCARRoto = new ScanRoto (NASCARURL, email, to, password, text);
	    NASCARThread = new Thread(NASCARRoto, "NASCAR");
	    NASCARThread.start();
	} //creates NASCAR thread
	
	System.out.println("Do you want to scan for college football updates?");
	input = scan.nextLine();
	if (input.startsWith("y") || input.startsWith("Y")){
	    CFBRoto = new ScanRoto (CFBURL, email, to, password, text);
	    CFBThread = new Thread(CFBRoto, "CFB");
	    CFBThread.start();
	} //creates college football thread
    }
}

class ScanRoto implements Runnable{
    private String url; //url for the thread
    private static String fromEmail; //source email address
    private static String toEmail; //destination email address
    private static String toText; //destination text messaging address
    private static String password; //GMail password 
    private Document doc; //document for the page
    private Blurb last; //the previous blurb
    private Blurb latest; //the latest blurb
    private Elements blurbs; //all of the blurbs on the page
    private String blurbString; //the string form of the most recent blurb
    public ScanRoto(String url, String fromEmail, String toEmail, String password, String text){
	this.url = url;
	this.fromEmail = fromEmail;
	this.toEmail = toEmail;
	this.password = password;
	this.toText = text; //stores local variables
    }
    public void run(){
	this.last = null; //initially no blurb loaded
	while (true){
	    try{
		this.doc = Jsoup.connect(this.url).get(); //gets the page
	    } catch(Exception e) {
		e.printStackTrace();
	    }
	    this.blurbs = this.doc.select("div[class=pb]"); //creates a list of blurbs
	    this.latest = new Blurb(this.blurbs.first()); //gets first blurb
	    this.blurbString = this.latest.toString(); //gets blurb in string form
	    if (this.last == null || !(this.last.toString().equals(this.latest.toString()))){
		//checks for updates to blurb
		this.last = this.latest; //updates last if there is a new blurb
		if (this.last.isComplete()){
		    //if blurb is complete (has impact), proceed
		    if (!(toEmail.equals("")))
			mailBlurb(blurbString);
		    //email blurb if we have an email address
		    if (!(toText.equals("")))
			textBlurb(blurbString);
		    //texts blurb if we have a text email address
		}
		    
	    }
	    try{
		Thread.sleep(60000); //sleeps for a minute
	    } catch (Exception e) {
		e.printStackTrace();
	    } 
	}
    }
    
    public static void mailBlurb(String text){
	SendEmail.send(fromEmail, toEmail, password, text);
	//sends the email
    }

    public static void textBlurb(String text){
	for (int i = 0; i < text.length(); i+= 159){
	    if (i + 159 > text.length())
		SendEmail.send(fromEmail, toText, password, text.substring(i, text.length()));
	    //sends email as text message in 160-character blocks
	    else
		SendEmail.send(fromEmail, toText, password, text.substring(i, i + 159));
	    //if we are at the end of the string, we send the rest
	}
    }
}
