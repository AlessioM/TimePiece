package timepiece;

public class GenThread extends Thread {

	GenAlg genAlg = null;
	
	public GenThread(GenAlg genAlg) {
		this.genAlg = genAlg;
	}

	@Override
	public void run() {
		while(true) {
			genAlg.incGeneration();
			
			Candidate newCandidate = null;
			int action = genAlg.rand.nextInt(16);
			
			if(action < 2) {
				newCandidate = genAlg.mixTogether(genAlg.getRandom(true), genAlg.getRandom(true));
			} else if(action < 10) {
				newCandidate = genAlg.addRandomWord(genAlg.getRandom(true));
			} else if(action < 11) {
				newCandidate = genAlg.changeRandom(genAlg.getRandom(true));
			} else {
				newCandidate = genAlg.createRandom();
			}
	
			genAlg.calcFittness(newCandidate);
			
			if(genAlg.doAdd(newCandidate)) {
				genAlg.addToSolution(newCandidate);
			}
			
		}
	}

}
