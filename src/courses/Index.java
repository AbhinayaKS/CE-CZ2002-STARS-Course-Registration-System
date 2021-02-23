package courses;

import java.io.Serializable;
import java.util.ArrayList;
/**   
 * Represents an index of a course.   
 * An index can have one or multiple sessions of class.   
 * @author  Zhang Yilin
 * @author  Kesarimangalam Srinivasan Abhinaya
 * @author  Musthiri Sajna
 * @author  Singh Aishwarya
 * @author  Unnikrishnan Malavika
 * @version 1.0   
 * @since   2020-11-17  
 */
public class Index implements Serializable{
	
    /**
	 * To write to the binary file
	 */
	private static final long serialVersionUID = 1L;

	/**
     * The Index Number of the index.
     */
	private String IndexNo;

	/**
     * The Course Code which the index belongs to.
     */
	private String CourseCode;
    
	/**
     * The array of name of registered student in this index.
     */
	private ArrayList<String> RegiStudName = new ArrayList<String>();
    
	/**
     * The array of name of students in wailist in this index.
     */
	private ArrayList<String> WaitlistStudName= new ArrayList<String>();;
    
	/**
     * The number of vacancy in this index.
     */
	private int vacancy;
    
	/**
     * The lecture session in this index.
     */
	private Session lec;
    
	/**
     * The tutorial session in this index.
     */
    private Session tut;
    
    /**
     * The lab session in this index.
     */
    private Session lab;
   	
	//Constructor
    /**
     * Constructor to add an index in a course.
	 * Initialize IndexNo, CourseCode, vacancy, lecture Session, tutorial Session, lab Session of the index.
	 * May have  lectures  only,  lectures  and tutorial  only  or  lectures,  tutorial  and laboratory sessions.
     * @param IndexNo The Index Number of the index.
     * @param CourseCode The Course Code which the index belongs to. 
     * @param vacancy The number of vacancy in this index.
     * @param lec The lecture session in this index.
     * @param tut The tutorial session in this index.(maybe null)
     * @param lab The tutorial session in this index.(maybe null)
     */
	public Index(String IndexNo, String CourseCode,int vacancy,
			Session lec, Session tut, Session lab)
	{
		this.IndexNo = IndexNo;
		this.CourseCode = CourseCode;
		this.vacancy = vacancy;
		this.RegiStudName = null;
		this.WaitlistStudName = null;
		this.lec=lec;
		this.tut = tut;
		this.lab=lab;
	}

	//Methods
	//accessor and mutator for each attribute
	//print index
	public void printIndex() {
		System.out.println("Course Code: "+CourseCode);
		System.out.println("Index Number: "+IndexNo);
		System.out.println("vacancy number: "+vacancy);
		System.out.println("lecture time: "+lec.getStarttime()+" to "+lec.getEndtime());
		System.out.println("lecture venue: "+lec.getVenue());
		System.out.println("lecture day: "+lec.getDay());
	}
	
	public void printWaitlist() {
		if (WaitlistStudName != null)
		for (int i = 0; i < WaitlistStudName.size(); i++) {
			System.out.println(WaitlistStudName.get(i));
		}
	}
	
	public void printReadlist() {
		if (RegiStudName != null)
		for (int i = 0; i < RegiStudName.size(); i++) {
			System.out.println(RegiStudName.get(i));
		}
	}
	//add a student to waitlist
	public void appendNameWL(String StuName) {		
		ArrayList<String> waitList = new ArrayList<String>();
		waitList.add(StuName);
		WaitlistStudName = waitList;
	}
	
