package designpatterns;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

public class ObeserverDemo {
	public static void main(String[] args) {
        System.out.println("Add Score ");
        EventSource eventSource = new EventSource();
        eventSource.addObserver(new ScoreBoardObserver());
        eventSource.scanSystemIn(new ScoreBoard());
    }
}

class EventSource {

	private final List<Observer> observers = new ArrayList<>();
	
	private void notifyObservers(Observable event, Object o) {
        observers.forEach(observer -> observer.update(event, o));
    }
  
    public void addObserver(Observer observer) {
        observers.add(observer);
    }
    
    public void scanSystemIn(ScoreBoard ob) {
    	Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            Integer i = Integer.parseInt(line);
            ob.updateScore(i);
            notifyObservers(ob, i);
        }
        scanner.close();
    }
}

class ScoreBoardObserver implements Observer {

	@Override
	public void update(Observable o, Object arg) {

		if(o instanceof ScoreBoard){
			ScoreBoard sb = (ScoreBoard)o;
			System.out.println("Score --> " + sb.getScore());
		}

	}

}

class ScoreBoard extends Observable {
	private Integer score;

	public void setScore(Integer score) {
		this.score = score;
	}

	public Integer getScore() {
		return this.score;
	}

	public ScoreBoard() {
		this.score = 0;
	}

	public void updateScore(Integer score) {
		this.score = this.score + score;
	}

}
