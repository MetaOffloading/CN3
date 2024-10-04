package com.sam.webtasks.client;

import com.sam.webtasks.basictools.Counterbalance;
import com.sam.webtasks.iotask2.IOtask2BlockContext;
import com.sam.webtasks.timeBasedOffloading.TimeBlock;
import com.sam.webtasks.client.SequenceHandler;

public class Instructions {

	public static String Get(int index) {
		String i="";
		 
		switch(index) {
		case 10:
			i="In this task you will see a sequence of letters, one by one.<br><br>If the letter "
					+ "matches the one you saw two letters ago, please press the <b>X</b> key. "
					+ "Otherwise, press the <b>Z</b> key.<br><br>For example, if you saw the sequence "
					+ "O A S A P, you would press Z Z Z X Z.<br><br>Please respond as quickly "
					+ "and as accurately as possible.";
			break;
		case 20:
			i = "At the same time as doing this test, you will also do a 'timer task'. A digital clock "
					+ "will be displayed above the letters. You will be asked to press "
					+ "one of the keys on your keyboard at a particular time.<br><br>For example you might get "
					+ "the instruction \"Hit the T key at 0:10\". When you see a message "
					+ "like this, first you should immediately press the spacebar to continue "
					+ "with the letter task. You should keep going with the letter task like before. Then, "
					+ "when the clock gets to the specified time (such as 0:10) you should press the "
					+ "instructed button (in this case, T). Keep going with the letter task after this.<br><br>"
					+ "The most important thing is to continue the letter task as quickly and as accurately "
					+ "as possible. It is more important that you pay attention to the letters than the clock. "
					+ "But you should also try to do the timer task at the same time, if you can.<br><br>"
					+ "If you press the special key within 2 seconds of the instructed time (for example "
					+ "0:08 to 0:12) this will be counted as correct.";
			break;	
		case 30:
			i = "Each time you are asked to press "
					+ "a special key, you can only do this <b>once</b>. For example, if you are told to press the F key at "
					+ "0:10 and you already press it at 0:05, it will not count if you press it again. So you should only "
					+ "press the special key when it is time to do so.<br><br> In this experiment, sometimes you will be "
					+ "asked to press the special key after 5 seconds and sometimes you will be asked to press it "
					+ "after 25 seconds.<br><br>When you remember to press the special key, you will "
					+ "earn an extra bonus payment that will be sent to you after the experiment via Prolific.<br><br>"
					+ "<br><br>Please click below to practise again";
			break;
		case 31:
			i = "Now that you have had some practice, we would like you to tell us how accurately you "
					+ "think you can perform the timer task. We will ask you to make seperate predictions for "
					+ "how well you can perform the task when you have to wait A) 5 seconds and "
					+ "B) 25 seconds until it is time to press the special key.<br><br> "
					+ "First of all, please use the scale below to indicate what percentage "
					+ "of times you will remember to correctly press the special key when there is a <b>5 second wait</b>."
					+ "<br><br>100% would mean that you will always get every single one correct. 0% would mean "
					+ "that you can never get any of them correct.";
			break;
		case 32:
			i = "Now, please use the scale below to indicate what percentage "
					+ "of times you will remember to correctly press the special key when there is a <b>25 second wait</b>."
					+ "<br><br>100% would mean that you will always get every single one correct. 0% would mean "
					+ "that you can never get any of them correct.";
			break;
		case 40:
			i = "From now on, you can decide whether to take the timer task when it is presented. "
					+ "As before, you will earn extra points and money if you remember to press the special key. "
					+ "For example, you may be told <br><br> \"You can earn " + SequenceHandler.reward + " points at 0:30 <br><br> For Instructions "
					+ "press Enter <br><br> (Cost: 3 points) <br><br> Or Space to continue\" <br><br>"
					+ "To take the timer task, you should press Enter to reveal the instructed time and special key. "
					+ "This will cost you some points but you will earn "
					+ SequenceHandler.reward + " points if you remember to press the special key. "
					+ "In the above example, if you choose to reveal further instructions "
					+ "<b>and</b> you remember to press the special key at 0:30, "
					+ "you will earn 7 bonus points for completing the timer task. "
					+ "<br><br>You will receive £1 for every 100 points you score, which means that you can earn an additional "
					+ "bonus payment of over " + SequenceHandler.maxBonus + ", on top of the base payment from Prolific."
					+ "<br><br>Alternatively, you can opt out of the timer task. This will avoid the cost but you will not "
					+ "score any bonus points when you do this. To opt out of the timer task, just press the spacebar to continue with the "
					+ "letter task. <br><br> "
					+ "It is completely up to you whether you choose to take the timer task. If you like, you can take the timer task some "
					+ "times but not others. When you get this choice, just pick whichever option you prefer."
					+ "<br><br>Please click below to practise again";
			break;
		case 41:
			i = "Each time the opportunity to take the timer task arises, there may be a different cost to revealing the instructed time and special key. This means that "
					+ "sometimes in this experiment you may earn more points when you remember to press the special key, and other times you may "
					+ "earn fewer points. You will always be told the cost of revealing the instructed time and special key "
					+ "before deciding whether to do the timer task. Please pay attention to the instructions before you make your decision."
					+ "<br><br>If you choose to do the timer task, don't forget to do the letter task as well. "
					+ "You need to score at least 70% accuracy in the letter task, otherwise you will not receive any bonus "
					+ "payments from the timer task.<br><br>The experiment will now begin. Please click below to proceed";
			break;
		case 50:			
			i = "In this block, you can earn " 
					+ SequenceHandler.reward 
					+ " points for successfully completing the optional timer task.";
			break;
		case 120:
			i = "You have now completed the experiment. Thank you for taking part.<br><br>"
					+ "Please click on the link below to receive your payment:"
					+ "<b><a href=\"https://app.prolific.co/submissions/complete?cc=C1I2CPC5\">"
					+ "CLICK HERE</a></b>";
			break;
		}

		return(i);	
	}
	
