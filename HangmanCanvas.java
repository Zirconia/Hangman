/*
 * File: HangmanCanvas.java
 * ------------------------
 * This file keeps track of the Hangman display.
 * 
 */

import acm.graphics.*;

public class HangmanCanvas extends GCanvas {
    
    /* Constants for the simple version of the picture (in pixels) */
    private static final int SCAFFOLD_HEIGHT = 360;
    private static final int BEAM_LENGTH = 144;
    private static final int ROPE_LENGTH = 18;
    private static final int HEAD_RADIUS = 36;
    private static final int BODY_LENGTH = 144;
    private static final int ARM_OFFSET_FROM_HEAD = 28;
    private static final int UPPER_ARM_LENGTH = 72;
    private static final int LOWER_ARM_LENGTH = 44;
    private static final int HIP_WIDTH = 36;
    private static final int LEG_LENGTH = 108;
    private static final int FOOT_LENGTH = 28;
    
    //strings representing the current word's state and number of incorrect guesses
    public GLabel w = new GLabel("",25,435);
    public GLabel incorrectList = new GLabel("",25,460);
    
    /*Resets the display so that only the scaffold appears */
    public void reset() {
        drawScaffold();
    }
    
    /*
     * Updates the word on the screen to correspond to the current
     * state of the game. The argument string shows what letters have
     * been guessed so far; unguessed letters are indicated by hyphens.
     *     
     */
    public void displayWord(String word) {
        w.setLabel(word);
        w.setFont("SansSerif-bold-24");
        add(w);       
    }
    
    /*
     * Updates the display to correspond to an incorrect guess by the
     * user. Calling this method causes the next body part to appear
     * on the scaffold and adds the letter to the list of incorrect
     * guesses that appears at the bottom of the window.
     *      
     */
    public void noteIncorrectGuess(String word) {
        incorrectList.setLabel(word);
        incorrectList.setFont("SansSerif-14");
        add(incorrectList);  
    
        switch(word.length()){
            case 1:
                drawHead();
                break;
            case 2:
                drawBody();
                break;
            case 3:
                drawLeftArm();
                break;
            case 4:
                drawRightArm();
                break;
            case 5:
                drawLeftLeg();
                break;
            case 6:
                drawRightLeg();
                break;
            case 7:
                drawLeftFoot();
                break;
            case 8:
                drawRightFoot();
                break;
        }
    }
    
    public void drawHead(){
        double size = HEAD_RADIUS * 2;
        double x = (getWidth() - size)/2;
        double y = (getHeight()-size)/6;
        GArc head = new GArc(x,y,size,size,45,360);
        add(head);    
    }
    
    public void drawBody(){
        double bodyY = (getHeight() - HEAD_RADIUS * 2)/6 + HEAD_RADIUS * 2;
        double bodyY2 = getHeight()/6 + BODY_LENGTH;
        double hipX  = getWidth()/2 - HIP_WIDTH/2 + HIP_WIDTH;
        GLine body = new GLine(getWidth()/2, bodyY, getWidth()/2, bodyY2);
        GLine hips = new GLine(getWidth()/2 - HIP_WIDTH/2, getHeight()/6 + BODY_LENGTH, hipX, bodyY2 );
        add(body);
        add(hips);
    }
    
    public void drawRightArm(){
        double upperArmY = (getHeight() - HEAD_RADIUS * 2)/6 + HEAD_RADIUS * 2 + ARM_OFFSET_FROM_HEAD;
        double armX = getWidth()/2 + UPPER_ARM_LENGTH;
        GLine upperRightArm = new GLine(getWidth()/2, upperArmY , armX, upperArmY);
        GLine lowerRightArm = new GLine(armX, upperArmY, armX, upperArmY + LOWER_ARM_LENGTH);
        add(upperRightArm);
        add(lowerRightArm);
    }
    
    
    public void drawLeftArm(){
        double upperArmY = (getHeight() - HEAD_RADIUS * 2)/6 + HEAD_RADIUS * 2 + ARM_OFFSET_FROM_HEAD;
        double armX = getWidth()/2 - UPPER_ARM_LENGTH;
        GLine upperLeftArm = new GLine(armX, upperArmY , getWidth()/2, upperArmY);
        GLine lowerLeftArm = new GLine(armX, upperArmY, armX, upperArmY + LOWER_ARM_LENGTH);
        add(upperLeftArm);
        add(lowerLeftArm);
    }
    
    public void drawLeftLeg(){
        double legY = getHeight()/6 + BODY_LENGTH + LEG_LENGTH;
        double legX = getWidth()/2 - HIP_WIDTH/2;
        GLine leftLeg =  new GLine(legX, getHeight()/6 + BODY_LENGTH, legX, legY);
        add(leftLeg);    
    }
    
    
    public void drawRightLeg(){
        double legX = getWidth()/2 - HIP_WIDTH/2 + HIP_WIDTH;
        double legY = getHeight()/6 + BODY_LENGTH + LEG_LENGTH;
        GLine rightLeg =  new GLine(legX, getHeight()/6 + BODY_LENGTH, legX, legY);
        add(rightLeg);
    }
    
    public void drawLeftFoot(){
        double footX = getWidth()/2 - HIP_WIDTH/2 - FOOT_LENGTH;
        double footY = getHeight()/6 + BODY_LENGTH + LEG_LENGTH;
        GLine leftFoot = new GLine(footX, footY, getWidth()/2 - HIP_WIDTH/2, footY);
        add(leftFoot);
    }
    
    public void drawRightFoot(){
        double footY =  getHeight()/6 + BODY_LENGTH + LEG_LENGTH;
        double footX =  getWidth()/2 - HIP_WIDTH/2 + HIP_WIDTH + FOOT_LENGTH;
        GLine rightFoot = new GLine(getWidth()/2 - HIP_WIDTH/2 + HIP_WIDTH, footY, footX, footY);
        add(rightFoot);
    }
    
    private void drawScaffold(){
        double ropeY = (getHeight() - HEAD_RADIUS * 2)/6 - ROPE_LENGTH;
        double beamX = getWidth()/2 - BEAM_LENGTH;
        GLine rope = new GLine(getWidth()/2, (getHeight() - HEAD_RADIUS * 2)/6 ,getWidth()/2, ropeY);
        add(rope);
        GLine beam = new GLine(beamX, ropeY, getWidth()/2, ropeY);
        add(beam);
        GLine scaff = new GLine(beamX, ropeY, beamX, ropeY + SCAFFOLD_HEIGHT);
        add(scaff);
    }
}
    
        
