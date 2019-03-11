
public class Person implements Comparable<Person>{

	String name;
	int score;
	
	public Person(String name,int score) {
		this.name=name;
		this.score=score;
	}
	  
	
	public String getName() {
		return name;
	}


	public int getScore() {
		return score;
	}

	@Override
	public int compareTo(Person o) {
		// TODO Auto-generated method stub
		return this.score-o.score;
	}


}
