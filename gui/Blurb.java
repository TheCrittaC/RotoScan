import org.jsoup.nodes.Element;

public class Blurb{

    private String playerName;
    private String position;
    private String team;
    private String report;
    private String impact;
    private boolean completeBlurb; //local variables


    public Blurb(Element e){
	completeBlurb = true; //assumes blurb is complete unless otherwise set
	this.playerName = e.select("div[class=player]").toString().split(">")[2].split("<")[0];
	this.position = e.select("div[class=player]").toString().split(">")[3].split("- ")[1];
	this.team = e.select("div[class=player]").toString().split(">")[4].split("<")[0];
	this.report = e.select("div[class=report]").toString().replaceAll("<i>", "");
	this.report = this.report.replaceAll("</i>", ""); //remove italics tags
	this.report = this.report.split("<p>")[1].split("</p>")[0];
	this.report = this.report.replaceAll("&quot;", "\"");
	this.report = this.report.replaceAll("\n", ""); //removes newlines
	try{
	    this.impact = e.select("div[class=impact]").toString().replaceAll("<i>", "");
	    this.impact = this.impact.replaceAll("</i>", "");
	    //remove italics tags
	    this.impact = this.impact.split(">\n  ")[1].split("<")[0];
	    //gets the data from the page
	    this.impact = this.impact.replaceAll("&quot;", "\""); 
	    //changes HTML quotes to actual quotes
	    this.impact = this.impact.replaceAll("\n", ""); //removes newlines
	} catch (Exception exc) {
	    this.impact = "";
	    completeBlurb = false;
	    //since there is no impact, the blurb is not complete and we
	    //do not want to send it yet
	}
    }

    public boolean isComplete(){
	return completeBlurb; //returns if blurb is complete or not
    }

    public String toString(){
	return playerName + " - " + position + " - " + team + "\n" + report + "\n" + impact;
	//converts to the following form
	/*
	  Player Name - Position - Team
	  Report
	  Impact */
    }
    

}			  
