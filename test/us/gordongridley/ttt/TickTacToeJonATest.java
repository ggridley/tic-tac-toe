package us.gordongridley.ttt;

import org.junit.Assert;
import org.junit.Test;

import us.gordongridley.ttt.TickTacToeJonA;


public class TickTacToeJonATest {
	@Test
	public void testCase1FirstRow(){
		String[] args = {"1", "4", "2", "5", "3", "4"};
		Assert.assertEquals(TickTacToeJonA.X_WINS, TickTacToeJonA.gamePlay(args));
	}
	
	@Test
	public void testCase2SecondRow(){
		String[] args = {"1", "4", "2", "5", "7", "6", "3"};
		Assert.assertEquals(TickTacToeJonA.O_WINS, TickTacToeJonA.gamePlay(args));
	}
	
	@Test
	public void testCase3ThirdRow(){
		String[] args = {"1", "2", "3", "7", "5", "8", "4", "9"};
		Assert.assertEquals(TickTacToeJonA.O_WINS, TickTacToeJonA.gamePlay(args));
	}
	
	@Test
	public void testCase4FirstColumn(){
		String[] args = {"1", "2", "4", "5", "7", "9"};
		Assert.assertEquals(TickTacToeJonA.X_WINS, TickTacToeJonA.gamePlay(args));
	}

	@Test
	public void testCase5SecondColumn(){
		String[] args = {"1", "2", "4", "5", "9", "8", "7"};
		Assert.assertEquals(TickTacToeJonA.O_WINS, TickTacToeJonA.gamePlay(args));
	}

	@Test
	public void testCase6ThirdColumn(){
		String[] args = {"1", "2", "3", "5", "6", "7", "9"};
		Assert.assertEquals(TickTacToeJonA.X_WINS, TickTacToeJonA.gamePlay(args));
	}

	@Test
	public void testCase7KiddyDown(){
		String[] args = {"1", "6", "5", "4", "9", "8", "7"};
		Assert.assertEquals(TickTacToeJonA.X_WINS, TickTacToeJonA.gamePlay(args));
	}

	@Test
	public void testCase8KiddyUp(){
		String[] args = {"1", "7", "2", "3", "4", "5", "9"};
		Assert.assertEquals(TickTacToeJonA.O_WINS, TickTacToeJonA.gamePlay(args));
	}
	
	@Test
	public void testCase9Tie1(){
		String[] args = {"1", "7", "9", "5", "3", "2", "8", "6", "4"};
		Assert.assertEquals(TickTacToeJonA.TIE, TickTacToeJonA.gamePlay(args));
	}

	@Test
	public void testCase10Tie2(){
		String[] args = {"5", "3", "2", "8", "9", "1", "6", "4", "7"};
		Assert.assertEquals(TickTacToeJonA.TIE, TickTacToeJonA.gamePlay(args));
	}

	@Test
	public void testCase11InvalidData(){
		String[] args = {"1", "B", "6", "5", "4", "9", "8", "7"};
		Assert.assertEquals(TickTacToeJonA.X_WINS, TickTacToeJonA.gamePlay(args));
	}

	@Test
	public void testCase12DupeEntry(){	
		String[] args = {"1", "7", "7", "2", "3", "4", "5", "9"};
		Assert.assertEquals(TickTacToeJonA.O_WINS, TickTacToeJonA.gamePlay(args));
	}

}
