package models;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public abstract class CV {

	private String name;
	protected ArrayList<Section> sections;
	
	
	
	public CV(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}
	
	public ArrayList<Section> getSections() {
		return sections;
	}
	
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSections(ArrayList<Section> sections) {
		this.sections = sections;
	}

	public abstract void printTofile();
	
	public void exportTxt()throws Exception{
		PrintWriter writer = new PrintWriter(name+".txt", "UTF-8");
	    
	    boolean n = false;
	    int i=1;
		for(Section section: sections){
			writer.println(""+i+". "+section.getTitle());
			if(!n){
				writer.println("Name: "+name);
				n=true;
			}
			exportTxt(section, writer);
			i++;
		}
		
	    writer.close();
	}
	
	private void exportTxt(Section section, PrintWriter writer) {
		for(Item i:section.getItems()){
			BulletList item = (BulletList) i;
			if(section.getTitle().equals("SKILLS AND EXPERIENCE")){
				writer.println("SKILLS AND EXPERIENCE ON "+item.toString());
			}else{
				writer.println(item.toString());
			}
			
			exportTxt(item,writer);
		}
		writer.println("----------------------------------------");
	}

	private void exportTxt(BulletList item, PrintWriter writer) {
		ArrayList<Bullet> bullets = item.getBullets();
		if(bullets.size()>0){
			for(Bullet bullet:bullets){
				writer.println("	"+bullet.getText());
				exportTxt(bullet,writer,2);
			}
		}
		
	}

	private void exportTxt(Bullet bullet, PrintWriter writer, int i) {
		ArrayList<Bullet> subBullets = bullet.getSubBullets();
		if(subBullets.size()>0){
			for(Bullet sub:subBullets){
				for(int j=0;j<i;j++){
					writer.print("	");
				}
				writer.println(sub.getText());
				exportTxt(sub,writer,i+1);
			}
		}
	}
	
	
	public static CV importText(String path) throws Exception{
		BufferedReader in = new BufferedReader(new FileReader(path));
		
		ArrayList<String> lines = new ArrayList<String>();
		String line;
		int type = 0; //0 chrono 1 functional 2 combined
		while((line = in.readLine()) != null){
			lines.add(line);
			if(line.contains("SKILLS AND EXPERIENCE") || line.contains("PROFESSIONAL EXPERIENCE")){
				type++;
			}
		}
		
		String name = lines.get(1).split(": ")[1];
		CV cv = null;
		if(type == 0){
			cv = new ChronologicalCV(name);
		}else if(type == 1){
			cv = new FunctionalCV(name);
		}else{
			cv = new CombinedCV(name);
		}
		
		int index = 2;
		line = lines.get(index);
		int sNum = 0;
		
		while(index < lines.size()){
			line = lines.get(index);
			if(line.contains("------")){
				sNum++;//next section
				index++;//jump  section name
			}else{
				/*BulletList item = new BulletList(line);
				cv.getSections().get(sNum).addItem(item);
				System.out.println(line);*/
				
				addline(cv.getSections().get(sNum), line);
			}
			index++;
		}
		
		return cv;
		
	}
	
	

	private static void addline(Section section, String line) {
		if(countab(line) == 0){
			BulletList item = (BulletList)factorItem(line);
			section.addItem(item);
		}else{
			BulletList last = (BulletList)section.getItems().get(section.getItems().size()-1);
			line = line.replaceFirst("	", "");
			addline(last, line);
		}
	}
	
	private static void addline(BulletList bulletlist, String line) {
		if(countab(line) == 0){
			Bullet bullet = new Bullet(line);
			bulletlist.addBullet(bullet);
		}else{
			Bullet last = bulletlist.getBullets().get(bulletlist.getBullets().size()-1);
			line = line.replaceFirst("	", "");
			addline(last, line);
		}
		
	}

	private static void addline(Bullet parent, String line) {
		if(countab(line) == 0){
			Bullet bullet = new Bullet(line);
			parent.addSubBullet(bullet);
		}else{
			Bullet last = parent.getSubBullets().get(parent.getSubBullets().size()-1);
			line = line.replaceFirst("	", "");
			addline(last, line);
		}
		
	}

	private static int countab(String s){
		int counter = 0;
		for( int i=0; i<s.length(); i++ ) {
		    if( s.charAt(i) == '\t' ) {
		        counter++;
		    } 
		}
		return counter;
	}

	/***********************latex********************************/
	
	public void exportLatex() throws Exception{
		PrintWriter writer = new PrintWriter(name+".tex", "UTF-8");
	    
	    writer.println("\\documentclass{article}");
	    writer.println("\\begin{document} ");
	    
	    boolean n = false;
	    
		for(Section section: sections){
			writer.println("\\section{"+section.getTitle()+"}");
			writer.println("\\begin{itemize}");
			if(!n){
				writer.println("\\item Name: "+name);
				n=true;
			}
			exportLatex(section, writer);
			writer.println("\\end{itemize}");
		}
		
		writer.println("\\end{document} ");
	    writer.close();
	}

	protected void exportLatex(Section section, PrintWriter writer) {
		for(Item i:section.getItems()){
			if(section.getTitle().equals("SKILLS AND EXPERIENCE")){
				BulletList item = (BulletList) i;
				writer.println("\\item SKILLS AND EXPERIENCE ON "+item.toString());
				exportLatex(item,writer);
			}else{
				BulletList item = (BulletList) i;
				writer.println("\\item "+item.toString());
				exportLatex(item,writer);
			}
			
		}
		
	}

	protected void exportLatex(BulletList item, PrintWriter writer) {
		ArrayList<Bullet> bullets = item.getBullets();
		if(bullets.size()>0){
			writer.println("\\begin{itemize}");
			for(Bullet bullet:bullets){
				writer.println("\\item "+bullet.getText());
				exportLatex(bullet,writer);
			}
			writer.println("\\end{itemize}");
		}
		
	}

	private void exportLatex(Bullet bullet, PrintWriter writer) {
		ArrayList<Bullet> subBullets = bullet.getSubBullets();
		if(subBullets.size()>0){
			writer.println("\\begin{itemize}");
			for(Bullet sub:subBullets){
				writer.println("\\item "+sub.getText());
				exportLatex(sub,writer);
			}
			writer.println("\\end{itemize}");
		}
	}
	
	
	
	
	
	
	public static CV importLatex(String path) throws Exception{
		BufferedReader in = new BufferedReader(new FileReader(path));
		
		int type = 0; //0 chrono 1 functional 2 combined
		
		String line;
		in.readLine();
		in.readLine();
		in.readLine();
		in.readLine();
		
		line = in.readLine();
		String name = line.split(": ")[1];
		
		ArrayList<Item> personal = new ArrayList<Item>();
		while((line = in.readLine()) != null){
			if(line.contains("end{itemize}")){
				break;
			}
			String noitem = line.split("item ")[1];
			personal.add(new BulletList(noitem));
		}
		Section pers = new Section("PERSONAL INFORMATION");
		pers.setItems(personal);
		
		ArrayList<Section> sections = new ArrayList<Section> ();
		sections.add(pers);
		while((line = in.readLine()) != null){
			if(line.contains("SKILLS AND EXPERIENCE") || line.contains("PROFESSIONAL EXPERIENCE")){
				type++;
			}
			
			if(line.contains("section")){
				String s1 = line.split("section")[1];
				Section s = new Section(s1.substring(1, s1.length()-1));
				sections.add(s);
			}else if(line.contains("begin")){
				updateSection(sections.get(sections.size()-1),in);
			}
		}
		
		CV cv = null;
		if(type == 0){
			cv = new ChronologicalCV(name);
		}else if(type == 1){
			cv = new FunctionalCV(name);
		}else{
			cv = new CombinedCV(name);
		}
		cv.setSections(sections);
		
		return cv;
		
	}

	private static void updateSection(Section section, BufferedReader in) throws Exception {
		String line;
		while((line = in.readLine()) != null){
			if(line.contains("end{itemize}")){
				return;
			}else if(line.contains("item ")){
				if(line.contains("SKILLS AND EXPERIENCE ON ")){
					String item = line.split("item ")[1].split("SKILLS AND EXPERIENCE ON ")[1];
					section.addItem(factorItem(item));
				}else{
					String item = line.split("item ")[1];
					section.addItem(factorItem(item));
				}
				
			}else if(line.contains("begin{itemize}")){
				ArrayList<Item> items = section.getItems();
				updateBulletList((BulletList) items.get(items.size()-1),in);
			}
		}
	}

	

	private static void updateBulletList(BulletList bulletList, BufferedReader in) throws Exception {
		String line;
		while((line = in.readLine()) != null){
			if(line.contains("end{itemize}")){
				return;
			}else if(line.contains("item ")){
				String item = line.split("item ")[1];
				bulletList.addBullet(new Bullet(item));
			}else if(line.contains("begin{itemize}")){
				ArrayList<Bullet> bullets = bulletList.getBullets();
				updateBullet(bullets.get(bullets.size()-1),in);
			}
		}
		
	}

	

	private static void updateBullet(Bullet bullet, BufferedReader in) throws Exception {
		String line;
		while((line = in.readLine()) != null){
			if(line.contains("end{itemize}")){
				return;
			}else if(line.contains("item ")){
				String item = line.split("item ")[1];
				bullet.addSubBullet(new Bullet(item));
			}else if(line.contains("begin{itemize}")){
				ArrayList<Bullet> bullets = bullet.getSubBullets();
				updateBullet(bullets.get(bullets.size()-1),in);
			}
		}
		
	}

	private static BulletList factorItem(String str) {
		
		if(str.contains("SKILLS AND EXPERIENCE ON ")){
			str = str.split("SKILLS AND EXPERIENCE ON ")[1];
		}
		
		String []spl = str.split(", ");
		if(spl.length <=1){
			return new BulletList(str);
		}else{
			String last = spl[spl.length-1];
			try{
				DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				Date date = format.parse(last);
				String title = "";
				for(int i=0;i<spl.length-1;i++){
					title+=spl[i];
				}
				return new BulletList(title,date);
			}catch (Exception e){
				return new BulletList(str);
			}
		}
	}
	
	public CV differencesWith(CV other){
		if(!this.name.equals(other.name)){
			return null;
		}
		
		CV cv = null;
		if(this instanceof ChronologicalCV){
			cv = new ChronologicalCV(name);
		}else if(this instanceof FunctionalCV){
			cv = new FunctionalCV(name);
		}else if(this instanceof CombinedCV){
			cv = new CombinedCV(name);
		}
		
		for(int i=0;i<sections.size();i++){
			Section s1 = this.sections.get(i);
			Section s2 = other.sections.get(i);
			Section s = cv.sections.get(i);
			
			for(Item item: s1.getItems()){
				BulletList b = (BulletList) item;
				if(!s2.containsBulletList(b)){
					s.addItem(b);
				}
			}
			
			for(Item item: s2.getItems()){
				BulletList b = (BulletList) item;
				if(!s1.containsBulletList(b)){
					s.addItem(b);
				}
			}
		}
		return cv;
	}
	
	
	public CV intersectWith(CV other){
		if(!this.name.equals(other.name)){
			return null;
		}
		
		CV cv = null;
		if(this instanceof ChronologicalCV){
			cv = new ChronologicalCV(name);
		}else if(this instanceof FunctionalCV){
			cv = new FunctionalCV(name);
		}else if(this instanceof CombinedCV){
			cv = new CombinedCV(name);
		}
		
		for(int i=0;i<sections.size();i++){
			Section s1 = this.sections.get(i);
			Section s2 = other.sections.get(i);
			Section s = cv.sections.get(i);
			
			for(Item item: s1.getItems()){
				BulletList b = (BulletList) item;
				if(s2.containsBulletList(b)){
					s.addItem(b);
				}
			}
		}
		return cv;
	}
	
	public static CV merge (CV intersections, CV differences){
		for(int i=0;i<intersections.getSections().size();i++){
			Section s1 = intersections.getSections().get(i);
			Section s2 = differences.getSections().get(i);
			
			for(Item item: s2.getItems()){
				BulletList b = (BulletList) item;
				s1.addItem(b);
			}
		}
		return intersections;
	}

	
	
}
