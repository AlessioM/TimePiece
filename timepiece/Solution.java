package timepiece;

import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Solution {
	public int generation = 0;
	public List<Candidate> candidates = new LinkedList<Candidate>();
	public Candidate fittest = null;
	public Candidate worst = null;
}
