package com.sam.webtasks.timeBasedOffloading;

import java.util.Date;

import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootPanel;
import com.sam.webtasks.basictools.PHP;
import com.sam.webtasks.basictools.Points;
import com.sam.webtasks.basictools.TimeStamp;
import com.sam.webtasks.client.SequenceHandler;

public class TimeResponse {
	public static Date stimOn;
	
	public static void Run(int response) {
		int RT = (int) (new Date().getTime() - stimOn.getTime());
	
		//end of block? if so return control to the sequencehandler
		//we specify blockDuration in seconds if it is positive, or trials if it is negative
		boolean blockOver = false;
		
		
		if (response==KeyCodes.KEY_Q) {
			TimeBlock.PMhits=1;
			TimeBlock.nBackMatchCorr=1;
			TimeBlock.nBackNonMatchCorr=1;
			blockOver = true;
		}
		
		if (TimeBlock.blockDuration > 0) { //specified in seconds
			if (TimeBlock.currentTime >= TimeBlock.blockDuration) {
				blockOver = true;
			}
		}
		
		if (TimeBlock.blockDuration < 0) { //specified in trials
			if (TimeBlock.trialNumber >= -TimeBlock.blockDuration) {
				blockOver = true;
			}
		}
		
		if (blockOver) {
			RootPanel.get().remove(TimeDisplay.focusPanel);
			
			TimeDisplay.clockTimer.cancel();
			
			new Timer() {
				public void run() {
					String data = TimeBlock.blockNumber + "," + TimeBlock.trialNumber + ",";
					data = data + TimeBlock.timerButtonVisible + ",";
					data = data + TimeBlock.reminderButtonVisible + ",";
					data = data + TimeBlock.nBackNonMatchCorr + "," + TimeBlock.nBackMatchCorr + ",";
					data = data + TimeBlock.PMhits + "," + TimeBlock.nReminders;
					
					PHP.logData("blockEnd", data, true);
				}
			}.schedule(1000);
		} else if (TimeDisplay.timeForInstruction) {
			TimeBlock.spacebarPressed=false;
			
			TimeDisplay.timeForInstruction=false;
			
			TimeDisplay.focusPanel.setFocus(false);
			
			TimeDisplay.stimulusDisplay.setHTML("");
			
			TimeDisplay.timerButton.setHTML("Set timer (" + TimeDisplay.timerClicksRemaining + ")");
			TimeDisplay.reminderButton.setHTML("Create reminder");
			
			new Timer() {
				public void run() {
					if (!TimeDisplay.waitForSpacebar) { 
						//make sure clock is visible if there is a PM instruction
						TimeDisplay.SetClockVisible(true);
						
						TimeDisplay.stimulusDisplay.setHTML(TimeDisplay.instructionString);
						TimeDisplay.waitForSpacebar=true;
						TimeDisplay.focusPanel.setFocus(true);
						
						//TimeDisplay.spacebarToContinue.schedule(7000); //remind them to press the spacebar to continue
						
						TimeBlock.instructionOn=true;
						TimeBlock.instructionTimeStamp = new Date();
					}
				}
			}.schedule(TimeBlock.RSI);
		}
		
		String data="";
		
		if (TimeDisplay.waitForSpacebar) {
			if (response==TimeBlock.instructionKey) {
				data = TimeBlock.blockNumber + "," + TimeBlock.currentTime + ",";
				data = data + TimeBlock.nextInstruction + "," + TimeBlock.lastTarget + "," + "1," + TimeStamp.Now();
				
				PHP.logData("TB_instruction_response", data, false);
				
				TimeDisplay.instructionString = "Hit the " + (char)(TimeBlock.PMchar+'A') + " key at " + TimeDisplay.timeString(TimeBlock.lastTarget);
				TimeDisplay.stimulusDisplay.setHTML(TimeDisplay.instructionString);
				
				Points.subtractPoints(TimeBlock.PMinstructionCost);
			} else if (response==TimeBlock.spaceBarKey) {
				data = TimeBlock.blockNumber + "," + TimeBlock.currentTime + ",";
				data = data + TimeBlock.nextInstruction + "," + TimeBlock.lastTarget + "," + "0," + TimeStamp.Now();
				
				PHP.logData("TB_instruction_response", data, false);
				
				if (TimeBlock.instructionOn) {
					TimeBlock.instructionOn=false;
					
					int instructionReadingTime = (int) (new Date().getTime() - TimeBlock.instructionTimeStamp.getTime());
					
					data = TimeBlock.blockNumber + "," + TimeBlock.trialNumber + ",";
					data = data + instructionReadingTime + "," + TimeBlock.currentTime + "," + TimeStamp.Now();
					
					PHP.logData("instuctionReadingTime", data, false);
				}
						
				if (TimeDisplay.awaitingPMresponse) {
					if (TimeBlock.allowOffloading) {
						TimeDisplay.timerButton.setEnabled(true);
						TimeDisplay.reminderButton.setEnabled(true);
					}
				}
				
				TimeDisplay.spacebarToContinue.cancel(); // no need to remind participant they need to press spacebar
				TimeDisplay.waitForSpacebar = false;
				TimeDisplay.focusPanel.setFocus(false);
				TimeDisplay.stimulusDisplay.setHTML("");
				TimeDisplay.startClock();
				
				//set timer to remove clock if it's not always on
				if (!TimeBlock.clockAlwaysOn) {
					new Timer() {
						public void run() {
							TimeDisplay.SetClockVisible(false);
						}
					}.schedule(TimeBlock.clockReveal_msec);
				}
				
				new Timer() {
					public void run() {
						TimeDisplay.stimulusDisplay.setHTML(TimeDisplay.generateStimulus());
						TimeDisplay.focusPanel.setFocus(true);
					}
				}.schedule(TimeBlock.RSI);
			} else {
				Window.alert("Press the spacebar to continue");
			}
		} else {
			if ((response == TimeBlock.matchKey)||(response == TimeBlock.nonMatchKey)) { //increment trial number if one of the 2back keys was pressed
				TimeBlock.trialNumber++;
			} else if (response == TimeBlock.revealClockKey) {
				data = TimeBlock.blockNumber + "," + TimeBlock.timerButtonVisible + ",";
				data = data + TimeBlock.reminderButtonVisible + ",";
				data = data + TimeBlock.trialNumber + "," + TimeDisplay.stimulus + ",";
				data = data + TimeDisplay.awaitingPMresponse + "," + (TimeDisplay.stimulus == TimeDisplay.stimulus_2back) + ",";
				data = data + TimeBlock.currentTime + ",";
				data = data + TimeStamp.Now();
				
				PHP.logData("TB_clockCheck", data, false);
				
				TimeDisplay.SetClockVisible(true);
				
				if (!TimeBlock.clockAlwaysOn) {
					new Timer() {
						public void run() {
							TimeDisplay.SetClockVisible(false);
						}
					}.schedule(TimeBlock.clockReveal_msec);
				}
			}
			
			boolean nBackCorrect = false;
			
			if (TimeDisplay.stimulus == TimeDisplay.stimulus_2back) {
				if (response == TimeBlock.matchKey) {
					nBackCorrect = true;
					TimeBlock.nBackMatchCorr++;
				}
			} else {
				if (response == TimeBlock.nonMatchKey) {
					nBackCorrect = true;
					TimeBlock.nBackNonMatchCorr++;
				}
			}
			
			data = TimeBlock.blockNumber + "," + TimeBlock.timerButtonVisible + ",";
			data = data + TimeBlock.reminderButtonVisible + ",";
			data = data + TimeBlock.trialNumber + "," + TimeDisplay.stimulus + ",";
			data = data + response + "," + RT + ",";
			data = data + TimeDisplay.awaitingPMresponse + "," + (TimeDisplay.stimulus == TimeDisplay.stimulus_2back) + ",";
			data = data + nBackCorrect + "," + TimeBlock.nextTarget + "," + TimeBlock.currentTime + ",";
			data = data + TimeStamp.Now();
			
			PHP.logData("TB_response", data, false);
			
			if (response==TimeBlock.PMkey) {
				if (TimeDisplay.awaitingPMresponse) {
					if (Math.abs(TimeBlock.currentTime-TimeBlock.lastTarget) <= TimeBlock.PMwindow) {
						TimeBlock.PMhits++;
						
						TimeDisplay.awaitingPMresponse=false;
						
						TimeDisplay.reminder.cancel();
						TimeDisplay.showTimer = false;
						TimeDisplay.timerButton.setEnabled(false);
						TimeDisplay.reminderButton.setEnabled(false);

						if (TimeBlock.spacebarPressed==false) {
							TimeDisplay.clockDisplay.addStyleName("greenyellow");
							Points.addPoints(TimeBlock.PMreward);
						}
						
						new Timer() {
							public void run() {
								TimeDisplay.clockDisplay.removeStyleName("greenyellow");
							}
						}.schedule(200);
					} else { //premature space-bar
						TimeBlock.spacebarPressed=true;
					}
				}
			} else if ((response==TimeBlock.matchKey)||(response==TimeBlock.nonMatchKey)) {
				TimeDisplay.focusPanel.setFocus(false);
		
				TimeDisplay.stimulusDisplay.setHTML("");
		
				new Timer() {
					public void run() {
						if (!TimeDisplay.waitForSpacebar) { 
							TimeDisplay.stimulusDisplay.setHTML(TimeDisplay.generateStimulus());
							TimeDisplay.focusPanel.setFocus(true);
							TimeResponse.stimOn = new Date();
							
							String data = TimeBlock.blockNumber + "," + TimeDisplay.stimulus + ",";
							data = data + TimeStamp.Now();
							
							PHP.logData("TB_stimOn", data, false);
						}
					}
				}.schedule(TimeBlock.RSI);
			}
		}
	}
}