	public static String InfoText() {
		String expDur = "40";
		String eligibility = "All registered users of Prolific ";
		String nVolunteers = "50";
		
		return ("We would like to invite you to participate in this research project. "
				+ "You should only participate if you want to; choosing not to take part "
				+ "will not disadvantage you in any way. Before you decide whether you "
				+ "want to take part, please read the following information carefully and "
				+ "discuss it with others if you wish. Ask us if there is anything that "
				+ "is not clear or you would like more information.<br><br>"
				+ "We are recruiting approximately " + nVolunteers + " volunteers to "
				+ "take part in an experiment aiming to improve our understanding of human "
				+ "attention and memory. " + eligibility + "are eligible "
				+ "to take part. "
				+ "The experiment will last approximately " + expDur + " minutes. During this experiment you "
				+ "will see various shapes on the screen and you will use your mouse or touchscreen "
				+ "to drag them from one location to another. Full instructions will be provided. "
				+ "There are no anticipated risks or "
				+ "benefits associated with participation in this study.<br><br>"
				+ "It is up to you to decide whether or not to take part. If you choose "
				+ "not to participate, you won't incur any penalties or lose any "
				+ "benefits to which you might have been entitled. If you do "
				+ "decide to take part, you can print out this information sheet and "
				+ "you will be asked to fill out a consent form on the next page. " + "Even after agreeing to take "
				+ "part, you can still withdraw at any time and without giving a reason. This will not affect "
				+ "any benefits that you are entitled to. "
				+ "<br><br>All the information that we collect about you during the course of the research will be "
				+ "kept strictly confidential. The personal information that we will collect is your Prolific ID code. This "
				+ "allows us to link you to your data so that we can process any applicable bonus payments. You will not "
				+ "be able to be identified in any ensuing reports "
				+ "or publications. The results of this research may be published in scientific journals. You can "
				+ "contact the investigator to receive a copy of any published results.<br><br>"
				+ "The data controller for this project will be University College London (UCL). The "
				+ "UCL Data Protection Office provices oversight of UCL activities involving the processing "
				+ "of personal data, and can be contacted at <a href=\"mailto: data-protection@ucl.ac.uk\">"
				+ "data-protection@ucl.ac.uk</a>. UCL's Data Protection Officer can also be contacted at "
				+ "<a href=\"data-protection@ucl.ac.uk\">data-protection@ucl.ac.uk</a>.<br><br>"
				+ "Your personal data will be processed for the purposes outlined in this notice. The "
				+ "legal basis that would be used to process your <i>personal data</i> will be "
				+ "performance of a task in the pulic interest. Your personal data will be processed "
				+ "so long as it is required for the research project. If we are able to anonumise or pseudonymise "
				+ "the personal data you provide we will undertake this, and will endeavour to minimise "
				+ "the processing of personal data wherever possible. Data from this project may "
				+ "also be uploaded to a public repository such as <a href=\"https://osf.io\">https://osf.io</a>. "
				+ "Any data uploaded to a public repository will be anonymised so that it will not "
				+ "be possible to identify you from this data. No personal data will be uploaded publically. "
				+ "If you have any concerns or complaints about this research you should "
				+ "<a href=\"mailto: sam.gilbert@ucl.ac.uk\">contact</a> the principal researcher. "
				+ "If you feel that your complaint has not been handled to your satisfaction you can "
				+ "contact the Chair of the UCL Research Ethics Committee - "
				+ "<a href=\"mailto: ethics@ucl.ac.uk\">ethics@ucl.ac.uk</a>.");
    }

}
