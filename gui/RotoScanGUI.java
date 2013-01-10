import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.*;
import java.util.Scanner;
import java.lang.Thread;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class RotoScanGUI extends Jpanel{

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

    private JCheckBox NFLBox;
    private JCheckBox MLBBox;
    private JCheckBox NBABox;
    private JCheckBox NHLBox;
    private JCheckBox PGABox;
    private JCheckBox NASCARBox;
    private JCheckBox CFBBox; //checkboxes for various sections of Rotoworld

    private JPanel left;
    private JPanel right; //left and right sides of the window

    private JPanel emailBox;
    private JLabel emailLabel;
    private JPanel textBox;
    private JLabel textLabel;
    private JTextField emailField;
    private JTextField textField;
    private JTextField yourEmailField;
    private JLabel yourEmailLabel;
    private JLabel passwordLabel;
    private JPasswordField passwordField;
    private JButton startStop; //right-side components

    void setup(){

	this.setLayout(new FlowLayout());
	left = new JPanel(new GridLayout(7, 1)); //left side is just checkboxes
	NFLBox = new JCheckBox("Football");
	left.add(NFLBox);
	MLBBox = new JCheckBox("Baseball");
	left.add(MLBBox);
	NBABox = new JCheckBox("Basketball");
	left.add(NBABox);
	NHLBox = new JCheckBox("Hockey");
	left.add(NHLBox);
	PGABox = new JCheckBox("Golf");
	left.add(PGABox);
	NASCARBox = new JCheckBox("NASCAR");
	left.add(NASCARBox);
	CFBBox = new JCheckBox("College Football");
	left.add(CFBBox); //creates checkboxes and adds them to left side

	right = new JPanel(new GridLayout(11,1));
	emailBox = new JCheckBox("Send email?");
	right.add(emailBox);
	textBox = new JCheckBox("Send text message?");
	right.add(textBox);
	emailLabel = new JLabel("Destination email address:");
	right.add(emailLabel);
	emailField = new JTextField();
	right.add(emailField);
	textLabel = new JLabel("Destination text message address:");
	right.add(textLabel);
	textField = new JTextField();
	right.add(textField);
	yourEmailLabel = new JLabel("Your email address:");
	right.add(yourEmailLabel);
	yourEmailField = new JTextField();
	right.add(yourEmailField);
	passwordLabel = new JLabel("Your email password:");
	right.add(passwordLabel);
	passwordField = new JPasswordField();
	right.add(passwordField);
	startStop = new JButton("Start!");
	right.add(startStop); //adds right-side components
	
	startStop.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
		    if (startStop.getText().equals("Start!")){
			startStop.setText("Stop!");
			if (emailBox.isSelected())
			    email = yourEmailField.getText();
			else
			    email = ""; //sets email
			to = emailField.getText(); //gets destination address
			password = passwordField.getText(); //gets password
			if (textBox.isSelected())
			    text = textField.getText();
			else
			    text = ""; //gets text message address
			if (NFLBox.isSelected()){
			    NFLRoto = new ScanRoto(NFLURL, email, to, password, text);
			    NFLThread = new Thread(NFLRoto, "NFL");
			    NFLThread.start();
			} //creates NFL thread

			if (MLBBox.isSelected()){
			    MLBRoto = new ScanRoto(MLBURL, email, to, password, text);
			    MLBThread = new Thread(MLBRoto, "MLB");
			    MLBThread.start();
			} //creates MLB thread
			    
			if (NBABox.isSelected()){
			    NBARoto = new ScanRoto(NBAURL, email, to, password, text);
			    NBAThread = new Thread(NBARoto, "NBA");
			    NBAThread.start();
			} //creates NBA thread

			if (NHLBox.isSelected()){
			    NHLRoto = new ScanRoto(NHLURL, email, to, password, text);
			    NHLThread = new Thread(NHLRoto, "NHL");
			    NHLThread.start();
			} //creates NHL thread

			if (PGABox.isSelected()){
			    PGARoto = new ScanRoto(PGAURL, email, to, password, text);
			    PGAThread = new Thread(PGARoto, "PGA");
			    PGAThread.start();
			} //creates PGA thread

			if (NASCARBox.isSelected()){
			    NASCARRoto = new ScanRoto(NASCARURL, email, to, password, text);
			    NASCARThread = new Thread(NASCARRoto, "NASCAR");
			    NASCARThread.start();
			} //creates NASCAR thread

			if (CFBBox.isSelected()){
			    CFBRoto = new ScanRoto(CFBURL, email, to, password, text);
			    CFBThread = new Thread(CFBRoto, "CFB");
			    CFBThread.start();
			} //creates college football thread
			NFLBox.setEnabled(false);
			MLBBox.setEnabled(false);
			NBABox.setEnabled(false);
			NHLBox.setEnabled(false);
			PGABox.setEnabled(false);
			NASCARBox.setEnabled(false);
			CFBBox.setEnabled(false); //disables all checkboxes
			//once the start button is clicked
		    }

		    else{
			if (NFLBox.isSelected())
			    NFLRoto.stop();
			if (MLBBox.isSelected())
			    MLBRoto.stop();
			if (NBABox.isSelected())
			    NBARoto.stop();
			if (NHLBox.isSelected())
			    NHLRoto.stop();
			if (PGABox.isSelected())
			    PGARoto.stop();
			if (NASCARBox.isSelected())
			    NASCARRoto.stop();
			if (CFBBox.isSelected())
			    CFBRoto.stop(); //stops all running threads
			startStop.setText("Start!");
			NFLBox.setEnabled(true);
			MLBBox.setEnabled(true);
			NBABox.setEnabled(true);
			NHLBox.setEnabled(true);
			PGABox.setEnabled(true);
			NASCARBox.setEnabled(true);
			CFBBox.setEnabled(true); //enables all checkboxes
		    }
		});
	    }
	
	
    }
    public RotoScanGUI(){
	setup(); //we create the panel
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
	private boolean running;
	public ScanRoto(String url, String fromEmail, String toEmail, String password, String text){
	    this.url = url;
	    this.fromEmail = fromEmail;
	    this.toEmail = toEmail;
	    this.password = password;
	    this.toText = text; //stores local variables
	    this.running = true; //sets thread state to running
	}

	public void stop(){
	    running = false;
	}
    
	public void run(){
	    this.last = null; //initially no blurb loaded
	    while (running){
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