	/**
	 * Check index with another index for timetable clashes
	 * used when student adding course
	 * Return a boolean value: false means no time clash, true means time clashes between two index
	 * Print out the details of which session clashes with which session
	 */
	public boolean checkIndexClash(Index i1) {
		boolean a= false, b = false, c= false, d= false, e= false, f= false, g= false, h= false, i= false;
		a= this.getLecture().checkSessionClash(i1.getLecture()); 
		if (this.getTutorial()!=null) {
			b= this.getTutorial().checkSessionClash(i1.getLecture());
			if (i1.getTutorial()!=null) {
				c= this.getTutorial().checkSessionClash(i1.getTutorial());
			}
			if (i1.getLab()!=null) {
				d= this.getTutorial().checkSessionClash(i1.getLab());
			}
		}
		if(this.getLab()!=null) {
			e= this.getLab().checkSessionClash(i1.getLecture());
			if(i1.getTutorial()!=null) {
				f= this.getLab().checkSessionClash(i1.getTutorial());
			}
			if (i1.getLab()!=null) {
				g= this.getLab().checkSessionClash(i1.getLab());
			}
		}
		if(i1.getTutorial()!=null) {
		h= this.getLecture().checkSessionClash(i1.getTutorial());
		}
		if(i1.getLab()!=null) {
		i= this.getLecture().checkSessionClash(i1.getLab());
		}
		if(a) {
			System.out.println("Timetable clashes between session!");
			System.out.println(this.getcoursecode()+" Lecture clashes with "+ i1.getcoursecode()+" Lecture");
		}
		if (b) {
			System.out.println("Timetable clashes between session!");
			System.out.println(this.getcoursecode()+" Tutorial clashes with "+ i1.getcoursecode()+" Lecture");
		}
		if (c) {
			System.out.println("Timetable clashes between session!");
			System.out.println(this.getcoursecode()+" Tutorial clashes with "+ i1.getcoursecode()+" Tutorial");
		}
		if (d) {
			System.out.println("Timetable clashes between session!");
			System.out.println(this.getcoursecode()+" Tutorial clashes with "+ i1.getcoursecode()+" Lab");
		}
		if (e) {
			System.out.println("Timetable clashes between session!");
			System.out.println(this.getcoursecode()+" Lab clashes with "+ i1.getcoursecode()+" Lecture");
		}
		if (f) {
			System.out.println("Timetable clashes between session!");
			System.out.println(this.getcoursecode()+" Lab clashes with "+ i1.getcoursecode()+" Tutorial");
		}
		if (g) {
			System.out.println("Timetable clashes between session!");
			System.out.println(this.getcoursecode()+" Lab clashes with "+ i1.getcoursecode()+" Lab");
		}
		if (h) {
			System.out.println("Timetable clashes between session!");
			System.out.println(this.getcoursecode()+" Lecture clashes with "+ i1.getcoursecode()+" Tutorial");
		}
		if (i) {
			System.out.println("Timetable clashes between session!");
			System.out.println(this.getcoursecode()+" Lecture clashes with "+ i1.getcoursecode()+" Lab");
		}
		return a||b||c||d||e||f||g||h||i;
	}

		
	public ArrayList<String> getRegiStudName() {
		return RegiStudName;
	}
	
	public void setRegiStudName(ArrayList<String> RegisStudent) {
		this.RegiStudName = RegisStudent;
	}
	
	public ArrayList<String> getWaitlistStudName() {
		return WaitlistStudName;
	}
	
	public void setWaitlistStudName(ArrayList<String> waitList) {
		this.WaitlistStudName = waitList;
	}
		
    public Session getLecture() {
		return lec;		
	}
	
    public void setLecture(Session lec) {
		this.lec = lec;
	}
	
	public Session getTutorial() {
		return tut;		
	}
	
	public void setTutorial(Session tut) {
		this.tut = tut;
	}
	
	public Session getLab() {
		return lab;		
	}
	
	public void setLab(Session lab) {
		this.lab = lab;
	}

	public String getcoursecode() {
		return CourseCode;		
	}
	
	public void setCourseCode(String CourseCode) {
		this.CourseCode = CourseCode;
	}
	
	public String getIndexNo() {
		return IndexNo;		
	}
	
	public void setIndexNo(String IndexNo) {
		this.IndexNo = IndexNo;
	}

	public int getVacancy() {
		return vacancy;
	}
	
	public void setVacancy(int vacancy) {
		this.vacancy = vacancy;
	}

}