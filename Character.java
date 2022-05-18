
//package 
package GuessWhoGame;   

//Imports
import javax.swing.JLabel;

//The character class that extends JLabel
public class Character extends JLabel {

//Instance fields(In other words, attributes for the character profile)
private String Name;
private String hairColour;
private String clothingColour;
private boolean wearsHat;
private boolean hasClaw;
private boolean hasVehicle;
private boolean hasBeard;
private boolean wearsEarings;
private boolean wearsHelmet;
private boolean wearsTie;
private boolean hasBeak;
private boolean hasTail;
private boolean hasGuitar;
private boolean hasScales;
private String filename;

//All of the sets and gets method for the instance fields. Keep in mind that the auto imports autofilled the boolean gets as "is". This is because a boolean can only have 2 values. true or false.
public String getName() {
	return Name;
}


public void setName(String name) {
	Name = name;
}


public String getHairColour() {
	return hairColour;
}


public void setHairColour(String hairColour) {
	this.hairColour = hairColour;
}


public String getClothingColour() {
	return clothingColour;
}


public void setClothingColour(String clothingColour) {
	this.clothingColour = clothingColour;
}


public boolean isWearsHat() {
	return wearsHat;
}


public void setWearsHat(boolean wearsHat) {
	this.wearsHat = wearsHat;
}


public boolean isHasClaw() {
	return hasClaw;
}


public void setHasClaw(boolean hasClaw) {
	this.hasClaw = hasClaw;
}


public boolean isHasVehicle() {
	return hasVehicle;
}


public void setHasVehicle(boolean hasVehicle) {
	this.hasVehicle = hasVehicle;
}


public boolean isHasBeard() {
	return hasBeard;
}


public void setHasBeard(boolean hasBeard) {
	this.hasBeard = hasBeard;
}


public boolean isWearsEarings() {
	return wearsEarings;
}


public void setWearsEarings(boolean wearsEarings) {
	this.wearsEarings = wearsEarings;
}


public boolean isWearsHelmet() {
	return wearsHelmet;
}


public void setWearsHelmet(boolean wearsHelmet) {
	this.wearsHelmet = wearsHelmet;
}


public boolean isWearsTie() {
	return wearsTie;
}


public void setWearsTie(boolean wearsTie) {
	this.wearsTie = wearsTie;
}


public boolean isHasBeak() {
	return hasBeak;
}


public void setHasBeak(boolean hasBeak) {
	this.hasBeak = hasBeak;
}


public boolean isHasTail() {
	return hasTail;
}


public void setHasTail(boolean hasTail) {
	this.hasTail = hasTail;
}


public boolean isHasGuitar() {
	return hasGuitar;
}


public void setHasGuitar(boolean hasGuitar) {
	this.hasGuitar = hasGuitar;
}


public boolean isHasScales() {
	return hasScales;
}


public void setHasScales(boolean hasScales) {
	this.hasScales = hasScales;
}

public String getFileName() {
	return filename;
}


//Added the extra lines because of some apparent reason, my computer wouldn't find the file unless I used the absolute path. This might cause some troubles for another computer.
public void setFileName(String filename) {
	this.filename = "C:\\Users\\16475\\eclipse-workspace\\GuessWhoProject2\\src\\images\\" + filename + ".jpeg".trim();
}


//Overridden toString method
@Override
public String toString() {
	return "Character [Name=" + Name + ", hairColour=" + hairColour + ", clothingColour=" + clothingColour
			+ ", wearsHat=" + wearsHat + ", hasClaw=" + hasClaw + ", hasVehicle=" + hasVehicle + ", hasBeard="
			+ hasBeard + ", wearsEarings=" + wearsEarings + ", wearsHelmet=" + wearsHelmet + ", wearsTie=" + wearsTie
			+ ", hasBeak=" + hasBeak + ", hasTail=" + hasTail + ", hasGuitar=" + hasGuitar + ", hasScales=" + hasScales
			+ ", filename=" + filename + "]";
}






}
